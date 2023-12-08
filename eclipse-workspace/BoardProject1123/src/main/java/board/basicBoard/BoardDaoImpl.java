package board.basicBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import board.DBConnection;
import oracle.jdbc.OracleTypes;

public class BoardDaoImpl implements BoardDao {
    
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	
	@Override
	public void insert(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
	
			
			String sql ="{ call ps_board1122_insert(?, ?, ?, ?) } ";
			cstmt=conn.prepareCall(sql);
			cstmt.setString(1, vo.getSname());
			cstmt.setString(2, vo.getTitle());
			cstmt.setString(3, vo.getContent());	
			cstmt.setString(4, vo.getImg());	
			cstmt.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(BoardVO vo) {
		System.out.println("Update vo:" + vo.getImg());
		try {
			conn =DBConnection.getConnection();
				
			if (vo.getImg() == null ) {				
				System.out.println("==> 이미지 미 첨부상태 ");
				String sql ="{ call ps_board1122_update(?, ?, ?, ?) } ";
				cstmt=conn.prepareCall(sql);
				cstmt.setString(1, vo.getSname());
				cstmt.setString(2, vo.getTitle());
				cstmt.setString(3, vo.getContent());	
				cstmt.setString(4, vo.getIdx());
				cstmt.execute();				
	
			}else {				
				if(!vo.getImg().equals("space.png")) {
					System.out.println("==> 이미지 첨부상태 ");
					String sql ="{ call ps_board1122_update_img(?, ?, ?, ?, ?) } ";
					cstmt=conn.prepareCall(sql);
					cstmt.setString(1, vo.getSname());
					cstmt.setString(2, vo.getTitle());
					cstmt.setString(3, vo.getContent());	
					cstmt.setString(4, vo.getImg());
					cstmt.setString(5, vo.getIdx());
					cstmt.execute();
				} else {
					System.out.println("==> 이미지 미 첨부상태 ");
					String sql ="{ call ps_board1122_update(?, ?, ?, ?) } ";
					cstmt=conn.prepareCall(sql);
					cstmt.setString(1, vo.getSname());
					cstmt.setString(2, vo.getTitle());
					cstmt.setString(3, vo.getContent());	
					cstmt.setString(4, vo.getIdx());
					cstmt.execute();	
				}
				
			}						
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
				
			String sql ="{ call ps_board1122_delete(?) } ";
			cstmt=conn.prepareCall(sql);
			cstmt.setString(1, vo.getIdx());
			cstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public BoardVO edit(BoardVO vo) {
		BoardVO m = null;
		try {
			conn =DBConnection.getConnection();
			
			String sql ="{ call ps_board1122_edit(?, ?) } ";
			cstmt=conn.prepareCall(sql);
			cstmt.setString(1, vo.getIdx());
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
			rs=(ResultSet) cstmt.getObject(2);  // 2번 값 얻기 
			
			
			if(rs.next()) {
				m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				m.setCnt(rs.getInt("cnt"));
				m.setImg(rs.getString("img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return m;
	}

	@Override
	public List<BoardVO> list(BoardVO vo) {
		List<BoardVO> li  = null;
		try {
			conn =DBConnection.getConnection();
	
			String sql ="{ call ps_board1122_select(?) } ";
			cstmt=conn.prepareCall(sql);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			rs=(ResultSet) cstmt.getObject(1);
			
			li = new ArrayList<>();
			BoardVO m = null;
		    while(rs.next()) {
				m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				m.setCnt(rs.getInt("cnt"));
				m.setImg(rs.getString("img"));
				li.add(m);
				// System.out.println("===> call ps_board1122_select : " + m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return li;
	}

	@Override
	public void cntCount(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
			/*
			String update_sql =" update  board1122 "
					+ " set cnt = cnt + 1  "
					+ " where idx=? ";
			pstmt=conn.prepareStatement(update_sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.executeUpdate();
			*/
			
			String sql ="{ call ps_board1122_cntCount(?) } ";
			cstmt=conn.prepareCall(sql);
			cstmt.setString(1, vo.getIdx());
			cstmt.execute();	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
