package com.min.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int classId;
	private String title;
	private String body;
	private int extra__sumReactionPoint;
	private int goodReactionPoint;
	private int badReactionPoint;
	
	
	private String extra__writerName;
	private boolean extra__actorCanDelete;
	private boolean extra__isLike;
	
	public String getRegDateForPrint() {
		return regDate.substring(2,16);
	}
	
	public String getUpdateDateForPrint() {
		return updateDate.substring(2,16);
	}
}
