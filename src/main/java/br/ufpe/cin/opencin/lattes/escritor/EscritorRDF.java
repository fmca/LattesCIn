package br.ufpe.cin.opencin.lattes.escritor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import br.ufpe.cin.opencin.lattes.entidades.Autor;
import br.ufpe.cin.opencin.lattes.entidades.Professor;
import br.ufpe.cin.opencin.lattes.util.Utils;

public class EscritorRDF {

	public static void transformarEmRDFProfessor(List<Professor> professores, String fileName) throws IOException{

		String propriedade = "http://www.cin.ufpe.br/opencin/";

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
		while(doc >= 0){
			Resource rc = model.createResource("http://www.cin.ufpe.br/opencin/academic");

			model.createResource(professores.get(doc).getProfessorID())
					.addProperty(nomeCompleto, professores.get(doc).getNomeCompleto())
					.addProperty(academicDegree, "")
					.addProperty(birthDate, "")
					.addProperty(gender, professores.get(doc).getGender())
					.addProperty(homepage, professores.get(doc).getHomepage())
					.addProperty(lattes, professores.get(doc).getLattes())
					.addProperty(office, professores.get(doc).getOffice())
					.addProperty(phone, professores.get(doc).getPhone())
					.addProperty(email, professores.get(doc).getEmail())
					.addProperty(nameCitation, professores.get(doc).getNomeCitacao())
					.addProperty(RDF.type, rc);

			//System.out.println(doc);
			doc--;
		}
		model.setNsPrefix("cin", propriedade);
		OutputStream output = new FileOutputStream(fileName);
		model.write(output);
		//model.write(System.out);

	}

	public static void transformarEmRDFPublicacao(List<Professor> professores, String fileName) throws FileNotFoundException{



		Model model = ModelFactory.createDefaultModel();

		//String dcterms = "http://purl.org/dc/terms/";
		//String bibo = "http://purl.org/ontology/bibo/";
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		String cin = "http://www.cin.ufpe.br/opencin/";
		
		Property title = model.createProperty(cin,"title");
		Property language = model.createProperty(cin,"language");
		Property local = model.createProperty(cin,"local");
		Property issued = model.createProperty(cin,"issued");
		Property uri = model.createProperty(cin,"uri");
		Property subject = model.createProperty(cin,"subject");
		Property author = model.createProperty(cin, "author");
		Property areasDoConhecimento = model.createProperty(cin,"knowledgeArea");
		Property meioDeDivulgacao = model.createProperty(cin,"release");
		Property natureza = model.createProperty(cin,"nature");
		Property palavraChave = model.createProperty(cin,"keyWord");
		Property idprofessor = model.createProperty(cin, "idProfessor");

		//Article
		Property pageStart = model.createProperty(cin,"pageStart");
		Property pageEnd = model.createProperty(cin,"pageEnd");
		//Property volume = model.createProperty(cin,"volume");
		//Property issue = model.createProperty(cin,"issue");
		Property status = model.createProperty(cin,"status");

		//Chapter - só pageStart e pagEnd (já criados anteriormente)

		//Book
		Property isbn = model.createProperty(cin,"isbn");
		Property numPages = model.createProperty(cin,"numPages");
		//Property numVolumes = model.createProperty(cin,"numVolumes");
		Property edition = model.createProperty(cin,"edition");


		//int doc = professores.size()-32;
		int doc = professores.size()-1;
		int v = 0;

		Resource rcArtigo = model.createResource("http://www.cin.ufpe.br/opencin/article");
		Resource rcLivro = model.createResource("http://www.cin.ufpe.br/opencin/book");
		Resource rcCapitulo = model.createResource("http://www.cin.ufpe.br/opencin/chapter");

		while(doc >= 0){

			Professor p = professores.get(doc);
			Resource rcAtual = model.createResource(p.getProfessorID());
			if(!p.getProfessorID().equals("")){//professor sérgio ainda não está
				//artigo
				
				for (int i = 0; i < p.getArtigos().size(); i++) {
					Resource article = model.createResource(cin+"article/"+v)
							//.addProperty(nomeProfessor, p.getNomeCompleto())
							.addProperty(idprofessor, rcAtual)
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
							.addProperty(status, "")
							.addProperty(local, p.getArtigos().get(i).getTituloPeriodicoRevista())
							.addProperty(RDF.type, rcArtigo);
					v++;
					
					adicionarAutores(author, article, p.getArtigos().get(i).getAutores(), professores);


				}

				//livro
				for (int i = 0; i < p.getLivros().size(); i++) {

					Resource livro = model.createResource(cin+"book/"+v)
							.addProperty(idprofessor, rcAtual)
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
							.addProperty(edition, "")
							.addProperty(RDF.type, rcLivro);
					v++;
					
					adicionarAutores(author, livro, p.getLivros().get(i).getAutores(), professores);

				}

				//capitulo
				for (int i = 0; i < p.getCapitulos().size(); i++) {

					Resource capitulo = model.createResource(cin+"chapter/"+v)
							.addProperty(idprofessor, rcAtual)
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
							.addProperty(pageEnd, p.getCapitulos().get(i).getPagFinal())
							.addProperty(RDF.type, rcCapitulo);
					v++;
					
					adicionarAutores(author, capitulo, p.getCapitulos().get(i).getAutores(), professores);

				}
			}
			doc--;


		}
		model.setNsPrefix("cin", cin);
		model.setNsPrefix("rdf", rdf);
		OutputStream output = new FileOutputStream(fileName);
		model.write(output);


	}

	public static void transformarRDFOrientacoes(List<Professor> professores, String fileName) throws FileNotFoundException{

		String cin = "http://www.cin.ufpe.br/opencin/";
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

		Model model = ModelFactory.createDefaultModel();

		//TG
		Property title = model.createProperty(cin,"title");
		Property idprofessor = model.createProperty(cin, "idProfessor");
		Property year = model.createProperty(cin,"year");
		Property language = model.createProperty(cin,"language");
		Property student = model.createProperty(cin,"student");
		Property university = model.createProperty(cin,"university");
		Property course = model.createProperty(cin,"course");

		//Mestrado e Doutorado
		Property fundingAgency = model.createProperty(cin,"fundingAgency");
		Property masterType = model.createProperty(cin,"masterType"); //só mestrado
		Property supervisionType = model.createProperty(cin,"supervisionType");


		Resource rcTg = model.createResource("http://www.cin.ufpe.br/opencin/DiplomaThesis");
		Resource rcMestrado = model.createResource("http://www.cin.ufpe.br/opencin/MastersThesis");
		Resource rcDoutorado = model.createResource("http://www.cin.ufpe.br/opencin/PhDThesis");

		int doc = professores.size()-1;
		int v = 0;
		while(doc >= 0){

			Professor p = professores.get(doc);
			Resource rcAtual = model.createResource(p.getProfessorID());
			if(!p.getProfessorID().equals("")){
				//doutorado
				for (int i = 0; i < p.getDoutorado().size() ; i++) {

					model.createResource(cin+"PhDThesis/"+v)
							.addProperty(idprofessor, rcAtual)
							.addProperty(title, p.getDoutorado().get(i).getTitulo())
							.addProperty(year, p.getDoutorado().get(i).getAno())
							.addProperty(language, p.getDoutorado().get(i).getIdioma())
							.addProperty(student, p.getDoutorado().get(i).getNome_orientado())
							.addProperty(course, p.getDoutorado().get(i).getCurso())
							.addProperty(supervisionType, p.getMestrado().get(i).getTipo_orientacao())
							.addProperty(fundingAgency, p.getMestrado().get(i).getAgencia_financiadora())
							.addProperty(university, p.getDoutorado().get(i).getInstituicao())
							.addProperty(RDF.type, rcDoutorado);
					v++;
				}

				//Mestrado
				for (int i = 0; i < p.getMestrado().size() ; i++) {

					model.createResource(cin+"MastersThesis/"+v)
							.addProperty(idprofessor, rcAtual)
							.addProperty(title, p.getMestrado().get(i).getTitulo())
							.addProperty(year, p.getMestrado().get(i).getAno())
							.addProperty(language, p.getMestrado().get(i).getIdioma())
							.addProperty(student, p.getMestrado().get(i).getNome_orientado())
							.addProperty(course, p.getMestrado().get(i).getCurso())
							.addProperty(masterType, p.getMestrado().get(i).getTipo_mestrado())
							.addProperty(supervisionType, p.getMestrado().get(i).getTipo_orientacao())
							.addProperty(fundingAgency, p.getMestrado().get(i).getAgencia_financiadora())
							.addProperty(university, p.getMestrado().get(i).getInstituicao())
							.addProperty(RDF.type, rcMestrado);
					v++;
				}

				//TG
				for (int i = 0; i < p.getTg().size() ; i++) {

					model.createResource(cin+"DiplomaThesis/"+v)
							.addProperty(idprofessor, rcAtual)
							.addProperty(title, p.getTg().get(i).getTitulo())
							.addProperty(year, p.getTg().get(i).getAno())
							.addProperty(language, p.getTg().get(i).getIdioma())
							.addProperty(student, p.getTg().get(i).getNome_orientado())
							.addProperty(course, p.getTg().get(i).getCurso())
							.addProperty(university, p.getTg().get(i).getInstituicao())
							.addProperty(RDF.type, rcTg);
					v++;
				}

			}
			doc--;
		}

		model.setNsPrefix("cin", cin);
		model.setNsPrefix("rdf", rdf);
		OutputStream output = new FileOutputStream(fileName);
		model.write(output);

	}
	
	
	private static void adicionarAutores(Property authorProperty, Resource resource, List<Autor> autores, List<Professor> professoresID){
		for(Autor autor : autores){
			Professor p = Utils.procurarProfessorPorNome(autor.getNomeCompleto(), professoresID);
			if(p==null){
				resource.addProperty(authorProperty, autor.getNomeCompleto());
			}else{
				resource.addProperty(authorProperty, resource.getModel().createResource(p.getProfessorID()));
			}
		}
	}
}
