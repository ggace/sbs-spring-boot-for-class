<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name } 게시물 페이지" />
<%@include file="../common/header.jspf" %>



<main>
	<section class="mt-5">
		
		<div class="mx-auto container px-3">
			게시물 개수 : ${articlesCount }개
			<div class="flex">
				<div>
					<form action="" class="flex w-full">
						
						<select id="limitSelect" class="select select-bordered m-1 max-w-xs select-sm w-24" onchange="changeLimit(value)" name="limit">
							<option disabled selected>제한 수</option>
							<option id="5" value="5">5</option>
							<option id="10" value="10">10</option>
							<option id="15" value="15">15</option>
							<option id="20" value="20">20</option>
						</select>
						
						
					</form>
				
				</div>	
				<div class="flex flex-grow"></div>
			
				<div>
					<form action="/usr/article/search">
					
						<input type="hidden" name="boardId" value="${boardId }" />
						
						<select id="limitSelect" class="select select-bordered m-1 max-w-xs select-sm w-28" name="type">
							<option id="tb" value="0">제목+내용</option>
							<option id="t" value="1">제목</option>
							<option id="b" value="2">내용</option>
						</select>
						
						<input name="str" class="input input-bordered input-sm" type="text" />
						
						
						<input type="submit" class="btn btn-outline btn-sm" value="검색" />
					</form>
					
				</div>
				
				
				
								
			</div>
			
		</div>
	</section>
	<section class="mt-3">
		<div class="container mx-auto px-3">
			
			
			<div class="mt-3">
				<table class="table table-fixed w-full">
					<colgroup>
						<col width="50"/>
						<col width="150"/>
						<col width="150"/>
						<col width="150"/>
						<col />
					</colgroup>
					
					
					<thread>
						<tr >
							<th>번호</th>
							<th>작성날짜</th>
							<th>수정날짜</th>
							<th>작성자</th>
							<th>제목</th>
						</tr>
					</thread>
					
					<tbody>
						<c:forEach var="article" items="${articles}">
							<tr>
								<td>${article.id }</td>
								<td>${article.regDateForPrint }</td>
								<td>${article.updateDateForPrint }</td>
								<td>${article.extra__writerName }</td>
								<td>
									<a class="btn-text-link block w-full truncate" href="/usr/article/detail?id=${article.id }">${article.title }</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="mt-2 flex justify-end">
				
				<a class="btn btn-link btn-sm" href="/usr/article/write">글쓰기</a>
			</div>
			
			<div class="flex justify-center btn-group page-menu">
				<c:set var="pageMemuArmLength" value="6" />
				
				<c:set var="startPage" value="${currentPage - pageMemuArmLength >= 1 ? currentPage - pageMemuArmLength : 1}" />
        		<c:set var="endPage" value="${currentPage + pageMemuArmLength <= pages ? currentPage + pageMemuArmLength : pages}" />
				<c:if test="${pages != 1 }">
					<c:if test="${startPage > 1 }">
						<a class="btn btn-xs" href="?str=${str}&type=${type}&boardId=${boardId }&page=1&limit=${limit}"><<</a>
						<a class="btn btn-xs" href=?str=${str}&type=${type}&boardId=${boardId }&page=${currentPage-1}&limit=${limit}"><</a>
					</c:if>
					
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						
						<c:if test="${i >= currentPage-pageMemuArmLength && i <= currentPage+pageMemuArmLength}">
							
							<a class='btn btn-xs ${i == currentPage ? "btn-disabled btn-active" : "" }' href="?str=${str}&type=${type}&boardId=${boardId }&page=${i}&limit=${limit}">${i }</a>
						</c:if>
						
					</c:forEach>
					
					<c:if test="${endPage < pages }">
						<a class="btn btn-xs" href="?str=${str}&type=${type}&boardId=${boardId }&page=${currentPage+1}&limit=${limit}">></a>
						<a class="btn btn-xs" href="?str=${str}&type=${type}&boardId=${boardId }&page=${pages}&limit=${limit}">>></a>
					</c:if>
				</c:if>
				
			</div>
		</div>
	</section>				
</main>
<script>
	function changeLimit(value){
		location.replace('/usr/article/list?boardId=${boardId}&page=${currentPage}&limit=' + value)
	}
</script>
	
	
<%@include file="../common/foot.jspf" %>