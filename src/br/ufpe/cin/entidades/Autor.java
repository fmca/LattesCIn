package br.ufpe.cin.entidades;

public class Autor {
	
	private String nomeCompleto;
	private String NomeCitacao;
	
	public Autor(String nomeCompleto, String nomeCitacao) {
		super();
		this.nomeCompleto = nomeCompleto;
		NomeCitacao = nomeCitacao;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeCitacao() {
		return NomeCitacao;
	}

	public void setNomeCitacao(String nomeCitacao) {
		NomeCitacao = nomeCitacao;
	}
	
	
}
