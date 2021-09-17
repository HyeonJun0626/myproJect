package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.FileUtils;
import com.dto.BoardDto;
import com.dto.BoardFileDto;
import com.mapper.BoardMapper;



@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileutils;
	
	@Override
	public void profileInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		
		List<BoardFileDto> list = fileutils.parseFileInfo(boardDto, image);
		
		if (CollectionUtils.isEmpty(list) == false) {
			boardMapper.profileInsert(list);
		}
		
		if (!"".equals(boardDto.getUserNick()) & boardDto.getUserNick() != null) {
			Map <String, Object> data = new HashMap<String, Object>();
			data.put("userSeq", boardDto.getUserSeq());
			data.put("userNick", boardDto.getUserNick());
			boardMapper.reNick(data);			
		}

	}

	@Override
	public void boardInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		
		boardMapper.boardInsert(boardDto);
		if (!"".equals(Integer.toString(boardDto.getBoardSeq())) & Integer.toString(boardDto.getBoardSeq()) != null) {
			
			List<BoardFileDto> list = fileutils.parseFileInfo(boardDto, image);
			
			if (CollectionUtils.isEmpty(list) == false) {
				
				boardMapper.boardImgInsert(list);
				
			}
		}
	}
	
	@Override
	public List<BoardDto> getBoardList(int userSeq) throws Exception {
		List<BoardDto> board = boardMapper.getBoardList(userSeq);
		
		for (int i = 0; i < board.size(); i++) {
			List<BoardFileDto> imgList = boardMapper.getBoardImg(board.get(i).getBoardSeq());
			board.get(i).setImgList(imgList);
		}
		return board;
	}
		

	}