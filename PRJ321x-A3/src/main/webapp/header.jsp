<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
</head>
<body>
	<c:if test  ="${sessionScope.user != null}">
		<strong id="wellcomeContent">Wellcome ${sessionScope.user}</strong>
	</c:if>
	<div id="headInfo">
		<div id="header">
			<img src="${pageContext.request.contextPath}/media/logo.jpg"
				alt="lolgo" id="logo" />
		</div>
		<div id="imageHead">
			<img id="headBanner"
				src="${pageContext.request.contextPath}/media/banner01.jpg"
				alt="iphone promotion" />
		</div>
	</div>
	<div class="topnav">
		<a href="<%=request.getContextPath()%>/home.jsp">Home</a>
		<form name="searchForm" action="<%=request.getContextPath()%>/search">
			<input type="hidden" value="on" name="check" /> <input
				id="searchValue" name="search" type="text"
				placeholder="What are you looking for?" /> <input id="searchButton"
				type="submit" value="Search" />
		</form>
		<a href="<%=request.getContextPath()%>/callme">Login</a> <a
			href="${pageContext.request.contextPath}/register">Register</a> <a
			id="enterCart" href="${pageContext.request.contextPath}/addtocart">Enter
			Cart</a>
	</div>
</body>
</html>