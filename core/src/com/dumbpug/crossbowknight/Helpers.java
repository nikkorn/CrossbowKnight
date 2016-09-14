package com.dumbpug.crossbowknight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Various helpers.
 * @author nikolas.howard
 */
public class Helpers {
	
	/**
	 * Read a JSON object from file.
	 * @param file
	 * @return JSONObject
	 */
	public static JSONObject readJSONObjectFromFile(File file) {
		String rawJsonObject = Helpers.readStringFromFile(file);
		// Return our constructed JSON object.
		return new JSONObject(rawJsonObject);
	}
	
	/**
	 * Read a JSON array from file.
	 * @param file
	 * @return JSONObject
	 */
	public static JSONArray readJSONArrayFromFile(File file) {
		String rawJsonObject = Helpers.readStringFromFile(file);
		// Return our constructed JSON object.
		return new JSONArray(rawJsonObject);
	}
	
	/**
	 * Read entire file into a string.
	 * @param file
	 * @return string
	 */
	public static String readStringFromFile(File file) {
		String rawString = "";
		if(file.exists()) {
			Scanner scanner = null;
			// Set up our scanner.
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while(scanner.hasNextLine()) {
				rawString += scanner.nextLine();
			}
		} 
		return rawString;
	}
}
