package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read symptoms file
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * create an objet with the filepath
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}

	/**
	 * open the file , read the file of symptoms, put the symptoms in a List<>, close the file
	 * @return a List<Symptoms>
	 */
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<>();

		if (filepath != null) {
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(filepath);
				BufferedReader reader = new BufferedReader (fileReader);
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} finally {
				try {
					if (fileReader != null) {
						fileReader.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(2);
				}
			}
		}
		return result;
	}
}
