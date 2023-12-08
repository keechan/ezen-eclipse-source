package com.springbook.biz.Client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardServiceImpl;
import com.springbook.biz.board.BoardVO;

public class updateClient {

	public static void main(String[] args) {
		//BoardServiceImpl service = new BoardServiceImpl();
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		//Object 리턴
		BoardService service = (BoardService)container.getBean("BoardService");
		
		int k = 101; 
		BoardVO vo = service.getBoard(k);
		System.out.println("수정전 ===> " + vo);
		
		vo.setSeq(k);
		vo.setTitle("TEST");
		vo.setWriter("TEST");
		vo.setContent("TEST");
		service.update(vo);
		
		vo = service.getBoard(k);
		System.out.println("수정후 ===> " + vo);
	}

}
