let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{	//function(){} 을 사용하지 않는 이유: this를 바인딩 하기 위해서!
			this.save();
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
			location.href="/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});	

	}
	
}

index.init();