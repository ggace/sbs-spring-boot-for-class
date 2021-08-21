package com.min.sbs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.min.sbs.dto.Member;

@Mapper
public interface MemberDao {

	public void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
			@Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

	public int getLastId();

	public Member getMemberById(@Param("id") int id);

	public Member getMemberByLoginId(@Param("loginId") String loginId);

	public Member getMemberByNameAndEmail(String name, String email);

}
