package board.reBoard;

import java.util.List;

public interface BoardDao {
   // CRUD 
   void insert(BoardVO  vo);	
   void rwInsert(BoardVO  vo);
   void rwUpdate(BoardVO vo);
//   void  update(BoardVO  vo);
   void delete(BoardVO  vo);
//   
//   void  cntCount(BoardVO  vo);
//   
   BoardVO edit(BoardVO  vo);
   List<BoardVO> list(BoardVO  vo);
   
}
