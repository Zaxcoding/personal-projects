package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Teleporter extends Shape
{

	private int counter;
	private static final int TELEPORT_DELAY = 75;
	private double destX, destY;
	private int id;
	
	public Teleporter(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 5;
		name = "Teleporter";
	}

	public void setID(int x)
	{
		id = x;
	}
	
	public void setDestination(double x, double y)
	{
		destX = x;
		destY = y - height;
	}
	
	@Override
	public void interact(Player player)
	{
		counter++;
		if (counter > TELEPORT_DELAY)
		{
			player.x = destX - player.width/2;
			player.y = destY;
		}
		touched = true;
	}

	@Override
	public void doYourThing()
	{
		// try to decrease if not active
		if (!touched)
			if (counter > 0)
				counter--;
		if (touched)
			touched = false;
	//	System.out.println(counter);
	}

	@Override
	public void draw()
	{
		glColor3d(.1, .2, .6);
		glRectd(x, y, x + width, y + height);
		drawBorder();
		glColor3d(.1, .2, .9);
		if (counter > 0 && counter < TELEPORT_DELAY*2/3)
		{
			glBegin(GL_LINES); 
				glVertex2d(x, y);
				glVertex2d(x, y - 2*counter);
				glVertex2d(x + width, y);
				glVertex2d(x + width, y -  2*counter);
			glEnd ();
		}
		if (counter >= TELEPORT_DELAY*2/3)
		{
			glBegin(GL_LINES); 
				glVertex2d(x, y);
				glVertex2d(x, y - 2*TELEPORT_DELAY*2/3);
				glVertex2d(x + width, y);
				glVertex2d(x + width, y -  2*TELEPORT_DELAY*2/3);
				glVertex2d(x, y - 2*TELEPORT_DELAY*2/3);
				glVertex2d(x + width, y - 2*TELEPORT_DELAY*2/3);
			glEnd ();
		}
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 5 is the code for Teleporter
			OS.writeDouble(x);
			OS.writeDouble(y);
			OS.writeDouble(width);
			OS.writeDouble(height);
			OS.writeDouble(destX);
			OS.writeDouble(destY);
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
	public Shape load(ObjectInputStream IS)
	{
		Teleporter temp = new Teleporter(0,0,0,0);
		try
		{
			temp = new Teleporter(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
			temp.setDestination(IS.readDouble(), IS.readDouble());
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
		// do nothing
	}

}
