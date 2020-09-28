/*------------------------------------------------------------------------
#Name: Luis Leon
#Student ID: 
#COP3530- Data Structures
#Fall 2020 – We 6:00 PM - 7:40 PM
#Assignment # 3 - Polynomial addition
*/

//Class to store the polynomial terms
//Code provided by our instructor
public class Term 
{
	private double coef;									//Store the term coefficient
	private int exp;										//Store the term exponent	
	public Term(double c, int e){coef = c; exp = e;}		//Constructor
	public void add(double c, int e){coef = c; exp = e;}	//add a term to the collection
	public double getCoef(){return coef;}					//getter methods
	public int getExp(){return exp;}	
	
}
