/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 42
 * Using words.txt, how many are triangle words? 
 */

import java.util.*;
import java.io.*;

public class problem42
{ 
	public static void main(String [] args) throws IOException
	{
		int sum = 0;
		String [] wordList = new String[2000];
		Scanner fileScan = new Scanner(new File("words.txt"));
		fileScan.useDelimiter(",");
		
		for (int i = 0; fileScan.hasNext(); i++)
			wordList[i] = fileScan.next();
			
		for (int i = 0; i < wordList.length && wordList[i] != null; i++)
			if (isTriangle(nameScore(wordList[i])))
			{
				System.out.println("Got One! " + wordList[i] + " == " + nameScore(wordList[i]));
				sum++;
			}
		
		System.out.println("Total: " + sum);
	}
	
	public static int nameScore(String name)
	{
		int score = 0;
		for (int i = 0; i < name.length(); i++)
			score += (int) (name.charAt(i) - 64);
			
		return score;
	}	
	
	public static boolean isTriangle(int n)
	{
		if (n == 1 || n == 3 || n == 6 || n== 10 || n== 15 || n== 21 || n== 28 || n== 36 || n== 45
			 || n== 55 || n== 66 || n== 78 || n== 91 || n== 105 || n== 120 || n== 136 || n== 153
			  || n== 171 || n== 190 || n== 210 || n== 231 || n== 253 || n== 276 || n== 300
			   || n== 325 || n== 351 || n== 378 || n== 406 || n== 435 || n== 465 || n== 496
			    || n== 528 || n== 561 || n== 595 || n== 630 || n== 666 || n== 703 || n== 741)
			return true;
		return false;
	}
}