package practice.utils.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import practice.utils.dao.ExcelReader;

@Service
public class ExcelReaderImpl implements ExcelReader {
	public static final String NEW_LINE_CHARACTER = "\r\n";
	public static final String JOIN_LINE_CHARACTER = "\"";
	Logger logger = LoggerFactory.getLogger(ExcelReaderImpl.class);

	public void removeNewLines(String filename, String delimiter) {
		File file = new File(filename);
		try (InputStream inputStream = new FileInputStream(file);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
			String line = null;
			StringBuffer stringBuffer = new StringBuffer();
			boolean hasNewLine = false;
			while ((line = bufferedReader.readLine()) != null) {
				String[] splits = line.split(delimiter);
				for (int i = 0; i < splits.length; i++) {
					if (splits[i].startsWith(JOIN_LINE_CHARACTER)) {
						// new line character in this cell.
						splits[i] = splits[i].replace(NEW_LINE_CHARACTER, "");
						hasNewLine = true;
						stringBuffer.append(splits[i]);
					} else {
						if (splits[i].endsWith(JOIN_LINE_CHARACTER)) {
							hasNewLine = false;
						}
						stringBuffer.append(splits[i] + delimiter);
					}
				}
				if (!hasNewLine && stringBuffer != null) {
					if(stringBuffer.length() > 0 && stringBuffer.substring(stringBuffer.length()-delimiter.length()).equals(delimiter)) {
						stringBuffer = stringBuffer.replace(stringBuffer.length()-delimiter.length(), stringBuffer.length(), "");
					}
					logger.info("****** completed row looks like: " + stringBuffer.toString());
					stringBuffer = new StringBuffer();
				}
			}
		} catch (IOException ex) {
			logger.error("Exception occurred while parsing: ", ex);
			throw new RuntimeException(ex);
		}
	}

}
