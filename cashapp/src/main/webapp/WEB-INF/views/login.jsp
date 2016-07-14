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
	<h1 align="center">Login</h1>
	<div align="center">
	<form method="post">   
	<!-- default :action="/cashapp/user/login" -->
		UserName: <input type="text" name="name" /> <br/>
		PassWord: <input type="password" name="password" /> <br/>
		<input type="submit" value="Register">
	</form>
	</div>
</body>
</html>