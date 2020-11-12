<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="mb-3">
		No.<span id="id" class="mr-3"><i>${board.id} </i></span>
		<span id="username">작성자 <i>${board.user.username} </i></span>
	</div>
	
	<div class="form-group">
		<h3>${board.title}</h3>
	</div>
	<hr />
	
	<div class="form-group">
		<div>${board.content}</div>
	</div>
	<hr />
	
	<div class="card">
		<div class="card-body">
			<textarea class="form-control" rows="1" cols=""></textarea>
		</div>
		
		<div class="card-footer d-flex justify-content-end">
			<button class="btn btn btn-primary">등록</button>
		</div>
	</div>
	<br />
	
	<div class="card">
		<div class="card-header">
			댓글 리스트
		</div>
		<ul id="comment--box" class="list-group">
			<li id="comment--1" class="list-group-item d-flex justify-content-between">
			댓글댓
				<div class="d-flex">
					<div><i>From. 유저</i>&nbsp;&nbsp;</div>
					<button class="badge">삭제</button>
				</div>
			</li>
		</ul>
	</div>
	<br />
	
	<c:if test="${board.user.id == principal.user.id}">
	<a href="/board/${board.id}/updateForm" class="btn btn-info">수정</a>
	<button id="btn-delete" class="btn btn-info">삭제</button>
	<br />
	</c:if>
	<button type="button" class="btn btn-primary mt-3" onclick="history.back()">글목록</button>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
