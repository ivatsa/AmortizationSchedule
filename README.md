AmortizationSchedule
====================

##Build an amortization schedule program using Java. 

The program should prompt the user for
- The amount he or she is borrowing,
- The annual percentage rate used to repay the loan,
- The term, in years, over which the loan is repaid.  

The output should include:
- The first column identifies the payment number.
- The second column contains the amount of the payment.
- The third column shows the amount paid to interest.
- The fourth column has the current balance.  The total payment amount and the interest paid fields.

Use appropriate variable names and comments. You choose how to display the output (i.e. Web, console).  
Amortization Formula
This will get you your monthly payment.
M = P * (J / (1 - (Math.pow(1/(1 + J), N))));

Where:
- P = Principal
- I = Interest
- J = Monthly Interest in decimal form:  I / (12 * 100)
- N = Number of months of loan
- M = Monthly Payment Amount

To create the amortization table, create a loop in your program and follow these steps:
- Calculate H = P x J, this is your current monthly interest
- Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
- Calculate Q = P - C, this is the new balance of your principal of your loan.
- Set P equal to Q and go back to Step 1: You loop around until the value Q (and hence P) goes to zero.


##Refactoring:
##### + Split the class according to functionalities

1. Class AmortizationSchedule
 * The core class which handles all the aspects of calculations related to generate a table of scheduled payments.

2. Class Utility
 * Provides all the console related (input/output) functionality under one hood.
 
3. Class CalculateAmortizationSchedule
 * Driver class to exercise Amortization Schedule

##### + Renamed variables following the Java naming conventions
##### + Modularized code to make it more readable and ofcourse reusable
##### + Documented each function (JDoc style) which provides neat discricption box as you hover over methods.
