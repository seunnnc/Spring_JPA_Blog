let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{	//function(){} 을 사용하지 않는 이유: this를 바인딩 하기 위해서!
			this.save();
		});
		$("#btn-update").on("click", ()=>{	//function(){} 을 사용하지 않는 이유: this를 바인딩 하기 위해서!
			this.update();
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
			url: "/auth/joinProc",
			data: JSON.stringify(data),	//http body 요청 
			contentType: "application/json; charset=utf-8"	//body data 어떤 타입인지 
			//dataType: "json"	//요청해서 서버에 응답왔을 때 json으로 오면 javascript object로 변경 
		}).done(function(resp) {
			if(resp.status === 500) {
				alert("회원가입에 실패했습니다.");
			} else {
				//요청 성공 시 실행
				alert("회원가입을 환영합니다!");
				location.href="/";
			}
		}).fail(function(error) {
			//요청 실패 시 실행
			alert(JSON.stringify(error));
		});	

	},
	
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),	
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("회원정보 수정완료!");
			location.reload();
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});	

	}
	
}

index.init();