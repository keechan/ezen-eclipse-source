package DBPKG.project;

import java.util.List;

public interface ProjectDao {
   List<TeacherVO> teacherSelect();
   List<MemberVO>  memberSelect();
   List<MoneyVO> moneySelect();
   void insert(ClassVO vo);
   List<ClassVO> classList();
   
   MemberVO memberEdit(String cno);
   List<MemoVO> memoList(String cno);
   void memoInsert(MemoVO vo);
   void memoDelete(int idx);
}
