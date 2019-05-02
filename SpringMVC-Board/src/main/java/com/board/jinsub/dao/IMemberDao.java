package com.board.jinsub.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.board.jinsub.bean.Member;
//@Repository//@Component
public interface IMemberDao {

	//public Member getMemberInfo(Member mb);
	//public Member getMemberInfo(Map<String, String> hm);

	public boolean memberInsert(Member mb);

	public String getSecurityPwd(String m_id);

	public Member getMemberInfo(String m_id);

	public List<Member> mybatisTest(@Param("cName")String cName, @Param("point")Integer search);
	
}
