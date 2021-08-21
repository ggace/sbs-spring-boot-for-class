<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 작성 페이지" />
<%@include file="../common/header.jspf" %>
	<section class="mt-5">
		<div class="container mx-auto px-3">
			<div>
				<form class="flex flex-col" action="/usr/article/doAdd">
					<div class="flex">
						<span class="flex items-center">
							게시판 :
						</span>
						 
						<select name="boardId" id="" class="select select-bordered select-sm flex-grow m-3">
							<option disabled selected>게시판을 선택해주세요</option>
							<option value="1">공지</option>
							<option value="2">자유</option>
						</select>
					</div>
					
					<input required="required" name="title" class="my-2 p-2 input input-bordered" type="text" placeholder="제목를 입력해주세요" />
			
					<textarea required="name=" name="body" class="my-2 p-2 textarea textarea-bordered" name="" id="" cols="30" rows="10" placeholder="내용을 입력해주세요"></textarea>
					
					<input class="my-1 p-2 btn btn-primary" type="submit" value="글쓰기"/>
					<input class="my-1 p-2 btn btn-outline" type="reset" onclick="history.back()" value="취소"/>
				</form>
				
			</div>
			
		</div>
	</section>
<%@include file="../common/foot.jspf" %>