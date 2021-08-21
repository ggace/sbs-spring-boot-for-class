<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 리스트 페이지" />
<%@include file="../common/header.jspf" %>
	<section class="mt-5">
		<div class="container mx-auto px-3">
			<div>
				<form class="flex flex-col" action="/usr/article/doModify">
					<input type="hidden" name="id" value="<%=request.getParameter("id") %>"/>
					<input name="title" class="my-2 p-2 input input-bordered" type="text" placeholder="제목를 입력해주세요" value="${article.title }" />
			
					<textarea name="body" class="my-2 p-2 textarea textarea-bordered" name="" id="" cols="30" rows="10" placeholder="내용을 입력해주세요">${article.body }</textarea>
					
					<input class="my-1 p-2 btn btn-primary" type="submit" value="수정"/>
					<input class="my-1 p-2 btn btn-outline" type="reset" onclick="history.back()" value="취소"/>
				</form>
				
			</div>
			
		</div>
	</section>
<%@include file="../common/foot.jspf" %>