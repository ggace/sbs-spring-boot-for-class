package com.min.sbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.min.sbs.dto.Board;

@Mapper
public interface BoardDao {

	public Board getBoardById(@Param("boardId") int boardId);

}
