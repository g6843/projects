package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import web.UploadServlet;

public class Service {

	private final static String FILENAME = "temp.txt";
	private final static String DESTINATION_PATH = "/ProjectHackathon/PosterMaker/WebContent/images";
	private final static String URL = "http://localhost:8080/PosterMaker/images";
	public TextDTO getText() {
		// call method and convert to DTO, return list of DTOs

		File dir = new File(UploadServlet.SAVE_DIR + UploadServlet.SRC_DIR);
		File file = null;
		if(dir.exists() && dir.isDirectory()){
			if(dir.listFiles().length > 0){
				file = dir.listFiles()[0].getAbsoluteFile();
			}
		}

		try {
			PDDocument doc = PDDocument.load(file);

			String text = new PDFTextStripper().getText(doc);

			PDFParser pdfParser = new PDFParser();

			BufferedWriter bufferedWriter = null;

			bufferedWriter = new BufferedWriter(new FileWriter(FILENAME));
			bufferedWriter.write(text);
			bufferedWriter.close();

			return convertToTextDTO(pdfParser.parseText(FILENAME));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private TextDTO convertToTextDTO(List<PDFObject> pdfObjects) {
		ResultDTO[] dtoObjects = new ResultDTO[10];
		TextDTO textDTO = new TextDTO();
		int i = 0;
		for (PDFObject pdfObject : pdfObjects) {
			ResultDTO result = new ResultDTO();
			result.setBody(pdfObject.value);
			result.setHeading(pdfObject.key);
			dtoObjects[i] = result;
			i++;
		}
		textDTO.setResult(dtoObjects);
		return textDTO;
	}

	public ImageDTO getImages() {
		
		File source = new File(UploadServlet.SAVE_DIR + UploadServlet.IMG_DIR);
		File destination = new File(DESTINATION_PATH);
		
		try{
			destination.mkdirs();
			File finalsource = new File(source.getAbsolutePath());
			File finaldestination = new File(destination.getAbsolutePath());
			FileUtils.copyDirectory( finalsource, finaldestination);			
		}
		catch(Exception e){	
			
		}
		int i=0;
		OutputDTO[] outputDtos = new OutputDTO[10];
		for (final File fileEntry : destination.listFiles()) {
			OutputDTO output = new OutputDTO(); 
	        output.setName(fileEntry.getName());
	        output.setUrl(URL + File.separator + output.getName());
	        outputDtos[i] = output;
	        i++;
	    }
		ImageDTO imageDTO = new ImageDTO();
		
		imageDTO.setImages(outputDtos);
		return imageDTO;
	}

}
