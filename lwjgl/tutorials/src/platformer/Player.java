package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Player extends Shape
{

	boolean alive;
	public double startX, startY;
	public int lives = 100;
	public Checkpoint activeCheckpoint;
	
	public Player(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		user = true;
		code = 3;
		name = "Player";
		alive = true;
		startX = x;
		startY = y;
	}

	public void setActiveCheckpoint(Checkpoint x)
	{
		activeCheckpoint = x;
	}
	
	@Override
	public void interact(Player player)
	{
	}

	@Override
	public void draw()
	{
		glColor3d(.3,.5,.4);
		glRectd(x, y, x + width, y + height);
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);
			OS.writeDouble(x);
			OS.writeDouble(y);
			OS.writeDouble(width);
			OS.writeDouble(height);
		} 
		catch (FileNotFoundException e)	{
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	@Override
	public Shape load(ObjectInputStream OS)
	{
		return new Player(0,0,0,0);
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}
	
	public void die()
	{
		lives--;
		if (activeCheckpoint != null)
		{
			startX = activeCheckpoint.x + activeCheckpoint.width/2;
			startY = activeCheckpoint.y;
		}
		if (lives > 0)
		{
			x = startX;
			y = startY;
			alive = true;
		}
		else
			System.out.println("Game over");
	}

	@Override
	public void doYourThing()
	{
		if (!alive)
			die();
	}

}
