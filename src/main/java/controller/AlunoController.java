package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.AlunoDetalhes;
import model.Curso;
import model.Matricula;
import model.Telefone;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AlunoDAO;
import dao.CursoDAO;

@WebServlet("/aluno")
public class AlunoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private AlunoDAO alunoDAO;
	private CursoDAO cursoDAO;
	
    public AlunoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		if(opcao.contains("inserir")) {
			cursoDAO = new CursoDAO();
			List<Curso> cursos = new ArrayList<Curso>();
			try {
				cursos = cursoDAO.listar();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("cursos", cursos);
			request.getRequestDispatcher("aluno.jsp").forward(request, response);
		}
		
		else if(opcao.contains("atualizar") || opcao.contains("deletar")) {
			alunoDAO = new AlunoDAO();
			List<Aluno> alunos = new ArrayList<Aluno>();
			try {
				alunos = alunoDAO.listar();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("alunos", alunos);
			request.getRequestDispatcher("operacaoAlunos.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String botao = request.getParameter("botao");
		
		alunoDAO = new AlunoDAO();
		
		
		if(botao.contains("inserir")) {
			String cpf = request.getParameter("cpf");
			String nome = request.getParameter("nome");
			String nomeSocial = request.getParameter("nome_social");
			Date dataNascimento = Date.valueOf(request.getParameter("data_nascimento"));
			String[] telefones = request.getParameterValues("telefone");
			String emailPessoal = request.getParameter("email_pessoal");
			String emailCorporativo = request.getParameter("email_corporativo");
			Date dataConclusao = Date.valueOf(request.getParameter("data_conclusao"));
			String instituicao = request.getParameter("instituicao");
			float pontuacao = Float.parseFloat(request.getParameter("pontuacao"));
			int posicao = Integer.parseInt(request.getParameter("posicao"));
			int cursoId = Integer.parseInt(request.getParameter("curso_id"));
			
			AlunoDetalhes detalhes = new AlunoDetalhes();
			Aluno aluno = new Aluno();
			Matricula matricula = new Matricula();
			
			List<Telefone> listaTelefones = new ArrayList<Telefone>();
			for(String numero : telefones) {
				Telefone telefone = new Telefone();
				telefone.setAluno(aluno);
				telefone.setTelefone(numero);
				listaTelefones.add(telefone);
				
			}
			
			aluno.setCPF(cpf);
			aluno.setNome(nome);
			aluno.setNomeSocial(nomeSocial);
			aluno.setTelefones(listaTelefones);
			aluno.setDataNascimento(dataNascimento);
			detalhes.setAluno(aluno);
			detalhes.setDataConclusao(dataConclusao);
			detalhes.setInstituicaoSegundoGrau(instituicao);
			detalhes.setPontuacao(pontuacao);
			detalhes.setPosicao(posicao);
			aluno.setEmailPessoal(emailPessoal);
			aluno.setEmailCorporativo(emailCorporativo);
			aluno.setDetalhes(detalhes);
			
			matricula.setAluno(aluno);
			aluno.setMatricula(matricula);
			Curso curso = new Curso();
			curso.setId(cursoId);
			matricula.setCurso(curso);
			aluno.setMatricula(matricula);
			
			try {
				if(alunoDAO.inserir(aluno)) {
					response.sendRedirect("/");
				}
				else {
					request.setAttribute("mensagem", "Algo deu errado. Tente novamente");
					request.getRequestDispatcher("aluno.jsp").forward(request, response);
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("erro", e);
				request.getRequestDispatcher("").forward(request, response);
				e.printStackTrace();
			}
			System.out.println(aluno.getMatricula().getCurso().getId());
			System.out.println(aluno.getDataNascimento());
	}
		
		if(botao.contains("Atualizar")) {
			doPut(request, response);
		}
		
		if(botao.contains("deletar")) {
			doDelete(request, response);
		}
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ra = request.getParameter("ra");
		String[] telefones = request.getParameterValues("telefone");
		String emailPessoal = request.getParameter("emailPessoal");
		String emailCorporativo = request.getParameter("emailCorporativo");
		
		Aluno aluno = new Aluno();
		aluno.setRa(ra);
		aluno.setEmailPessoal(emailPessoal);
		aluno.setEmailCorporativo(emailCorporativo);
		List<Telefone> telefoneLista = new ArrayList<Telefone>();
 		for(String numero : telefones) {
			Telefone telefone = new Telefone();
			telefone.setTelefone(numero);
			telefoneLista.add(telefone);
		}
 		aluno.setTelefones(telefoneLista);
 		try {
			alunoDAO.atualizar(aluno);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
