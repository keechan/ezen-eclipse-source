package com.springbook.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.conn.DBConnection;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	//DBConnection dbConnection = null;
	@Autowired
	private DBConnection dbConnection;
	
	Connection conn = null;
	String sql = "";
	PreparedStatement pstmt = null;
	ResultSet rs = null;

//	public BoardDaoImpl() {
//		dbConnection = DBConnection.getInstance();
//	}

	@Override
	public BoardVO getBoard(int k) {
		BoardVO vo = null;
		try {
			conn = dbConnection.getConnection();
			sql = "select seq, title, writer, content, regdate, cnt from board where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, k);
			rs = pstmt.executeQuery();
			vo = new BoardVO();
			if (rs.next()) {
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				//System.out.println("========[BoardDAO]=========");
				//System.out.println(vo.toString());
				//System.out.println("===========================");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> li = new ArrayList<BoardVO>();
		try {
			conn = dbConnection.getConnection();
			sql = "select seq, title, writer, content, regdate, cnt from board order by seq desc";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, k);
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
//			System.out.println("========[BoardDAO]=========");
//			System.out.println(vo.toString());
//			System.out.println("===========================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return li;
	}

	@Override
	public List<BoardVO> getBoardList(String ch1, String ch2) {
		System.out.println("ch1 : " + ch1 + ", ch2 : " + ch2);
		List<BoardVO> li = new ArrayList<BoardVO>();
		try {
			conn = dbConnection.getConnection();

			if (ch1 == null || ch2 == null || ch2 == "") {
				sql = "select * from board";
				pstmt = conn.prepareStatement(sql);
			} else if (ch1.equals("title")) {
				sql = "select * from board where title like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ch2+"%");
			} else if (ch1.equals("writer")) {
				sql = "select * from board where writer like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ch2+"%");
			}			
			
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
			//System.out.println("========[BoardDAO]=========");
			//System.out.println(vo.toString());
			//System.out.println("===========================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return li;
	}

}
