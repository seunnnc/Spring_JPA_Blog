let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{	//function(){} 을 사용하지 않는 이유: this를 바인딩 하기 위해서!
			this.save();
		});
	},
	
	save: function() {
		//alert("save 함수호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		//console.log(data);
		
		//ajax호출 시 default는 비동기 호출임 
		//ajax통신 이용 파라미터 json으로 변경하여 insert 요청 
		$.ajax({
			//회원가입 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),	//http body 요청 
			contentType: "application/json; charset=utf-8"	//body data 어떤 타입인지 
			//dataType: "json"	//요청해서 서버에 응답왔을 때 json으로 오면 javascript object로 변경 
		}).done(function(resp) {
			//요청 성공 시 실행
			alert("회원가입을 환영합니다!");
			console.log(resp);
			//location.href="/blog";
		}).fail(function(error) {
			//요청 실패 시 실행
			alert(JSON.stringify(error));
		});	
		
		
	}
}

index.init();