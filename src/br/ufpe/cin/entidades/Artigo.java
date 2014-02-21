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
	private int volume;
	private String fasciculo;
	private String serie;
	private int pagInicial;
	private int pagFinal;
	private String localPublicacao;
	private List<Autor> autores;
	private PalavrasChave palavrasChave;
	private List<AreaConhecimento> areasConhecimento;
	
	
	public Artigo(String titulo, int ano, String pais, String idioma,
			String meioDeDivulgacao, String tituloPeriodicoRevista,
			String issn, int volume, String fasciculo, String serie,
			int pagInicial, int pagFinal, String localPublicacao,
			List<Autor> autores, PalavrasChave palavrasChave,
			List<AreaConhecimento> areasConhecimento) {
		super();
		this.titulo = titulo;
		this.ano = ano;
		this.pais = pais;
		this.idioma = idioma;
		this.meioDeDivulgacao = meioDeDivulgacao;
		this.tituloPeriodicoRevista = tituloPeriodicoRevista;
		this.issn = issn;
		this.volume = volume;
		this.fasciculo = fasciculo;
		this.serie = serie;
		this.pagInicial = pagInicial;
		this.pagFinal = pagFinal;
		this.localPublicacao = localPublicacao;
		this.autores = autores;
		this.palavrasChave = palavrasChave;
		this.areasConhecimento = areasConhecimento;
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
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
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
	public int getPagInicial() {
		return pagInicial;
	}
	public void setPagInicial(int pagInicial) {
		this.pagInicial = pagInicial;
	}
	public int getPagFinal() {
		return pagFinal;
	}
	public void setPagFinal(int pagFinal) {
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
