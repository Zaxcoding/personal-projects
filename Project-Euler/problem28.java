/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 28
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way? 
 */

import java.math.*;

public class problem28
{ 
	public static void main(String [] args)
	{
		BigInteger answer = new BigInteger("1");
		for (int i = 3; i <= 1001; i += 2)
			answer = answer.add(new BigInteger(spiralSum(i)));

		System.out.println(answer);
	}
	
	public static String spiralSum(int n)
	{
		int sum = 0;
		for (int i = n*n - 3*n + 3; i <= n*n; i += n-1)
			sum += i;
			
		return Integer.toString(sum);
	}	
}