package board.basicBoard;

import java.util.List;

public interface BoardService {
	   void  insert(BoardVO  vo);	
	   void  update(BoardVO  vo);	
	   void  delete(BoardVO  vo);	
	   
	   //void  cntCount(BoardVO  vo);
	   
	   BoardVO  edit(BoardVO  vo);	
	   List<BoardVO>  list(BoardVO  vo);	
}
