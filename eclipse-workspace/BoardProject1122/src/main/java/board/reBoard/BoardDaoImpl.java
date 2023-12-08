package board.reBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.DBConnection;

public class BoardDaoImpl implements BoardDao {
    
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public void insert(BoardVO vo) {
		try {
			System.out.println("DaoImpl(1) =====>");
			conn =DBConnection.getConnection();
			String insert_sql = " insert into ReBoard1123 "
					          + " (idx, sname, title, content, cnt, ref, re_step, re_level) values "
					          + " (idx_board.nextval, ?, ?, ?, 1, "
					          + " (select nvl(max(ref), 0) + 1 from reboard1123), 1, 1)";
			pstmt=conn.prepareStatement(insert_sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}

//	@Override
//	public void update(BoardVO vo) {
//		try {
//			conn =DBConnection.getConnection();
//			String update_sql =" update  ReBoard1123 "
//					         + " set sname=?, title=?, content=?  "
//					         + " where idx=? ";
//			pstmt=conn.prepareStatement(update_sql);
//			pstmt.setString(1, vo.getSname());
//			pstmt.setString(2, vo.getTitle());
//			pstmt.setString(3, vo.getContent());
//			pstmt.setString(4, vo.getIdx());
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBConnection.close(pstmt, conn);
//		}
//		
//	}

	@Override
	public void delete(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
			String delete_sql = " delete from  reBoard1123 "
					          + "  where idx = ? ";
			pstmt=conn.prepareStatement(delete_sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.executeUpdate();
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
			String edit_sql =" select * from ReBoard1123 "
					       + "  where idx=? ";
			pstmt=conn.prepareStatement(edit_sql);
			pstmt.setString(1, vo.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				m.setCnt(rs.getInt("cnt"));
				
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));			}
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
			String select_sql =" select * from  reBoard1123 order by ref desc, re_step asc";
					
			pstmt=conn.prepareStatement(select_sql);
		
			rs = pstmt.executeQuery();
			li = new ArrayList<>();
			BoardVO m = null;
		    while(rs.next()) {
				m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				m.setCnt(rs.getInt("cnt"));
				
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));
				
				li.add(m);
				//System.out.println("===> DAO " + m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}
//
//	@Override
//	public void cntCount(BoardVO vo) {
//		try {
//			conn =DBConnection.getConnection();
//			String update_sql = " update ReBoard1123 "
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

	@Override
	public void rwInsert(BoardVO vo) {
		try {
			System.out.println("DaoImpl(RW1) =====>");
			conn =DBConnection.getConnection();
			String insert_sql = " insert into ReBoard1123 "
					          + " (idx, sname, title, content, cnt, ref, re_step, re_level) values "
					          + " (idx_board.nextval, ?, ?, ?, 1, "
					          + " ?, ?, ?)";
			pstmt=conn.prepareStatement(insert_sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			pstmt.setInt(4, vo.getRef());
			pstmt.setInt(5, vo.getRe_step()+1);
			pstmt.setInt(6, vo.getRe_level()+1);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}	
	}

	@Override
	public void rwUpdate(BoardVO vo) {
		try {
			System.out.println("DaoImpl(rwUpdate) =====>");
			conn =DBConnection.getConnection();
			String update_sql = " update ReBoard1123 "
					          + "    set re_step = re_step + 1 "
					          + "  where ref = ? "
					          + "    and re_step > ? ";
			pstmt=conn.prepareStatement(update_sql);
			pstmt.setInt(1, vo.getRef());
			pstmt.setInt(2, vo.getRe_step());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}
}
