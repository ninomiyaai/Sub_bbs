<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー新規登録画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3>ユーザー新規登録</h3>

<div class="header">
	<a href="userControl">戻る</a>
</div>


<c:if test="${ not empty errorMessages }">
	<div class="errorMessages" style="color:red">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
</c:if>

<div class="main-contents">

	<form action="signup" method="post">

		<p><label for="login_id">ログインID(半角英数字6～20文字)</label><br />
		<input name="login_id" value="${signupUser.login_id}" id="login_id"/></p>

		<p><label for="password">パスワード(半角文字6～255文字)</label><br />
		<input name="password" type="password" id="password"/></p>
		<p><label for="password">パスワード(確認)</label><br />
		<input name="confirm_password" type="password" id="confirm_password"/></p>

		<p><label for="name">名前(10文字以下)</label><br />
		<input name="name" value="${signupUser.name}" id="name"/></p>

		<p>
		<label for="branch_id">支店</label><br />
		<select name="branch_id">
			<option value=""></option>
			<c:forEach items="${branches}"  var="branch">
				<c:if test="${branch.id != signupUser.branch_id}">
					<option value="${branch.id}">${branch.name}</option>
				</c:if>
				<c:if test="${branch.id == signupUser.branch_id}">
					<option value="${branch.id}"selected>${branch.name}</option>
				</c:if>
			</c:forEach>
		</select>
		</p>

		<p>
		<label for="position_id">役職</label><br />
		<select name="position_id">
			<option value=""></option>
			<c:forEach items="${positions}"  var="position">

				<c:if test="${position.id != signupUser.position_id}">
					<option value="${position.id}">${position.name}</option>
				</c:if>
				<c:if test="${position.id == signupUser.position_id}">
					<option value="${position.id}"selected>${position.name}</option>
				</c:if>

			</c:forEach>
		</select>
		</p>

		<input type="submit" value="登録" /> <br /> <br />

	</form>
</div>
</body>
</html>
