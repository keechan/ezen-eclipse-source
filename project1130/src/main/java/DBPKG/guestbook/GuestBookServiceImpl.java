package DBPKG.guestbook;

import java.util.List;

public class GuestBookServiceImpl implements GuestBookService {
	GuestBookDao dao = null;
	
	GuestBookServiceImpl() {
		dao = new GuestBookDaoImpl();
	}
	@Override
	public List<GuestBookVO> list(GuestBookVO vo) {
		System.out.println("GuestBookServiceImpl .........> list");
		return dao.list(vo);
	}
	@Override
	public void write(GuestBookVO vo) {
		System.out.println("GuestBookServiceImpl --------> write");
		dao.write(vo);
	}
	@Override
	public int rowCount(GuestBookVO vo) {
		return dao.rowCount(vo);
	}
	@Override
	public void delete(String idx) {
		dao.delete(idx);
	}
	@Override
	public int totalPage() {		
		return dao.totalPage();
	}	
}
