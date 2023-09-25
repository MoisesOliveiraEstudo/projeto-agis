package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.AlunoDetalhes;
import model.Curso;
import model.Disciplina;
import model.Matricula;

public class AlunoDAO implements DaoComposto<Aluno>{

	Connection connection;
	
	@Override
	public boolean inserir(Aluno aluno) throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "CALL insere_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		CallableStatement statement = connection.prepareCall(query);
		statement.setString(1, aluno.getCPF());
		statement.setString(2, aluno.getNome());
		if(aluno.getNomeSocial() != null) {
			statement.setString(3, aluno.getNomeSocial());
		}
		else {
			statement.setString(3, null);
		}
		statement.setDate(4, aluno.getDataNascimento());
		statement.setString(5, aluno.getTelefones().get(0).getTelefone());
		statement.setString(6, aluno.getTelefones().get(1).getTelefone());
		statement.setString(7, aluno.getEmailPessoal());
		statement.setString(8, aluno.getEmailCorporativo());
		statement.setDate(9, aluno.getDetalhes().getDataConclusao());
		statement.setString(10, aluno.getDetalhes().getInstituicaoSegundoGrau());
		statement.setFloat(11, aluno.getDetalhes().getPontuacao());
		statement.setInt(12, aluno.getDetalhes().getPosicao());
		statement.setInt(13, aluno.getMatricula().getCurso().getId());	
		statement.registerOutParameter(14, java.sql.Types.BOOLEAN);
		statement.execute();
		Boolean resultado = statement.getBoolean(14);
		return resultado;
	}
	
	@Override
	public void atualizar(Aluno aluno) throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "CALL atualiza_aluno(?,?,?,?,?)";
		CallableStatement statement = connection.prepareCall(query);
		statement.setString(1, aluno.getRa());
		statement.setString(2, aluno.getTelefones().get(0).getTelefone());
		statement.setString(3, aluno.getTelefones().get(1).getTelefone());
		statement.setString(4, aluno.getEmailPessoal());
		statement.setString(5, aluno.getEmailCorporativo());
		statement.execute();
		statement.close();
		connection.close();
	}
	
	@Override
	public Aluno consultar(Aluno e) throws SQLException, ClassNotFoundException {
		return null;
	}
	
	public void matricularDisciplina(Aluno aluno, Disciplina disciplina) throws SQLException, ClassNotFoundException{
		connection = Connector.connect();
		String query = "INSERT INTO matricula_disciplina VALUES (?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, disciplina.getId());
		statement.setString(2, aluno.getRa());
		statement.execute();
		statement.close();
		connection.close();
	}
	
	
	@Override
	public List<Aluno> listar() throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "SELECT aluno.cpf, aluno.ra, aluno.nome, curso.nome, aluno_detalhes.data_conclusao, emailCorporativo, emailPessoal FROM aluno, aluno_detalhes, matricula, curso\r\n"
				+ "WHERE aluno.ra = aluno_detalhes.aluno_ra\r\n"
				+ "AND aluno.ra = matricula.aluno_ra\r\n"
				+ "AND curso.codigo = matricula.curso_codigo";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		List<Aluno> alunos = new ArrayList<Aluno>();
		while(set.next()) {
			Aluno aluno = new Aluno();
			AlunoDetalhes detalhes = new AlunoDetalhes();
			detalhes.setDataConclusao(set.getDate("data_conclusao"));
			aluno.setRa(set.getString("ra"));
			aluno.setCPF(set.getString("cpf"));
			aluno.setNome(set.getString("nome"));
			aluno.setEmailPessoal(set.getString("emailPessoal"));
			aluno.setEmailCorporativo(set.getString("emailCorporativo"));
			alunos.add(aluno);
		}
		return alunos;
	}
	
	public List<Aluno> consultarAlunoCurso() throws SQLException, ClassNotFoundException{
		connection = Connector.connect();
		String query = "SELECT curso.codigo, aluno.ra, aluno.nome AS aluno, curso.nome AS curso FROM aluno, matricula, curso\r\n"
				+ "WHERE aluno.ra = matricula.aluno_ra\r\n"
				+ "AND matricula.curso_codigo = curso.codigo";
		
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		while(set.next()) {
			Curso curso = new Curso();
			curso.setId(set.getInt(1));
			Aluno aluno = new Aluno();
			aluno.setRa(set.getString(2));
			aluno.setNome(set.getString(3));
			curso.setNome(set.getString(4));
			Matricula matricula = new Matricula();
			matricula.setCurso(curso);
			aluno.setMatricula(matricula);
			alunos.add(aluno);
		}
		
		return alunos;
	}
}
