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
	<c:if test="${board.user.id == principal.user.id}">
	<a href="/board/${board.id}/updateForm" class="btn btn-info">수정</a>
	<button id="btn-delete" class="btn btn-info">삭제</button>
	<br />
	</c:if>
	<button type="button" class="btn btn-primary mt-3" onclick="history.back()">글목록</button>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
