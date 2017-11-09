<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<table>
		<tr>
			<th>JSP Page</th>
		</tr>
		<tr>
			<td>发送方：${message.from}</td>
		</tr>
		<tr>
			<td>接收方：${message.to}</td>
		</tr>
		<tr>
			<td>消息内容：${message.content}</td>
		</tr>
	</table>
</body>
</html>