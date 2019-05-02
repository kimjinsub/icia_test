package com.board.jinsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
