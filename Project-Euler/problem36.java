/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 36
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 */

import java.util.*;

public class problem36
{ 
	public static void main(String [] args)
	{
		int sum = 0;
		for (int i = 1; i < 1000000; i+= 2)			// don't need to check even numbers
		{
			if (palindrome(Integer.toString(i)) && palindrome(Integer.toBinaryString(i)))
			{
				System.out.println("Found one: " + i);
				sum += i;
			}
		}
		System.out.println("\nAnswer is: " + sum);
	}
	
	public static boolean palindrome(String intString)
	{
		for (int start = 0, end = intString.length()-1; start < end; start += 1, end -= 1)
			if (intString.charAt(start) != intString.charAt(end))
				return false;
		return true;
	}		
}