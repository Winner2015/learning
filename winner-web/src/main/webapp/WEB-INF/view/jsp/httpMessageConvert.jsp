<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HttpMessageConvertTest</title>
<script type="text/javascript" src="/webjarslocator/jquery/jquery.min.js"></script>
</head>
<body>
<textarea rows="3" cols="60" id="sendMsg">沙悟净:大师兄:师傅被妖怪抓走了！</textarea>
<br/>
<input type="button" id="sendBtn" value="发送消息"/>

<hr/>

<textarea rows="3" cols="60" id="receiveMsg" disabled></textarea>
<br/>
<input type="button" id="receiveBtn" value="接收消息"/>
	
<script>
$(function() {

	$("#sendBtn").click(function() {
		var msg = $("#sendMsg").val();
		$.ajax({
			type: "POST",
			url: "/converter/sendMessage",
			data: $("#sendMsg").val(),
			contentType: "application/clf",
			dataType: "text",
			success: function(data) {
				alert(data);
			},
			error: function(xhr, error) {
				alert(error);
			}
		});
		
	});
	
	$("#receiveBtn").click(function() {
		var msg = $("#receiveMsg").val();
		$.ajax({
			type: "POST",
			url: "/converter/receiveMessage",
			success: function(data) {
				$("#receiveMsg").val(data);
			}
		});
		
	});

	
});
</script>	
</body>
</html>