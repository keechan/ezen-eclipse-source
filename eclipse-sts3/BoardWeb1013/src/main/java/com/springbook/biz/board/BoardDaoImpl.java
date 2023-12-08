package com.springbook.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class BoardDaoImpl implements BoardDao {

	//@Autowired
	private DBConnection DBConn;
	private Connection conn = null;
	String sql = "";
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public void insert(BoardVO vo) {
		try {
			//System.out.println("*===> BoardDaoImpl(insert)");
			conn = DBConn.getConnection();
			sql = "insert into board (seq, title, writer, content) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close1(pstmt, conn);
		}
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> li = new ArrayList<BoardVO>();
		try {
			conn = DBConn.getConnection();
			sql = "select * from board order by seq desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			BoardVO vo = null;
			while (rs.next()) {
				vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));	
				vo.setCnt(rs.getInt("cnt"));
				li.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return li;
	}
	
	@Override
	public void delete(BoardVO vo) {
		try {
			//System.out.println("*===> BoardDaoImpl(insert)");
			conn = DBConn.getConnection();
			sql = "delete from board where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close1(pstmt, conn);
		}
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
