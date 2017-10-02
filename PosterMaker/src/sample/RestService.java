package sample;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/pdf")
public class RestService {

	private Service service = new Service();

	@GET
	@Path("/text")
	@Produces(MediaType.TEXT_PLAIN)
	public String getText() {
		TextDTO textDTO =  service.getText();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			// Convert object to JSON string
			jsonInString = mapper.writeValueAsString(textDTO);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonInString;
		
	}

	@GET
	@Path("/images")
	public String getImages() {
		ImageDTO imageDTO = service.getImages();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			// Convert object to JSON string
			jsonInString = mapper.writeValueAsString(imageDTO);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonInString;
		// /WebContent/images
	}
}
