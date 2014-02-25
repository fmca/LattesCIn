package br.ufpe.cin.entidades;

import java.util.List;

public class Professor {
	
	private String nomeCompleto;
	private String nomeCitacoes;
	private String nacionalidade;
	private String paisNascimento;
	private String UFNascimento;
	private String cidadeNascimento;
	private List<Artigo> artigos;
	private List<Livro> livros;
	
	
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
		
	}
	
	public Professor(String nomeCompleto, String nomeCitacoes,
			String nacionalidade, String paisNascimento, String uFNascimento,
			String cidadeNascimento) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.nomeCitacoes = nomeCitacoes;
		this.nacionalidade = nacionalidade;
		this.paisNascimento = paisNascimento;
		UFNascimento = uFNascimento;
		this.cidadeNascimento = cidadeNascimento;
	}
	
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getNomeCitacoes() {
		return nomeCitacoes;
	}
	public void setNomeCitacoes(String nomeCitacoes) {
		this.nomeCitacoes = nomeCitacoes;
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
