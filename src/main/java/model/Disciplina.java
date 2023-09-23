package model;

import java.util.Set;

public class Disciplina {

	private int id;
	private Curso curso;
	private String nome;
	private Set<Conteudo> conteudos;
	
	public Disciplina() {
		
	}
	
	public Disciplina(int id, Curso curso, String nome) {
		this.id = id;
		this.curso = curso;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Conteudo> getConteudos() {
		return conteudos;
	}
	
	public void setConteudos(Set<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
	
}
