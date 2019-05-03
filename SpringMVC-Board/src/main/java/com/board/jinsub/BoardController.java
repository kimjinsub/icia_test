package com.board.jinsub;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.Board;
import com.board.jinsub.service.BoardManagement;
import com.board.jinsub.userClass.DBException;

@Controller
public class BoardController {
	@Autowired
	private BoardManagement bm;  //게시판 서비스 클래스(Model), 비지니스 로직
	
	ModelAndView mav;
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET) //method 생략시 get,post 모두 가능
	public ModelAndView boardList(Integer pageNum) {//래퍼클래스를 쓰면 null도 허용된다,,int는 null이 안됨
		mav=bm.getBoardList(pageNum);
		return mav;
	}

	@RequestMapping(value = "/contents", method = RequestMethod.GET)
	public ModelAndView getContents(Integer bnum) {
		mav=bm.getContents(bnum);
		return mav;
	}

	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(Integer bnum) throws DBException{
		mav=bm.boardDelete(bnum);
		return mav;
	}
	
	@RequestMapping(value = "/writeFrm", method = RequestMethod.GET)
	public ModelAndView writeFrm() {
		mav=new ModelAndView();
		mav.setViewName("writeFrm");
		return mav;
	}
	
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) {
		mav=bm.boardWrite(multi);
		return mav;
	}
	
	/*@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(Board board, List<MultipartFile> b_files) {
		System.out.println("title="+board.getB_title());
		System.out.println("file1="+b_files.get(0).getOriginalFilename());
		System.out.println("file2="+b_files.get(1).getOriginalFilename());
		return mav;
	}*/
}
