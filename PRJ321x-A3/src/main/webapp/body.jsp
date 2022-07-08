<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>


<body>
	<%-- DECLARE CLASS --%>
	<jsp:useBean id="data" class="bean.Body" scope="page" />


	<div class="row">
		<div class="leftcolumn">
			<div class="card">
				<div class="fakeimg" style="background: white;">
					<c:set scope="page" var="count" value="${0}" />
					<c:forEach var="image" items="${data.product_name}">
						<c:set scope="page" var="imgname" value="${image}"></c:set>
						<c:set scope="page" var="price"
							value="${data.product_price.get(count)}" />
						<%-- <c:set scope = "page" var = "desc" value = "${data.product_des.get(count)}"/>--%>
						<div class="productbox">
							<a
								href="<c:url value = '/gallery?action=image&image=${imgname}&price=${price}'/>"><img
								src="${pageContext.request.contextPath}/media/${imgname}.jpg"
								class="product_image" /></a>
							<p>CELLPHONE</p>
							<p>
								<c:out value="${imgname}" />
							</p>
							<p class="priceText">
								<c:out value="${price}.$" />
							</p>

							<c:set scope="page" var="count" value="${count + 1}" />
						</div>
					</c:forEach>
				</div>

			</div>

			<div>
				<div class="fakeimg"><img id = "bottomBanner" src="${pageContext.request.contextPath}/media/banner.jpg" alt="banner promotion" /></div>
			</div>
		</div>

		<div class="rightcolumn">
			<div class="card">
				<div class="fakeimg">
					<c:set var="cart" value="${sessionScope.cart}" />
					<c:set var="listProduct" value="${cart.getItems()}" />
					<c:set var="user" value="${sessionScope.user}" />
					<c:set var="pay" value="${sessionScope.pay}" />
					<table class="styled-table">
						<thead>
							<tr>
								<td class="texthead">Name product</td>
								<td class="texthead">Amount</td>
								<td class="texthead">Price</td>
							</tr>
						</thead>
						<c:if test="${cart != null && !pay.equalsIgnoreCase('on')}">
							<tbody>
								<c:forEach var="product" items="${listProduct}">
									<tr>
										<td class="nameCartHome"><c:out
												value="${product.getName()}" /></td>
										<td class="numberCartHome"><c:out
												value="${product.getNumber()}" /></td>
										<td class="priceCartHome"><c:out
												value="${product.getPrice()}.$" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>

						<c:if test="${user != null && cart == null && !pay.equalsIgnoreCase('on')}">
							<c:set var="amount" value="${sessionScope.tempAmount}" />
							<c:set var="listItem" value="${sessionScope.listItem}" />
							<tbody>
								<c:forEach var="product" items="${listItem}">
									<tr>
										<td class="nameCartHome"><c:out
												value="${product.getName()}" /></td>
										<td class="numberCartHome"><c:out
												value="${product.getNumber()}" /></td>
										<td class="priceCartHome"><c:out
												value="${product.getPrice()}.$" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
					</table>

				</div>
			</div>

			<div id="rimg">
				<div class="bannerRight"><img class = "imgRight" src="${pageContext.request.contextPath}/media/banner03.jpg" alt="banner promotion" /></div>
				<div class="bannerRight"><img class = "imgRight" src="${pageContext.request.contextPath}/media/bannerIp13.jpg" alt="banner promotion" /></div>
				<div class="bannerRight"></div>
			</div>
		</div>
	</div>
</body>
</html>