package dao;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Curso;

public class AlunoDAO implements DaoComposto<Aluno>{

	@Override
	public boolean inserir(Aluno e) throws SQLException, ClassNotFoundException {
		return false;
	}
	
	@Override
	public boolean atualizar(Aluno e) throws SQLException, ClassNotFoundException {
		return false;
	}
	
	@Override
	public Aluno consultar(Aluno e) throws SQLException, ClassNotFoundException {
		return null;
	}
	
	@Override
	public List<Aluno> listar() throws SQLException, ClassNotFoundException {
		return null;
	}
	
	public Aluno consultarCurso() throws SQLException, ClassNotFoundException{
		return null;
	}
}
