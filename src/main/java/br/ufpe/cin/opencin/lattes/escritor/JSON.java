package br.ufpe.cin.opencin.lattes.escritor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.ufpe.cin.opencin.lattes.entidades.Professor;

public class JSON {

	public static void transformarJSON(Professor professor){


		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string, and save to a file
			mapper.writeValue(new File("professores.json"), professor);

			// display to console
			//System.out.println(mapper.writeValueAsString(professor));

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void transformarJSON(List<Professor> professores){


		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string, and save to a file
			mapper.writeValue(new File("professores.json"), professores);

			// display to console
			//System.out.println(mapper.writeValueAsString(professor));

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
