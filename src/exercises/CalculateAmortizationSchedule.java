package exercises;

import java.io.IOException;

public class CalculateAmortizationSchedule {

	// 
		public static void main(String [] args) {
			//making it immutable? Final?
			String[] userPrompts = {
					"Please enter the amount you would like to borrow: ",
					"Please enter the annual percentage rate used to repay the loan: ",
					"Please enter the term, in years, over which the loan is repaid: "
			};
			
			String line = "";
			double amount = 0;
			double apr = 0;
			int years = 0;
			
			// read Input
			for (int i = 0; i< userPrompts.length; ) {
				String userPrompt = userPrompts[i];
				try {
					line = utility.readLine(userPrompt);
				} catch (IOException e) {
					utility.print("An IOException was encountered. Terminating program.\n");
					return;
				}
			//end read input
			// validation of 
				boolean isValidValue = true;
				try {
					switch (i) {
					case 0:
						amount = Double.parseDouble(line);
						if (AmortizationSchedule.isValidBorrowAmount(amount) == false) {
							isValidValue = false;
							double range[] = AmortizationSchedule.getBorrowAmountRange();
							utility.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
						}
						break;
					case 1:
						apr = Double.parseDouble(line);
						if (AmortizationSchedule.isValidAPRValue(apr) == false) {
							isValidValue = false;
							double range[] = AmortizationSchedule.getAPRRange();
							utility.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
						}
						break;
					case 2:
						years = Integer.parseInt(line);
						if (AmortizationSchedule.isValidTerm(years) == false) {
							isValidValue = false;
							int range[] = AmortizationSchedule.getTermRange();
							utility.print("Please enter a positive integer value between " + range[0] + " and " + range[1] + ". ");
						}
						break;
					//default case?
						
					}
				} catch (NumberFormatException e) {
					isValidValue = false;
				}
				if (isValidValue) {
					i++;
				} else {
					utility.print("An invalid value was entered.\n");
				}
			}
			
			try {
				AmortizationSchedule as = new AmortizationSchedule(amount, apr, years);
				as.outputAmortizationSchedule();
			} catch (IllegalArgumentException e) {
				utility.print("Unable to process the values entered. Terminating program.\n");
			}
		}
}
