AmortizationSchedule
====================

Exercise Details:
Build an amortization schedule program using Java. 

The program should prompt the user for
		the amount he or she is borrowing,
		the annual percentage rate used to repay the loan,
		the term, in years, over which the loan is repaid.  

The output should include:
		The first column identifies the payment number.
		The second column contains the amount of the payment.
		The third column shows the amount paid to interest.
		The fourth column has the current balance.  The total payment amount and the interest paid fields.

Use appropriate variable names and comments.  You choose how to display the output (i.e. Web, console).  
Amortization Formula
This will get you your monthly payment.  Will need to update to Java.
M = P * (J / (1 - (Math.pow(1/(1 + J), N))));

Where:
P = Principal
I = Interest
J = Monthly Interest in decimal form:  I / (12 * 100)
N = Number of months of loan
M = Monthly Payment Amount

To create the amortization table, create a loop in your program and follow these steps:
1.      Calculate H = P x J, this is your current monthly interest
2.      Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
3.      Calculate Q = P - C, this is the new balance of your principal of your loan.
4.      Set P equal to Q and go back to Step 1: You loop around until the value Q (and hence P) goes to zero.

