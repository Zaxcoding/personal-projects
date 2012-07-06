package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeathStick extends Shape
{

	public DeathStick(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 8;
		name = "Death Stick";
	}

	@Override
	public void interact(Player player)
	{
		player.alive = false;
	}

	@Override
	public void doYourThing()
	{
	}

	@Override
	public void draw()
	{
		glColor3d(1, 0, 0);
		glRectd(x, y, x + width, y + height);
		drawBorder();
	}

	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 8 is the code for DeathStick
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
		DeathStick temp = new DeathStick(0,0,0,0);
		try
		{
			temp = new DeathStick(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
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
		player.alive = false;
	}

}
