<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択画面</title>
</head>
<body>

	<c:if test="${empty controller}">
		<c:redirect url="index" />
	</c:if>
	<p>${controller}さん、こんにちは</p>
	<p>
		<a href="select">検索</a>
	</p>
	<p>
		<a href="insert">登録</a>
	</p>
	<p>
		<a href="update">更新</a>
	</p>
	<p>
		<a href="delete">削除</a>
	</p>
	<form action="logout" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>