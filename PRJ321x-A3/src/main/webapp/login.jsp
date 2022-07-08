<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/JavaScript Resources/validlogin.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login01.css" />
</head>
<body>

	<%
	String username = "", pass = "", rememberme = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cook : cookies) {

			if (cook.getName().equals("cookRemember")) {

		rememberme = cook.getValue();

			} else if (cook.getName().equals("cookPass")) {

		pass = cook.getValue();

			} else if (cook.getName().equals("cookUname")) {
		username = cook.getValue();

			}
		}

		if (!rememberme.equals("1")) {

			username = "";
			pass = "";
		}

	}
	%>
	<center>
		<div id="container">
			<div id="form">
				<h2>Sign in</h2>
				<form name="myForm" onsubmit="return validateForm()" method="get">

					<input type="text" class="fname" name="fname" value="<%=username%>"></input><br />
					<input type="password" class="fname" name="fpass" value="<%=pass%>"></input>
					<br />

					<div>
						<input type="checkbox" id="check" name="remember" value="1"
							<%="1".equals("rememberme") ? "checked = 'checked'" : ""%>></input>
						<p style="display: inline">Remember me</p>
						<a href="#"> Forgot password?</a>
					</div>
					<br />
					<div>
						<input type="submit" value="Login" id="sub" /> <br />
					</div>
				</form>
				<p style="color: red"><%=session.getAttribute("error") == null ? "" : session.getAttribute("error")%></p>
			</div>
			<div id="wellcome">
				<h2 class="text">Wellcome Back!</h2>
				<p class="text">To keep connected with us</p>
				<p class="text">Please login with your personal</p>
				<p class="text">info</p>

			</div>

		</div>
	</center>

</body>
</html>