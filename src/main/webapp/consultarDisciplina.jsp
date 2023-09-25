<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
	
	<form action="/matricula" method="POST">
		
		<select name="aluno_ra">
			<c:forEach var="aluno" items="${alunos }">
					<option value='${aluno.ra}'>
						<c:out value="${aluno.nome }"/>
					</option>
				</c:forEach>
		</select>
		<input type="submit" name="botao" value="Consultar">
	</form>
	<h3>Lista de Disciplina</h3>
	<c:forEach var="disciplina" items="${disciplinas }">
		<div>
			
			<div>
				<label><c:out value="${disciplina.nome }"></c:out></label>
			</div>
			<div>
				<label><c:out value="${disciplina.diaDaSemana }"></c:out></label>
			</div>
			<div>
				<label><c:out value="${disciplina.horaComeco }"></c:out>h</label>
			</div>
			
			
			
		</div>
	</c:forEach>
	
</body>
</html>