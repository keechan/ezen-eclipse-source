package com.springbook.biz.Client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardServiceImpl;
import com.springbook.biz.board.BoardVO;

public class InsertClient {
	public static void main(String[] args) {
		//BoardServiceImpl service = new BoardServiceImpl();
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		//Object 리턴
		BoardService service = (BoardService)container.getBean("BoardService");
		
		BoardVO vo = new BoardVO();
		//ResultSet rs = null;
		
		int k = 101;
		vo.setSeq(k);
		vo.setTitle("김");
		vo.setWriter("MBC방송국");
		vo.setTitle("안녕하세요? 김태호 PD입니다.");
		vo.setContent("안녕하세요? 김태호 PD입니다.");
		service.insert(vo);
		
		BoardVO m = service.getBoard(k);
		System.out.println("=========[결과확인]==========");
		System.out.println(m.toString());
		System.out.println("===========================");
		
		
	}
}
