package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import dao.CursoDAO;
import dao.DisciplinaDAO;

@WebServlet("/disciplina")
public class DisciplinaController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private CursoDAO cursoDAO;
    private DisciplinaDAO disciplinaDAO;
	
	
    public DisciplinaController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		cursoDAO = new CursoDAO();
		disciplinaDAO = new DisciplinaDAO();
		try {
			if(opcao.contains("adicionar")) {
				List<Curso> cursos = cursoDAO.listar();
				Curso curso = new Curso("Experimental");
				//request.setAttribute("curso", curso);
				request.setAttribute("cursos", cursos);
				request.getRequestDispatcher("novaDisciplina.jsp").forward(request, response);
			}
			if(opcao.contains("atualizar")) {
				request.getRequestDispatcher("atualizarDisciplina.jsp").forward(request, response);
			}
		} 
		
		catch (Exception e) {
			request.getRequestDispatcher("novaDisciplina.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPut(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCurso = request.getParameter("checkbox");
		String horario = request.getParameter("horaInicio");
		System.out.println(idCurso);
		System.out.println(horario);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
