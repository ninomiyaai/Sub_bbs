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
<script type="text/javascript">
	<!--
		function check(type){
			var input_message = document.getElementById("deleted");

			var test = "停止";

			if (type) {
				test = "復活"
			}
			if(window.confirm('アカウントを' + test + 'してもよろしいですか')){ // 確認ダイアログを表示
				return true; // 「OK」時は送信を実行
			}
			else{//「キャンセル」時の処理
				window.alert('キャンセルされました'); // 警告ダイアログを表示
				return false; // 送信を中止
			}
		}
	// -->
</script>
</head>
<body>
<h3>ユーザー管理</h3>
<div class="main-contents">

	<div class="header">
		<a href="./"></a>
		<a href="signup">ユーザー新規登録</a>
		<a href="./">戻る</a>
	</div>

	<div class="users"><br><br>
		<table rules="all" frame="box" cellpadding="10">
			<tr>
				<th>ログインID</th>
				<th>名前</th>
				<th>編集</th>
				<th>状態</th>
			</tr>
			<c:forEach items="${users}" var="users">
				<tr>
					<td><div class="login_id"><c:out value="${users.login_id}" /></div></td>
					<td><div class="name"><c:out value="${users.name}" /></div></td>
					<td><input type="submit" value="編集" onClick="location.href='userSetting?id=${users.id}'"></td>
					<form action="userControl" method="post" onSubmit="return check(${users.deleted})">
						<input type="hidden" name="user_id" value="${users.id}">
						<c:if test="${users.deleted == 0}">
							<input type="hidden" name="deleted" value="1">
							<td><input type="submit" name="deleted" value="アカウントを停止" id="deleted"></td>
						</c:if>
						<c:if test="${users.deleted == 1}">
							<input type="hidden" name="deleted" value="0">
							<td><input type="submit" name="deleted" value="アカウントを復活" id="deleted"></td>
						</c:if>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>