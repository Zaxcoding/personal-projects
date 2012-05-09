/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 24
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */

import java.math.*;

public class problem24
{ 
	public static void main(String [] args)
	{
		System.out.println("I can't figure this out yet. Recursion?");	
	}
	
	public static String swap(int n)
	{
		char [] charArray = "0123456789".toCharArray();
		char first = charArray[n];
		charArray[n] = charArray[n+1];
		charArray[n+1] = first;
		return new String(charArray);
	}
}