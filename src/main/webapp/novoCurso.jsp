<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AGIS - Adicionar novo Aluno</title>
</head>
<body>
	<form action="/curso" method="POST">
		<label>Curso</label>
		<input type="text" name="nome">
		<input type="submit">
	</form>
	
	<div>
		<c:out value="${curso.id}" />
		<c:out value="${curso.nome }"></c:out>
	</div>
</body>
</html>