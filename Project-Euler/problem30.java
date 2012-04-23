/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 30
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits. 
 */

import java.math.*;

public class problem30
{ 
	public static void main(String [] args)
	{
		int sum = 0;
		
		for (int i = 2; i < 10000000; i++)
			if (i == sumOfPowersDigits(i))
				sum += i;
		System.out.println("Answer is: " + sum);
	}
	
	public static int sumOfPowersDigits(int n)
	{
		
		Integer num1 = new Integer(n);
		String num = num1.toString();
		int digit = 0, sum = 0;
		
		for (int i = 0; i < num.length(); i++)
		{
			digit = Character.getNumericValue(num.charAt(i));
			sum += digit*digit*digit*digit*digit;
		}
		return sum;
	}	
}