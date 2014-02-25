package br.ufpe.cin.escritor;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.ufpe.cin.entidades.Professor;

public class JSON {
	
public static void transformarJSON(Professor professor){
	
 
	ObjectMapper mapper = new ObjectMapper();
 
	try {
 
		// convert user object to json string, and save to a file
		mapper.writeValue(new File("bernadette farias.json"), professor);
 
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
