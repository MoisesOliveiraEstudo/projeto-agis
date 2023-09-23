package dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoBasico<E> {

	public void inserir(E e) throws SQLException, ClassNotFoundException;
	public List<E> listar() throws SQLException, ClassNotFoundException;
}
