/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 48
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000 
 */

import java.math.*;

public class problem48
{ 
	public static void main(String [] args)
	{
		BigInteger num = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		BigInteger sum = new BigInteger("0");
		
		for (int i = 0; i < 1000; i++)
		{
			num =  num.add(one);
			sum = sum.add(trimTo10Digits(num.pow(num.intValue())));
		}
		System.out.println(trimTo10Digits(sum));
	}
	
	public static BigInteger trimTo10Digits(BigInteger n)
	{
		String digits = n.toString();
		
		if (digits.length() > 10)
			digits = digits.substring(digits.length()-10);
				
		return new BigInteger(digits);
	}	
}