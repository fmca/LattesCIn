package br.ufpe.cin.opencin.lattes.entidades;

public class AreaConhecimento {

	private String grandeArea;
	private String area;
	private String subArea;
	
	public AreaConhecimento(String grandeArea, String area, String subArea) {
		super();
		this.grandeArea = grandeArea;
		this.area = area;
		this.subArea = subArea;
	}

	public String getGrandeArea() {
		return grandeArea;
	}

	public void setGrandeArea(String grandeArea) {
		this.grandeArea = grandeArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}


}
