/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 52
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */

import java.util.*;

public class problem52
{ 
	public static void main(String [] args)
	{
		boolean done = false;
		long ans;
		for (ans = 100000; !done; ans++)
			if (test(ans))
				done = true;		
		System.out.println("Answer is: " + ans);
	}

	public static boolean test(long n)
	{
		String [] intStrings = new String[6];
		char [] temp = new char[6];
		for (int i = 0; i < 6; i++)
		{	
			temp = Long.toString(n*(i+1)).toCharArray();
			Arrays.sort(temp);
			intStrings[i] = new String(temp);
		}
		if (intStrings[0].equals(intStrings[1]))
			if (intStrings[1].equals(intStrings[2]))
				if (intStrings[2].equals(intStrings[3]))
					if (intStrings[3].equals(intStrings[4]))
						if (intStrings[4].equals(intStrings[5]))
							return true;
		return false;
	}
}