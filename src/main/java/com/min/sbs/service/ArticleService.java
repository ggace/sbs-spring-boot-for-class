package com.min.sbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.ArticleDao;
import com.min.sbs.dto.Article;
import com.min.sbs.dto.ResultData;
import com.min.sbs.util.Util;

@Service
public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleRepository) {
		this.articleDao = articleRepository;
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public List<Article> getForPrintArticles(int actorId) {
		List<Article> articles  = articleDao.getForPrintArticles();
		
		for(Article article : articles) {
			updateForPrintData(actorId, article);
		}
		
		return articles;
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}
	
	public Article getForPrintArticle(int actorId, int id) {
		Article article = articleDao.getForPrintArticle(id);
		
		updateForPrintData(actorId, article);
		
		return article;
	}

	private void updateForPrintData(int actorId, Article article) {
		if(article == null) {
			return;
		}
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
	}

	public ResultData<Integer> doAdd(int memberId, int boardId, String title, String body) {
		articleDao.addArticle(memberId, boardId, title, body);
		int id = articleDao.getLastArticleId();
		return ResultData.from("S-1", Util.format("%s번 게시물이 추가되었습니다.", id), "id", id);
	}

	public void doDelete(int id) {
		articleDao.deleteArticle(id);
	}

	public Article doModify(int id, String title, String body) {
		
		articleDao.modifyArticle(id, title, body);
		return getArticle(id);
	}
	
	public ResultData actorCanModify(int actorId, Article article) {
		if ( article == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}
		
		if ( article.getMemberId() != actorId && actorId != 1 ) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "게시물 수정이 가능합니다.");
	}
	
	public ResultData actorCanDelete(int actorId, Article article) {
		if ( article == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if ( article.getMemberId() != actorId && actorId != 1) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
	}

	public List<Article> getForPrintArticlesByBoardId(int loginedMemberId, int boardId) {
		List<Article> articles  = articleDao.getForPrintArticlesByBoardId(boardId);
		
		for(Article article : articles) {
			updateForPrintData(loginedMemberId, article);
		}
		
		return articles;
	}

	public int getArticlesCount(int boardId) {
		return articleDao.getArticlesCount(boardId);
	}

	public List<Article> getForPrintLimitedArticlesByBoardId(int loginedMemberId, int boardId, int startIndex,
			int limit) {
		List<Article> articles  = articleDao.getForPrintLimitedArticlesByBoardId(boardId, startIndex, limit);
		
		for(Article article : articles) {
			updateForPrintData(loginedMemberId, article);
		}
		
		return articles;
	}

	public List<Article> getForPrintLimitedArticlesByInnerTextAndtype(int loginedMemberId, int boardId, int startIndex, int limit, String str, int type) {
		List<Article> articles  = articleDao.getForPrintLimitedArticlesByInnerTextAndtype(boardId, startIndex, limit, str, type);
		
		for(Article article : articles) {
			updateForPrintData(loginedMemberId, article);
		}
		
		return articles;
	}

	public int getArticlesCountAfterSearching(Integer boardId, String str, Integer type) {
		return articleDao.getArticlesCountAfterSearching(boardId, str, type);
	}

	public void doAddHitCount(int id) {
		articleDao.doAddHitCount(id);
	}

	

}
