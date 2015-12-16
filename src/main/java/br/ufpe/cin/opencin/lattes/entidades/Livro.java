package br.ufpe.cin.opencin.lattes.entidades;

import java.util.List;

public class Livro {

	private String tipo;
	private String natureza;
	private String titulo;
	private String ano;
	private String pais;
	private String idioma;
	private String numeroPaginas;
	private String isbn;
	private String nomeEditora;
	private List<Autor> autores;



	public Livro() {
		super();
		this.tipo = "";
		this.natureza = "";
		this.titulo = "";
		this.ano = "";
		this.pais = "";
		this.idioma = "";
		this.numeroPaginas = "";
		this.isbn = "";
		this.nomeEditora = "";
		this.autores = null;
	}


	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNatureza() {
		return natureza;
	}
	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public String getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getNomeEditora() {
		return nomeEditora;
	}
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}



}
