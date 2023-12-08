package com.springbook.biz.Client;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

public class selectClient2 {
	public static void main(String[] args) {
		//BoardService service = new BoardServiceImpl();
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		//Object 리턴
		BoardService service = (BoardService)container.getBean("BoardService");
		
		String str1 = "title", str2 = "공부중";
		List<BoardVO> li = service.getBoardList(str1, str2);
		
		for (int i=0;i<li.size();i++) {
			BoardVO m = li.get(i);
			System.out.println("----------------------------------------");
			System.out.println("번호:" + m.getSeq() + ", 제목:" + m.getTitle() + ", 이름:" + m.getWriter());
		}
	}
}
