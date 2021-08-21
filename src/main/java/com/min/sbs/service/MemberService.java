package com.min.sbs.service;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.MemberDao;
import com.min.sbs.dto.Member;
import com.min.sbs.dto.ResultData;
import com.min.sbs.util.Util;

@Service
public class MemberService {
	private MemberDao memberDao;

	public MemberService(MemberDao memberRepository) {
		this.memberDao = memberRepository;
	}

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		Member oldMember = memberDao.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return ResultData.from("F-1", Util.format("해당 아이디(%s)는 이미 사용중입니다.", loginId));
		}

		oldMember = memberDao.getMemberByNameAndEmail(name, email);

		if (oldMember != null) {
			return ResultData.from("F-2", Util.format("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email));
		}

		memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		return ResultData.from("S-1", "회원 가입되었습니다.", "id", memberDao.getLastId());
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
