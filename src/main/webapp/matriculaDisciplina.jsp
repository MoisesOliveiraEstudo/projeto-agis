<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGIS - Matricular Disciplinas</title>
</head>
<body>
	<nav>
		<h2>AGIS</h2>
	</nav>

	<label>Selecione o aluno para visualizar as mat�rias:</label>
	<form action="/matricula" method="POST">
		
		<select name="aluno">
			<c:forEach var="aluno" items="${alunos }">
					<option value='${aluno.ra}'>
						<c:out value="${aluno.nome }"/>
					</option>
				</c:forEach>
		</select>
		<input type="submit" name="botao" value="Pesquisar">
	</form>
	
	<form action="matricula" method="POST">
			<label>Disciplinas</label>
			<c:forEach var="disciplina" items="${disciplinas }">
				<div style="display: flex; justify-content: space-between; max-width: 500px;">
					<div style="display:flex; flex-direction: column">
						<label><c:out value="${disciplina.nome }"></c:out></label>
						<label><c:out value="${disciplina.horaComeco }"></c:out>h</label>
						<label><c:out value="${disciplina.diaDaSemana }"></c:out></label>
					</div>
					<input type="text" name="aluno_ra" value="${aluno.ra }" style="visibility: hidden">
					<input type="checkbox" name="disciplina" value="${disciplina.id }">	
				</div>
				
			</c:forEach>
			<input type="submit" name="botao">
	</form>
	
	
	
</body>
</html>