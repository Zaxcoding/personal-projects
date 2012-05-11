/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 92
 * How many starting numbers below ten million will arrive at 89?
 */

import java.util.*;

public class problem92
{ 
	public static void main(String [] args)
	{
		int sum = 0;
		for (int i = 1; i < 10000000; i++)
			if (eightyNine(Integer.toString(i)))
				sum++;
		System.out.println("Sum: " + sum);
	}
	
	public static boolean eightyNine(String num)
	{
		int tempSum = 0;
		for (int i = 0; i < num.length(); i++)
			tempSum += ((int)num.charAt(i) - 48) * ((int)num.charAt(i) - 48);
		if (tempSum == 1)
			return false;
		if (tempSum == 89)
			return true;	
		return eightyNine(Integer.toString(tempSum));		
	}	
}