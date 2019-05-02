package com.board.jinsub.bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Alias("reply")
@Data
public class Reply {
	private int r_num;
	private int r_bnum;
	private String r_contents;
	private String r_mid;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone= "GMT+9")
	private Timestamp r_date;
	//@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	//private LocalDateTime r_date;
}
