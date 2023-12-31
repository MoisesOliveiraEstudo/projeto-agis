<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGIS - Operacoes com alunos</title>
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
	
	<form action="/aluno" method="POST">
		<label>Aluno</label>
		<select name="ra">
			<c:forEach var="aluno" items="${alunos }">
					<option value='${aluno.ra}'>
						<c:out value="${aluno.nome }"/>
					</option>
				</c:forEach>
		</select>
		
		<label>Telefone 1</label>
		<input type="text" name="telefone">
		<label>Telefone 2</label>
		<input type="text" name="telefone">
		<label>Email Pessoal</label>
		<input type="text" name="emailPessoal">
		<label>Email Corporativo</label>
		<input type="text" name="emailCorporativo">
		<input type="submit" name="botao" value="Atualizar">
		<input type="submit" name="botao" value="Deletar">
	</form>
</body>
</html>