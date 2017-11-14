<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formatter</title>
<script type="text/javascript" src="/webjarslocator/jquery/jquery.min.js"></script>
</head>
<body>
<textarea rows="8" cols="100" id="json">{"dateValue": "2017-11-13 12:00:00"}</textarea>
<br/>
<input type="button" id="sendBtn" value="发送消息"/>

	
<script>
$(function() {

	$("#sendBtn").click(function() {
		var jsonValue = $("#json").val();
		$.ajax({
			type: "POST",
			url: "/formatter/string2DateByJsonConverter",
			data: jsonValue,
			contentType: "application/json",
			success: function(data) {
				alert(data);
			},
			error: function(xhr, error) {
				alert(error);
			}
		});
		
	});
	
	
});
</script>
</body>
</html>