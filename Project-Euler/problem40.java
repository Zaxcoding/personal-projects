/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 40
 * If dn represents the nth digit of the fractional part, find the value of the following expression.
 *				d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000 
 */

import java.util.*;

public class problem40
{ 
	public static void main(String [] args)
	{
		StringBuilder bigNum = new StringBuilder("!");
		for (int i = 1; bigNum.length() <= 1000000; i++)
			bigNum.append(i);
		
		System.out.println(bigNum.charAt(1) + " * " + bigNum.charAt(10) + " * " + bigNum.charAt(100) + " * " + 
							bigNum.charAt(1000) + " * " + bigNum.charAt(10000) + " * " +
							 bigNum.charAt(100000) + " * " + bigNum.charAt(1000000));
	}
}