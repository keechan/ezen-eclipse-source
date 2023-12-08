package com.springbook.biz.Client;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardServiceImpl;
import com.springbook.biz.board.BoardVO;

public class deleteClient {

	public static void main(String[] args) {
		//BoardServiceImpl service = new BoardServiceImpl();
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		//Object 리턴
		BoardService service = (BoardService)container.getBean("BoardService");
		
		int k = 101;
		//먼저 삭제
		service.delete(k);
		//삭제 확인
		List<BoardVO> li = service.getBoardList();
		for (int i=0;i<li.size();i++) {
			BoardVO vo = li.get(i);
			System.out.println("번호:" + vo.getSeq() + ", " + "제목:" + vo.getTitle() + ", 이름:" + vo.getWriter());
		}
	}
}
