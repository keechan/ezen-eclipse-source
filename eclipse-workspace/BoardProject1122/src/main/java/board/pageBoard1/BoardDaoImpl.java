package board.pageBoard1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import board.DBConnection;

public class BoardDaoImpl implements BoardDao {
	
	private Connection conn =null; 
	private PreparedStatement pstmt =null;
	private ResultSet rs =null; 
	@Override
	public void insert(BoardVO vo) {
		try {
			conn  = DBConnection.getConnection();
			String SQL = "insert  into board1127(idx, sname, title, content,cnt) "
					+ " values(idx_board2.nextval, ?, ?, ?, 1)" ;
			pstmt=conn.prepareStatement(SQL);
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

	@Override
	public void update(BoardVO vo) {
		try {
			conn  = DBConnection.getConnection();
			String SQL = "update board1127 set sname=?, title=?, content=? where idx =? ";
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());	
			pstmt.setString(4, vo.getIdx());
			pstmt.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}

	@Override
	public void delete(String idx) {
		try {
			conn  = DBConnection.getConnection();
			String SQL = "delete board1127 where idx = ? ";
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}		
	}

	@Override
	public BoardVO edit(String idx) {
		BoardVO m = null;
		try {
			conn  = DBConnection.getConnection();
    		String SQL = " select  *   from  board1127  where idx = ? "; 
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, idx );
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
			DBConnection.close(rs, pstmt, conn);
		}		
		return m;
	}

	@Override
	public List<BoardVO> select(BoardVO vo) {
		
		System.out.println("===> select : " + vo.getCh1() +":"+vo.getCh2());
		
		List<BoardVO> li = new ArrayList<>();
		try {
			
			int  startIdx = vo.getStart();
			int pageSize = vo.getPageSize();
			
			String ch1 = vo.getCh1();
			String ch2 = vo.getCh2();
			
			conn  = DBConnection.getConnection();
			
			if (ch1 == null || ch1.equals("null") || ch2.equals("") ) {
				String SQL = " select  rownum, Q.*  " ;
				SQL  = SQL +  " from " ;
				SQL  = SQL +  " (    " ;
				SQL  = SQL +  "    select  rownum  as rnum, K.*  ";
				SQL  = SQL +  "             from  ";
				SQL  = SQL +  "            (select  *   from  board1127 "; 
				SQL  = SQL +  "             order  by  idx  desc ) K  "; 
				SQL  = SQL +  "             where  rownum <= ? ";
				SQL  = SQL +  "   )Q ";
				SQL  = SQL +  "  where rnum >= ? ";				
				
				pstmt=conn.prepareStatement(SQL);
				pstmt.setInt(1, startIdx + pageSize -1 );
				pstmt.setInt(2, startIdx);
				rs = pstmt.executeQuery();	
			}else if(ch1.equals("sname")) {
				String SQL = " select  rownum, Q.*  " ;
				SQL  = SQL +  " from " ;
				SQL  = SQL +  " (    " ;
				SQL  = SQL +  "    select  rownum  as rnum, K.*  ";
				SQL  = SQL +  "             from  ";
				SQL  = SQL +  "            (select  *   from  board1127 where sname like ? "; 
				SQL  = SQL +  "             order  by  idx  desc ) K  "; 
				SQL  = SQL +  "             where  rownum <= ? ";
				SQL  = SQL +  "   )Q ";
				SQL  = SQL +  "  where rnum >= ? ";
								
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + ch2 +"%" );
				pstmt.setInt(2, startIdx + pageSize -1 );
				pstmt.setInt(3, startIdx);
				rs = pstmt.executeQuery();	
			}else if(ch1.equals("title")) {
				String SQL = " select  rownum, Q.*  " ;
				SQL  = SQL +  " from " ;
				SQL  = SQL +  " (    " ;
				SQL  = SQL +  "    select  rownum  as rnum, K.*  ";
				SQL  = SQL +  "             from  ";
				SQL  = SQL +  "            (select  *   from  board1127  where title like ?  "; 
				SQL  = SQL +  "             order  by  idx  desc ) K  "; 
				SQL  = SQL +  "             where  rownum <= ? ";
				SQL  = SQL +  "   )Q ";
				SQL  = SQL +  "  where rnum >= ? ";						
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + ch2 +"%" );
				pstmt.setInt(2, startIdx + pageSize -1 );
				pstmt.setInt(3, startIdx);
				rs = pstmt.executeQuery();	
			}			
	
			BoardVO m = null;
			while(rs.next()) {
				m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				m.setCnt(rs.getInt("cnt"));
				
				m.setRownum(rs.getInt("rownum"));
				m.setRnum(rs.getInt("rnum"));
				
				li.add(m);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}		
		return li;
	}

	@Override
	public void bigInsert(BoardVO vo) {
		
		try {
			conn  = DBConnection.getConnection();	
			for (int i=1 ; i <= 2000; i++) {
				
				
				if (i % 50 == 0) {
					Thread.sleep(2000); //1초 대기
				}
				
				int snameR = (int) (Math.random()*7);
				int titleR1 = (int) (Math.random()*5);
				int titleR2 = (int) (Math.random()*3);
				int contentR = (int) (Math.random()*4);
				 
				String [] sname = {"영심이","하니","똘이","하늘이","바다","지효","지솔"}  ;
				String [] title1 = {"영어","국어","수학","역사","컴퓨터"} ;
				String [] title2 = {"초급","중급","고급"} ;
				
				String [] content = {"열심히 공부하자","기초부터 차근차근","휴강","그룹과제 하기"};
				
				String arrsname = sname[snameR];
				String arrtitle = title1[titleR1] + " " + title2[titleR2] + " " + i;
				String arrcontent = arrsname + " " + arrtitle + " " + content[contentR];
				
				String SQL = "insert  into board1127(idx, sname, title, content,cnt) "
						+ " values(idx_board2.nextval, ?, ?, ?, 1)" ;
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, arrsname);
				pstmt.setString(2, arrtitle);
				pstmt.setString(3, arrcontent );			
				pstmt.executeUpdate();
				System.out.println("===>" + i +"번 실행" );
				DBConnection.close(pstmt);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}	
		
	}

	@Override
	public int totalCount(BoardVO vo) {
		
		String ch1 = vo.getCh1();
		String ch2 = vo.getCh2();
		
	    int  tc = 0;
		try {			
			conn  = DBConnection.getConnection();
			
			if (ch1 == null ||  ch1.equals("null")  || ch2.equals("")) {
				String SQL = " select count(*) tc from board1127  " ;
				pstmt=conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();		
			}else if(ch1.equals("sname")) {
				String SQL = " select count(*) tc from board1127 where sname like ?  " ;
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + ch2 +"%");
				rs = pstmt.executeQuery();	
			}else if(ch1.equals("title")) {
				String SQL = " select count(*) tc from board1127 where title like ? " ;
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + ch2 +"%");
				rs = pstmt.executeQuery();	
			}
						
			if(rs.next()) {			
			 tc = rs.getInt("tc");
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}		
		return tc;
	}


}
