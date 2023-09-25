package model;

import java.sql.Date;

public class AlunoDetalhes {

	private float pontuacao;
	private int posicao;
	private Date dataConclusao;
	private String instituicaoSegundoGrau;
	
	private Aluno aluno;

	public float getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(float pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getInstituicaoSegundoGrau() {
		return instituicaoSegundoGrau;
	}

	public void setInstituicaoSegundoGrau(String instituicaoSegundoGrau) {
		this.instituicaoSegundoGrau = instituicaoSegundoGrau;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	
}
