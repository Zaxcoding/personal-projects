/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 45
 * After 40755, what is the next triangle number that is also pentagonal and hexagonal?
 */

public class problem45
{ 
	public static void main(String [] args)
	{
		long triNum = 0;
		
		for (int i = 0; i < 100000; i++)
		{
			
			triNum += i;
			if (isPentagonal(triNum) && isHexagonal(triNum))
				System.out.println("Got one: " + triNum);
		}	
		
	}

	public static boolean isPentagonal(long n)
	{
		for (long i = 0; i < 50000; i++)
			if (i*(3*i-1)/2 == n)
				return true;
		return false;
	}
	
	public static boolean isHexagonal(long n)
	{
		for (long i = 0; i < 50000; i++)
			if (i*(2*i - 1) == n)
				return true;
		return false;
	}
	
}