<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema AGIS</title>
</head>
<body>

	<style>
		body{
			font-family: sans-serif;
		}
		.opcao{
			padding: 10px;
		}
	</style>


	<nav>
		<h2>AGIS</h2>
	</nav>

	<h3>Opções</h3>
	<div class="opcao">
		<a href="/aluno?opcao=inserir"><h3>Novo Aluno</h3></a>
	</div>
	
	<div class="opcao">
		<a href="/aluno?opcao=listar"><h3>Listar Alunos</h3></a>
	</div>
	<div class="opcao">
		<a href="aluno?opcao=atualizar"><h3>Atualizar/Deletar Aluno</h3></a>
	</div>
	<div class="opcao">
		<a href="/matricula?opcao=consultar"><h3>Consultar Disciplinas</h3></a>
	</div>
</body>
</html>