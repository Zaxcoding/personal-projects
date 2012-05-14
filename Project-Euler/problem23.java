/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 23
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers. 
 */

import java.util.*;

public class problem23
{ 
	public static void main(String [] args)
	{
		PriorityQueue<Integer> abundants = new PriorityQueue<Integer>(1000);
		for (int i = 12; i < 28111; i++)
			if (abundant(i))
				abundants.add(i);
		System.out.println("Made it here");
		
		Integer [] intArray = abundants.toArray(new Integer[0]);
			
		int sum = 0;		
		boolean done = false;
		for (int i = 0; i < intArray.length; i++) 
			for (int n = i; n < intArray.length && !done; n++) 
			{	
				sum = intArray[i] + intArray[n];
				if (sum > 28123)
					done = true;
			}	
			
	/*	for (int i = 0; i < 28123; i++)
			if (!sums.contains(i))
				sum += i;
	*/
		System.out.println("Sum is : " + sum);
		

	}
	
	public static boolean abundant(int n)
	{
		int sum = 0;
		for (int i = 1; i < n; i++)
			if (n % i == 0)
				sum += i;
	
		return sum > n;
	}	
}
