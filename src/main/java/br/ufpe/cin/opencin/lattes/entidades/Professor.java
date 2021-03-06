package br.ufpe.cin.opencin.lattes.entidades;

import java.util.List;


public class Professor extends Autor{

	private String academicDegree;
	private String birthDate;
	private String email;
	private String gender;
	private String homepage;
	private String professorID;
	private String lattes;
	private String office;
	private String phone;
	private String nacionalidade;
	private String paisNascimento;
	private String UFNascimento;
	private String cidadeNascimento;
	private List<LinhaDePesquisa> linhasDePesquisas;
	private List<Artigo> artigos;
	private List<Livro> livros;
	private List<Capitulo> capitulos;
	private List<Doutorado> doutorado;
	private List<Mestrado> mestrado;
	private List<TG> tg;

	public List<Doutorado> getDoutorado() {
		return doutorado;
	}


	public void setDoutorado(List<Doutorado> doutorado) {
		this.doutorado = doutorado;
	}


	public List<Mestrado> getMestrado() {
		return mestrado;
	}


	public void setMestrado(List<Mestrado> mestrado) {
		this.mestrado = mestrado;
	}


	public List<TG> getTg() {
		return tg;
	}


	public void setTg(List<TG> tg) {
		this.tg = tg;
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
		super(nomeCompleto, nomeCitacoes);
		this.nacionalidade = nacionalidade;
		this.paisNascimento = paisNascimento;
		UFNascimento = uFNascimento;
		this.cidadeNascimento = cidadeNascimento;
		this.artigos = null;
		this.capitulos = null;
		this.livros = null;
	}

	
	

	

	@Override
	public String toString() {
		return "Professor [nomeCompleto=" + getNomeCompleto() + ", nameCitation=" + getNomeCitacao() + ", academicDegree="
				+ academicDegree + ", birthDate=" + birthDate + ", email=" + email + ", gender=" + gender
				+ ", homepage=" + homepage + ", professorID=" + professorID + ", lattes=" + lattes + ", office="
				+ office + ", phone=" + phone + ", nacionalidade=" + nacionalidade + ", paisNascimento="
				+ paisNascimento + ", UFNascimento=" + UFNascimento + ", cidadeNascimento=" + cidadeNascimento
				+ ", artigos=" + artigos + ", livros=" + livros + ", capitulos=" + capitulos + ", doutorado="
				+ doutorado + ", mestrado=" + mestrado + ", tg=" + tg + "]";
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


	public String getProfessorID() {
		return professorID;
	}


	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}


	public List<LinhaDePesquisa> getLinhasDePesquisas() {
		return linhasDePesquisas;
	}


	public void setLinhasDePesquisas(List<LinhaDePesquisa> linhasDePesquisas) {
		this.linhasDePesquisas = linhasDePesquisas;
	}
	

}
