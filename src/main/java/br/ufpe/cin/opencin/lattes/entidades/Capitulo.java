package br.ufpe.cin.opencin.lattes.entidades;

public class Capitulo extends Publicacao{

	private String tipo;
	private String tituloCapitulo;
	private String ano;
	private String pais;
	private String idioma;
	private String tituloLivro;
	private String pagInicial;
	private String pagFinal;
	private String isbn;
	private String organizadores;
	private String nomeEditora;


	public Capitulo() {
		super();
		this.tipo = "";
		this.tituloCapitulo = "";
		this.ano = "";
		this.pais = "";
		this.idioma = "";
		this.tituloLivro = "";
		this.pagInicial = "";
		this.pagFinal = "";
		this.isbn = "";
		this.organizadores = "";
		this.nomeEditora = "";
		this.setAutores(null);
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTituloCapitulo() {
		return tituloCapitulo;
	}
	public void setTituloCapitulo(String tituloCapitulo) {
		this.tituloCapitulo = tituloCapitulo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getTituloLivro() {
		return tituloLivro;
	}
	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}
	public String getPagInicial() {
		return pagInicial;
	}
	public void setPagInicial(String pagInicial) {
		this.pagInicial = pagInicial;
	}
	public String getPagFinal() {
		return pagFinal;
	}
	public void setPagFinal(String pagFinal) {
		this.pagFinal = pagFinal;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getOrganizadores() {
		return organizadores;
	}
	public void setOrganizadores(String organizadores) {
		this.organizadores = organizadores;
	}
	public String getNomeEditora() {
		return nomeEditora;
	}
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}


}
