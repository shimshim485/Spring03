package net.developia.spring03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.developia.spring03.dao.BoardDAO;
import net.developia.spring03.dto.BoardDTO;

@Service
public interface BoardService {

	void insertBoard(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> getBoardList() throws Exception;

	BoardDTO getDetail(long no) throws Exception;

	void deleteBoard(BoardDTO boardDTO) throws Exception;

	void updateBoard(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> getBoardListPage(long pg) throws Exception;
	
	long getBoardCount() throws Exception;

}
