<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cashapp</title>
</head>
<body>
	<h1 align="center">A brief web cashapp</h1>

	<p style="FONT-SIZE: 16px" align="center">
		Product information list: <br>
	</p>
	<table align="center" border="2">
		<tr>
				<td width="100">barcode</td>
				<td width="100">name</td>
				<td width="100">unit</td>
				<td width="100">category</td>
				<td width="140">subCategory</td>
				<td width="100">price</td>
		</tr>
		<c:forEach items="${productList}" var="product">
			<tr>
				<td width="100"><c:out value="${product.barcode}" /></td>
				<td width="100"><c:out value="${product.name}" /></td>
				<td width="100"><c:out value="${product.unit}" /></td>
				<td width="100"><c:out value="${product.category}" /></td>
				<td width="140"><c:out value="${product.subCategory}" /></td>
				<td width="100"><c:out value="${product.price}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<div align="center">
		<br> Discount information list: <br>
		<textarea rows="3" cols="100"> <c:out
				value="${discountList}"></c:out> </textarea>
	</div>

	<div align="center">
		<br> Input product barcode list: <br>
	<form action="/cashapp/start/inputbarcode" method="post"
		id="barcodeList">		
		<textarea name="inputbarcode" cols="100" rows="3"></textarea> <br>
			<input type="submit" value="InputBarcode">
		</form>
	</div>
	
	<div align="center">
		<br> Output product price details: <br>
		<textarea rows="6" cols="100"> <c:out value="${detail}"></c:out> </textarea>	
	</div>
	
</body>
</html>