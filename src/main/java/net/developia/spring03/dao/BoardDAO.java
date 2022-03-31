package net.developia.spring03.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.developia.spring03.dto.BoardDTO;

public interface BoardDAO {

	void insertBoard(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> getBoardList() throws Exception;

	BoardDTO getDetail(long no) throws Exception;

	void updateReadcount(long no) throws Exception;

	int deleteBoard(BoardDTO boardDTO) throws Exception; //수정 삭제는 레코드 확인해주는 게 좋음

	int updateBoard(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> getBoardListPage(@Param("startNum") long startNum, @Param("endNum")long endNum);

	long getBoardCount() throws Exception;
}
