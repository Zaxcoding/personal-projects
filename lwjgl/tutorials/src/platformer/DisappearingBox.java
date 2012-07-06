package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DisappearingBox extends Shape
{
	private int counter;
	private static final int DISAPPEAR_SPEED = 10;
	
	public DisappearingBox(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 2;
		name = "Disappearing Box";
	}

	@Override
	public void interact(Player player)
	{
		counter++;
		if (counter > DISAPPEAR_SPEED)
			removeMe = true;
	}

	@Override
	public void draw()
	{
		glColor3d(.6, .3, .4);
		glRectd(x, y, x + width, y + height);
		drawBorder();
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 2 is the code for DisappearingBox
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
		DisappearingBox temp = new DisappearingBox(0,0,0,0);
		try
		{
			temp = new DisappearingBox(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
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
	public void doYourThing()
	{
	}

	@Override
	public void touch(Player player)
	{
		// do nothing
	}
	
}
