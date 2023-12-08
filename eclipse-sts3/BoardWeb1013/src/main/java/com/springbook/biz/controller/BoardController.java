package com.springbook.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardServiceImpl;
import com.springbook.biz.board.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardServiceImpl service; 
	
	public BoardController() {
		System.out.println("===> BoardController");
	}
	@RequestMapping("/insertBoard.do")
	public String insert(BoardVO vo) {
		service.insert(vo);
		return "getBoardList.do";	 
	}
	
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardVO vo, ModelAndView mav ) {
		mav.addObject("boardList", service.selectAll()) ;
		mav.setViewName("getBoardList.jsp");
		return mav;
	}
	
	@RequestMapping("/delete.do")
	public String delete(BoardVO vo) {
		service.delete(vo);
		return "getBoardList.do";	 
	}
	
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO vo, ModelAndView mav ) {
		mav.addObject("m", service.getBoard(vo)) ;
		mav.setViewName("getBoard.jsp");
		return mav;
	}
}
