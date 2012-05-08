/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 22
 * What is the total of all the name scores in the file? 
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class problem22
{ 
	public static void main(String [] args)
	{
		ArrayList<String> nameList = new ArrayList<String>(5164);
		
		try
		{
			Scanner fileScan = new Scanner(new File("names.txt"));
			fileScan.useDelimiter(",");
			while(fileScan.hasNext())
				nameList.add(fileScan.next());
		}
		 catch(FileNotFoundException e)
		{	System.out.println("Where's the file?!");	}
	
		Collections.sort(nameList);	
		
		BigInteger finalScore = new BigInteger("0");
		
		for (int i = 0; i < nameList.size(); i++)
			finalScore = finalScore.add(new BigInteger(Integer.toString((i+1)*nameScore(nameList.get(i)))));
		
  /*	//Uncomment for trace mode	
		for (int i = 0; i < nameList.size(); i++)	
		{
			String currString = nameList.get(i);
			int currScore = nameScore(currString);
			BigInteger currBig = new BigInteger(Integer.toString((i+1)*currScore));
			System.out.println(currString + " has value: " + currScore + " currBig = " + currBig);
			finalScore = finalScore.add(currBig);
		}		
  */		
  
		System.out.println("Final Score: " + finalScore);
	}
	
	public static int nameScore(String name)
	{
		int score = 0;
		for (int i = 0; i < name.length(); i++)
			score += (int) (name.charAt(i) - 64);
			
		return score;
	}	
}