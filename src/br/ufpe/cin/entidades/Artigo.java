package br.ufpe.cin.entidades;

import java.util.List;

public class Artigo {
	
	private String titulo;
	private int ano;
	private String pais;
	private String idioma;
	private String meioDeDivulgacao;
	private String tituloPeriodicoRevista;
	private String issn;
	private String volume;
	private String fasciculo;
	private String serie;
	private String pagInicial;
	private String pagFinal;
	private String localPublicacao;
	private List<Autor> autores;
	private PalavrasChave palavrasChave;
	private List<AreaConhecimento> areasConhecimento;
	
	
	public Artigo() {
		super();
		this.titulo = "";
		this.ano = 0;
		this.pais = "";
		this.idioma = "";
		this.meioDeDivulgacao = "";
		this.tituloPeriodicoRevista = "";
		this.issn = "";
		this.volume = "";
		this.fasciculo = "";
		this.serie = "";
		this.pagInicial = "";
		this.pagFinal = "";
		this.localPublicacao = "";
		this.autores = null;
		this.palavrasChave = new PalavrasChave("", "", "", "", "", "");
		this.areasConhecimento = null;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
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
	public String getMeioDeDivulgacao() {
		return meioDeDivulgacao;
	}
	public void setMeioDeDivulgacao(String meioDeDivulgacao) {
		this.meioDeDivulgacao = meioDeDivulgacao;
	}
	public String getTituloPeriodicoRevista() {
		return tituloPeriodicoRevista;
	}
	public void setTituloPeriodicoRevista(String tituloPeriodicoRevista) {
		this.tituloPeriodicoRevista = tituloPeriodicoRevista;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getFasciculo() {
		return fasciculo;
	}
	public void setFasciculo(String fasciculo) {
		this.fasciculo = fasciculo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
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
	public String getLocalPublicacao() {
		return localPublicacao;
	}
	public void setLocalPublicacao(String localPublicacao) {
		this.localPublicacao = localPublicacao;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public PalavrasChave getPalavrasChave() {
		return palavrasChave;
	}
	public void setPalavrasChave(PalavrasChave palavrasChave) {
		this.palavrasChave = palavrasChave;
	}
	public List<AreaConhecimento> getAreasConhecimento() {
		return areasConhecimento;
	}
	public void setAreasConhecimento(List<AreaConhecimento> areasConhecimento) {
		this.areasConhecimento = areasConhecimento;
	}
	
}
