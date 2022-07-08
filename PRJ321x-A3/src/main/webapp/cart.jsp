<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/cart.css" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<c:set var="cart" value="${sessionScope.cart}" />
	<c:set var="listProduct" value="${cart.getItems()}" />
	<c:set var="user" value="${sessionScope.user}" />
	<c:set var = "pay" value = "${sessionScope.pay}"/>
	<table>
		<tr>
			<td class="numOfProduct"><strong>Product in cart:
					number of product</strong></td>
			<td class="price"><strong>Price</strong></td>
			<td class="quantity"><strong>Quantity</strong></td>
			<td class="amount"><strong>Amount</strong></td>
		</tr>

		<c:if test="${cart != null && !pay.equalsIgnoreCase('on')}">
			<c:forEach var="product" items="${listProduct}">

				<tr>
					<td class="numOfProduct"><c:out value="${product.getName()}" />
						<br />ID<c:out value="${product.getId()}" /></td>
					<td class="price"><c:out value="${product.getPrice()}" /></td>
					<td class="quantity"><c:out value="${product.getNumber()}" /></td>
					<td class="amount"><c:out
							value="${product.getPrice()}" /></td>


					<td class="addorless">
						<form action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" value="${product.getId()}" name="id" /> <input
								type="hidden" value="addproduct" name="action" /> <input
								type="submit" value="+" />
						</form>
						<form action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" value="${product.getId()}" name="id" /> <input
								type="hidden" value="lessproduct" name="action" /> <input
								type="submit" value="-" />
						</form>

					</td>

					<td class="delete">
						<form action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" value="${product.getId()}" name="id" /> <input
								type="hidden" value="delete" name="action" /> <input
								type="submit" value="delete" />
						</form>
					</td>
				</tr>
			</c:forEach>
			<table>
				<tr>
					<td>TOTAL</td>
					<td><c:out value="${cart.getAmount()}" /></td>
				</tr>
			</table>


		</c:if>

		<%-- display data cart of user from cookie --%>
		<c:if test="${user != null && cart == null && !pay.equalsIgnoreCase('on')}">
			<c:set var="amount" value="${sessionScope.tempAmount}" />
			<c:set var="listItem" value="${sessionScope.listItem}" />
			<c:forEach var="product" items="${listItem}">
				<tr>
					<td class="numOfProduct"><c:out value="${product.getName()}" /></td>
					<td class="price"><c:out value="${product.getPrice()}" /></td>
					<td class="quantity"><c:out value="${product.getNumber()}" /></td>
					<td class="amount"><c:out
							value="${product.getPrice()}" /></td>


					<td class="addorless">
						<form action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" value="${product.getId()}" name="id" /> <input
								type="hidden" value="addproduct" name="action" /> <input
								type="hidden" value="add" /> <input type="submit" value="+" />
						</form>
						<form action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" value="${product.getId()}" name="id" /> <input
								type="hidden" value="lessproduct" name="action" /> <input
								type="hidden" value="less" /> <input type="submit" value="-" />
						</form>

					</td>

					<td class="delete">
						<form action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" value="${product.getId()}" name="id" /> <input
								type="hidden" value="delete" name="action" /> <input
								type="submit" value="delete" />
						</form>
					</td>
				</tr>
			</c:forEach>
			<table>
				<tr>
					<td>TOTAL</td>
					<td><c:out value="${amount}" /></td>
				</tr>
			</table>

		</c:if>



	</table>
	<div>
		<form action="${pageContext.request.contextPath}/pay">
			<input type="hidden" value="on" name="pay" />
			<div>
				<label>Customer Name:</label> <input class="input" type="text"
					name="name" />
			</div>
			<div>
				<label>Customer Address:</label> <input class="input" type="text"
					name="address" />
			</div>
			<div>
				<label>Discount Code:</label> <input class="input" type="text"
					name="discount" />
			</div>
			<input type="submit" value="Submit" id="button" />
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>