package net.developia.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;
import net.developia.spring03.service.BoardService;

@Log4j
@Controller
public class BoardController {

	private BoardService boardService;
	
	@Value("${pageSize}") //system.properties에 있는 value
	private int pageSize;
	
	@Value("${blockSize}")
	private long blockSize;

	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("insert")
	public String insertBoard() {   
		return "insert";
	}

	@PostMapping("insert")
	public String insertBoard(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		try {
			boardService.insertBoard(boardDTO);
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg", "입력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}

	@GetMapping("list")
	public String list(@RequestParam(defaultValue = "1") long pg, Model model) throws Exception {
		
		try {
			
			long recordCount = boardService.getBoardCount();
			long pageCount = recordCount/pageSize;
			if(recordCount%pageSize!=0) {
				pageCount++;
			}
			
			List<BoardDTO> list = boardService.getBoardListPage(pg);
			
			long startPage = (pg - 1)/blockSize*blockSize+1;
			long endPage   = startPage + blockSize - 1;
			if (endPage > pageCount) endPage = pageCount;
			
			model.addAttribute("list", list);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pg", pg);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			return "list";
		} catch (Exception e) {
			model.addAttribute("msg", "list 출력 에러");
			model.addAttribute("url", "index");
			return "result";
		}
	}
	
	@GetMapping(value = "detail")
	public String detail(@RequestParam long no,
			@RequestParam long pg,Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetail(no);
			model.addAttribute("boardDTO", boardDTO);
			model.addAttribute("pg", pg);
			return "detail";
		} catch (RuntimeException e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "list");
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", "상세보기 에러");
			model.addAttribute("url", "list");
			return "result";
		}
	}

	@GetMapping(value = "delete")
	public String delete(@RequestParam long no,Model model) {
		model.addAttribute("no",no);
		return "delete";
	}
	
	@PostMapping("delete")
	public ModelAndView deleteBoard(@ModelAttribute BoardDTO boardDTO, Model model) { //ModelAndView 이용하는 또 다른 방법
		log.info(boardDTO.toString());
		
		ModelAndView mav = new ModelAndView("result");
		try {
			boardService.deleteBoard(boardDTO);
			mav.addObject("msg", boardDTO.getNo()+"번 게시물이 삭제되었습니다.");
			mav.addObject("url", "list");
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			
		}
		return mav;
	}
	
	@GetMapping(value = "update")
	public String update(@RequestParam long no,Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetail(no);
			model.addAttribute("boardDTO", boardDTO);
			return "update";
		} catch (RuntimeException e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "list");
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", "수정하기 에러");
			model.addAttribute("url", "list");
			return "result";
		}
	}
	
	
	@PostMapping("update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) { //ModelAndView 이용하는 또 다른 방법
		log.info(boardDTO.toString());
		
		try {
			boardService.updateBoard(boardDTO);

			BoardDTO newBoardDTO = boardService.getDetail(boardDTO.getNo());
			model.addAttribute("boardDTO", boardDTO);
			return "detail";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "result";

		}
	}
	
}
