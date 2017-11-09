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
	<input type="button" id="btn" value="按钮"/>
	
	
<script>
$(document).ready(function() {
	

	$("#btn").click(function() {
		$.ajax({
			type: "POST",
			url: "/test/receiveMessage",
			data: "沙悟净:大师兄:师傅被妖怪捉走了！",
			contentType: "application/clf",
			//dataType: "text",
			success: function(data) {
				alert(data);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){  
				alert(textStatus);
			}
		});
	});
	
});
</script>	
</body>
</html>