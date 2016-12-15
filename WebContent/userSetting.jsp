<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3>ユーザー編集</h3>
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
<form action="userSetting" method="post"><br />

	<input type="hidden" name="user_id" value="${editUser.id}">

	<label for="login_id">ログインID</label>
	<input name="login_id" value="${editUser.login_id}" id="login_id"/> <br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />
	<label for="password">パスワード(確認)</label>
	<input name="confirm_password" type="password" id="confirm_password"/> <br />

	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/> <br />

	<p>
	<label for="branch_id">支店</label>
	<select name="branch_id">
		<option value=""></option>
		<c:forEach items="${branches}"  var="branch">
			<c:if test="${branch.id != editUser.branch_id}">
				<option value="${branch.id}">${branch.name}</option>
			</c:if>
			<c:if test="${branch.id == editUser.branch_id}">
				<option value="${branch.id}"selected>${branch.name}</option>
			</c:if>
		</c:forEach>
	</select>
	</p>

	<p>
	<label for="position_id">役職</label>
	<select name="position_id">
		<option value=""></option>
		<c:forEach items="${positions}"  var="position">

			<c:if test="${position.id != editUser.position_id}">
				<option value="${position.id}">${position.name}</option>
			</c:if>
			<c:if test="${position.id == editUser.position_id}">
				<option value="${position.id}"selected>${position.name}</option>
			</c:if>

		</c:forEach>
	</select>
	</p>


	<input type="submit" value="編集" /> <br /> <br />
	<a href="userControl">戻る</a>

</form>
</div>
</body>
</html>