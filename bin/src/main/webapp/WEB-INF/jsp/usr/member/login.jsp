<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인 페이지" />
<c:set var="isTitleCenter" value="${true }" />
<%@include file="../common/header.jspf" %>
	<section class="container mx-auto">
		<div class="mt-5">
			<form action="doLogin" method="POST" class="flex flex-col w-96 mx-auto">
				<input name="loginId" class="p-3 m-2 input input-bordered" type="text" placeholder="아이디" />
				<input name="loginPw" class="p-3 m-2 input input-bordered" type="password" placeholder="비밀번호" />
				<input class="p-3 m-2 btn btn-primary" type="submit" />
				<input class="p-3 m-2 btn btn-ghost btn-outline" type="button" value="뒤로가기"/>
			</form>
		</div>
	</section>
<%@include file="../common/foot.jspf" %>