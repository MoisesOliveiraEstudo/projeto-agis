package model;

public class Conteudo {

	private int id;
	private String nome;
	private Disciplina disciplina;
	
	public Conteudo() {}
	
	public Conteudo(int id, String nome, Disciplina disciplina) {
		this.id = id;
		this.disciplina = disciplina;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}
