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
			<div class="flex">
				<c:if test="${rq.isLogined() }">
					<a onclick="doChangeLike('good')" class="good m-1 btn btn-primary btn-sm ${isGoodReact ? "btn-active" : "btn-outline" } article-detail__like">👍 <span class="mx-1" id="goodCount">${article.goodReactionPoint }</span></a>
					<a onclick="doChangeLike('bad')" class="bad m-1 btn btn-secondary btn-sm ${isbadReact ? "btn-active" : "btn-outline" } article-detail__like">👎 <span class="mx-1" id="badCount">${article.badReactionPoint }</span></a>
				</c:if>
				
				<div class="flex-grow"></div>
				<p class="article-detail__hit-count"></p>
			</div>
			<div class="flex mt-1">
				
				<div class="flex flex-grow"></div>
				<c:if test="${article.extra__actorCanDelete }">
					<a class="btn btn-ghost btn-outline btn-sm m-1" href="/usr/article/modify?id=${article.id }">수정</a>
					<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) return false;" class="btn btn-secondary btn-sm m-1 " href="/usr/article/doDelete?id=${article.id }">삭제</a>
				</c:if>
			</div>
			<div id="replies">
					<p>댓글 {{count}}</p>
					<div>
						<input id="replyInputFrom" type="text" class="input input-bordered input-sm"/>
					
						<button v-on:click="writeReply" type="button" class="btn btn-primary btn-sm btn-outline"/>댓글 작성</button>
					</div>
					<div v-for="reply in replies" class="px-3 flex">
						<p class="px-3">{{reply.extra__memberName}}({{reply.updateDate.substring(2,16)}})  :  {{reply.body}}</p>
						<button class="text-red-500" v-on:click="deleteReply(reply.id)" v-if="reply.extra__actorCanDelete">삭제</button>
					</div>
					
					
			</div>
			<div>
				<button class="btn btn-link btn-sm" onclick="history.back()">뒤로가기</button>
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
	
	<script defer="defer">
		
		let app = new Vue({
			el: "#replies",
			data: {
				replies : null,
				count : 0
			},
			methods: {
				deleteReply : function(id){
					let url = '/usr/reply/doDeleteReply?articleId=${article.id}&id=' + id;
					fetch(url)
					.then((response) =>{
						cloneResponse = response
						if(cloneResponse.ok){
							return cloneResponse.json();
						}
						throw new Error("Network reponse was not ok");
					})
					.then((json) =>{
						this.replies = json.data1;
						this.count = json.data2;
					})
					.catch((error) =>{
						console.log(error);
					});
				},
				writeReply : function(){
					let url = '/usr/reply/doWriteReply?articleId=${article.id}&body=' + $("#replyInputFrom").val()
					fetch(url)
					.then((response) =>{
						cloneResponse = response
						if(cloneResponse.ok){
							return cloneResponse.json();
						}
						throw new Error("Network reponse was not ok");
					})
					.then((json) =>{
						this.replies = json.data1;
						this.count = json.data2;
					})
					.catch((error) =>{
						console.log(error);
					});
					$("#replyInputFrom").val("")
				}
			},
			created() {
				fetch('/usr/reply/getReplies?articleId=${article.id}')
				.then((response) =>{
					cloneResponse = response
					if(cloneResponse.ok){
						return cloneResponse.json();
					}
					throw new Error("Network reponse was not ok");
				})
				.then((json) =>{
					this.replies = json.data1;
					this.count = json.data2;
				})
				.catch((error) =>{
					console.log(error);
				});
			}
			
		})
	</script>
	
	<script type="text/javascript">
		var isGoodReact = ${isGoodReact ? isGoodReact: false};
		var isBadReact = ${isbadReact ? isbadReact : false};
		
		var doChangeLike =  function (from){
			
			if(from == "good"){
				if(isGoodReact){
					doAjaxToChangeLike(getParam("id"), "cancel", "article");
				}
				else{
					doAjaxToChangeLike(getParam("id"), "like", "article");
				}
			}
			else if(from == "bad"){
				if(isBadReact){
					doAjaxToChangeLike(getParam("id"), "cancel", "article");
				}
				else{
					doAjaxToChangeLike(getParam("id"), "hate", "article");
				}
			}
			
		}
		
		var doAjaxToChangeLike = function doAjaxToChangeLike(id, type, relTypeCode){
			$.get("/usr/reaction/doChangeLike",{
		      	id: id,
		      	type: type, 
		      	relTypeCode : relTypeCode,
			    ajaxMode: "Y"
			}, function(data){
				if(data.data1 == 1){
			    	$(".good").removeClass("btn-outline")
			    	$(".bad").removeClass("btn-active")
			    	$(".good").addClass("btn-active")
			    	$(".bad").addClass("btn-outline")
			    	isGoodReact = true;
			    	isBadReact = false;
			    }
			    else if(data.data1 == -1){
			    	$(".good").removeClass("btn-active")
			    	$(".bad").removeClass("btn-outline")
			    	$(".good").addClass("btn-outline")
			    	$(".bad").addClass("btn-active")
			    	isGoodReact = false;
			    	isBadReact = true;
			    }
			    else{
			    	$(".good").removeClass("btn-active")
			    	$(".bad").removeClass("btn-active")
			    	$(".good").addClass("btn-outline")
			    	$(".bad").addClass("btn-outline")
			    	isGoodReact = false;
			    	isBadReact = false;
			    }
				
				getReact();
			    
			}, 'json');
		}
		
		function getReact(){
			$.get("/usr/article/getArticle", {
				id: getParam("id"),
			    ajaxMode: "Y"
			}, function(data){
				$("#goodCount").text(data.data1.goodReactionPoint);
				$("#badCount").text(data.data1.badReactionPoint);
			})
		}
		
		
	</script>
	
	<script>
	window.onpageshow = function(event) {
		if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
			location.reload();
		}
	}
	</script>
<%@include file="../common/foot.jspf" %>