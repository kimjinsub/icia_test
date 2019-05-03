package com.board.jinsub.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DataBindingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.Board;
import com.board.jinsub.bean.Reply;
import com.board.jinsub.dao.IBoardDao;
import com.board.jinsub.userClass.DBException;
import com.board.jinsub.userClass.Paging;
import com.google.gson.Gson;

@Service
public class BoardManagement {//final 붙으면 상속불가. 최종클래스임
	@Autowired
	private IBoardDao bDao;
	@Autowired
	private HttpSession session;
	
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
}
