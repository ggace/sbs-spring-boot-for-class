package com.min.sbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.ResultData;
import com.min.sbs.dto.Rq;
import com.min.sbs.service.ReplyService;
import com.min.sbs.util.Util;

@Controller
public class ReplyController {
	private ReplyService replyService;
	private Rq rq;
	
	public ReplyController(ReplyService replyService, Rq rq) {
		this.replyService= replyService;
		this.rq = rq;
	}
	@RequestMapping("/usr/reply/getReplies")
	@ResponseBody
	public ResultData getReplies(Integer articleId) {
		if(articleId == null) {
			return ResultData.from("F-1", "articleId를 입력해주세요");
		}
		return replyService.getReplies(rq.getLoginedMemberId(), articleId);
	}
	
	@RequestMapping("/usr/reply/doWriteReply")
	@ResponseBody
	public ResultData doWriteReply(Integer articleId, String body) {
		if(articleId == null) {
			return ResultData.from("F-1", "articleId를 입력해주세요");
		}
		
		if(Util.isEmpty(body)) {
			return ResultData.from("F-2", "body를 입력해주세요");
		}
		
		replyService.doWriteReply(rq.getLoginedMemberId(), articleId, body);
		
		return replyService.getReplies(rq.getLoginedMemberId() ,articleId);
	}
	
	@RequestMapping("/usr/reply/doDeleteReply")
	@ResponseBody
	public ResultData doDeleteReply(Integer articleId, Integer id) {
		if(articleId == null) {
			return ResultData.from("F-1", "articleId를 입력해주세요");
		}
		if(id == null) {
			return ResultData.from("F-2", "id를 입력해주세요");
		}
		replyService.doDeleteReply(rq.getLoginedMemberId(), id);
		
		return replyService.getReplies(rq.getLoginedMemberId(), articleId);
	}
	
	@RequestMapping("/usr/reply/doModifyReply")
	@ResponseBody
	public ResultData doModifyReply(Integer id, Integer articleId, String body) {
		if(id == null) {
			return ResultData.from("F-2", "id를 입력해주세요");
		}
		if(articleId == null) {
			return ResultData.from("F-1", "articleId를 입력해주세요");
		}
		
		if(Util.isEmpty(body)) {
			return ResultData.from("F-2", "body를 입력해주세요");
		}
		
		replyService.doModifyReply(rq.getLoginedMemberId(), id, articleId, body);
		
		return replyService.getReplies(rq.getLoginedMemberId(), articleId);
	}
}
;