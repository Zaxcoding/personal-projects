/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 34
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 */

public class problem34
{ 
	public static void main(String [] args)
	{
		int ans = 0;
		for (int i = 3; i < 1000000; i++)
			if (i == sumOfFactorial(i))
			{
				System.out.println("Got one: " + i);
				ans += sumOfFactorial(i);
			}
		System.out.println("\nAnswer is: " + ans);
	}
	
	public static int sumOfFactorial(int n)
	{
		int sum = 0;
		String intString = Integer.toString(n);
		for (int i = 0; i < intString.length(); i++)
		{
			if (intString.charAt(i) == '0')
				sum += 1;
			if (intString.charAt(i) == '1')
				sum += 1;
			if (intString.charAt(i) == '2')
				sum += 2;
			if (intString.charAt(i) == '3')
				sum += 6;
			if (intString.charAt(i) == '4')
				sum += 24;
			if (intString.charAt(i) == '5')
				sum += 120;
			if (intString.charAt(i) == '6')
				sum += 720;
			if (intString.charAt(i) == '7')
				sum += 5040;
			if (intString.charAt(i) == '8')
				sum += 40320;
			if (intString.charAt(i) == '9')
				sum += 362880;
		}
		return sum;
	}	
}