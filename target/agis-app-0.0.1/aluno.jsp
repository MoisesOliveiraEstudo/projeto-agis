<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGIS - Cadastro do Aluno</title>
<link rel="stylesheet" href="/style.css">
</head>
<body>

	<nav>
		<a>
			<h2>AGIS</h2>
		</a>
	</nav>

	<div>
		<c:out value="${mensagem }" />
	</div>

	<form action="/aluno" method="POST" style="padding: 20px; ">
		<label>CPF</label>
		<input type="text" name="cpf" size="1px">
		<label>Nome</label>
		<input type="text" name="nome">
		<label>Nome social</label>
		<input type="text" name="nome_social">
		<label>Data de Nascimento</label>
		<input type="date" name="data_nascimento">
		
		<label>Curso</label>
		<select name="curso_id">
			<c:forEach var="curso" items="${cursos }">
					<option value='${curso.id }'>
						<c:out value="${curso.nome }"/>
					</option>
				</c:forEach>
		</select>
		
		<label>Telefone 1</label>
		<input type="text" name="telefone" maxlength="9">
		<label>Telefone 2</label>
		<input type="text" name="telefone" maxlenght="9">
		<label>Email Pessoal</label>
		<input type="text" name="email_pessoal">
		<label>Email Corporativo</label>
		<input type="text" name="email_corporativo">
		<label>Data de Conclusão</label>
		<input type="date" name="data_conclusao">
		<label>Instituição</label>
		<input type="text" name="instituicao">
		<label>Pontuação</label>
		<input type="number" step="any" name="pontuacao">
		<label>Posição</label>
		<input type="number" name="posicao">
		<input type="submit" name="botao" value="inserir">
	</form>
</body>
</html>