package board.pageBoard1;

import java.util.List;

public class BoardServiceImpl implements BoardService{
	
	private BoardDao dao = null;
	
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
	public void delete(String idx) {
		dao.delete(idx);		
	}

	@Override
	public BoardVO edit(String idx) {		
		return dao.edit(idx);
	}

	@Override
	public List<BoardVO> select(BoardVO vo) {
		return dao.select(vo);
	}

	@Override
	public void bigInsert(BoardVO vo) {		
		dao.bigInsert(vo);
	}

	@Override
	public int totalCount(BoardVO vo) {
		return dao.totalCount(vo);
	}


}
