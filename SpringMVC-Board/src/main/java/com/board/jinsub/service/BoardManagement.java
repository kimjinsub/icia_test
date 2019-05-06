package com.board.jinsub.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DataBindingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.Board;
import com.board.jinsub.bean.Reply;
import com.board.jinsub.dao.IBoardDao;
import com.board.jinsub.userClass.DBException;
import com.board.jinsub.userClass.Paging;
import com.board.jinsub.userClass.UploadFile;
import com.board.jinsub.userClass.UploadTest;
import com.google.gson.Gson;

@Service
public class BoardManagement {//final 붙으면 상속불가. 최종클래스임
	@Autowired
	private IBoardDao bDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private UploadFile upload;
	
	ModelAndView mav;
	
	public ModelAndView getBoardList(Integer pageNum) {
		mav=new ModelAndView();
		String view=null;
		List<Board> bList=null;
		int num=(pageNum==null)?1:pageNum;
		bList=bDao.getBoardList(num);
		mav.addObject("bList",bList);
		mav.addObject("paging",getPaging(num));
		Gson gson = new Gson();
		String jsonObj=gson.toJson(bList);
		mav.addObject("bList_json",jsonObj);
		view="boardList";
		mav.setViewName(view);
		return mav;
	}

	private String getPaging(int pageNum) {//현재 페이지번호
		int maxNum=bDao.getBoardCount();//전체 글의 개수
		System.out.println("maxNum="+maxNum);
		int listCount=5;//페이지당 글의 수
		int pageCount=2;//페이지그룹당 페이지 수
		String boardName="boardList";//게시판이 여러개일때
		Paging paging=new Paging(maxNum, pageNum, listCount, pageCount, boardName);
		return paging.makeHtmlPaging();
	}

	public ModelAndView getContents(Integer bnum) {
		mav=new ModelAndView();
		String view=null;
		Board board=bDao.getContents(bnum);
		mav.addObject("board",board);
		List<Reply> rList=bDao.getReplyList(bnum);
		mav.addObject("rList",rList);
		view="boardContentsAjax";
		mav.setViewName(view);
		return mav;
	}

	public String replyInsert(Reply r) {
		r.setR_mid(session.getAttribute("id").toString());
		String json=null;
		if(bDao.replyInsert(r)) {
			List<Reply> rList=bDao.getReplyList(r.getR_bnum());
			json=new Gson().toJson(rList);
			System.out.println("json="+json);
		}else {
			json=null;
		}
		return json;
	}

	public Map<String, List<Reply>> replyInsertJackSon(Reply r) {
		Map<String, List<Reply>> rMap=null;
		r.setR_mid(session.getAttribute("id").toString());
		if(bDao.replyInsert(r)) {
			List<Reply> rList=bDao.getReplyList(r.getR_bnum());
			rMap=new HashMap<String, List<Reply>>();
			rMap.put("rList", rList);
			System.out.println(rMap);
		}else {
			rMap=null;
		}
		return rMap;
	}

	@Transactional
	public ModelAndView boardDelete(Integer bnum) throws DBException{
		mav=new ModelAndView();
		boolean r=bDao.replyDelete(bnum);
		boolean a=bDao.articleDelete(bnum);//bnum //1000번글 삭제시 댓글 롤백확인
		if(a==false) {
			throw new DBException();
			//try catch안하면 리턴을 요청자(이 경우는 현재service니까 controller로)에게 보내주니
			//계속 위로위로 throws해줘야한다. 아니면 위에서 try catch하던지...
		}
		if(r && a) {
			System.out.println("삭제 트랜잭션 성공");
		}else {
			System.out.println("삭제 트랜잭션 실패");
		}
		mav.setViewName("redirect:boardList");
		//mav.setViewName("redirect:/boardList");
		return mav;
	}
	
	@Transactional
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) 
			throws DBException{
		mav=new ModelAndView();
		//1개의 file tag를 이용해서 여러파일을 선택했을 때
		String view=null;
		String title=multi.getParameter("b_title");
		String contents=multi.getParameter("b_contents");
		int check=Integer.valueOf(multi.getParameter("fileCheck"));
		String id=session.getAttribute("id").toString();
		Board board=new Board();
		board.setB_title(title);
		board.setB_contents(contents);
		board.setB_mid(id);
		boolean b=bDao.boardInsert(board);
		System.out.println("b="+b);
		board.setB_num(bDao.getBoardNum(id));//DB에서 글번호 가져옴
		boolean f=false;
		if(b) {
			if(check==1) {
				upload=new UploadFile();
				System.out.println("board_b_num"+board.getB_num());
				//f=upload.fileUp(multi, board.getB_num());
				UploadTest upload2 = new UploadTest();
				//f=upload2.fileupTest(multi, board.getB_num());
				//서버에 파일을 업로드한 후 -> 오리지널파일명,시스템파일명을 리턴 후에 맵에 저장
				String str=upload2.fileupTest(multi, board.getB_num());
				String strArr[]=str.split(",");
				String oriname=strArr[0];
				String sysname=strArr[1];
				System.out.println("hihi="+oriname+","+sysname);
				f=bDao.fileInsert(oriname, sysname, board.getB_num());
				if(f) {
					view="redirect:boardList";
				}else {
					throw new DBException();
				}
			}else if(check==0) {//글쓰기 성공
				view="redirect:boardList";
			}else {
				throw new DBException();
			}
		}else {
			view="writeFrm";
		}
		mav.setViewName(view);
		return mav;
	}
}
