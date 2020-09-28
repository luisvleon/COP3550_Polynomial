/*------------------------------------------------------------------------
#Name: Luis Leon
#Student ID: 
#COP3530- Data Structures
#Fall 2020 – We 6:00 PM - 7:40 PM
#Assignment # 3 - Polynomial addition
*/
		/* Important notes:
		* 
		*  0 Coefficients are discarded without error
		*  The program makes reductions if the same degree term 
		*  is added to the same polynomial as is show in the example (2nd polynomial)
		*  
		*  Negatives coefficients are handled by the program avoiding (+ -) symbols between terms
		*  
		*  Terms are represented as follows into the Terms dynamic array     
		*  
		*  		2x^7		= (2,7)
		*  		x^3		= (1,3)
		*  		2x		= (2,0)
		*  		5		= (5,0)
		*/

//Driver class to demonstrate the usage of the Polynomial class
public class Driver {

	public static void main(String[] args) 
	{
		// Polynomial variables to store the polynomials
		//Operands and the final polynomial after the addition
		Polynomial poly1 = new Polynomial();
		Polynomial poly2 = new Polynomial();
		Polynomial result = new Polynomial();
		
		// Add few terms to the first polynomial 
		
		// x^5 - 2x^2 + x^3 + 5
		poly1.add(1,5);		//valid term  x^5
		poly1.add(-2,2);	//valid term  -2x^2
		poly1.add(1,3);		//valid term  x^3
		poly1.add(5,0);		//valid term  5
		
		// Add few terms to the second polynomial
		// I introduced to similar values to test the program 
		// (5,0) and (-2,0) they are added together
		// I introduced also a (0,0) value to test it is omitted 		
		
		//2x^3 - x^2 + 5 + 4x^4 - 2 + 0		
		//Reduced to: 2x^3 - x^2 + 3 + 4x^4
		poly2.add(2,3);		//valid term 2x^3
		poly2.add(-1,2);	//valid term -x^2
		poly2.add(5,0);		//valid term 5x
		poly2.add(4,4);     	//valid term 4x^4
		poly2.add(-2,0);    	//Reduced term with same degree previous value
		poly2.add(0,0);     	//Discarded
		poly2.add(0,3);     	//Discarded
		
		//Call and store the result of the addition function
		result = Polynomial.addition(poly1, poly2);

		System.out.println("N terms - Two polynomial addition program\n");
		//Sort the polynomials calling the sort function
		
		if(poly1.size() > 0)Polynomial.printOut("1st Polynomial:\t\t\t\t", poly1);else System.out.println("1st polynomial no defined\n");
		if(poly2.size() > 0)Polynomial.printOut("2nd Polynomial:\t\t\t\t", poly2);else System.out.println("2nd polynomial no defined\n");
		
		if(poly1.size() > 1)Polynomial.printOut("1st Polynomial in descending order:\t", Polynomial.sort(poly1));
		if(poly2.size() > 1)Polynomial.printOut("2nd Polynomial in descending order:\t", Polynomial.sort(poly2));
		
		if(!(poly1.size() == 0 || poly2.size() == 0))Polynomial.printOut("Addition operation result:\t\t", Polynomial.sort(result));else System.out.println("No addition operation performed\n");

	}
}
