package com.board.jinsub.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.Member;
import com.board.jinsub.dao.IBoardDao;
import com.board.jinsub.dao.IMemberDao;

@Service//@Component
public class MemberManagement {
	@Autowired
	private IMemberDao mDao;
	@Autowired
	private HttpSession session;//request는 권장하지 않음. session은 괜찮음.
	
	private ModelAndView mav;

	public ModelAndView memberAccess(Member mb) {
		mav=new ModelAndView();
		String view=null;
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		//해당 아이디의 암호화된 비번을 가져옴
		String pwdEncode=mDao.getSecurityPwd(mb.getMid());
		if(pwdEncode!=null) {
			if(pwdEncoder.matches(mb.getMpwd(), pwdEncode)) {
				session.setAttribute("id", mb.getMid());
				//로그인 후 회원정보를 화면에 출력하기위해
				mb=mDao.getMemberInfo(mb.getMid());
				session.setAttribute("mb", mb);
				//mav.addObject("mb",mb);
				//view="forward:/boardList";//'forward:/' 가 생략되어있다(default),,디스패쳐방식임
				view="redirect:/boardList";//redirect방식임,, 세션에 mb를 저장했으니 redirect해도됨
				//forward:url,POST-POST,GET-GET끼리만 가능
				//redirect:url,POST-GET --> GET방식만 가능
			}else {//비번 오류
				view="home";
				mav.addObject("check",2);
			}
		}else {//아이디 오류
			view="home";
			mav.addObject("check",2);
		}
		mav.setViewName(view);
		return mav;
	}

	public ModelAndView memberInsert(Member mb) {
		mav=new ModelAndView();
		String view=null;
		//비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		//비번을 암호화한 코드로 변환해서 저장
		mb.setMpwd(pwdEncoder.encode(mb.getMpwd()));
		if(mDao.memberInsert(mb)) {
			view="home";
			mav.addObject("check",1);//회원가입 성공
		}else{
			view="joinFrm";
		}
		mav.setViewName(view);
		return mav;
	}

	public ModelAndView mybatisTest(String cName, Integer search) {
		List<Member> mList= mDao.mybatisTest(cName,search);
		mav.addObject("mList",mList);
		mav.setViewName("test");
		return mav;
	}
}
