package com.springbook.biz.board;

import java.util.List;

public interface BoardService {
	void insert(BoardVO vo);
	void update(BoardVO vo);
	void delete(int k);
	BoardVO getBoard(int k);
	List<BoardVO> getBoardList();
	List<BoardVO> getBoardList(String ch1, String ch2);
}