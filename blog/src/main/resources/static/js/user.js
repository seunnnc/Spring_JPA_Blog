let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save: function() {
		//alert("save 함수호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}
		
		//console.log(data);
		$.ajax().done().fail();	//ajax통신 이용 파라미터 json으로 변경하여 insert 요청 
	}
}

index.init();