package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso;

import java.io.IOException;
import java.sql.SQLException;

import dao.CursoDAO;

@WebServlet("/curso")
public class CursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CursoDAO cursoDAO;
	
    public CursoController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		try {
			
			if(opcao.contains("listar")) {
				request.getRequestDispatcher("cursos/listarCursos.jsp").forward(request, response);
			}
			if(opcao.contains("atualizar")) {
				request.getRequestDispatcher("cursos/atualizarCurso.jsp").forward(request, response);
			}
			if(opcao.contains("consultar")) {
				request.getRequestDispatcher("cursos/consultarCurso.jsp").forward(request, response);
			}
		}
		catch(Exception e) {
			response.getWriter().append("Ocorreu um erro, tente novamente por outro meio");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("botao");
		
		if(operacao.contains("Inserir")) {
			cursoDAO = new CursoDAO();
			String nome = request.getParameter("nome");
			Curso curso = new Curso(nome);
			try {
				cursoDAO.inserir(curso);
				response.sendRedirect("index.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				response.sendRedirect("error.jsp");
			}
		}
		if(operacao.contains("Atualizar")) doPut(request, response);
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
