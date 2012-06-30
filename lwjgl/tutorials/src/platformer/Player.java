package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Player extends Shape
{

	public Player(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		user = true;
		code = 3;
	}

	@Override
	public void interact()
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

	@Override
	public void doYourThing()
	{
	}

}
