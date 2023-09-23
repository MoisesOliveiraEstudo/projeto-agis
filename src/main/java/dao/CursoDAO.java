package dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Curso;

public class CursoDAO implements DaoBasico<Curso>{

	Connection connection;
	
	@Override
	public void inserir(Curso curso) throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "INSERT INTO curso VALUES (?)";
		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, curso.getNome());
		statement.execute();
		ResultSet res = statement.getGeneratedKeys();
		while(res.next()) {
			curso.setId(res.getInt(1));
		}
		statement.close();
		connection.close();
	}
	

	public List<Curso> listar() throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "SELECT id, nome FROM curso";
		PreparedStatement statement = connection
				.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		List<Curso> cursos = new ArrayList();
		while(set.next()) {
			Curso curso = new Curso(set.getString(2));
			curso.setId(Integer.parseInt(set.getString(1)));
			cursos.add(curso);
		}
		return cursos;
	}
}
