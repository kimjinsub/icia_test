package com.board.jinsub.userClass;

public class DBException extends RuntimeException {
	
	public DBException(){
		super(""
				+ "스프링은 RuntimeException(선택) 예외만 인정한다."
				+ "필수예외가 발생한 경우는 RuntimeException 예외로 변환한다.");
	}
	
}
