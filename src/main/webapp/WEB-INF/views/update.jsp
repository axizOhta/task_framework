<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		更新を行うデータのIDを入力してください<br> <span class="required"></span>は必須です
	</p>

	<c:if test="${not empty error}">
		<div class="message">
			<p class="required">${error}</p>
		</div>
	</c:if>

	<form:form action="updateInput" modelAttribute="command" method="post">
		<div>
			<span class = "required">ID:</span>
			<form:input path="id" />
		</div>
		<div>
			<form:button>確認</form:button>
		</div>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>