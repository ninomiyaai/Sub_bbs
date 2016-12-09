<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3>ユーザー管理</h3>
<div class="main-contents">

	<div class="header">
		<a href="./"></a>
		<a href="signup">ユーザー新規登録</a>
		<a href="./">戻る</a>
	</div>

	<div class="users">
		<c:forEach items="${users}" var="users">
			<hr>
			<div class="login_id"><c:out value="${users.login_id}" /></div>
			<div class="name"><c:out value="${users.name}" /></div>

			<input type="submit" value="編集" onClick="location.href='userSetting?id=${users.id}'">

			<c:if test="${users.deleted == true}">
				<input type="submit" value="停止" onClick="location.href='userControl'">
			</c:if>
			<c:if test="${users.deleted == false}">
				<input type="submit" value="復活" onClick="location.href='userControl'">
			</c:if>


			<form action="userControl" method="post">
			<INPUT type="submit" name="" value="停止" onclick="location.href='userControl'">
			</form>

		</c:forEach>
	</div>
</div>
</body>
</html>