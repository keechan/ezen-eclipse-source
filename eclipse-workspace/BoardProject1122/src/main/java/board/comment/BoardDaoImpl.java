package board.comment;

import java.sql.*;
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
			conn =DBConnection.getConnection();
			String insert_sql =" insert  into board1123 "
					+ " (idx, sname, title, content,cnt) "
					+ " values(idx_board.nextval, ?, ?, ?, 1)";
			pstmt=conn.prepareStatement(insert_sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}

	@Override
	public void update(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
			String update_sql =" update  board1123 "
					         + "    set sname=?, title=?, content=?  "
			   		         + "  where idx=? ";
			pstmt=conn.prepareStatement(update_sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
		
	}

	@Override
	public void delete(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
			String delete_sql = " delete from  board1123 "
					          + "  where idx=? ";
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
			String edit_sql = " select * from  board1123 "
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
			conn =DBConnection.getConnection();
			String select_sql =" select * from  board1123 order  by  idx  desc ";
					
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
				li.add(m);
				System.out.println("===> DAO " + m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public void cntCount(BoardVO vo) {
		try {
			conn =DBConnection.getConnection();
			String update_sql =" update board1123 "
					         + "    set cnt = cnt + 1  "
					         + "  where idx=? ";
			pstmt=conn.prepareStatement(update_sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}

	@Override
	public List<CommentVO> commentlist(CommentVO vo) {
		List<CommentVO> li  = null;
		try {
			conn =DBConnection.getConnection();
			String select_sql =" select * from  comment_board1123  where comment_idx=? order  by  idx  desc ";
					
			pstmt=conn.prepareStatement(select_sql);
			pstmt.setString(1, vo.getComment_idx());
			rs = pstmt.executeQuery();
			li = new ArrayList<>();
			CommentVO m = null;
		    while(rs.next()) {
				m = new CommentVO();
				m.setCidx(rs.getString("idx"));
				m.setComment_idx(rs.getString("comment_idx"));
				m.setCommentContent(rs.getString("commentcontent"));
				m.setCommentDate(rs.getDate("commentDate"));
				li.add(m);
				System.out.println("===> DAO " + m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return li;
	}

	@Override
	public void commentInsert(CommentVO vo) {
		try {
			conn =DBConnection.getConnection();
			String insert_sql =" insert into comment_board1123 "
					         + "       (idx, comment_idx, commentContent) "
					         + " values(idx_board.nextval, ?, ?)";
			pstmt=conn.prepareStatement(insert_sql);
			pstmt.setString(1, vo.getComment_idx());
			pstmt.setString(2, vo.getCommentContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void commentDelete(CommentVO vo) {
		try {
			conn =DBConnection.getConnection();
			String delete_sql =" delete from comment_board1123 "
					         + "  where idx = ? ";
			pstmt=conn.prepareStatement(delete_sql);
			pstmt.setString(1, vo.getCidx());
			System.out.println("DaoImpl -------> " + vo.getCidx());
			System.out.println("DaoImpl -------> " + vo.getComment_idx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
