package com.board.jinsub;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.Reply;
import com.board.jinsub.service.BoardManagement;

//@Controller//#1
@RestController
@RequestMapping(value = "/ajax")
public class BoardRestController {//Restful방식
	@Autowired
	private BoardManagement bm;
	
	ModelAndView mav;
	
	/*@RequestMapping(value = "/ajax/replyInsert"
			,produces = "application/json; charset=utf8")//json한글 깨짐 방지
	public @ResponseBody String replayInsert(@RequestBody Reply r) {
		//json이 String이기때문에 포워딩을 실행 아니하고, 요청페이지에 응답하려면 @ResponseBody를 사용해야함
		//json을커맨드 객체에 저장하기 위해서는 @RequestBody를 사용해야함
		String json=bm.replyInsert(r);
		return json;
	}*///#1
	@RequestMapping(value = "/replyInsert",produces = "application/json; charset=utf-8")
	public Map<String, List<Reply>> replayInsert(@RequestBody Reply r) {
		//json이 String이기때문에 포워딩을 실행 아니하고, 요청페이지에 응답하려면 @ResponseBody를 사용해야함
		//json을커맨드 객체에 저장하기 위해서는 @RequestBody를 사용해야함
		Map<String, List<Reply>> rMap=bm.replyInsertJackSon(r);
		return rMap;
		//jackson:Map을 --->json으로 변환
		//{'rList',rList} ---> {"rList":[],[],[]}
		//외부라이브러리 gson을 안거치기 때문에 빠름(퍼포먼스 굳)
	}
	
}
