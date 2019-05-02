package com.board.jinsub;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.Member;
import com.board.jinsub.service.MemberManagement;

//모델객체 mb를 생성하면 request영역에 session이 종료되기까지 저장한다.
@Controller
//@SessionAttributes("mb")//불안정해서 공부하고 쓰겠음
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private MemberManagement mm;
	@Autowired
	HttpSession session;
	
	ModelAndView mav;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		mav=new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) {
	//public ModelAndView access(@ModelAttribute("mb") Member mb) {
		//bean으로 자동으로 받고싶지 않을 땐, https://heavenly-appear.tistory.com/302
		//mav=new ModelAndView();
		//mav.addObject("mb",mb);
		mav=mm.memberAccess(mb);
		return mav;
	}
	
	@RequestMapping(value = "/joinFrm", method = RequestMethod.GET)
	public ModelAndView joinFrm() {
		mav=new ModelAndView();
		mav.setViewName("joinFrm");
		return mav;
	}
	
	@RequestMapping(value = "/memberInsert", method = RequestMethod.POST)
	public ModelAndView memberInsert(Member mb) {
		mav=mm.memberInsert(mb);
		return mav;
	}
	
	//GET으로 하면 주소창에 logout치면 로그아웃이 된다
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView mybatisTest(String cName,Integer search) {//int로도 해보라-->null을 못받는다
		System.out.println("cName="+cName);
		System.out.println("search="+search);
		mav=mm.mybatisTest(cName,search);
		return mav;
	}
	
}
