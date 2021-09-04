package com.min.sbs.service;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.ReactionPointDao;
import com.min.sbs.dto.ReactionPoint;

@Service
public class ReactionPointService {

	private ReactionPointDao reactionPointDao;

	public ReactionPointService(ReactionPointDao likeDao) {
		this.reactionPointDao = likeDao;
	}
	
	public void doArticleLike(int id, int actorId, String relTypeCode) {
		reactionPointDao.doArticleLike(id, actorId, relTypeCode);
	}

	public void doCancelArticleLike(int id, int actorId, String relTypeCode) {
		reactionPointDao.doCancelArticleLike(id, actorId, relTypeCode);
	}

	public int getSumReactionPointByMemberId(int id, int actorId, String relTypeCode) {
		return reactionPointDao.getSumReactionPointByMemberId(id, actorId, relTypeCode);
	}

	public int getLikeCount(int id) {
		return reactionPointDao.getLikeCount(id);
	}

	public void doAddArticleLike(int id, int actorId, String relTypeCode) {
		reactionPointDao.doAddArticleLike(id, actorId, relTypeCode);
	}

	public void doAddArticleDisLike(int id, int actorId, String relTypeCode) {
		reactionPointDao.doAddArticleDisLike(id, actorId, relTypeCode);
	}

	public void doUpdateArticleDisLike(int id, int actorId) {
		// TODO Auto-generated method stub
		reactionPointDao.doUpdateArticleDisLike(id, actorId);
	}

	public void doUpdateArticleLike(int id, int actorId) {
		reactionPointDao.doUpdateArticleLike(id, actorId);
	}
	
	public void updateArticleReactionPoint() {
		reactionPointDao.updateArticleReactionPointInit();
		reactionPointDao.updateArticleReactionPoint();
	}

}