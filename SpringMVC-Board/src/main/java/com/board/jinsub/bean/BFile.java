package com.board.jinsub.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("bfile")
@Getter @Setter
public class BFile {
	//글번호 필요하면 추가할것
	private String bf_oriname;
	private String bf_sysname;
}
