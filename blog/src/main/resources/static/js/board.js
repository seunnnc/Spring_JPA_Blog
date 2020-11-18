let index = {
	init: function() {
		$("#btn-save").on("click", () => {	//function(){} 을 사용하지 않는 이유: this를 바인딩 하기 위해서!
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			//글쓰기 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),	//http body 요청 
			contentType: "application/json; charset=utf-8",	//body data 어떤 타입인지 
			dataType: "json"	//요청해서 서버에 응답왔을 때 json으로 오면 javascript object로 변경 
		}).done(function(resp) {
			alert("글쓰기 완료!");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},


	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제 완료!");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},


	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수정 완료!");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
		
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("댓글등록 완료!");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}

}

index.init();