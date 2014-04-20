package exercises;

import java.io.IOException;

public class CalculateAmortizationSchedule {
	final static String[] USER_PROMPT = {
			"Please enter the amount you would like to borrow: ",
			"Please enter the annual percentage rate used to repay the loan: ",
			"Please enter the term, in years, over which the loan is repaid: "
	};
	private static double amount = 0;
	private static double apr = 0;
	private static int years = 0;
	
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
	
	private static boolean checkValidAmount(){
		boolean isValidValue = true;
		if (AmortizationSchedule.isValidBorrowAmount(amount) == false) {
			isValidValue = false;
			double range[] = AmortizationSchedule.getBorrowAmountRange();
			Utility.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
		}
		return  isValidValue;
	}
	
	private static boolean checkValidApr(){
		boolean isValidValue = true;
		if (AmortizationSchedule.isValidAPRValue(apr) == false) {
			isValidValue = false;
			double range[] = AmortizationSchedule.getAPRRange();
			Utility.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
		}
		return isValidValue;	
	}
	
	private static boolean checkValidYears(){
		boolean isValidValue = true;
		if (AmortizationSchedule.isValidTerm(years) == false) {
			isValidValue = false;
			int range[] = AmortizationSchedule.getTermRange();
			Utility.print("Please enter a positive integer value between " + range[0] + " and " + range[1] + ". ");
		}
		return isValidValue;
	}
	
	private static boolean validateInput(int i, String line){
		boolean isValidValue = true;
		try{
			switch (i) {
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
	
	public static void main(String [] args) {
		String line = "";
		boolean isValidValue = true;
		
		for (int i = 0; i< USER_PROMPT.length; ) {
			line = readInput(USER_PROMPT[i]);
			isValidValue = validateInput(i,  line);
			
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