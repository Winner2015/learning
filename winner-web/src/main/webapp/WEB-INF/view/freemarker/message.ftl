<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeMarker Page</title>
</head>
<body>
	<table>
		<tr>
			<th>FreeMarker Page</th>
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