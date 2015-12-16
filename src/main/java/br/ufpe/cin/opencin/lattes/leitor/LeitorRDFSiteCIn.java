package br.ufpe.cin.opencin.lattes.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import br.ufpe.cin.opencin.lattes.entidades.Professor;


public class LeitorRDFSiteCIn {

	//ler RDF do professor. O RDF que montei manualmente
	public static List<Professor> lerXML(File f)
			throws IOException, JDOMException{

		List<Professor> lista = new ArrayList<Professor>();

		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));

		Namespace ns = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

		Element root = (Element) doc.getRootElement();
		List<Element> filhos = root.getChildren();

		Iterator<Element> i = filhos.iterator();

		while(i.hasNext()){

			Professor p = new Professor();

			Element elemento = (Element) i.next();
			p.setProfessorID(elemento.getAttributeValue("about", ns));

			List<Element> filhos2 = elemento.getChildren();
			Iterator<Element> i2 = filhos2.iterator();

			while(i2.hasNext()){

				Element elemento2 = (Element) i2.next();

				if(elemento2.getName().equals("name")){

					p.setNomeCompleto(elemento2.getText());

				}else if(elemento2.getName().equals("lattes")){

					p.setLattes(elemento2.getText());

				}else if(elemento2.getName().equals("nameCitation")){

					p.setNameCitation(elemento2.getText());

				}else if(elemento2.getName().equals("homepage")){

					p.setHomepage(elemento2.getText());

				}else if(elemento2.getName().equals("email")){

					p.setEmail(elemento2.getText());

				}else if(elemento2.getName().equals("gender")){

					p.setGender(elemento2.getText());

				}else if(elemento2.getName().equals("office")){

					p.setOffice(elemento2.getText());

				}else if(elemento2.getName().equals("phone")){

					p.setPhone(elemento2.getText());

				}


			}
			lista.add(p);


		}

		return lista;
	}

	

}
