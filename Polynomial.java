/*------------------------------------------------------------------------
#Name: Luis Leon
#Student ID: 
#COP3530- Data Structures
#Fall 2020 – We 6:00 PM - 7:40 PM
#Assignment # 3 - Polynomial addition
*/
import java.util.ArrayList;
import java.util.*;

//Class to represent a polynomial into an ArrayList data structure
public class Polynomial
{	
	//Instance variable to store the polynomial terms
	private ArrayList<Term> terms = new ArrayList<Term>();
	
	//Empty constructor - Do nothing in case of a term with no parameters
	public Polynomial() {} 
	
	public void add(int coef, int exp)
	{	
		//Store temporal array of exponents for polynomial reduction
		ArrayList<Integer> used = new ArrayList<Integer>();
		
		if(coef != 0)
		{
			//Iterate all the exponents for reduction process
			for(int i = 0; i < terms.size(); i++)
			{
				//Add the exponent to the exponents ArrayList
				used.add(terms.get(i).getExp());
			}
	
			//If any terms contains the term is been added then
			if(used.contains(exp))
			{
				for(int i = 0; i < terms.size(); i++)
				{
					//Locate the term
					if(exp == terms.get(i).getExp())
					{
						// Add the coefficient value to the actual term
						// to avoid to have two terms with the same degree
						terms.set(i, new Term(terms.get(i).getCoef() + coef,exp));
					}
				}
			}
			else 
			{
				//If the actual term exponent been added is not used before, just add it 
				if(!(coef == 0 && exp == 0))terms.add(new Term(coef, exp));
			}		
		}				
	}
	
	//Class to prepare to proceed with the addition of polynomials
	@SuppressWarnings("unchecked")
	public static Polynomial addition(Polynomial poly1, Polynomial poly2)
	{
		//Term variable to store the result of the addition
		Polynomial polyResult = new Polynomial();
		ArrayList<Term> result = new ArrayList<Term>();		
		
		//Evaluate the size of the operands to send the less terms operand first 
		//and call the function that do the operation
		if(poly1.terms.size() <= poly2.terms.size())
		{
			result = sum(poly1.terms, poly2.terms);	
		}		
		else
		{
			result = sum(poly2.terms, poly1.terms);
		}
		
		//returns the resulting term	
		polyResult.terms = (ArrayList<Term>) result.clone();		
		return polyResult;
	}		
	
	//public function that actually do the algebraic addition on two polynomials
	public static ArrayList<Term> sum(ArrayList<Term> a, ArrayList<Term> b)
	{
		//Term variable to store the result of the addition
		ArrayList<Term> result = new ArrayList<Term>();	
		
		//Array to store the exponents involved into additions
		//to exclude them further from add them to the result
		ArrayList<Integer> used = new ArrayList<Integer>();						
		
		//First select all the common exponent terms and do the individual addition
		//Iterate over the small number of terms operand
		for(int i = 0; i < a.size(); i++)
		{
			//Iterate over the greater terms operand
			for(int j = 0; j < b.size(); j++)
			{			
				//If two terms have the same exponent, add theirs coefficients
				//********Can be optimized do not making to many calls, but do the work*******
				if(((int)a.get(i).getExp()) == ((int)b.get(j).getExp()))
				{
					//Create a new term with the result form the individual terms addition
					result.add(new Term(a.get(i).getCoef() + b.get(j).getCoef(), b.get(j).getExp()));
					
					//Register the exponent of the previous operation to be excluded from further addition
					used.add(a.get(i).getExp());
				}
			}				
		}		
		
		//Once all the individual operations ended we need to add the non common terms
		//Iterate the first operand
		for(int j = 0; j < a.size(); j++)
		{
			//If the exponent has not been excluded add the term to the result
			if(!(boolean) used.contains(a.get(j).getExp()))
			{
				//Copy the term to the result term variable				
					result.add(new Term(a.get(j).getCoef(),a.get(j).getExp())); 
			}
		}		
		
		//Iterate the second operand
		for(int j = 0; j < b.size(); j++)
		{
			//If the exponent has not been excluded add the term to the result
			if(!(boolean) used.contains(b.get(j).getExp()))
			{
				//Copy the term to the result term variable
					result.add(new Term(b.get(j).getCoef(),b.get(j).getExp())); 
			}
		}			
		//Return the resulting term variable
		return result;
	}
	
	//Public function to sort in descending order any polynomial
	public static Polynomial sort(Polynomial poly)
	{				
		//Store the polynomial to be sorted
		int[][] tmp = new int[poly.terms.size()][2];	
		
		//Store the sorted polynomial into an array
		int[][] tmp2 = new int[poly.terms.size()][2];					
		
		//Translate the Term variable polynomial to a simple array to be sorted
		//Iterate the Term variable
		for(int i = 0; i < poly.terms.size(); i++)
		{
			//Copy the term coefficient into the array
			tmp[i][0] = (int)(poly.terms.get(i).getCoef());
			
			//copy the term exponent into the array
			tmp[i][1] = poly.terms.get(i).getExp();
		}
		
		//Sort the array using the comparator class by the exponent column
		Arrays.sort(tmp, Comparator.comparingInt(arr -> arr[1]));
		
		//Empty the original term variable
		poly.terms.clear();
		
		//Counter
		int cont = tmp.length -1;
		
		//iterate the sorted array and store it in descending order
		for(int i = 0; i < tmp2.length; i++)
		{
			//Store in reverse order the arrays
			tmp2[i][0] = tmp[cont][0];
			tmp2[i][1] = tmp[cont][1];
			
			//Increase external counter
			cont--;
		}
		
		//Store the final polynomial into the Term variable iterating the array
		for(int i = 0; i < tmp2.length; i++)
		{
			poly.terms.add(new Term(tmp2[i][0], tmp2[i][1]));
		}		
		//Return the sorted polynomial to the caller
		return poly;
	}
	
	//Public function to display any polynomial to the screen
	public static void printOut(String label, Polynomial polynomial)
	{
		//Variable to store the final string
		String display = "";	
		
		//Iterate the polynomial
		for (int i=0; i < polynomial.terms.size() ; i++) 
	  	{
			//Store the term exponent
	  		int expo = polynomial.terms.get(i).getExp();
	  		int coef = (int) polynomial.terms.get(i).getCoef();
	  		
	  		//If the coefficient is different from 0 or 1 add it to the display, or hide it
	  	    if(coef > 1 || coef < -1) display += Math.abs(coef); 
	  	    
	  	    if(expo == 1)display += "x";			//If the exponent is 1 add 'x' to the display variable
	  	    else if (expo == 0) display += "";		//If the exponent is 0 do not add anything
	  	    else display += "x^";					//in other case add 'x^' to the display variable
	  	    
	  	    if(expo > 1 || expo <-1) display += expo;	//If the exponent is different from 0  add it to the display variable	    
	  	    
	  	    if(i < polynomial.terms.size() - 1)			//If there are more terms add a sign
  	    	{
	  	    	//Depending on the next coefficient show the + or - sign
	  	    	if((int) polynomial.terms.get(i + 1).getCoef() > 0)display += " + ";else display += " - ";	  	    		
  	    	}
	  	}	  	    
		//Display the label and the display variable (polynomial) content
	    System.out.println(label + display + "\n");			
	}
	
	public int size()
	{
		int size = this.terms.size();
		
		return size;
	}
}
