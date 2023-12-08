package board.comment;

import java.util.List;

public class BoardServiceImpl implements BoardService {
	BoardDao dao = null;
	public  BoardServiceImpl(){
		dao = new BoardDaoImpl();
	}
	
	@Override
	public void insert(BoardVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public void update(BoardVO vo) {
		dao.update(vo);		
	}

	@Override
	public void delete(BoardVO vo) {
		dao.delete(vo);		
	}

	@Override
	public BoardVO edit(BoardVO vo) {
		
		return dao.edit(vo);
	}

	@Override
	public List<BoardVO> list(BoardVO vo) {
		
		return dao.list(vo);
	}

	@Override
	public void cntCount(BoardVO vo) {
		dao.cntCount(vo);
		
	}

	@Override
	public List<CommentVO> commentlist(CommentVO vo) {
		// TODO Auto-generated method stub
		return dao.commentlist(vo);
	}

	@Override
	public void commentInsert(CommentVO vo) {
		dao.commentInsert(vo);
		
	}

	@Override
	public void commentDelete(CommentVO vo) {
		dao.commentDelete(vo);
		
	}

}
