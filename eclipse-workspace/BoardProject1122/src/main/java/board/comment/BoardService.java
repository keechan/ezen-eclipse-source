package board.comment;

import java.util.List;

public interface BoardService {
	   void  insert(BoardVO  vo);	
	   void  update(BoardVO  vo);	
	   void  delete(BoardVO  vo);	
	   
	   void  cntCount(BoardVO  vo);
	   
	   BoardVO  edit(BoardVO  vo);	
	   List<BoardVO>  list(BoardVO  vo);	
	   
	   List<CommentVO>  commentlist(CommentVO  vo);
	   void commentInsert(CommentVO  vo);
	   void commentDelete(CommentVO  vo);
}
