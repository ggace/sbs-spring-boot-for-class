package com.min.sbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.ReplyDao;
import com.min.sbs.dto.Reply;
import com.min.sbs.dto.ResultData;

@Service
public class ReplyService {

	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public ResultData getReplies(int articleId) {
		
		List<Reply> replies = replyDao.getReplies(articleId);
		
		if(replies.isEmpty()) {
			return ResultData.from("F-a", "reply가 없습니다.");
		}
		
		return ResultData.from("S-1", "reply리스트입니다.", "replies", replies);
	}

	public void doWriteReply(int memberId, int articleId, String body) {
		replyDao.doWriteReply(memberId, articleId, body);
	}

}
