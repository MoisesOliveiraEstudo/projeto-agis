<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGIS - Alunos</title>
</head>
<body>

<style>
		body{
	padding: 2px;
	font-family: sans-serif;
	max-width: 400px;
}

form{
	display: flex;
	flex-direction: column;
	padding: 20px;
}

div{
	display: flex;
	flex-direction: column;
	padding-bottom: 20px;
}

input{
	margin-bottom: 15px;
}

a{
	text-decoration: none;
	color: black;
}

	</style>


	<nav>
		<a href="/">
			<h2>AGIS</h2>
		</a>
	</nav>
	<div>
		<h3>Lista de Alunos</h3>
	</div>
	<div>
		<c:forEach var="aluno" items="${alunos }">
			<div>
				<div>
					<label>CPF: <c:out value="${aluno.CPF }"></c:out></label>
				</div>
				<div>
					<label>RA: <c:out value="${aluno.ra }"></c:out></label>
				</div>
				
				<label>Nome: <c:out value="${aluno.nome }"></c:out></label>
			</div>
		</c:forEach>
	</div>
</body>
</html>