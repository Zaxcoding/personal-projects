package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;

public class Box extends Shape
{	
	public Box(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 1;
		name = "Box";
	}

	@Override
	public void draw()
	{
		glColor3d(1, 1, 1);
		glRectd(x, y, x + width, y + height);
		drawBorder();
	}
	
	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 1 is the code for Box
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

	public void interact(Player player)
	{
		// bounce off the sides/bottom, stay on the top
	}

	public boolean intersects(Shape other)
	{
		// not filling this in yet
		return false;
	}

	@Override
	public Shape load(ObjectInputStream IS)
	{
		Shape temp = new Box(0,0,0,0);
		try
		{
			temp = new Box(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void doYourThing()
	{
	}
	
}