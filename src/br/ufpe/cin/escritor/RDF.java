package br.ufpe.cin.escritor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import br.ufpe.cin.entidades.Professor;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;

public class RDF {

	public static void transformarRDFProfessor(List<Professor> professores) throws FileNotFoundException{

		String recurso = "http://www.cin.ufpe.br/docentes/";
		String propriedade = "http://www.cin.ufpe.br/propriedades/";

		Model model = ModelFactory.createDefaultModel();

		Property nomeCompleto = model.createProperty(propriedade, "name");
		Property nameCitation = model.createProperty(propriedade, "nameCitation");
		Property academicDegree = model.createProperty(propriedade, "academicDegree");
		Property birthDate = model.createProperty(propriedade, "birthDate"); 
		Property email = model.createProperty(propriedade, "email");
		Property gender = model.createProperty(propriedade, "gender");
		Property homepage = model.createProperty(propriedade, "homepage");
		Property lattes = model.createProperty(propriedade, "lattes");
		Property office = model.createProperty(propriedade, "office");
		Property phone = model.createProperty(propriedade, "phone");


		//Iterator i = professores.iterator();
		int doc = professores.size()-1;
		while(doc > 0){

			Resource docente = model.createResource(recurso+professores.get(doc).getNomeCompleto())
					.addProperty(nomeCompleto, professores.get(doc).getNomeCompleto())
					.addProperty(academicDegree, "")
					.addProperty(birthDate, "")
					.addProperty(gender, "")
					.addProperty(homepage, "www.cin.ufpe.br/~")
					.addProperty(lattes, "lattes.cnpq.br/")
					.addProperty(office, "")
					.addProperty(phone, "")
					.addProperty(email, "")
					.addProperty(nameCitation, professores.get(doc).getNomeCitacoes());

			//System.out.println(doc);
			doc--;
		}
		model.setNsPrefix("cin", propriedade);
		OutputStream output = new FileOutputStream("professoresRDF.xml");
		model.write(output);
		//model.write(System.out);

	}

	public static void transformarRDFPublicacao(List<Professor> professores) throws FileNotFoundException{

		Model model = ModelFactory.createDefaultModel();

		String dcterms = "http://purl.org/dc/terms/";
		String bibo = "http://purl.org/ontology/bibo/";
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		String cin = "http://www.cin.ufpe.br/publicacao/";

		Property rdfType = model.createProperty(rdf,"type");
		Property title = model.createProperty(dcterms,"title");
		Property language = model.createProperty(dcterms,"language");
		Property issued = model.createProperty(dcterms,"issued");
		Property uri = model.createProperty(bibo,"uri");
		Property subject = model.createProperty(cin,"subject");
		Property areasDoConhecimento = model.createProperty(cin,"areasDoConhecimento");
		Property meioDeDivulgacao = model.createProperty(cin,"meioDeDivulgacao");
		Property natureza = model.createProperty(cin,"natureza");
		Property palavraChave = model.createProperty(cin,"palavraChave");

		//Article
		Property pageStart = model.createProperty(bibo,"pageStart");
		Property pageEnd = model.createProperty(bibo,"pageEnd");
		Property volume = model.createProperty(bibo,"volume");
		Property issue = model.createProperty(bibo,"issue");
		Property status = model.createProperty(bibo,"status");

		//Chapter - só pageStart e pagEnd (já criados anteriormente)

		//Book
		Property isbn = model.createProperty(bibo,"isbn");
		Property numPages = model.createProperty(bibo,"numPages");
		Property numVolumes = model.createProperty(bibo,"numVolumes");
		Property edition = model.createProperty(bibo,"edition");

		int doc = professores.size()-1;
		while(doc > 0){

			Professor p = professores.get(doc);
			
			//artigo
			for (int i = 0; i < p.getArtigos().size(); i++) {
				//System.out.println(p.getArtigos().get(i).getTitulo());
				Resource publicacao = model.createResource(cin+"artigo/"+p.getArtigos().get(i).getIssn())
						//.addProperty(rdfType, "rdf:resource='http://www.cin.ufpe.br/publicacao/Artigo'")
						.addProperty(title, p.getArtigos().get(i).getTitulo())
						.addProperty(language, p.getArtigos().get(i).getIdioma())
						.addProperty(issued, String.valueOf(p.getArtigos().get(i).getAno()))
						.addProperty(uri, "")
						.addProperty(subject, "")
						.addProperty(areasDoConhecimento, "")
						.addProperty(meioDeDivulgacao, p.getArtigos().get(i).getMeioDeDivulgacao())
						.addProperty(natureza, "")
						.addProperty(palavraChave, p.getArtigos().get(i).getPalavrasChave().getPalavraChave1())
						.addProperty(palavraChave, p.getArtigos().get(i).getPalavrasChave().getPalavraChave2())
						.addProperty(palavraChave, p.getArtigos().get(i).getPalavrasChave().getPalavraChave3())
						.addProperty(palavraChave, p.getArtigos().get(i).getPalavrasChave().getPalavraChave4())
						.addProperty(palavraChave, p.getArtigos().get(i).getPalavrasChave().getPalavraChave5())
						.addProperty(palavraChave, p.getArtigos().get(i).getPalavrasChave().getPalavraChave6())
						.addProperty(pageStart, p.getArtigos().get(i).getPagInicial())
						.addProperty(pageEnd, p.getArtigos().get(i).getPagFinal())
						.addProperty(status, "");

			}
			
			//livro
			for (int i = 0; i < p.getLivros().size(); i++) {
				//System.out.println(p.getArtigos().get(i).getTitulo());
				Resource publicacao = model.createResource(cin+"livro/"+p.getLivros().get(i).getIsbn())
						//.addProperty(rdfType, "rdf:resource='http://www.cin.ufpe.br/publicacao/Artigo'")
						.addProperty(title, p.getLivros().get(i).getTitulo())
						.addProperty(language, p.getLivros().get(i).getIdioma())
						.addProperty(issued, String.valueOf(p.getLivros().get(i).getAno()))
						.addProperty(uri, "")
						.addProperty(subject, "")
						.addProperty(areasDoConhecimento, "")
						.addProperty(meioDeDivulgacao, "")
						.addProperty(natureza, p.getLivros().get(i).getNatureza())
						.addProperty(palavraChave,"")
						.addProperty(isbn, p.getLivros().get(i).getIsbn())
						.addProperty(numPages, p.getLivros().get(i).getNumeroPaginas())
						.addProperty(edition, "");

			}
			
			//capitulo
			for (int i = 0; i < p.getCapitulos().size(); i++) {
				//System.out.println(p.getArtigos().get(i).getTitulo());
				Resource publicacao = model.createResource(cin+"capitulo/"+p.getCapitulos().get(i).getIsbn())
						//.addProperty(rdfType, "rdf:resource='http://www.cin.ufpe.br/publicacao/Artigo'")
						.addProperty(title, p.getCapitulos().get(i).getTituloCapitulo())
						.addProperty(language, p.getCapitulos().get(i).getIdioma())
						.addProperty(issued, String.valueOf(p.getCapitulos().get(i).getAno()))
						.addProperty(uri, "")
						.addProperty(subject, "")
						.addProperty(areasDoConhecimento, "")
						.addProperty(meioDeDivulgacao, "")
						.addProperty(natureza, "")
						.addProperty(palavraChave,"")
						.addProperty(isbn, p.getCapitulos().get(i).getIsbn())
						.addProperty(pageStart, p.getCapitulos().get(i).getPagInicial())
						.addProperty(pageEnd, p.getCapitulos().get(i).getPagFinal());

			}

			doc--;

		}

		model.setNsPrefix("cin", cin);
		model.setNsPrefix("dcterms", dcterms);
		model.setNsPrefix("bibo", bibo);
		model.setNsPrefix("rdf", rdf);
		OutputStream output = new FileOutputStream("publicacoesRDF.xml");
		model.write(output);
		//model.write(System.out);


	}

}
