
import java.util.*;

public class Die
{
	private int sides, value;
	private Random rng;
	private boolean held;
		
	public Die(int num)
	{
		sides = num;
		rng = new Random();
		value = roll();
		held = false;
	}
	
	public int roll()
	{
		value = rng.nextInt(sides) + 1;
		return value;
	}
	
	public int value()
	{
		return value;
	}
	
	public boolean held()
	{
		return held;
	}
	
	public void toggleHeld()
	{
		held = !held;
	}
	
/*	public graphic getPicture()
	{
		return picture;
	}
	*/		
}