/* Zach Sadler
 * zaxcoding@gmail.com
 * Problem 17
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many
 * letters would be used?  
 */

import java.math.*;

public class problem17
{ 
	public static void main(String [] args)
	{
		int one = 3, two = 3, three = 5, four = 4, five = 4, six = 3;
		int seven = 5, eight = 5, nine = 4, ten = 3, eleven = 6, twelve = 6;
		int thirteen = 8, fourteen = 8, fifteen = 7, sixteen = 7, seventeen = 9;
		int eighteen = 8, nineteen = 8, twenty = 6, thirty = 6, forty = 5;
		int fifty = 5, sixty = 5, seventy = 7, eighty = 6, ninety = 6, hundred = 7, and = 3;
		int thousand = 8;

		int oneToNine = one + two + three + four + five + six + seven + eight + nine;
		int tenToNineteen = ten + eleven + twelve + thirteen + fourteen + fifteen + 
											sixteen + seventeen + eighteen + nineteen;
		int oneTo99 = oneToNine*9 + tenToNineteen + twenty*10 + thirty*10 + forty*10 +
						fifty*10 + sixty*10 + seventy*10 + eighty*10 + ninety*10;
 		
 		int ans = oneTo99;
 		ans += (one + hundred)*100 + and*99 + oneTo99;
 		ans += (two + hundred)*100 + and*99 + oneTo99;
 		ans += (three + hundred)*100 + and*99 + oneTo99;
 		ans += (four + hundred)*100 + and*99 + oneTo99;
 		ans += (five + hundred)*100 + and*99 + oneTo99;
 		ans += (six + hundred)*100 + and*99 + oneTo99;
 		ans += (seven + hundred)*100 + and*99 + oneTo99;
 		ans += (eight + hundred)*100 + and*99 + oneTo99;
 		ans += (nine + hundred)*100 + and*99 + oneTo99;
 		ans += one + thousand;
		
		System.out.println("Sum is " + ans);
	}
	
}