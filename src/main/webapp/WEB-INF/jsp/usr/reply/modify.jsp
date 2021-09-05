<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="댓글 수정 페이지" />
<%@include file="../common/header.jspf" %>

<script>
  let ReplyModify__submitDone = false;
  function ReplyModify__submit(form) {
    if ( ReplyModify__submitDone ) {
      return;
    }
    
    form.body.value = form.body.value.trim();
    
    if ( form.body.value.length == 0 ) {
      alert('내용을 입력해주세요.')
      form.body.focus();
      
      return;
    }
    
    ArticleModify__submitDone = true;
    form.submit();
  }
</script>
	<section class="mt-5">
		<div class="container mx-auto px-3">
			<div>
				<div class="flex div-table-type-1 flex-col">
				<div class="flex">
					<p>제목</p>
					<p class="break-all">${article.title}</p>
				</div>
				
				<div class="flex">
					<p>추천</p>
					<p class="break-all">${article.goodReactionPoint}</p>
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
				<form class="flex flex-col mt-3" action="/usr/reply/doModifyReplyForWeb">
					
					<div class="flex">
						<span class="flex items-center">
							댓글 <span class="badge badge-primary">${reply.id }</span> : 
						</span>
					</div>
					<input type="hidden" name="articleId" value="${reply.articleId }"/>
					<input type="hidden" name="id" value="${reply.id }"/>
					<div class="flex">
						<input required="required" name="body" class="my-1 input input-bordered input-sm" type="text" placeholder="댓글을 입력해주세요" value="${reply.body }"/>
						<input class="my-1 btn btn-primary  btn-sm" type="submit" value="완료" onsubmit="ReplyModify__submit(this)"/>
						<input class="my-1 btn btn-outline btn-sm" type="reset" onclick="history.back()" value="취소"/>
					</div>
					
				</form>
				
			</div>
			
		</div>
	</section>
	
	<script>
	window.onpageshow = function(event) {
		if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
			history.back();
		}
	}
	</script>
<%@include file="../common/foot.jspf" %>