/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 21
 * Evaluate the sum of all the amicable numbers under 10000. 
 */

import java.math.*;

public class problem21
{ 
	public static void main(String [] args)
	{
		int sum = 0, a = 0, b = 0;
		for (int i = 0; i < 10000; i++)
		{
			a = properDivisors(i);
			b = properDivisors(a);
			if ((i == b) && (a != b))
				sum += a + i;
		}	
		sum /= 2;
		
		System.out.println("Sum is " + sum);
	}
	
	public static int properDivisors(int n)
	{
		int sum = 0;
		boolean sqrt = false;
		for (int i = 1; i < n; i++)
			if (n % i == 0)
				sum += i;
				
		return sum;
	}	
}