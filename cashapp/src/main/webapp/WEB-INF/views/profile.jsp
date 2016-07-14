<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blog for people who follows your heart</title>
</head>
<body>
	<h4 align="center">Welcome to cashapp</h4>
	<div align="center">
	Your name: <c:out value="${user.name}"></c:out> <br>
	Your description: <c:out value="${user.description}"></c:out>
	</div>
</body>
</html>