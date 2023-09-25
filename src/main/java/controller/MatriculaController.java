package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Disciplina;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AlunoDAO;
import dao.DisciplinaDAO;

@WebServlet("/matricula")
public class MatriculaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private AlunoDAO alunoDAO;
    private DisciplinaDAO disciplinaDAO;
    
	
	
    public MatriculaController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		alunoDAO = new AlunoDAO();
		disciplinaDAO = new DisciplinaDAO();
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			alunos = alunoDAO.consultarAlunoCurso();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("alunos", alunos);
		
		if(opcao.contains("adicionar")) {
			request.getRequestDispatcher("matriculaDisciplina.jsp").forward(request, response);
		}
		
		if(opcao.contains("consultar")) {
			
			request.getRequestDispatcher("consultarDisciplina.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String botao = request.getParameter("botao");
		String ra = request.getParameter("aluno_ra");
		
		if(botao.contains("Consultar")) {
			Aluno aluno = new Aluno();
			aluno.setRa(ra);
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
			try {
				disciplinas = disciplinaDAO.listarDisciplinasMatriculadas(aluno);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("disciplinas", disciplinas);
			request.getRequestDispatcher("consultarDisciplina.jsp").forward(request, response);
		}
		
		
		else if(botao.contains("Pesquisar")) {
			doPut(request, response);
		}
		else {
			String[] disciplina_ids = request.getParameterValues("disciplina");
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
			for(String disciplina_id : disciplina_ids) {
				int id = Integer.parseInt(disciplina_id);
				Disciplina disciplina = new Disciplina();
				disciplina.setId(id);
				disciplinas.add(disciplina);
			}
		
			if(validaDisciplinas(disciplinas)) {
				for(Disciplina disciplina : disciplinas) {
					Aluno aluno = new Aluno();
					aluno.setRa(ra);
					try {
						alunoDAO.matricularDisciplina(aluno, disciplina);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}	
	boolean validaDisciplinas(List<Disciplina> disciplinas) {
		for(int i = 0; i < disciplinas.size() - 1; i++) {
			for(int j = 1; j < disciplinas.size(); j++) {
				if(disciplinas.get(i).getComeco() == disciplinas.get(j).getComeco()) {
					if(disciplinas.get(i).getDiaDaSemana().equals(disciplinas.get(j).getDiaDaSemana()))
						return false;
				}
			}
		}
		return true;
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disciplinaDAO = new DisciplinaDAO();
		alunoDAO = new AlunoDAO();
		String ra = request.getParameter("aluno");
		Aluno aluno = new Aluno();
		aluno.setRa(ra);
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			disciplinas = disciplinaDAO.listarDisciplinasRelacionadas(aluno);
			alunos = alunoDAO.consultarAlunoCurso();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("disciplinas", disciplinas);
		request.setAttribute("aluno", aluno);
		request.getRequestDispatcher("consultarDisciplinas.jsp").forward(request, response);
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
