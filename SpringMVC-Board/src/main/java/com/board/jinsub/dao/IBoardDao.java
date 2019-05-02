package com.board.jinsub.dao;

import java.util.List;

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
	
}
