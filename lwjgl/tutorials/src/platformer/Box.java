package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Box extends Shape
{

	public Box(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	@Override
	public void draw()
	{
		glRectd(x, y, x + width, y + height);
	}
	
	public void save(ObjectOutputStream OS)
	{
		try
		{
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

	public void interact()
	{
		// bounce off the sides/bottom, stay on the top
	}

	public boolean intersects(Shape other)
	{
		// not filling this in yet
		return false;
	}

	@Override
	public void load(ObjectInputStream OS)
	{
		// don't have time for this either
	}
	
}