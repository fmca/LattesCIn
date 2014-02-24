package br.ufpe.cin.leitor;

import java.io.File;
import java.io.IOException;

import org.jdom2.JDOMException;

import br.ufpe.cin.escritor.JSON;

public class Main {

	/**
	 * @param args
	 * @throws JDOMException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, JDOMException {
		// TODO Auto-generated method stub
		
		
		JSON.transformarJSON(LeitorXML.lerXML(new File("bernadette farias.xml")));
		
		

	}

}
