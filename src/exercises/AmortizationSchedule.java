package exercises;

import java.lang.Math;
import java.lang.IllegalArgumentException;

/**
 * The Class AmortizationSchedule.
 *
 * This class handles all the aspects of calculations related to generate a 
 * table of scheduled payments.
 */
public class AmortizationSchedule {

	//Input
	private long amountBorrowed = 0;		// in cents
	private double apr = 0d;				// in percentage
	private int initialTermMonths = 0;		// in months
	
	private double monthlyInterest = 0d;	// in percentage
	private long monthlyPaymentAmount = 0;	// in cents

	//Constants
	private final double MONTHLY_INTEREST_DIVISOR = 12d * 100d;
	public static final double[] BORROW_AMOUNT_RANGE = new double[] { 0.01d, 
		1000000000000d };
	public static final double[] APR_RANGE = new double[] { 0.000001d, 100d };
	public static final int[] TERM_RANGE = new int[] { 1, 1000000 };

	/**
	 * Calculates monthly payment using formula
	 *  M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
	 *	Where:
	 *	P = Principal
	 *	I = Interest
	 *	J = Monthly Interest in decimal form:  I / (12 * 100)
	 *	N = Number of months of loan
	 *	M = Monthly Payment Amount
	 *
	 * @return the monthly payments to make
	 */
	private long calculateMonthlyPayment() {
		monthlyInterest = apr / MONTHLY_INTEREST_DIVISOR;
		double tmp = 1/(1d + monthlyInterest);
		tmp = Math.pow(tmp, initialTermMonths);
		tmp = 1/(1d - tmp);
		double monthlyPayment = amountBorrowed * monthlyInterest * tmp;

		return Math.round(monthlyPayment);
	}

	/**
	 * Prints the header line in the output on console.
	 */
	private void printHeaders(){
		String formatString = "%1$-20s%2$-20s%3$-20s%4$s,%5$s,%6$s\n";
		Utility.printf(formatString,
				"PaymentNumber", "PaymentAmount", "PaymentInterest",
				"CurrentBalance", "TotalPayments", "TotalInterestPaid");
	}

	/**
	 * Output amortization schedule creates the amortization table
	 * The Format of the table is:
	 * The first column identifies the payment number.
	 * The second column contains the amount of the payment.
	 * The third column shows the amount paid to interest.
	 * The fourth column has the current balance.  The total payment amount and
	 * the interest paid fields.
	 */
	public void outputAmortizationSchedule() { 

		long balance = amountBorrowed;
		int paymentNumber = 0;
		long totalPayments = 0;
		long totalInterestPaid = 0;
		long curMonthlyInterest = 0;
		long curPayoffAmount = 0;
		long curMonthlyPaymentAmount= 0;
		long curMonthlyPrincipalPaid = 0;
		long curBalance =0;
		printHeaders();

		// output is in dollars
		String formatString = "%1$-20d%2$-20.2f%3$-20.2f%4$.2f,%5$.2f,%6$.2f\n";
		Utility.printf(formatString, paymentNumber++, 0d, 0d,
				((double) amountBorrowed) / 100d,
				((double) totalPayments) / 100d,
				((double) totalInterestPaid) / 100d);

		final int maxNumberOfPayments = initialTermMonths + 1;
		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {
			
			curMonthlyInterest = calculateMonthlyInterest(balance);
			
			curPayoffAmount = calculateCurrentPayOffAmount(balance,
					curMonthlyInterest);
			
			curMonthlyPaymentAmount = calculateCurrentMonthlyPaymentAmount
					(curPayoffAmount);

			// it's possible that the calculated monthlyPaymentAmount is 0,
			// or the monthly payment only covers the interest payment - i.e. 
			// no principal 
			// 
			if ((paymentNumber == maxNumberOfPayments) &&
					((curMonthlyPaymentAmount == 0) || 
							(curMonthlyPaymentAmount == curMonthlyInterest))) {
				curMonthlyPaymentAmount = curPayoffAmount;
			}

			curMonthlyPrincipalPaid = calculateCurrentMonthlyPrincipalPaid
					(curMonthlyPaymentAmount, curMonthlyInterest);

			curBalance = calculateCurrentBalance(balance,
					curMonthlyPrincipalPaid);

			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;

			// output is in dollars
			Utility.printf(formatString, paymentNumber++,
					((double) curMonthlyPaymentAmount) / 100d,
					((double) curMonthlyInterest) / 100d,
					((double) curBalance) / 100d,
					((double) totalPayments) / 100d,
					((double) totalInterestPaid) / 100d);

			// Set P equal to Q and go back to Step 1: You thusly loop around
			// until the value Q (and hence P) goes to zero.
			balance = curBalance;
		}
	}

	/**
	 * Instantiates a new amortization schedule.
	 *
	 * @param amount the amount
	 * @param interestRate the interest rate
	 * @param years the years
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public AmortizationSchedule(double amount, double interestRate, int years) 
			throws IllegalArgumentException {

		if ((isValidBorrowAmount(amount) == false) ||
				(isValidAPRValue(interestRate) == false) ||
				(isValidTerm(years) == false)) {
			throw new IllegalArgumentException();
		}

		amountBorrowed = Math.round(amount * 100);
		apr = interestRate;
		initialTermMonths = years * 12;

		monthlyPaymentAmount = calculateMonthlyPayment();

		// the following shouldn't happen with the available valid ranges
		// for borrow amount, apr, and term; however, without range validation,
		// monthlyPaymentAmount as calculated by calculateMonthlyPayment()
		// may yield incorrect values with extreme input values
		if (monthlyPaymentAmount > amountBorrowed) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks if borrowing amount is valid.
	 *
	 * @param amount in dollars
	 * @return true, if it is valid borrow amount
	 */
	public static boolean isValidBorrowAmount(double amount) {
		double range[] = BORROW_AMOUNT_RANGE;
		return ((range[0] <= amount) && (amount <= range[1]));
	}

	/**
	 * Checks if Annual Percentage Rate (APR) value is valid.
	 *
	 * @param rate the rate
	 * @return true, if it is valid APR value
	 */
	public static boolean isValidAPRValue(double rate) {
		double range[] = APR_RANGE;
		return ((range[0] <= rate) && (rate <= range[1]));
	}

	/**
	 * Checks if Loan term is valid.
	 *
	 * @param years the years
	 * @return true, if it is valid term
	 */
	public static boolean isValidTerm(int years) {
		int range[] = TERM_RANGE;
		return ((range[0] <= years) && (years <= range[1]));
	}
	/**
	 * Calculate monthly interest using formula H = P x J, this is your current
	 * monthly interest
	 *
	 * @param balance principal amount
	 * @return the current monthly interest
	 */
	public long calculateMonthlyInterest(long balance) {
		return Math.round(((double) balance) * monthlyInterest);
	}

	/**
	 * Calculate the amount required to payoff the loan
	 *
	 * @param balance principal amount
	 * @param curMonthlyInterest is the current monthly interest 
	 * @return the amount to be paid this month
	 */
	public long calculateCurrentPayOffAmount(long balance,
			long curMonthlyInterest) {
		return balance + curMonthlyInterest;
	}
	// 
	/**
	 * Calculates the amount to payoff the remaining balance may be less than
	 * the calculated monthlyPaymentAmount
	 *
	 * @param currentPayoffAmount is amount to be paid for the month
	 * @return the amount remaining to pay off the Loan
	 */
	public long calculateCurrentMonthlyPaymentAmount(long currentPayoffAmount) {
		return Math.min(monthlyPaymentAmount, currentPayoffAmount);
	}

	/**
	 * Calculates C = M - H, this is the monthly payment minus your monthly 
	 * interest, so it is the amount of principal you pay for that month
	 * 
	 * @param currentMonthlyPaymentAmount is the current monthly payment amount
	 * @param currentMonthlyInterest is the current monthly interest
	 * @return the amount of principal you pay for that month
	 */
	public long calculateCurrentMonthlyPrincipalPaid(
			long currentMonthlyPaymentAmount, long currentMonthlyInterest) {
		return currentMonthlyPaymentAmount - currentMonthlyInterest;
	}

	/**
	 * Calculates Q = P - C, this is the new balance of your principal of your
	 * loan.
	 *
	 * @param balance the balance
	 * @param currentMontlyPrincipalPaid the current monthly principal paid
	 * @return the current balance aka new balance of principal  
	 */
	public long calculateCurrentBalance(long balance, 
			long currentMontlyPrincipalPaid) {
		return balance - currentMontlyPrincipalPaid;
	}
}