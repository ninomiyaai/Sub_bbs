<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板ホーム画面</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3>掲示板</h3>
<div class="main-contents">

<div class="header">
	<a href="newMessage">投稿する</a>
	<a href="">ユーザー管理</a>
	<a href="login">ログアウト</a>
</div>

errer


<div class="messages">
	<c:forEach items="${messages}" var="message">
				<div class="title"><c:out value="${message.title}" /></div>
				<div class="text"><c:out value="${message.text}" /></div>
				<div class="name"><c:out value="${message.name}" /></div>
				<div class="category"><c:out value="${message.category}" /></div>
				<div class="created_at"><fmt:formatDate value="${message.created_at}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
				<h5>-----------------------</h5>
			</div>
	</c:forEach>
</div>

<div class="comment">
	<form action="comment" method="post">
		<p>
		<input type="hidden" name="userid" value="12345">
		<input type="hidden" name="hyouka" value="good">
		<input type="hidden" name="riyu" value="1">
		<input type="submit" value="上記内容で送信する">
		</p>
		submit
	</form>
</div>

</body>
</html>