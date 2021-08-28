<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${article.id}번 상세 페이지" />
<%@include file="../common/header.jspf" %>
	
	<section class="mt-5">
		<div class="flex container mx-auto flex-col">
			<div class="flex div-table-type-1 flex-col">
				<div class="flex">
					<p>제목</p>
					<p class="break-all">${article.title}</p>
				</div>
				
				<div class="flex">
					<p>추천</p>
					<p class="break-all">${article.extra__goodReactionPoint}</p>
				</div>
				
				<div class="flex">
					<p>작성날짜</p>
					<p class="break-all">${article.regDateForPrint}</p>
				</div>
				
				<div class="flex">
					<p>수정날짜</p>
					<p class="break-all">${article.updateDateForPrint}</p>
				</div>
				
				<div class="flex">
					<p>작성자</p>
					<p class="break-all">${article.extra__writerName}</p>
				</div>
				<div class="flex">
					<p>내용</p>
					<p class="whitespace-pre-line break-all">${article.body}</p>
				</div>					
			</div>
			<div class="flex">
				<c:if test="${!canReact && rq.isLogined()}">
					<button class="m-1 btn btn-secondary btn-sm ${canReact ? "btn-active" : "btn-outline" } article-detail__like">👍${likeCount }</button>
				</c:if>
				
				<div class="flex-grow"></div>
				<p class="article-detail__hit-count"></p>
			</div>
			<div class="flex mt-1">
				<button class="btn btn-link btn-sm" onclick="history.back()">뒤로가기</button>
				<div class="flex flex-grow"></div>
				<c:if test="${article.extra__actorCanDelete }">
					<a class="btn btn-ghost btn-outline btn-sm m-1" href="/usr/article/modify?id=${article.id }">수정</a>
					<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) return false;" class="btn btn-secondary btn-sm m-1 " href="/usr/article/doDelete?id=${article.id }">삭제</a>
				</c:if>
			</div>
		</div>
		
	</section>
	<%String error = (String)request.getAttribute("errors"); %>
	<%if(error != null  && !error.equals("")){ %>
		<script type="text/javascript">
			alert("${errors}")
			location.replace("/usr/article/list")
		</script>
	<%} %>
<%@include file="../common/foot.jspf" %>