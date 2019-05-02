package com.board.jinsub.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("member")
@Setter @Getter
public class Member {
	private String m_id;
	private String m_pwd;
	private String m_name;
	private String m_birth;
	private String m_addr;
	private String m_phone;
	private int m_point;
	private String g_name;//등급이름
}
