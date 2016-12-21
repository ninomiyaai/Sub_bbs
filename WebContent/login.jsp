<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン画面</title>
</head>
<body>
<h3>ログイン</h3>


<c:if test="${ not empty errorMessages }">
	<div class="errorMessages" style="color:red">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div class="main-contents">
	<form action="login" method="post">
		<p><label for="login_id">ログインID</label><br />
		<input name="login_id" value="${login_id}" id="login_id"/></p>
		<p><label for="password">パスワード</label><br />
		<input name="password" type="password" id="password"/></p>
		<input type="submit" value="ログイン" /> <br />
	</form>
</div>
</body>
</html>
