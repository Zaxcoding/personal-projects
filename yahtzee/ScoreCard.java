/* The Score Card to go with the Yahtzee game.
 * At times it is a bit verbose but I did generally
 * try to reuse common calls and consolidate when I
 * felt it was efficient and still readable/easy to use. */

import java.util.*;
import javax.swing.*;

public class ScoreCard
{
	private int [] scores;
	
	public ScoreCard()
	{
		scores = new int[15];
		for (int i = 0; i < 15; i++)
		{
			scores[i] = -1;
		}
	}
	
	public void setScore(int n, int score)
	{
		scores[n] = score;
	}
	
	public int getScore(int n)
	{
		return scores[n];
	}
	
	public int upperScore()
	{
		int upperScore = 0;
		for (int i = 0; i < 5; i++)
			if (scores[i] != -1)
				upperScore += scores[i];
		return upperScore;
	}
	
	public int bonusScore()
	{
		if (bonus())
			return 35;
		return 0;
	}
	
	public int totalUpperScore()
	{
		return upperScore() + bonusScore();
	}
	
	public int lowerScore()
	{
		int lowerScore = 0;
		for (int i = 6; i < scores.length; i++)
			if (scores[i] != -1)
				lowerScore += scores[i];
		return lowerScore;
 	}

	public int grandTotal()
	{
		return upperScore() + lowerScore();
	}

	public boolean bonus()
	{
		return upperScore() >= 63;
	}

	/* use this to test for aces-sixes
	 * Ex: num(1, dice) will return true if and only if
	 * they have not yet played 1's, and they have a 1 */
	public boolean num(int n, Die [] dice)
	{
		boolean ans = false;
		for (int i = 0; i < 5; i++)
			if (dice[i].value() == n)
				ans = true;
				
		return ans;
	}	
	
	/* use this to test for 3/4 of a kind, and yahtzee.
	 * Ex: numOfAKind(4, dice) will return true if and only if
	 * they have not yet played 4 of a kind, and they have it */	
	public boolean numOfAKind(int n, Die [] dice)
	{
		boolean ans = false;
		int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
		
		for (int i = 0; i < 5; i++)
		{
			if (dice[i].value() == 1)
				ones++;
			if (dice[i].value() == 2)
				twos++;
			if (dice[i].value() == 3)
				threes++;
			if (dice[i].value() == 4)
				fours++;
			if (dice[i].value() == 5)
				fives++;
			if (dice[i].value() == 6)
				sixes++;
		}
		ans = (ones >= n) || (twos >= n) || (threes >= n) || (fours >= n)
						 || (fives >= n) || (sixes >= n);
				
		return ans;
	}		
	
	// brute force calculation, but it gets the job done.
	public boolean fullHouse(Die [] dice)
	{
		boolean ans = false;
		int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
		
		for (int i = 0; i < 5; i++)
		{
			if (dice[i].value() == 1)
				ones++;
			if (dice[i].value() == 2)
				twos++;
			if (dice[i].value() == 3)
				threes++;
			if (dice[i].value() == 4)
				fours++;
			if (dice[i].value() == 5)
				fives++;
			if (dice[i].value() == 6)
				sixes++;
		}		
		ans = (ones == 3 && (twos == 2 || threes == 2 || fours == 2 ||
				fives == 2 || sixes == 2)) || (twos == 3 && (ones == 2 ||
				 threes == 2 || fours == 2 || fives == 2 || sixes == 2)) ||
				 (threes == 3 && (ones == 2 || twos == 2 || fours == 2 ||
				 fives == 2 || sixes == 2)) || (fours == 3 && (ones == 2 ||
				 threes == 2 || twos == 2 || fives == 2 || sixes == 2))
				 || (fives == 3 && (ones == 2 || threes == 2 || fours == 2 ||
				 twos == 2 || sixes == 2)) || (sixes == 3 && (ones == 2 ||
				 threes == 2 || fours == 2 || fives == 2 || twos == 2));
				
		return ans;
	}
	
	// n == 4 for small, n == 5 for large
	public boolean numStraight(int n, Die [] dice)
	{
		boolean ans = false;
		int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
		
		for (int i = 0; i < 5; i++)
		{
			if (dice[i].value() == 1)
				ones++;
			if (dice[i].value() == 2)
				twos++;
			if (dice[i].value() == 3)
				threes++;
			if (dice[i].value() == 4)
				fours++;
			if (dice[i].value() == 5)
				fives++;
			if (dice[i].value() == 6)
				sixes++;
		}
		if (n == 4)
			ans = (ones >= 1 && twos >= 1 && threes >= 1 && fours >= 1) ||
			 		(twos >= 1 && threes >= 1 && fours >= 1 &&	fives >= 1) ||
					  (threes >= 1 && fours >= 1 && fives >= 1 && sixes >= 1);
		
		if (n == 5)
			ans = (ones >= 1 && twos >= 1 && threes >= 1 && fours >= 1 &&
					fives >= 1) || (twos >= 1 && threes >= 1 && fours >= 1 &&
						fives >= 1 && sixes >= 1);
		
		return ans;
	}

	public boolean chance()
	{
		return scores[12] != -1;
	}

	// true iff there's been a yahtzee, and they have a yahtzee
	public boolean yahtzeeBonus(Die [] dice)
	{
		return (numOfAKind(5, dice) && scores[11] == 50);
	}

}