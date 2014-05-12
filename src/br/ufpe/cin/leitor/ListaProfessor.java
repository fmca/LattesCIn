package br.ufpe.cin.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NameAlreadyBoundException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import br.ufpe.cin.entidades.Artigo;
import br.ufpe.cin.entidades.Autor;
import br.ufpe.cin.entidades.Capitulo;
import br.ufpe.cin.entidades.Livro;
import br.ufpe.cin.entidades.PalavrasChave;
import br.ufpe.cin.entidades.Professor;


public class ListaProfessor {
	
	//ler RDF do professor. O RDF que montei manualmente
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
			p.setProfessorID(elemento.getAttributeValue("about", ns));
			
			List filhos2 = elemento.getChildren();
			Iterator i2 = filhos2.iterator();
			
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
	
	public static void thaisParaJonatas(File f)
			throws IOException, JDOMException{
		
		List<Professor> listaP = ListaProfessor.lerXML(new File("professoresRDF.xml"));
		
		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));
		
		Namespace ns = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		Namespace portal = Namespace.getNamespace("portal", "http://www.portalors.org/ontology/portal#");
		
		Element root = (Element) doc.getRootElement();
		List filhos = root.getChildren();

		Iterator i = filhos.iterator();
		
		while(i.hasNext()){
			
			Element elemento = (Element) i.next();
			//String t = elemento.getAttributeValue("about", ns);
			if(elemento.getAttributeValue("about", ns).startsWith("#Academic/")){
				elemento.getAttribute("about", ns).setValue(ListaProfessor.procurarLogin(listaP, elemento.getChildText("name", portal)));
				//elemento.getAttribute("about", ns).setValue("OIIIIIIII");
			}
			
		}
		
		//document is processed and edited successfully, lets save it in new file
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        //output xml to console for debugging
        //xmlOutputter.output(doc, System.out);
        xmlOutputter.output(doc, new FileOutputStream("professoresAreaRDF.xml"));
		
	}
	
	public static String procurarLogin(List<Professor> listaP, String nome){
		
		nome = ListaProfessor.removeAcentos(nome);
		for(Professor p:listaP){
			
			if(ListaProfessor.removeAcentos(p.getNomeCompleto()).equalsIgnoreCase(nome)){
				//System.out.println(p.getHomepage());
				return p.getProfessorID();
			}
			
		}
		
		System.out.println(nome);
		return "oiii";
	}
	
	public static String removeAcentos(String str) {
		 
		  str = Normalizer.normalize(str, Normalizer.Form.NFD);
		  str = str.replaceAll("[^\\p{ASCII}]", "");
		  str = str.replaceAll("de", "");
		  str = str.replaceAll("da", "");
		  str = str.replaceAll("  ", " ");
		  return str;
		 
		}
	
	public static void thaisParaJonatasDisciplinas(File f)
			throws IOException, JDOMException{
		
		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));
		
		Namespace ns = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		Namespace portal = Namespace.getNamespace("portal", "http://www.portalors.org/ontology/portal#");
		Namespace aiiso = Namespace.getNamespace("aiiso", "http://purl.org/vocab/aiiso/schema#");
		
		Element root = (Element) doc.getRootElement();
		List filhos = root.getChildren();

		Iterator i = filhos.iterator();
		
		while(i.hasNext()){
			
			Element elemento = (Element) i.next();
			//String t = elemento.getAttributeValue("about", ns);
			if(elemento.getAttributeValue("about", ns).startsWith("#Course/")){
				elemento.getAttribute("about", ns).setValue("http://www.cin.ufpe.br/disciplina/"+elemento.getChildText("code", aiiso));
			}
			
		}
		
		//document is processed and edited successfully, lets save it in new file
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        //output xml to console for debugging
        //xmlOutputter.output(doc, System.out);
        xmlOutputter.output(doc, new FileOutputStream("professoresAreaRDF.xml"));
		
	}

}
