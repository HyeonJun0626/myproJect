package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;

public interface BoardService {

	void profileInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception;

	void boardInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception;
	
	List<BoardDto> getBoardList(int userSeq) throws Exception;
}