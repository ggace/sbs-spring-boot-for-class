package com.min.sbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.min.sbs.dto.Article;

@Mapper
public interface ArticleDao {

	public List<Article> getArticles();
	
	public List<Article> getForPrintArticles();

	public Article getArticle(@Param("id") int id);
	
	public Article getForPrintArticle(@Param("id") int id);

	public int getLastArticleId();

	public void addArticle(@Param("memberId") int memberId, int boardId, @Param("title") String title, @Param("body") String body);

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	public List<Article> getForPrintArticlesByBoardId(@Param("boardId") int boardId);

	public int getArticlesCount(@Param("boardId") int boardId);

	public List<Article> getForPrintLimitedArticlesByBoardId(@Param("boardId") int boardId, @Param("startIndex") int startIndex, @Param("limit") int limit);

	public List<Article> getForPrintLimitedArticlesByInnerTextAndtype(@Param("boardId") int boardId, @Param("startIndex") int startIndex, @Param("limit") int limit, @Param("str") String str, @Param("type") int type);

	public int getArticlesCountAfterSearching(Integer boardId, String str, Integer type);

	public int doAddHitCount(int id);

	public int getArticleHitCount(int id);

	

}
