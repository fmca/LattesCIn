package br.ufpe.cin.leitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

import br.ufpe.cin.escritor.JSON;
import br.ufpe.cin.entidades.Professor;

public class Main {

	/**
	 * @param args
	 * @throws JDOMException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, JDOMException {
		// TODO Auto-generated method stub
		
		List<Professor> professores = new ArrayList<Professor>();
		
		for(int i = 0; i < 73; i++){
			professores.add(LeitorXML.lerXML(new File("curriculo-"+i+".xml")));
		}
		JSON.transformarJSON(professores);
		
		

	}

}
