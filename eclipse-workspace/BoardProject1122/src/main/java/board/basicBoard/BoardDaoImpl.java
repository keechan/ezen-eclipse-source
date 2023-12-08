package board.basicBoard;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import board.DBConnection;

public class BoardDaoImpl implements BoardDao {
    
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    CallableStatement cstmt = null;
    
	@Override
	public void insert(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
			
			String sql = "{ call sp_board1122_insert(?, ?, ?, ?) }";
	        cstmt = conn.prepareCall(sql);
	        cstmt.setString(1, vo.getSname());
	        cstmt.setString(2, vo.getTitle());
	        cstmt.setString(3, vo.getContent());
	        cstmt.setString(4, vo.getImg());
	        cstmt.execute();
//	        rs = (ResultSet) cstmt.getObject(1);
	        
//			String insert_sql = " insert  into board1122 "
//					          + " (idx, sname, title, content,cnt) "
//					          + " values(idx_board.nextval, ?, ?,?,1)";
//			pstmt=conn.prepareStatement(insert_sql);
//			pstmt.setString(1, vo.getSname());
//			pstmt.setString(2, vo.getTitle());
//			pstmt.setString(3, vo.getContent());
//			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
		
	}

	@Override
	public void update(BoardVO vo) {
		System.out.println("DaoImpl --------> " + vo.getImg());
		try {			
			conn =DBConnection.getConnection();
			
			if(vo.getImg() != null || vo.getImg() != "space.png") {
				String sql = "{ call sp_board1122_update_img(?, ?, ?, ?, ?) }";
		        cstmt = conn.prepareCall(sql);
				cstmt.setString(1, vo.getSname());
				cstmt.setString(2, vo.getTitle());
				cstmt.setString(3, vo.getContent());
				cstmt.setString(4, vo.getImg());
				cstmt.setString(5, vo.getIdx());
			} else {
				String sql = "{ call sp_board1122_update(?, ?, ?, ?) }";
		        cstmt = conn.prepareCall(sql);
				cstmt.setString(1, vo.getSname());
				cstmt.setString(2, vo.getTitle());
				cstmt.setString(3, vo.getContent());
				cstmt.setString(4, vo.getIdx());
			}
			cstmt.execute();
			
//			String update_sql =" update  board1122 "
//					         + " set sname=?, title=?, content=?  "
//					         + " where idx=? ";
//			pstmt=conn.prepareStatement(update_sql);
//			pstmt.setString(1, vo.getSname());
//			pstmt.setString(2, vo.getTitle());
//			pstmt.setString(3, vo.getContent());
//			pstmt.setString(4, vo.getIdx());
//			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}

	@Override
	public void delete(BoardVO vo) {
		try {
			conn = DBConnection.getConnection();
			
			String sql = "{ call sp_board1122_delete(?) }";
			System.out.println("BasicDaoImpl---->(1) " + sql);
	        cstmt = conn.prepareCall(sql);
	        cstmt.setString(1, vo.getIdx());
	        cstmt.execute();
	        System.out.println("BasicDaoImpl---->(2) " + vo.getIdx());
//			String delete_sql = " delete from  board1122 "
//					          + " where idx=? ";
//			pstmt=conn.prepareStatement(delete_sql);
//			pstmt.setString(1, vo.getIdx());
//			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
		
	}

	@Override
	public BoardVO edit(BoardVO vo) {
		BoardVO m = null;
		try {
			conn =DBConnection.getConnection();
//			String edit_sql =" select * from  board1122 "
//					       + "  where idx=? ";
//			pstmt=conn.prepareStatement(edit_sql);
//			pstmt.setString(1, vo.getIdx());
//			rs = pstmt.executeQuery();
			
			String sql = "{ call sp_board1122_edit(?, ?) }";
	        cstmt = conn.prepareCall(sql);
	        cstmt.setString(1, vo.getIdx());
	        cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
	        cstmt.execute();
	        rs = (ResultSet) cstmt.getObject(2);
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
		} finally {
			DBConnection.close(pstmt, conn);
		}
		return m;
	}

	@Override
	public List<BoardVO> list(BoardVO vo) {
		List<BoardVO> li  = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "{ call sp_board1122_select(?) }";
	        cstmt = conn.prepareCall(sql);
	        cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
	        
	        cstmt.execute();
	        rs = (ResultSet) cstmt.getObject(1);
	        //ResultSetMetaData rmd = rs.getMetaData();
			li = new ArrayList<>();
			BoardVO m = null;	        
	        while (rs.next()) {
				m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				m.setCnt(rs.getInt("cnt"));
				m.setImg(rs.getString("img"));
				li.add(m);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

//	@Override
//	public void cntCount(BoardVO vo) {
//		try {
//			conn =DBConnection.getConnection();
//			String update_sql = " update board1122 "
//					          + "    set cnt = cnt + 1  "
//					          + "  where idx=? ";
//			pstmt=conn.prepareStatement(update_sql);
//			pstmt.setString(1, vo.getIdx());
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBConnection.close(pstmt, conn);
//		}
//	}
}



//@Override
//public List<BoardVO> list(BoardVO vo) {
//	List<BoardVO> li  = null;
//	try {
//		conn =DBConnection.getConnection();
//		String select_sql =" select * from  board1122 order  by  idx  desc ";
//				
//		pstmt=conn.prepareStatement(select_sql);
//	
//		rs = pstmt.executeQuery();
//		li = new ArrayList<>();
//		BoardVO m = null;
//	    while(rs.next()) {
//			m = new BoardVO();
//			m.setIdx(rs.getString("idx"));
//			m.setSname(rs.getString("sname"));
//			m.setTitle(rs.getString("title"));
//			m.setContent(rs.getString("content"));
//			m.setCnt(rs.getInt("cnt"));
//			li.add(m);
//			System.out.println("===> DAO " + m);
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	} finally {
//		DBConnection.close(rs, pstmt, conn);
//	}
//	return li;
//}