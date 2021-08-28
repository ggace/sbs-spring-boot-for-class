package com.min.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
	int id;
	private int memberId;
	private int relId;
	private String relTypeCode;
	private int point;
	private String regDate;
	private String updateDate;
}
