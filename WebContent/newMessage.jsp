<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3>新規投稿</h3>
<div class="main-contents">

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="newMessage" method="post"><br />

	件名<br />
	<textarea name="title" value="${message.title}" cols="70" rows="3" class="title-box"></textarea><br /><br />
	本文<br />
	<textarea name="text" value="${message.text}" cols="70" rows="8" class="text-box"></textarea><br /><br />

	<label for="category">カテゴリー</label>
	<input name="category" value="${message.category}" id="category"/> <br />



	<input type="submit" value="投稿" /> <br /> <br />
	<a href="./">戻る</a>

</form>
</div>
</body>
</html>