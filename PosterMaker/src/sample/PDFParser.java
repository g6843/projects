package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HEAD;

public class PDFParser {
	List<PDFObject> objects;
	private static final String[] HEADINGS = {"TITLE", "ABSTRACT",
			"INTRODUCTION", "EDGE-BASED STRUCTURE FEATURE EXTRACTION",
			"FEATURE TRANSFORMATION AND SELECTION", "EXAMPLE APPLICATION",
			"DESIGN DECISIONS", "Experiments and Comparisons", "CONCLUSIONS",
			"REFERENCES"};

	public PDFParser() {
		objects = new ArrayList<PDFObject>();
	}

	public List<PDFObject> parseText(String filename) {
		BufferedReader bufferedReader = null;
		Boolean flag = false;
		String str = null, key = null, previousKey = "TITLE";
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
			int i = 1;
			String temp = "";
			while ((str = bufferedReader.readLine()) != null) {
				if (i < HEADINGS.length && ((key = contains(str)) != null)
						&& !flag) {
					objects.add(new PDFObject(previousKey, temp));
					previousKey = key;
					i++;
					temp = "";
					flag = true;
				} else {
					temp += str;
					flag = false;
				}
			}
			objects.add(new PDFObject(previousKey, temp));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return objects;
	}

	private String contains(String str) {
		for (int i = 0; i < HEADINGS.length; i++) {
			if (str.contains(HEADINGS[i])) {
				return (HEADINGS[i]);
			}
		}
		return null;
	}
}
