package com.min.sbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.ResultData;
import com.min.sbs.dto.Rq;
import com.min.sbs.service.ReactionPointService;

@Controller
public class ReactionPointController {
	private ReactionPointService reactionPointService;
	
	private Rq rq;
	
	public ReactionPointController(ReactionPointService reactionPointService, Rq rq) {
		this.reactionPointService = reactionPointService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reaction/doChangeLike")
	@ResponseBody
	public ResultData doChageLike(int id, String type) {
		
		int sumReactionPointByMemberId = reactionPointService.getSumReactionPointByMemberId(id, rq.getLoginedMemberId());
		if(sumReactionPointByMemberId == 0) {
			if(type.equals("hate")) {
				reactionPointService.doAddArticleDisLike(id, rq.getLoginedMemberId());
			}
			else if(type.equals("like")) {
				reactionPointService.doAddArticleLike(id, rq.getLoginedMemberId());
			}
			else {
				reactionPointService.doCancelArticleLike(id, rq.getLoginedMemberId());
			}
		}
		else {
			if(type.equals("hate")) {
				reactionPointService.doUpdateArticleDisLike(id, rq.getLoginedMemberId());
			}
			else if(type.equals("like")) {
				reactionPointService.doUpdateArticleLike(id, rq.getLoginedMemberId());
			}
			else {
				reactionPointService.doCancelArticleLike(id, rq.getLoginedMemberId());
			}
		}
		reactionPointService.updateArticleReactionPoint();
		
		sumReactionPointByMemberId = reactionPointService.getSumReactionPointByMemberId(id, rq.getLoginedMemberId());
		
		return ResultData.from("S-1", "좋아요 싫어요 변경됨", "sumReactionPointByMemberId", sumReactionPointByMemberId);
	}
}
