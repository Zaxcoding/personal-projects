/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 12
 * What is the value of the first triangle number to have over five hundred divisors?
 */

public class problem12
{ 
	public static void main(String [] args)
	{
		boolean found = false;
		int ans = 0, num = 0, divisors = 0;
		while (!found)
		{
			num++;
			ans += num;
			divisors = 0;
			for (int i = 1; i <= (int)Math.pow(ans, .5); i++)
				if (ans % i == 0)
					divisors++;
			divisors *= 2;
			if (divisors > 500)
				found = true;
		}	
		System.out.println("Triangle number: " + ans + " has " + divisors + " divisors");
	}
}