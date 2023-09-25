package dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoComposto<E> {

	public boolean inserir(E e) throws SQLException, ClassNotFoundException;
	public void atualizar(E e) throws SQLException, ClassNotFoundException;
	public E consultar(E e) throws SQLException, ClassNotFoundException;
	public List<E> listar() throws SQLException, ClassNotFoundException;
}
