package com.min.sbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.Article;
import com.min.sbs.dto.Board;
import com.min.sbs.dto.ResultData;
import com.min.sbs.dto.Rq;
import com.min.sbs.service.ArticleService;
import com.min.sbs.service.BoardService;
import com.min.sbs.util.Util;

@Controller
public class UsrArticleController {
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;

	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

	@RequestMapping("/usr/article/search")
	public String search(Model model, String str, Integer boardId, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int type) {

		if (Util.isEmpty(str)) {
			return rq.historyBackJsOnView("str를 입력해주세요");
		}

		String innerText = "%" + str + "%";

		if (boardId == null) {
			return rq.historyBackJsOnView("boardId를 입력해주세요");
		}

		int articlesCount = articleService.getArticlesCountAfterSearching(boardId, innerText, type);
		int startIndex = (page - 1) * limit;

		if (startIndex >= articlesCount) {
			return rq.historyBackJsOnView("게시물이 존재하지 않습니다.");
		}

		Board board = boardService.getBoardById(boardId);

		if (board == null) {
			return rq.historyBackJsOnView("해당 게시판이 존재하지 않습니다.");
		}

		List<Article> articles = articleService.getForPrintLimitedArticlesByInnerTextAndtype(rq.getLoginedMemberId(),
				boardId, startIndex, limit, innerText, type);

		if (articles == null) {
			return rq.historyBackJsOnView("게시물이 존재하지 않습니다.");
		}

		int pages = (int) Math.ceil(articlesCount / (double) limit);

		model.addAttribute("articles", articles);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("board", board);
		model.addAttribute("boardId", board.getId());
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", page);
		model.addAttribute("limit", limit);
		model.addAttribute("str", str);
		model.addAttribute("type", type);

		return "usr/article/search";
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트 입니다.", "artcicles", articles);
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, Integer boardId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {

		if (boardId == null) {
			return rq.historyBackJsOnView("boardId를 입력해주세요");
		}

		int articlesCount = articleService.getArticlesCount(boardId);

		int startIndex = (page - 1) * limit;

		if (startIndex >= articlesCount) {
			return rq.historyBackJsOnView("페이지가 존재하지 않습니다.");
		}

		Board board = boardService.getBoardById(boardId);

		if (board == null) {
			return rq.historyBackJsOnView("해당 게시판이 존재하지 않습니다.");
		}

		List<Article> articles = articleService.getForPrintLimitedArticlesByBoardId(rq.getLoginedMemberId(), boardId,
				startIndex, limit);

		int pages = (int) Math.ceil(articlesCount / (double) limit);

		model.addAttribute("articles", articles);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("boardId", board.getId());
		model.addAttribute("board", board);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", page);
		model.addAttribute("limit", limit);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(Integer id) {

		if (id == null) {
			return ResultData.from("F-A", "id를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Util.format("%s번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Util.format("%s번 게시물입니다.", id), "article", article);
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Integer id, Model model) {

		if (id == null) {
			model.addAttribute("errors", "id를 입력해주세요");
			return "usr/article/detail";
		}

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			model.addAttribute("errors", Util.format("%s번 게시물은 존재하지 않습니다.", id));
			return "usr/article/detail";
		}
		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/write")
	public String write() {
		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(Integer boardId, String title, String body) {

		if (boardId == null) {
			return Util.jsHistoryBack("boardId를 입력해주세요");
		}
		if (Util.isEmpty(title)) {
			return Util.jsHistoryBack("title를 입력해주세요");
		}
		if (Util.isEmpty(body)) {
			return Util.jsHistoryBack("body를 입력해주세요");
		}

		ResultData<Integer> addRd = articleService.doAdd(rq.getLoginedMemberId(), boardId, title, body);
		int id = addRd.getData();

		return Util.jsReplace(addRd.getMsg(), "/usr/home/main");
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(Integer id) {

		if (id == null) {
			return Util.jsHistoryBack("id를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.format("%s번 글은 존재하지 않습니다.", id));
		}

		ResultData actorCanDeleteRd = articleService.actorCanDelete(rq.getLoginedMemberId(), article);

		if (actorCanDeleteRd.isFail()) {
			return Util.jsHistoryBack(actorCanDeleteRd.getMsg());
		}

		articleService.doDelete(id);

		return Util.jsReplace(Util.format("%s번 글이 삭제되었습니다.", id), "/usr/home/main");
	}

	@RequestMapping("/usr/article/modify")
	public String modify(Integer id, Model model) {

		if (id == null) {
			return rq.historyBackJsOnView("id를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return rq.historyBackJsOnView(Util.format("%d번 게시물이 존재하지 않습니다.", id));
		}

		model.addAttribute("article", article);

		return "/usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(Integer id, String title, String body) {

		if (id == null) {
			return Util.jsHistoryBack("id를 입력해주세요");
		}
		if (Util.isEmpty(title)) {
			return Util.jsHistoryBack("title를 입력해주세요");
		}
		if (Util.isEmpty(body)) {
			return Util.jsHistoryBack("body를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.format("%s번 글은 존재하지 않습니다.", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return Util.jsHistoryBack(actorCanModifyRd.getMsg());
		}

		articleService.doModify(id, title, body);

		return Util.jsReplace(Util.format("%s번 글을 수정하였습니다.", id), Util.format("/usr/article/detail?id=%d", id));
	}
}
