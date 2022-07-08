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
</head>
<body>
	<%-- use header --%>
	<%@ include file="header.jsp"%>
	<c:set var="products" value="${map1['list']}" />
	<c:set var="lengthList" value="${products.size()}" />
	<c:if test="${lengthList == 0}">
		<center>
			<p style="color: red;">
				<c:out value="${error}" />
			</p>
		</center>

	</c:if>
	<c:forEach var="product" items="${products}">
		<c:set scope="page" var="imgName" value="${product.getName()}" />
		<c:set scope="page" var="price" value="${product.getPrice()}" />
		<c:set scope="page" var="desc" value="${product.getDescription()}" />
		<c:set scope="page" var="idd" value="${product.getId()}" />
		<div>
			<div class="productbox">
				<a
					href="<c:url value = '/gallery?action=image&image=${imgname}&price=${price}'/>"><img
					src="${pageContext.request.contextPath}/media/${imgName}.jpg"
					class="product_image" /> </a>
				<p>CELLPHONE</p>
				<p>
					<c:out value="${imgname}" />
				</p>
				<p class="priceText">
					<c:out value="${price}.$" />
				</p>
				<p>
					Description:
					<c:out value="${desc}" />
				</p>
				<form action="${pageContext.request.contextPath}/addtocart">
					<input type="hidden" value="add" name="action" /> <input
						type="hidden" value="checked" name="check" /> <input
						type="hidden" value="${idd}" name="id" /> <input type="submit"
						value="Add to cart" />
				</form>
			</div>
		</div>
	</c:forEach>
	<%@ include file="footer.jsp"%>
</body>
</html>