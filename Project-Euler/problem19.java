/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 19
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)? 
 * NOTE: To generalize, just set dayOfWeek (0 = monday, 1 = tuesday, ... , 6 = sunday) to the correct day,
 * 		 and the bounds for currYear, and currMonth.
 
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class problem19
{ 
	public static void main(String [] args)
	{
		int ans = 0, maxDays, dayOfWeek = 1;
		
		for (int currYear = 1; currYear <= 100; currYear++)
			for (int currMonth = 1; currMonth <= 12; currMonth++)
			{
				// assume there's 31, and change it if not
				maxDays = 31;	
				if (currMonth == 4 || currMonth == 6 || currMonth == 9 || currMonth == 11)
					maxDays = 30;
				if (currMonth == 2)
				{
					maxDays = 28;
					if (currYear % 4 == 0 && currYear % 100 != 0)
						maxDays = 29;
				}
				for (int currDay = 1; currDay <= maxDays; currDay++)
				{
					if (currDay == 1 && dayOfWeek == 6)
						ans++;
				//	System.out.println("Today is : " + dayOfWeek + ", " + currMonth + "/" + currDay + "/" + currYear);
					dayOfWeek = (dayOfWeek + 1) % 7;

				}
			}					
		
		System.out.println("Answer: " + ans);
	}
}