package DBPKG.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao{
	
	Connection conn =null;
	PreparedStatement pstmt =null; 
	ResultSet rs =null ;
	
	@Override
	public List<TeacherVO> teacherSelect() {
		List<TeacherVO> li = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String SQL ="select  *  from  TBL_TEACHER_202201  order by  TEACHER_CODE";
			System.out.println(SQL);
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			TeacherVO m =null;
			while(rs.next()) {
				m =new TeacherVO();
				m.setTEACHER_CODE(rs.getInt("TEACHER_CODE"));
				System.out.println(rs.getInt("TEACHER_CODE"));
				m.setTEACHER_NAME(rs.getString("TEACHER_NAME"));
				m.setCLASS_NAME(rs.getString("CLASS_NAME"));
				m.setCLASS_PRICE(rs.getInt("CLASS_PRICE"));
				m.setTEACHER_REGIST_DATE(rs.getInt("TEACHER_REGIST_DATE"));
				li.add(m);
				System.out.println("TeacherVO:" + m);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public List<MemberVO> memberSelect() {
		List<MemberVO> li = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String SQL ="select  REGIST_MONTH, M.C_NO as C_NO, C_NAME,"
					+ "          CLASS_NAME, CLASS_AREA, TUITION ,GRADE "
					+ " from TBL_MEMBER_202201 M join TBL_CLASS_202201 C "
					+ " on M.C_NO = C.C_NO join TBL_TEACHER_202201 T "
					+ " on T.TEACHER_CODE = C.TEACHER_CODE ";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			MemberVO m =null;
			while(rs.next()) {
				m =new MemberVO();
				m.setREGIST_MONTH(rs.getString("REGIST_MONTH"));
				m.setC_NO(rs.getString("C_NO"));
				m.setC_NAME(rs.getString("C_NAME"));
				m.setCLASS_NAME(rs.getString("CLASS_NAME"));
				m.setCLASS_AREA(rs.getString("CLASS_AREA"));
				m.setTUITION(rs.getInt("TUITION"));
				m.setGRADE(rs.getString("GRADE"));
				li.add(m);
				System.out.println("MemberVO:" + m);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public List<MoneyVO> moneySelect() {
		List<MoneyVO> li = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String SQL =" select  T.TEACHER_CODE as T1 ,CLASS_NAME as T2 ,"
					+ "   TEACHER_NAME as T3, sum(TUITION) as T4 "
					+ " from TBL_MEMBER_202201 M join TBL_CLASS_202201 C "
					+ " on M.C_NO = C.C_NO join TBL_TEACHER_202201 T "
					+ " on T.TEACHER_CODE = C.TEACHER_CODE "
					+ " group  by T.TEACHER_CODE,CLASS_NAME,TEACHER_NAME "
					+ " order  by  T.TEACHER_CODE ";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			MoneyVO m =null;
			while(rs.next()) {
				m =new MoneyVO();
				m.setT1(rs.getString("T1"));
				m.setT2(rs.getString("T2"));
				m.setT3(rs.getString("T3"));
				m.setT4(rs.getInt("T4"));				
				li.add(m);
				System.out.println("MoneyVO:" + m);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public void insert(ClassVO vo) {	
		try {
			conn = DBConnection.getConnection();
			String SQL ="INSERT INTO TBL_CLASS_202201 "
					+ " (REGIST_MONTH, C_NO,CLASS_AREA,TUITION,TEACHER_CODE) "
					+ " VALUES(?,?, ?, ?, ?)" ;
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,vo.REGIST_MONTH);
			pstmt.setString(2,vo.C_NO);
			pstmt.setString(3,vo.CLASS_AREA);
			pstmt.setInt(4,vo.TUITION);
			pstmt.setString(5,vo.TEACHER_CODE);
			rs = pstmt.executeQuery();
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}		
	}

	@Override
	public List<ClassVO> classList() {
		List<ClassVO> li = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String SQL ="SELECT c.REGIST_MONTH||'('||m.C_NAME||')' REGIST_MONTH, c.C_NO, c.CLASS_AREA, c.TUITION, c.TEACHER_CODE, t.TEACHER_NAME "
			 		  + "  FROM TBL_CLASS_202201 c "
					  + "  JOIN TBL_TEACHER_202201 t "
					  + "    ON (C.TEACHER_CODE = T.TEACHER_CODE) "
					  + "  JOIN TBL_MEMBER_202201 m "
					  + "    ON (C.C_NO = m.C_NO) "
					  + " ORDER BY c.REGIST_MONTH, c.C_NO ";
			
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			ClassVO m =null;
			while(rs.next()) {
				m =new ClassVO();
				m.setREGIST_MONTH(rs.getString("REGIST_MONTH"));
				m.setC_NO(rs.getString("C_NO"));
				m.setCLASS_AREA(rs.getString("CLASS_AREA"));
				m.setTUITION(rs.getInt("TUITION"));
				m.setTEACHER_CODE(rs.getString("TEACHER_CODE"));
				m.setTEACHER_NAME(rs.getString("TEACHER_NAME"));
				li.add(m);
				System.out.println("ClassVO:" + m);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public MemberVO memberEdit(String cno) {
		MemberVO m = new MemberVO();
		try {
			conn = DBConnection.getConnection();
			String SQL ="SELECT C_NO, C_NAME, PHONE, ADDRESS, GRADE "
					  + "  FROM TBL_MEMBER_202201 "
					  + " WHERE C_NO = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setC_NO(rs.getString("C_NO"));
				m.setC_NAME(rs.getString("C_NAME"));
				m.setPHONE(rs.getString("PHONE"));
				m.setADDRESS(rs.getString("ADDRESS"));
				m.setGRADE(rs.getString("GRADE"));
				System.out.println("DaoImpl-------->" + rs.getString("GRADE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return m;
	}
	
	@Override
	public List<MemoVO> memoList(String cno) {
		List<MemoVO> li = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String SQL =  " SELECT idx, c_no cno, writer, memo "; 
				   SQL += "  FROM TBL_MEMBER_MEMO ";
				   SQL += " WHERE C_NO = ? ";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cno);			
			
			System.out.println(SQL);
			System.out.println(cno);
			
			rs = pstmt.executeQuery();
			MemoVO m =null;
			while(rs.next()) {
				m =new MemoVO();
				m.setIdx(rs.getInt("idx"));
				m.setCno(rs.getString("cno"));
				m.setWriter(rs.getString("writer"));
				m.setMemo(rs.getString("memo"));
				
				li.add(m);
				System.out.println("MemoVO:" + m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public void memoInsert(MemoVO vo) {
		try {
			conn = DBConnection.getConnection();
			String SQL = " insert into tbl_member_memo (idx, c_no, writer, memo) "
			   		   + " values ((select nvl(max(idx)+1, 10001) idx from tbl_member_memo), "
			   		   + "                ?, ?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getCno());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getMemo());
			pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
	}

	@Override
	public void memoDelete(int idx) {
		try {
			conn = DBConnection.getConnection();
			String SQL = " delete from tbl_member_memo where idx=? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}		
	}
}
