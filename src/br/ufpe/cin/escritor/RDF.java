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
		Property nomeCitacoes = model.createProperty(propriedade, "nomeCitacao");
		Property nacionalidade = model.createProperty(propriedade, "nacionalidade");
		Property paisNascimento = model.createProperty(propriedade, "paisNascimento");
		Property UFNascimento = model.createProperty(propriedade, "UFNascimento");
		Property cidadeNascimento = model.createProperty(propriedade, "cidadeNascimento");

		//Iterator i = professores.iterator();
		int doc = professores.size()-1;
		while(doc > 0){

			Resource docente = model.createResource(recurso+professores.get(doc).getNomeCompleto())
					.addProperty(nomeCompleto, professores.get(doc).getNomeCompleto())
					.addProperty(nomeCitacoes, professores.get(doc).getNomeCitacoes())
					.addProperty(nacionalidade, professores.get(doc).getNacionalidade())
					.addProperty(paisNascimento, professores.get(doc).getPaisNascimento())
					.addProperty(UFNascimento, professores.get(doc).getUFNascimento())
					.addProperty(cidadeNascimento, professores.get(doc).getCidadeNascimento());
			//System.out.println(doc);
			doc--;
		}
		model.setNsPrefix("cin", propriedade);
		OutputStream output = new FileOutputStream("professoresRDF.xml");
		model.write(output);

	}

}
