package com.springbook.biz.board;

import java.util.List;

public interface BoardDao {
	BoardVO getBoard(int k);
	List<BoardVO> getBoardList();
	List<BoardVO> getBoardList(String ch1, String ch2);
}