<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Test</title>
</head>
<body>
	<h2>Upload.jsp</h2>
	<form action="/file/upload" enctype="multipart/form-data" method="post">
		<input type="file" name="img"/> <br><br>
		<input type="submit" value="submit"/>
	</form>
</body>
</html>