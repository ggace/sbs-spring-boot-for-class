package com.min.sbs.service;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.BoardDao;
import com.min.sbs.dto.Board;

@Service
public class BoardService {
	
	private BoardDao boardDao;
	
	public BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	public Board getBoardById(int boardId) {
		return boardDao.getBoardById(boardId);
	}

}
