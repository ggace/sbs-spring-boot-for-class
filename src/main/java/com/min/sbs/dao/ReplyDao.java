package com.min.sbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.sbs.dto.Reply;

@Mapper
public interface ReplyDao {
	
	@Select("""
			<script>
				SELECT R.*, M.name AS extra__memberName 
				FROM reply AS R 
				LEFT JOIN `member` AS M
				ON R.memberId = M.id
				WHERE articleId = #{articleId}
				ORDER BY id DESC
			</script>
			""")
	List<Reply> getReplies(int articleId);
	
	
	@Insert("""
			<script>
				INSERT INTO reply
				SET regDate = NOW(),
				updateDate = NOW(),
				memberId = #{memberId},
				articleId = #{articleId},
				`body` = #{body}
			</script>
			""")
	void doWriteReply(int memberId, int articleId, String body);

}
