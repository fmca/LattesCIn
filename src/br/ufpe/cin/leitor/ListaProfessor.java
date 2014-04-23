package br.ufpe.cin.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NameAlreadyBoundException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import br.ufpe.cin.entidades.Artigo;
import br.ufpe.cin.entidades.Autor;
import br.ufpe.cin.entidades.Capitulo;
import br.ufpe.cin.entidades.Livro;
import br.ufpe.cin.entidades.PalavrasChave;
import br.ufpe.cin.entidades.Professor;


public class ListaProfessor {

	public static List<Professor> lerXML(File f)
			throws IOException, JDOMException{
		
		List<Professor> lista = new ArrayList<Professor>();
		
		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));
		
		Namespace ns = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		
		Element root = (Element) doc.getRootElement();
		List filhos = root.getChildren();

		Iterator i = filhos.iterator();
		
		while(i.hasNext()){
			
			Professor p = new Professor();
			
			Element elemento = (Element) i.next();
			p.setHomepage(elemento.getAttributeValue("about", ns));
			
			List filhos2 = elemento.getChildren();
			Iterator i2 = filhos2.iterator();
			
			while(i2.hasNext()){
				
				Element elemento2 = (Element) i2.next();
				
				if(elemento2.getName().equals("name")){
				
					//System.out.println(elemento2.getText());
					
				}
				
			}
			
			


		}

		return null;
	}

}