package com.min.sbs.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	int id;
	String regDate;
	String updateDate;
	String loginId;
	String loginPw;
	int authLevel;
	String name;
	String nickname;
	String cellphoneNo;
	String email;
	boolean delStatus;
	Date delDate;
}
