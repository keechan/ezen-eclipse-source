package com.springbook.biz.board;

import java.util.List;

public interface BoardDao {
	void insert(BoardVO vo);
	List<BoardVO> getBoardList();
	void delete(BoardVO vo);
	BoardVO getBoard(BoardVO vo);
}
