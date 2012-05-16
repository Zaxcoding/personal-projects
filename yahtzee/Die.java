
import java.util.*;

public class Die
{
	private int sides;
	private Random rng;
		
	public Die(int num)
	{
		sides = num;
		rng = new Random();
	}
	
	public int roll()
	{
		return rng.nextInt(sides) + 1;
	}
/*	public graphic getPicture()
	{
		return picture;
	}
	*/		
}