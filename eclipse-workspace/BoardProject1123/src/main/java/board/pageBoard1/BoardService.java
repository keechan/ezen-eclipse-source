package board.pageBoard1;

import java.util.List;

public interface BoardService {
	  void insert(BoardVO  vo);
	  void update(BoardVO  vo);
	  void delete(String  idx);
	  BoardVO edit(String  idx);
	  List<BoardVO> select(BoardVO  vo);  
	  
	  void  bigInsert(BoardVO  vo);	
	  int totalCount(BoardVO  vo);
}
