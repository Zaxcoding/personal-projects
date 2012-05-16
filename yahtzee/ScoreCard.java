

public class ScoreCard
{
	private int [] scores;
	
	public ScoreCard()
	{
		scores = new int[15];
	}
	
	public int getScore()
	{
		int score = 0;
		for (int i = 0; i < scores.length; i++)
			score += scores[i];
		return score;
	}
	
	public int getScore(int n)
	{
		return scores[n];
	}
	
	public int upperScore()
	{
		int upperScore = scores[0] + scores[1] + scores[2];
		 				 + scores[3] + scores[4] + scores[5];
		if (upperScore >= 63)
			upperScore += 35;
		return upperScore;
	}
	
	public int lowerScore()
	{
		return scores[6] + scores[7] + scores[8]
				 + scores[9] + scores[10] + scores[11];
 	}
	
}