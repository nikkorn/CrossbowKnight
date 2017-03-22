package com.dumbpug.crossbowknight;

/**
 * Outputs debug info to the console.
 * @author nikolas.howard
 */
public class Debug {
	
	/**
	 * The classifications of output.
	 * @author nikolas.howard
	 */
	public enum Type {
		INFO,
		WARNING,
		ERROR
	}
	
	/**
	 * Write out to the console if debug output is enabled. 
	 * @param type
	 * @param message
	 */
	public static void out(Debug.Type type, String message) {
		// We do not want to write to the console unless set in our constants.
		if(!C.WRITE_DEBUG_OUTPUT) {
			return;
		}
		// Prefix our message with its classifications.
		String typePrefix = "";
		switch(type) {
			case ERROR:
				typePrefix = "--ERROR--   : ";
				break;
			case INFO:
				typePrefix = "--INFO--    : ";
				break;
			case WARNING:
				typePrefix = "--WARNING-- : ";
				break;
		}
		// Spit our message out to the console.
		System.out.println(typePrefix + message);
	}
}
