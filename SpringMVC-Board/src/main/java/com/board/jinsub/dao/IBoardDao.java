package com.board.jinsub.dao;

import java.util.List;
import java.util.Map;

import com.board.jinsub.bean.BFile;
import com.board.jinsub.bean.Board;
import com.board.jinsub.bean.Reply;

public interface IBoardDao {

	List<Board> getBoardList(int num);

	Board getContents(Integer bnum);

	List<Reply> getReplyList(Integer bnum);

	boolean replyInsert(Reply r);

	boolean replyDelete(Integer bnum);

	boolean articleDelete(Integer bnum);

	int getBoardCount();

	boolean boardInsert(Board board);

	int getBoardNum(String id);

	//boolean fileInsert(BFile bf, int bnum);

	boolean fileInsert(String oriFileName, String sysFileName, int bnum);

	//boolean fileInsert(Map<String, String> fMap);
	
}
