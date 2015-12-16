package br.ufpe.cin.opencin.lattes.entidades;

public class LinhaDePesquisa {

	private String uri;
	private String name;
	public LinhaDePesquisa(String name) {
		this.name = name;
		
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
