package com.springbook.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Override
	public void insert(BoardVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<BoardVO> selectAll() {
		return dao.getBoardList();
	}
	
	@Override
	public void delete(BoardVO vo) {
		dao.delete(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {

		return null;
	}	
}
