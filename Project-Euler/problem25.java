/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 13
 * What is the first term in the Fibonacci sequence to contain 1000 digits? 
 */

import java.math.*;

public class problem13
{ 
	public static void main(String [] args)
	{
		BigInteger prev = new BigInteger("0");	
		BigInteger curr = new BigInteger("1");
		BigInteger temp = new BigInteger("0");
		
		boolean found = false;
		int ans = 1;
		while (!found)
		{
			ans++;
			temp = curr;
			curr = curr.add(prev);
			prev = temp;
			if (curr.toString().length() == 1000)
				found = true;	
		}
		System.out.println("The " + ans + "th term is: " + curr);
	}
}