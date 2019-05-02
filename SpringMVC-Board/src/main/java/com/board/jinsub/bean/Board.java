package com.board.jinsub.bean;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("board")
@Data
public class Board {
	private int b_num;
	private String b_title;
	private String b_contents;
	private String b_mid;
	private Timestamp b_date;//시분초까지 나옴
	private int b_views;
	private String m_name;
}
