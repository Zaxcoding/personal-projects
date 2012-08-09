package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ice extends Shape
{

	public int direction, speed = 1;
	
	public Ice(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 9;
		name = "Ice";
		direction = -7;
	}

	@Override
	public void interact(Player player)
	{			
		player.onIce = true;
		
	}

	public void toggleDirection()
	{
		direction *= -1;
	}
	
	@Override
	public void doYourThing()
	{
	}

	@Override
	public void draw()
	{
		glColor3d(0, 0, 1);
		glRectd(x, y, x + width, y + height);
		drawBorder();
	}

	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 9 is the code for Ice
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
	
	public Shape load(ObjectInputStream IS)
	{
		Ice temp = new Ice(0,0,0,0);
		try
		{
			temp = new Ice(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;	
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

	@Override
	public void touch(Player player)
	{
	}

}
