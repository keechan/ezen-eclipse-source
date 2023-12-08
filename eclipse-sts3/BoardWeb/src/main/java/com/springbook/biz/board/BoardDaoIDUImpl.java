package com.springbook.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.conn.DBConnection;

@Repository
public class BoardDaoIDUImpl implements BoardDaoIDU {
	//DBConnection dbConnection = null;
	@Autowired
	private DBConnection dbConnection;
	Connection conn = null;
	String sql = "";
	PreparedStatement pstmt = null;

	@Override
	public void insert(BoardVO vo) {
		try {
			conn = dbConnection.getConnection();
			sql = "insert into board (seq, title, writer, content) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(BoardVO vo) {
		try {
			conn = dbConnection.getConnection();
			sql = "update board set title = ?, writer = ?, content = ? where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getSeq());			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(int k) {
		try {
			conn = dbConnection.getConnection();
			sql = "delete from board where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, k);			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
