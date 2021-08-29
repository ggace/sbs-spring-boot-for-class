package com.min.sbs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.min.sbs.dto.ReactionPoint;

@Mapper
public interface ReactionPointDao {
	public void doArticleLike(int id, int actorId);

	public void doCancelArticleLike(int id, int actorId);

	public int getSumReactionPointByMemberId(int id, int actorId);

	public int getLikeCount(int id);

	public void doAddArticleLike(int id, int actorId);

	public void doAddArticleDisLike(int id, int actorId);

	public void doUpdateArticleDisLike(int id, int actorId);

	public void doUpdateArticleLike(int id, int actorId);
	
	public void updateArticleReactionPointInit();
	
	public void updateArticleReactionPoint();
}
