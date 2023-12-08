package DBPKG.guestbook;

import java.util.List;

public interface GuestBookDao {
	List<GuestBookVO> list(GuestBookVO vo);
	void write(GuestBookVO vo);
	int rowCount(GuestBookVO vo);
	void delete(String idx);
	int totalPage();
}
