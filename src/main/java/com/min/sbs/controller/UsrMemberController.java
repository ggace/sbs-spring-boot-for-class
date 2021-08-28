package com.min.sbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.Member;
import com.min.sbs.dto.ResultData;
import com.min.sbs.dto.Rq;
import com.min.sbs.service.MemberService;
import com.min.sbs.util.Util;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	private Rq rq;

	public UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}

	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Util.isEmpty(loginId)) {
			return ResultData.from("F-A", "loginId를 입력해주세요");
		}
		if (Util.isEmpty(loginPw)) {
			return ResultData.from("F-B", "loginPw를 입력해주세요");
		}
		if (Util.isEmpty(name)) {
			return ResultData.from("F-C", "name를 입력해주세요");
		}
		if (Util.isEmpty(nickname)) {
			return ResultData.from("F-D", "nickname를 입력해주세요");
		}
		if (Util.isEmpty(cellphoneNo)) {
			return ResultData.from("F-E", "cellphoneNo를 입력해주세요");
		}
		if (Util.isEmpty(email)) {
			return ResultData.from("F-F", "email를 입력해주세요");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		int id = joinRd.getData1();

		Member member = memberService.getMemberById(id);

		return ResultData.newData(joinRd, "member", member);
	}

	@RequestMapping("/usr/member/showLogin")
	public String showLogin() {
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		

		if (rq.isLogined()) {
			return Util.jsHistoryBack("이미 로그인 상태입니다.");
		}

		if (Util.isEmpty(loginId)) {
			return Util.jsHistoryBack("loginId를 입력해주세요");
		}
		if (Util.isEmpty(loginPw)) {
			return Util.jsHistoryBack("loginPw를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Util.jsHistoryBack("해당 회원이 존재하지 않습니다.");
		}
		if (!(member.getLoginPw().equals(loginPw))) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		rq.login(member);
		return Util.jsReplace(Util.format("%s님 환영합니다.", loginId), "/usr/home/main");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		
		
		if (!rq.isLogined()) {
			return Util.jsHistoryBack("현재 로그인 되어 있지 않습니다.");
		}
		rq.logout();
		return Util.jsReplace("로그아웃되었습니다.", "/usr/home/main");
	}
}
