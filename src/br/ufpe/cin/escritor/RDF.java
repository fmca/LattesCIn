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

	public static void transformarRDF(List<Professor> professores) throws FileNotFoundException{

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

}
