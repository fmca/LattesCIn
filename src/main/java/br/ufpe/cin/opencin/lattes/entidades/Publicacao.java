package br.ufpe.cin.opencin.lattes.entidades;

import java.util.List;

public class Publicacao {

	private String titulo;
	private List<Autor> autores;
	
	public Publicacao() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
}
