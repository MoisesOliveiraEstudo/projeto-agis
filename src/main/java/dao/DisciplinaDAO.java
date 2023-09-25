package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Curso;
import model.Disciplina;

public class DisciplinaDAO implements DaoBasico<Disciplina>{

	Connection connection;
	
	@Override
	public void inserir(Disciplina disciplina) throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "INSERT INTO disciplina(curso_id, nome) VALUES (?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, disciplina.getCurso().getId());
		statement.setString(2, disciplina.getNome());
		statement.execute();
		statement.close();
		connection.close();
	}

	@Override
	public List<Disciplina> listar() throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "SELECT id, curso_id, nome FROM disciplina";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		while(set.next()) {
			Disciplina disciplina = new Disciplina();
			disciplina.setId(set.getInt("id"));
			disciplina.setNome(set.getString("nome"));
			Curso curso = new Curso();
			curso.setId(set.getInt("curso_id"));
			disciplina.setCurso(curso);
			disciplinas.add(disciplina);
		}
		return disciplinas;
	}
	
	public List<Disciplina> listarDisciplinasRelacionadas(Aluno aluno) throws SQLException, ClassNotFoundException{
		connection = Connector.connect();
		String query = "SELECT disciplina.id, disciplina.nome, disciplina.dia_semana, disciplina.hora_comeco FROM disciplina\r\n"
				+ "WHERE disciplina.id not IN (SELECT disciplina_id FROM matricula_disciplina, matricula, curso WHERE matricula_disciplina.aluno_ra = ?\r\n"
				+ ") AND disciplina.id IN (SELECT disciplina.id FROM disciplina, matricula, curso WHERE matricula.curso_codigo = curso.codigo AND matricula.aluno_ra = ? \r\n"
				+ "AND matricula.curso_codigo = disciplina.curso_codigo\r\n"
				+ ")";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, aluno.getRa());
		statement.setString(2, aluno.getRa());
		ResultSet set = statement.executeQuery();
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		while(set.next()) {
			Disciplina disciplina = new Disciplina();
			disciplina.setId(set.getInt("id"));
			disciplina.setNome(set.getString("nome"));
			disciplina.setDiaDaSemana(set.getString("dia_semana"));
			disciplina.setComeco(set.getInt("hora_comeco"));
			disciplinas.add(disciplina);
		}
		
		return disciplinas;
	}
	

	
}
