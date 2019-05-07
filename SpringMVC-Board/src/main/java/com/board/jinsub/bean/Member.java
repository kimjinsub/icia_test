package com.board.jinsub.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("member")
@Setter @Getter
public class Member {
	private String mid;
	private String mpwd;
	private String mname;
	private String mbirth;
	private String maddr;
	private String mphone;
	private int mpoint;
	private String gname;//등급이름
	
}
