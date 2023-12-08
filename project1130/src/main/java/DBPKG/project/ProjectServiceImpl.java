package DBPKG.project;

import java.util.List;

public class ProjectServiceImpl implements  ProjectService{
	
	ProjectDao dao = null;
	
	public  ProjectServiceImpl(){
		dao = new  ProjectDaoImpl();
	}
	
	@Override
	public List<TeacherVO> teacherSelect() {
		System.out.println("ServiceImpl--------->");
		return dao.teacherSelect();
	}

	@Override
	public List<MemberVO> memberSelect() {
		// TODO Auto-generated method stub
		return dao.memberSelect();
	}

	@Override
	public List<MoneyVO> moneySelect() {
		return dao.moneySelect();
	}

	@Override
	public void insert(ClassVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public List<ClassVO> classList() {
		return dao.classList();
	}

	@Override
	public MemberVO memberEdit(String cno) {
		return dao.memberEdit(cno);
	}

	@Override
	public List<MemoVO> memoList(String cno) {
		System.out.println("ServiceImpl....(Memo) ------> " + cno);
		return dao.memoList(cno);
	}

	@Override
	public void memoInsert(MemoVO vo) {
		dao.memoInsert(vo);
	}

	@Override
	public void memoDelete(int idx) {
		dao.memoDelete(idx);
	}
}
