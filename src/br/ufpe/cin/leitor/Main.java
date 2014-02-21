package br.ufpe.cin.leitor;

import java.io.File;
import java.io.IOException;

import org.jdom2.JDOMException;

public class Main {

	/**
	 * @param args
	 * @throws JDOMException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, JDOMException {
		// TODO Auto-generated method stub
		//LeitorXML leitor = new LeitorXML();
		LeitorXML.lerXML(new File("curriculo.xml"));

	}

}
