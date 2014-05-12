package br.ufpe.cin.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import br.ufpe.cin.escritor.JSON;
import br.ufpe.cin.escritor.RDFPublicacao;
import br.ufpe.cin.entidades.Professor;


public class Main {

	/**
	 * @param args
	 * @throws JDOMException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, JDOMException {
		// TODO Auto-generated method stub

		//List<Professor> professores = new ArrayList<Professor>();

		//Pegar lista de professor e respectivo login
		//List<Professor> professorID = ListaProfessor.lerXML(new File("professoresRDF.xml"));


		//for(int i = 0; i < 78; i++){
		//	professores.add(LeitorXML.lerXML(new File("curriculo-"+i+".xml"), professorID));
		//}


		//gerar JSON
		//JSON.transformarJSON(professores);

		//gerar RDF Professor
		//RDFPublicacao.transformarRDFProfessor(professorID);

		//Gerar RDF publicacao
		//RDFPublicacao.transformarRDFPublicacao(professores);

		//Transaformar RDF de Thais (Disciplinas)
		//ListaProfessor.thaisParaJonatasDisciplinas(new File("thais.xml"));

		//Main.separaRDFAreaInterest(new File("professoresAreaRDF.xml"));

		Main.associaAreaProfessor(new File("professoresAreaRDF.xml"));

	}

	//Esse método separa e organiza os RDFs de Thaís
	public static void separaRDFAreaInterest(File f) throws UnsupportedEncodingException, JDOMException, IOException{

		String uriAI = "http://www.cin.ufpe.br/opencin/";
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		////////////////////////
		//Criando Modelo
		Model model = ModelFactory.createDefaultModel();

		//criando propriedades
		Property name = model.createProperty(uriAI, "name");

		//criando recurso
		Resource rc = model.createResource("http://www.cin.ufpe.br/opencin/interestArea");

		///////////////////////



		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));
		Namespace ns = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		Namespace nsCIn = Namespace.getNamespace("cin", "http://www.cin.ufpe.br/opencin/");

		Element root = (Element) doc.getRootElement();

		List areaRDF = root.getChildren();

		Iterator i = areaRDF.iterator();

		while(i.hasNext()){
			Element partes = (Element) i.next();

			if(partes.getAttributeValue("about", ns).startsWith("#Area-of-interest/")){

				List areaRDF2 = partes.getChildren();
				Iterator i2 = areaRDF2.iterator();

				while(i2.hasNext()){
					Element partes2 = (Element) i2.next();

					if(partes2.getName().equals("name")){

						Resource publicacao = model.createResource(uriAI+"interestArea/"+partes.getAttributeValue("about", ns).substring(18))
								.addProperty(name, (partes2.getText()))
								.addProperty(RDF.type, rc);

					}

				}


			}

		}

		model.setNsPrefix("cin", uriAI);
		model.setNsPrefix("rdf", rdf);
		OutputStream output = new FileOutputStream("areaInteresseRDF.xml");
		model.write(output);
		//model.write(System.out);

	}

	public static void associaAreaProfessor (File f) throws UnsupportedEncodingException, JDOMException, IOException{

		String cin = "http://www.cin.ufpe.br/opencin/";
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

		////////////////////////
		//Criando Modelo
		Model model = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();

		//criando propriedades
		Property name = model.createProperty(cin, "name");
		Property hasExpertise = model.createProperty(cin, "hasAreaExpertise");
		Property hasInterest = model.createProperty(cin, "hasAreaInterest");
		Property siape = model.createProperty(cin, "siape");

		//criando recurso
		Resource rcAcademic = model.createResource("http://www.cin.ufpe.br/opencin/academic");
		Resource rcExpertiseArea = model.createResource("http://www.cin.ufpe.br/opencin/expertiseArea");

		///////////////////////


		SAXBuilder sb = new SAXBuilder();
		FileInputStream fs = new FileInputStream(f);
		Document doc = sb.build(new InputStreamReader(fs, "UTF8"));

		Namespace ns = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		Namespace portal = Namespace.getNamespace("portal", "http://www.portalors.org/ontology/portal#");
		Namespace cinNs = Namespace.getNamespace("cin", "http://www.cin.ufpe.br/propriedades/");

		Element root = (Element) doc.getRootElement();
		List filhos = root.getChildren();

		Iterator i = filhos.iterator();

		while(i.hasNext()){

			Element elemento = (Element) i.next();
			//String t = elemento.getAttributeValue("about", ns);
			if(elemento.getAttributeValue("about", ns).startsWith("http://www.cin.ufpe.br/docentes/")){

				List root2 = elemento.getChildren();
				Iterator i2 = root2.iterator();

				Resource professor = model.createResource("http://www.cin.ufpe.br/opencin/academic/"+elemento.getAttributeValue("about", ns).substring(32));

				while(i2.hasNext()){
					Element elemento2 = (Element) i2.next();

					if(elemento2.getName().equals("has-area-of-expertise")){
						Resource rcExpertise = model.createResource("http://www.cin.ufpe.br/opencin/expertiseArea/"+elemento2.getAttributeValue("resource", ns).substring(19));
						professor.addProperty(hasExpertise, rcExpertise);


					}else if(elemento2.getName().equals("has-area-of-interest")){
						Resource rcInterest = model.createResource("http://www.cin.ufpe.br/opencin/interestArea/"+elemento2.getAttributeValue("resource", ns).substring(18));
						professor.addProperty(hasInterest, rcInterest);

					}else if(elemento2.getName().equals("name")){

						professor.addProperty(name, elemento2.getText());

					}else if(elemento2.getName().equals("siape")){

						professor.addProperty(siape, elemento2.getText());

					}

				}

				professor.addProperty(RDF.type, rcAcademic);

			}else if(elemento.getAttributeValue("about", ns).startsWith("#Area-of-expertise/")){

				List root2 = elemento.getChildren();
				Iterator i2 = root2.iterator();

				Resource areaInteresse = model2.createResource("http://www.cin.ufpe.br/opencin/expertiseArea/"+elemento.getAttributeValue("about", ns).substring(19));

				while(i2.hasNext()){
					Element elemento2 = (Element) i2.next();

					if(elemento2.getName().equals("name")){

						areaInteresse.addProperty(name, elemento2.getText());

					}

				}

				areaInteresse.addProperty(RDF.type, rcExpertiseArea);

			}

		}

		model.setNsPrefix("cin", cin);
		model.setNsPrefix("rdf", rdf);
		OutputStream output = new FileOutputStream("professorAreaRDF.xml");
		model.write(output);
		//model.write(System.out);
		
		model2.setNsPrefix("cin", cin);
		model2.setNsPrefix("rdf", rdf);
		OutputStream output2 = new FileOutputStream("areaExpertiseRDF.xml");
		model2.write(output2);
		//model2.write(System.out);

	}

}
