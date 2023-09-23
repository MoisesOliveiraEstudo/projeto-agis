package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Conteudo;

public class ConteudoDAO implements DaoBasico<Conteudo>{
	
	Connection connection;
	
	@Override
	public void inserir(Conteudo conteudo) throws SQLException, ClassNotFoundException {
		connection = Connector.connect();
		String query = "INSERT INTO conteudo(disciplina_id, nome) VALUES (?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, conteudo.getDisciplina().getId());
		statement.setString(2, conteudo.getNome());
		statement.execute();
		connection.close();
		statement.close();
	}
	
	@Override
	public void atualizar(Conteudo e) throws SQLException, ClassNotFoundException {
	}
	
	@Override
	public void deletar(Conteudo e) throws SQLException, ClassNotFoundException {
	}
	
	@Override
	public List<Conteudo> listar() throws SQLException, ClassNotFoundException {
		return null;
	}
}
