$(function(){
	$("#subm").on("click",function(){
		var lu = $("#longUrl").val().trim();
		if (lu !=null &&lu !="") {
			$.post("/saveSU",{"longUrl":lu},function(data){
				console.log(data)
				window.location.href="/html/success.html?shortUrl="+data.data.shortUrl;
			})
		}
	})
	$("#dataSearch").on("click",function(){
		window.location.href="shortUrl.html"
	})
	
})