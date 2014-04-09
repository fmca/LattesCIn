package br.ufpe.cin.entidades;

import java.util.List;


public class Professor {
	
	private String nomeCompleto;
	private String nameCitation;
	private String academicDegree;
	private String birthDate; 
	private String email;
	private String gender;
	private String homepage;
	private String lattes;
	private String office;
	private String phone;
	private String nacionalidade;
	private String paisNascimento;
	private String UFNascimento;
	private String cidadeNascimento;
	private List<Artigo> artigos;
	private List<Livro> livros;
	private List<Capitulo> capitulos;
	
	public String getNameCitation() {
		return nameCitation;
	}


	public void setNameCitation(String nameCitation) {
		this.nameCitation = nameCitation;
	}


	public String getAcademicDegree() {
		return academicDegree;
	}


	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getHomepage() {
		return homepage;
	}


	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}


	public String getLattes() {
		return lattes;
	}


	public void setLattes(String lattes) {
		this.lattes = lattes;
	}


	public String getOffice() {
		return office;
	}


	public void setOffice(String office) {
		this.office = office;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}


	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}


	public List<Livro> getLivros() {
		return livros;
	}


	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}


	public List<Artigo> getArtigos() {
		return artigos;
	}


	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public Professor(){
		this.artigos = null;
		this.capitulos = null;
		this.livros = null;
	}
	
	public Professor(String nomeCompleto, String nomeCitacoes,
			String nacionalidade, String paisNascimento, String uFNascimento,
			String cidadeNascimento) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.nameCitation = nomeCitacoes;
		this.nacionalidade = nacionalidade;
		this.paisNascimento = paisNascimento;
		UFNascimento = uFNascimento;
		this.cidadeNascimento = cidadeNascimento;
		this.artigos = null;
		this.capitulos = null;
		this.livros = null;
	}
	
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getNomeCitacoes() {
		return nameCitation;
	}
	public void setNomeCitacoes(String nomeCitacoes) {
		this.nameCitation = nomeCitacoes;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getPaisNascimento() {
		return paisNascimento;
	}
	public void setPaisNascimento(String paisNascimento) {
		this.paisNascimento = paisNascimento;
	}
	public String getUFNascimento() {
		return UFNascimento;
	}
	public void setUFNascimento(String uFNascimento) {
		UFNascimento = uFNascimento;
	}
	public String getCidadeNascimento() {
		return cidadeNascimento;
	}
	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}
	
}
