package br.ufpe.cin.opencin.lattes.entidades;

public class Livro extends Publicacao{

	private String tipo;
	private String natureza;
	private String ano;
	private String pais;
	private String idioma;
	private String numeroPaginas;
	private String isbn;
	private String nomeEditora;



	public Livro() {
		super();
		this.tipo = "";
		this.natureza = "";
		this.ano = "";
		this.pais = "";
		this.idioma = "";
		this.numeroPaginas = "";
		this.isbn = "";
		this.nomeEditora = "";
		this.setAutores(null);
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


}
