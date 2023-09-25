package model;

import java.sql.Date;
import java.util.List;

public class Aluno {

	private String CPF;
	private String ra;
	private String nome;
	private String nomeSocial;
	private Date dataNascimento;
	private String emailPessoal;
	private String emailCorporativo;
	private AlunoDetalhes detalhes;
	private List<Telefone> telefones;
	private Matricula matricula;
	
	
	
	public void setRa(String ra) {
		this.ra = ra;
	}
	
	public String getRa() {
		return ra;
	}
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeSocial() {
		return nomeSocial;
	}
	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmailPessoal() {
		return emailPessoal;
	}
	public void setEmailPessoal(String emailPessoal) {
		this.emailPessoal = emailPessoal;
	}
	public String getEmailCorporativo() {
		return emailCorporativo;
	}
	public void setEmailCorporativo(String emailCorporativo) {
		this.emailCorporativo = emailCorporativo;
	}
	public AlunoDetalhes getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(AlunoDetalhes detalhes) {
		this.detalhes = detalhes;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	
}
