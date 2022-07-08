<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/infoproduct.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<jsp:useBean id="bd" class="bean.Body" />
	<c:set var="listProduct" value="${bd.getProduct()}" />
	<c:set scope="page" var="imageName" value="${param.image}" />
	<c:set scope="page" var="price" value="${param.price}" />
	<%@ include file="header.jsp"%>
	<div class="container">
		<p class="imgName">
			<c:out value="${imageName}" />
		</p>
		<hr />
		<img src="${pageContext.request.contextPath}/media/${imageName}.jpg"
			class="img" />
		<p class="price">
			<c:out value="$.${price}" />
		</p>
		<c:forEach var="product" items="${listProduct}">
			<c:if test="${product.getName().equalsIgnoreCase(imageName)}">
				<p class="desc">
					<c:out value="${product.getDescription()}" />
				</p>
				<c:set var="idd" value="${product.getId()}" />
			</c:if>
		</c:forEach>

		<form action="${pageContext.request.contextPath}/addtocart">
			<input type = "hidden" value = "off" name = "pay"/>
			<input type="hidden" value="add" name="action" /> <input
				type="hidden" value="checked" name="check" /> <input type="hidden"
				value="${idd}" name="id" /> <input class="button"
				style="background-color: orange; color: white; border: 1px red solid; border-radius: 30px; width: 26%;"
				type="submit" value="Add to cart" />
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>