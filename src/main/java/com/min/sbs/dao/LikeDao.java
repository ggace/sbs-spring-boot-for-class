package com.min.sbs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.min.sbs.dto.Like;

@Mapper
public interface LikeDao {
	public void doArticleLike(int id, int actorId);

	public void doCancelArticleLike(int id, int actorId);

	public Like getIsLikeArticle(int id, int actorId);

	public int getLikeCount(int id);

	public void doAddArticleLike(int id, int actorId);
}
