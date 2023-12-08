package DBPKG.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBPKG.project.DBConnection;

public class GuestBookDaoImpl implements GuestBookDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public List<GuestBookVO> list(GuestBookVO vo) {
		int start = vo.getStart();
		int pageSize = vo.getPageSize();

		List<GuestBookVO> li = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String listSql =    " select rownum, q.* ";
			listSql = listSql + "   from ";
			listSql = listSql + "   (select rownum as rnum, K.* ";
			listSql = listSql + "      from "; 
			listSql = listSql + "   (select * from guestBOOK ";
			if (vo.ch1.equals("name")) {            	
				System.out.println("DaoImpl ====> " + vo.ch1 + " " + vo.ch2);
				listSql = listSql + " where name like ? ";
			} else if (vo.ch1.equals("title")) {
				System.out.println("DaoImpl ====> " + vo.ch1 + " " + vo.ch2);
				listSql = listSql + " where title like ?  ";
			}			
			listSql = listSql + "     ORDER BY IDX DESC ) k ";
			listSql = listSql + "     WHERE ROWNUM <= ? ";
			listSql = listSql + " ) Q ";
			listSql = listSql + "   where rnum >= ? ";

			listSql += " order by idx desc ";
			System.out.println(listSql);
			pstmt = conn.prepareStatement(listSql);
			if (!vo.ch2.equals("A")) {
				pstmt.setString(1, "%"+vo.ch2+"%");
				pstmt.setInt(2, start + pageSize - 1);
				pstmt.setInt(3, start + 1);
			} else {
				pstmt.setInt(1, start + pageSize - 1);
				pstmt.setInt(2, start + - 1);
			}
			System.out.println(listSql);
			rs = pstmt.executeQuery();
			System.out.println("GuestBookDaoImpl ----------> 1");
			GuestBookVO m = null;
			while (rs.next()) {
				m = new GuestBookVO();				
				m.setIdx(rs.getInt("idx"));
				m.setName(rs.getString("name"));
				m.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				li.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	public void write(GuestBookVO vo) {	
		try {
			conn = DBConnection.getConnection();
			String SQL ="insert into guestbook (idx, name, title, content) "
					  + "values (guestbookidx.nextval, ?, ?, ?) " ;
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.name);
			pstmt.setString(2, vo.title);
			pstmt.setString(3, vo.content);
			rs = pstmt.executeQuery();
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}		
	}

	@Override
	public int rowCount(GuestBookVO vo) {
		int cnt = 0;
		try {
			conn = DBConnection.getConnection();
			String countSql = "select count(*) cnt from guestbook ";			
			System.out.println("DaoImpl(rowCount) CHeck -------> " + vo.ch1 + "/" + vo.ch2);
            if (vo.ch1.equals("name")) {
            	//countSql = "select count(*) cnt from guestbook ";
            	countSql += "where name like ? ";
			} else if (vo.ch1.equals("title")) {
				System.out.println("DaoImpl ====> " + vo.ch1 + " " + vo.ch2);
				//countSql = "select count(*) cnt from guestbook ";
				countSql += "where title like ? ";
			}
			
			pstmt = conn.prepareStatement(countSql);
			if (!vo.ch2.equals("A")) {
				pstmt.setString(1, "%"+vo.ch2+"%");
			}

			rs = pstmt.executeQuery();
			System.out.println("GuestBookDaoImpl ----------> rowCount");
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public void delete(String idx) {
		try {
			conn = DBConnection.getConnection();
			String SQL ="delete from guestbook where idx = ? " ;
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}		
	}

	@Override
	public int totalPage() {
		int tp = 0;
		try {
			conn = DBConnection.getConnection();
			String tpSql = "select ceil(count(*) / 10) tp from guestbook ";			
			pstmt = conn.prepareStatement(tpSql);
			if (rs.next()) {
				tp = rs.getInt("tp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tp;
	}
}