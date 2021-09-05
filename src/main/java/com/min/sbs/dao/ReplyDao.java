package com.min.sbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.min.sbs.dto.Reply;

@Mapper
public interface ReplyDao {
	
	@Select("""
			<script>
				SELECT R.*, M.name AS extra__memberName 
				FROM reply AS R 
				LEFT JOIN `member` AS M
				ON R.memberId = M.id
				WHERE relTypeCode='article' 
				AND articleId = #{articleId}
				ORDER BY id DESC
			</script>
			""")
	List<Reply> getReplies(int articleId);
	
	
	@Insert("""
			<script>
				INSERT INTO reply
				SET regDate = NOW(),
				relTypeCode = 'article',
				updateDate = NOW(),
				memberId = #{memberId},
				articleId = #{articleId},
				`body` = #{body}
			</script>
			""")
	void doWriteReply(int memberId, int articleId, String body);

	@Delete("""
			<script>
				DELETE FROM reply
				WHERE relTypeCode = 'article'
				AND memberId = #{memberId}
				AND id = #{id}
			</script>
			""")
	void doDeleteReply(int memberId, Integer id);

	@Select("""
			SELECT IFNULL(COUNT(*), 0)
			FROM reply
			WHERE relTypeCode = 'article' 
			AND articleId = #{articleId}
			""")
	Object getCountOfReplies(int articleId);

	@Update("""
			<script>
				UPDATE reply
				SET updateDate = NOW(),
				`body` = #{body}
				WHERE memberId = #{memberId}
				AND articleId = #{articleId}
				AND id=#{id}
			</script>
			""")
	void doModifyReply(int memberId, int id, int articleId, String body);

	@Select("""
			<script>
				SELECT R.*, M.name AS extra__memberName 
				FROM reply AS R 
				LEFT JOIN `member` AS M
				ON R.memberId = M.id
				WHERE relTypeCode='article' 
				AND R.id=#{id}
				ORDER BY id DESC
			</script>
			""")
	Reply getReply(int id);

}
