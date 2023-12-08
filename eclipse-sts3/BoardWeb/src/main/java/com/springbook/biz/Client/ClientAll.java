package com.springbook.biz.Client;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

public class ClientAll {

	public static void main(String[] args) {
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		//Object 리턴
		BoardService service = (BoardService)container.getBean("BoardService");
		BoardVO vo = null;
		
		String sw = "delete";
		if (sw.equals("listAll")) {
			List<BoardVO> liAll = service.getBoardList();
			
			for (int i=0;i<liAll.size();i++) {
				BoardVO m = liAll.get(i);
				System.out.println("----------------------------------------");
				System.out.println("번호:" + m.getSeq() + ", 제목:" + m.getTitle() + ", 이름:" + m.getWriter());
			}			
		} else if (sw.equals("list")) {
			String str1 = "title", str2 = "공부중";
			List<BoardVO> li = service.getBoardList(str1, str2);
			
			for (int i=0;i<li.size();i++) {
				BoardVO m = li.get(i);
				System.out.println("----------------------------------------");
				System.out.println("번호:" + m.getSeq() + ", 제목:" + m.getTitle() + ", 이름:" + m.getWriter());
			}
		} else if (sw.equals("insert")) {
			int k = 111;
			vo = new BoardVO();
			vo.setSeq(k);
			vo.setTitle("김");
			vo.setWriter("MBC방송국");
			vo.setTitle("안녕하세요? 김태호 PD입니다.");
			vo.setContent("안녕하세요? 김태호 PD입니다.");
			service.insert(vo);
			
			BoardVO m = service.getBoard(k);
		} else if (sw.equals("update")) {
			int k = 111; 
			vo = service.getBoard(k);
			System.out.println("수정전 ===> " + vo);
			
			vo.setSeq(k);
			vo.setTitle("TEST");
			vo.setWriter("TEST");
			vo.setContent("TEST");
			service.update(vo);
			
			vo = service.getBoard(k);
			System.out.println("수정후 ===> " + vo);
		} else if (sw.equals("delete")) {
			int k = 111;
			//먼저 삭제
			service.delete(k);
			//삭제 확인
			List<BoardVO> li = service.getBoardList();
			for (int i=0;i<li.size();i++) {
				vo = li.get(i);
				System.out.println("번호:" + vo.getSeq() + ", " + "제목:" + vo.getTitle() + ", 이름:" + vo.getWriter());
			}
		} 
		
		container.close();
	}
}
