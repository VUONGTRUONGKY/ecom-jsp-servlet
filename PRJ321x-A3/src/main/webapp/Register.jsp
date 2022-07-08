<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register01.css" />
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/JavaScript Resources/validregister.js"></script>
</head>
<body>
	<div>
	<center>
		<form name = "regform" onsubmit="return validForm()" action="${pageContext.request.contextPath}/register">
			<div>
				<p class="text">User name:</p>
				<input type="text" name="username" class="input"/>
			</div>
			<div>
				<p class="text">Email:</p>
				<input type="text" name="email" class="input" />
			</div>
			<div>
				<p class="text">Phone:</p>
				<input type="text" name="userphone" class="input" />
			</div>
			<div>
				<p class="text">Address:</p>
				<input type="text" name="address" class="input" />
			</div>
			<div>
				<p class="text">Password:</p>
				<input type="password" name="password" class="input" />
			</div>
			<div>
				<p class="text">Confirm Password:</p>
				<input type="password" name="repassword" class="input" />
			</div>
			<input type="submit" value="Register" id="botton" />
		</form>
	</center>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
