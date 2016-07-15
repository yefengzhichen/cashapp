<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!-- 商品信息表格输出 -->
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
		<!-- 输入购买的商品条形码 -->
	<div align="center">
		<br> Input product barcode list(Example:Item0002,Item0010): <br>
	<form action="/cashapp/start/inputBarcode" method="post" id="inputBarcode">		
		<textarea name="inputBarcode" cols="100" rows="1"></textarea> <br>
			<input type="submit" value="InputBarcode">
		</form>
	</div>
	<!-- 输出商品价格详细清单 -->
	<div align="center">
		<br> Output product price details: <br>
		<textarea rows="6" cols="100"><c:out value="${datail}"></c:out> </textarea>	
	</div>	
	<!-- 输入要增加商品信息/cashapp/start/inputProduct  Item0010,apple,kg,fruit,fresh fruit,13.00-->
	<div align="center">
		<br> Input product information list(Example:Item0010,apple,kg,fruit,fresh fruit,13.00): <br>		
		<form action="/cashapp/start/inputProduct" method="post" id="inputProduct">		
		<textarea name="inputProduct" cols="100" rows="2"></textarea> <br>
			<input type="submit" value="inputProduct">
		</form>
	</div>	
	<!-- 已经保存的打折商品条形码列表 -->
	<div align="center">
		<br> Discount information list: <br>
		<textarea rows="2" cols="100"><c:out
				value="${discountList}"></c:out> </textarea>
	</div>
	<!-- 输入要增加的打折商品条形码-->
	<div align="center">
		<br> Input discount product list(Example:Item0002,Item0010): <br>		
		<form action="/cashapp/start/inputDiscount" method="post" id="inputDiscount">		
		<textarea name="inputDiscount" cols="100" rows="1"></textarea> <br>
			<input type="submit" value="InputDiscount">
		</form>
	</div>
	
</body>
</html>