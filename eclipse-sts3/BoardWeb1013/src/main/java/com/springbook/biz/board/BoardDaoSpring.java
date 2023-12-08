package com.springbook.biz.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoSpring implements BoardDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public void insert(BoardVO vo) {
		System.out.println("======> BoardDaoSpring(insert)");
		String sql = "insert into board (seq, title, writer, content) values (?, ?, ?, ?)";
		jdbc.update(sql, vo.getSeq(), vo.getTitle(), vo.getWriter(),  vo.getContent());
	}

	@Override
	public List<BoardVO> getBoardList() {
		System.out.println("======> BoardDaoSpring(List)");
		String sql = "select * from board order by seq desc";
		return jdbc.query(sql, new BoardRowMapper());
	}

	@Override
	public void delete(BoardVO vo) {
		System.out.println("======> BoardDaoSpring(delete)");
		String sql = "delete from board where seq = ?";
		jdbc.update(sql, vo.getSeq());
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		String sql = "select * from board where seq = ?";
		Object[] args = {vo.getSeq()};
		//queryForObject는 한개만 리턴 되야 함
		return jdbc.queryForObject(sql, new BoardRowMapper(), args);
	}
}

class BoardRowMapper implements RowMapper<BoardVO> {
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO m = new BoardVO();
		m = new BoardVO();
		m.setSeq(rs.getInt("seq"));
		m.setTitle(rs.getString("title"));
		m.setWriter(rs.getString("writer"));
		m.setContent(rs.getString("content"));
		m.setRegdate(rs.getDate("regdate"));	
		m.setCnt(rs.getInt("cnt"));
		
		return m;
	}
	
}
