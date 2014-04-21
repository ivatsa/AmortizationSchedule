package exercises;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;

/**
 * The Class Utility.
 * 
 * Provides all the input output functionality under one hood.
 */
public class Utility {
	
	/**
	 * This method reads a line of input from the user
	 *
	 * @param userPrompt is the string to be displayed to user about the input
	 * @return the raw string read from conole 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readLine(String userPrompt) throws IOException {
		String line = "";
		Console console = System.console();
		
		if (console != null) {
			line = console.readLine(userPrompt);
		} else {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			print(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}
	
	/**
	 * This is a convenient and simpler way to call printf 
	 *
	 * @param printStr is input String passed to be printed 
	 */
	public static void print(String printStr) {
		printf("%s", printStr);
	}
	
	/**
	 * This method overrides the java provided printf to format the ouput better  
	 *
	 * @param formatString according to which the values are output to console
	 * @param args are the set of values passed
	 */
	public static void printf(String formatString, Object... args) {
		Console console = System.console();
		try {
			if (console != null) {
				console.printf(formatString, args);
			} else {
				System.out.print(String.format(formatString, args));
			}
		} catch (IllegalFormatException e) {
			System.err.print("Error printing...\n");
		}
	}
}
