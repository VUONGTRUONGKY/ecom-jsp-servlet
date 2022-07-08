<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="index.css"></link>
</head>
<body>
	<%
		String username = "";
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cook: cookies) {
				if(cook.getName().equals("cookUname")) {
					username = cook.getValue();
				}
			}
		}
	%>

	<div id="contener" style="width: 100%">
		<p id = "wellcome">WELL COME TO: <%= username  %></p>
		<div id="navibar">
			<p class = "textlink"><a href = "#" style = "text-decoration: none; color: white">DASHBOARD</a></p>
			<p class = "textlink"><a href = "#" style = "text-decoration: none; color: white">MANAGER</a></p>
		</div>
		<div id="content">
			<img id="image" src="admin.jpg" alt="banner" />
		</div><br/>
	<p id = "logout"><a  style = "text-decoration: none; color: white" href="<%=request.getContextPath()%>/callme">LOGOUT</a></p>
	</div>

</body>
</html>