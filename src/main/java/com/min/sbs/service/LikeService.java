package com.min.sbs.service;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.LikeDao;
import com.min.sbs.dto.Like;

@Service
public class LikeService {

	private LikeDao likeDao;

	public LikeService(LikeDao likeDao) {
		this.likeDao = likeDao;
	}
	
	public void doArticleLike(int id, int actorId) {
		likeDao.doArticleLike(id, actorId);
	}

	public void doCancelArticleLike(int id, int actorId) {
		likeDao.doCancelArticleLike(id, actorId);
	}

	public Like getIsLikeArticle(int id, int actorId) {
		return likeDao.getIsLikeArticle(id, actorId);
	}

	public int getLikeCount(int id) {
		return likeDao.getLikeCount(id);
	}

}