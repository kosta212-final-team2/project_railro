<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>testpage</h1>
    ${pageContext.request.userPrincipal.name}님<p>
	<form action="${pageContext.request.contextPath}/logout" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
	<input type="submit" value="로그아웃" />
	</form>
</body>
</html>