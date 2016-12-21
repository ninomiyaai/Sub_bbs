<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿画面</title>
</head>
<body>
<h3>新規投稿</h3>

<div class="header">
	<a href="./">戻る</a>
</div>

<div class="main-contents">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages" style="color:red">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<form action="newMessage" method="post"><br />

		<input type="hidden" name="user_id" value="${loginUser.id}">

		件名(50文字以下で入力)<br />
		<input type="text" name="title" id="title" value="${message.title}"><br /><br />
		本文(1000文字以下で入力)<br />
		<textarea name="text" id="text"cols="70" rows="8" class="text-box">${message.text}</textarea><br /><br />

		カテゴリー(10文字以下で入力)<br />
		<input name="category" value="${message.category}" id="category"/> <br /><br />

		<input type="submit" value="投稿" /> <br /> <br />

	</form>
</div>
</body>
</html>