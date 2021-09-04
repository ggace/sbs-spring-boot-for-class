package com.min.sbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.ReplyDao;
import com.min.sbs.dto.Article;
import com.min.sbs.dto.Reply;
import com.min.sbs.dto.ResultData;

@Service
public class ReplyService {

	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public ResultData getReplies(int memberId, int articleId) {
		
		List<Reply> replies = replyDao.getReplies(articleId);
		
		if(replies.isEmpty()) {
			return ResultData.from("F-a", "reply가 없습니다.");
		}
		
		for(Reply reply : replies) {
			updateForPrintData(memberId, reply);
		}
		
		return ResultData.from("S-1", "reply리스트입니다.", "replies", replies);
	}
	
	private void updateForPrintData(int actorId, Reply reply) {
		if(reply == null) {
			return;
		}
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId, reply);
		reply.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
	}
	
	public ResultData actorCanDelete(int actorId, Reply reply) {
		if ( reply == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if ( reply.getMemberId() != actorId && actorId != 1) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
	}

	public void doWriteReply(int memberId, int articleId, String body) {
		replyDao.doWriteReply(memberId, articleId, body);
	}
	
	public void doDeleteReply(int memberId, Integer id) {
		// TODO Auto-generated method stub
		replyDao.doDeleteReply(memberId, id);
	}

}
