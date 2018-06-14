<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty error}">
		<div class="message">
			<p class="required">${error}</p>
		</div>
	</c:if>
	<form:form action="login" modelAttribute="login" method = "post">
		<div>
			ID
			<form:input path="id" />
			<form:errors path="id" cssStyle="color: red" />
		</div>
		<div>
			password
			<form:password path="pass" />
			<form:errors path="pass" cssStyle="color: red" />
		</div>
		<div>
			<form:button>送信</form:button>
		</div>
	</form:form>
	<div>
		<a href="index">TOP画面へ</a>
	</div>
</body>
</html>