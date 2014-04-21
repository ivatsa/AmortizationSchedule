package exercises;

import java.io.IOException;

/**
 * The Class CalculateAmortizationSchedule.
 * 
 * Driver class to exercise Amortization Schedule
 */
public class CalculateAmortizationSchedule {
	
	//Prompt Strings
	final static String[] USER_PROMPT = {
			"Please enter the amount you would like to borrow: ",
			"Please enter the annual percentage rate used to repay the loan: ",
			"Please enter the term, in years, over which the loan is repaid: "
	};
	
	private static double amount = 0;
	private static double apr = 0;
	private static int years = 0;
	
	/**
	 * Read input from the user via console
	 *
	 * @param userPrompt to be displayed to user
	 * @return Input user provided
	 */
	private static String readInput(String userPrompt){
		String line = "";
		try {
			line = Utility.readLine(userPrompt);
		} catch (IOException e) {
			Utility.print("An IOException was encountered. Terminating program.\n");
			return null;
		}
		return line;
	}
	
	/**
	 *  Checks if borrowing amount is valid and print appropriate message when
	 *  invalid so that user can re-enter the values 
	 *
	 * @return true, if valid
	 */
	private static boolean checkValidAmount(){
		boolean isValidValue = true;
		if (AmortizationSchedule.isValidBorrowAmount(amount) == false) {
			isValidValue = false;
			double range[] = AmortizationSchedule.BORROW_AMOUNT_RANGE;
			Utility.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
		}
		return  isValidValue;
	}
	
	/**
	 *  Checks if Annual Percentage Rate (APR) is valid and print appropriate 
	 *  message when invalid so that user can re-enter the values.
	 *
	 * @return true, if valid
	 */
	private static boolean checkValidApr(){
		boolean isValidValue = true;
		if (AmortizationSchedule.isValidAPRValue(apr) == false) {
			isValidValue = false;
			double range[] = AmortizationSchedule.APR_RANGE;
			Utility.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
		}
		return isValidValue;	
	}
	
	/**
	 *  Checks if Loan term is valid and print appropriate message when
	 *  invalid so that user can re-enter the values
	 *
	 * @return true, if valid
	 */
	private static boolean checkValidYears(){
		boolean isValidValue = true;
		if (AmortizationSchedule.isValidTerm(years) == false) {
			isValidValue = false;
			int range[] = AmortizationSchedule.TERM_RANGE;
			Utility.print("Please enter a positive integer value between " + range[0] + " and " + range[1] + ". ");
		}
		return isValidValue;
	}
	
	/**
	 * Validate and stores the appropriate values input by user
	 *
	 * @param msgNumber identifies which value is being entered by user
	 * @param Line is raw input from user  
	 * @return true, if valid successfully recorded
	 */
	private static boolean validateAndStoreInput(int msgNumber, String line){
		boolean isValidValue = true;
		try{
			switch (msgNumber) {
			case 0:
				amount = Double.parseDouble(line);
				isValidValue = checkValidAmount();
				break;
			case 1:
				apr = Double.parseDouble(line);
				isValidValue = checkValidApr();
				break;
			case 2:
				years = Integer.parseInt(line);
				isValidValue = checkValidYears();
				break;
			default:
				Utility.print("Program in an invalid. Terminating...\n");
				System.exit(0);
			}
		} catch (NumberFormatException e) {
			isValidValue = false;
		}
		return isValidValue;
	}
	
	/**
	 * The main method
	 *
	 * @param args[] is array of Commandline arguments passed to program 
	 */
	public static void main(String [] args) {
		String line = "";
		boolean isValidValue = true;
		
		for (int i = 0; i< USER_PROMPT.length; ) {
			line = readInput(USER_PROMPT[i]);
			isValidValue = validateAndStoreInput(i,  line);
			
			if (isValidValue)
				i++;
			else 
				Utility.print("An invalid value was entered.\n");
		}
		
		try {
			AmortizationSchedule as = new AmortizationSchedule(amount, apr, years);
			as.outputAmortizationSchedule();
		} catch (IllegalArgumentException e) {
			Utility.print("Unable to process the values entered. Terminating program.\n");
		}
	}
}