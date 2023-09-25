<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGIS - Nova Disciplina</title>
</head>
<body>
	<div>
		<c:out value="${erro}" />
	</div>

	<form action="/disciplina" method="POST">
		<div>
			<label>Curso</label>
			<select>
				<c:if test="${not empty curso}">
					<option><c:out value="$"></c:out></option>
				</c:if>
				<option>
					<c:out value="${curso.nome }" />
				</option>
				<c:forEach var="curso" items="${cursos }">
					<option>
						<c:out value="${curso.nome }"/>
					</option>
				</c:forEach>
			</select>
		</div>
		
		<div>
			<label>Nome</label>
			<input type="text" name="nome" />
		</div>
		
		<div>
			<label>Hora de Inicio</label>
			<input type="time" name="horaInicio">
		</div>
		
		<div>
			<label for="checkbox">Valor</label>
			<input type="checkbox" name="checkbox" value="valor do checkbox" />
			<label for="checkbox">Valor</label>
			<input type="checkbox" name="checkbox" value="valor do input" />
		</div>
		
		<input type="submit" name="adicionar" />
		
	</form>
</body>
</html>