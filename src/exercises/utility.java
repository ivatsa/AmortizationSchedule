package exercises;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;

public class utility {
	//private static Console console = System.console();
	
	public static String readLine(String userPrompt) throws IOException {
		String line = "";
		Console console = System.console();
		
		if (console != null) {
			line = console.readLine(userPrompt);
		} else {
			// print("console is null\n");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			print(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}
	
	public static void print(String s) {
		printf("%s", s);
	}
	
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
