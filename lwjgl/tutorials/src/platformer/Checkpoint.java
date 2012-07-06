package platformer;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glRectd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Checkpoint extends Shape
{
	public boolean active;

	public Checkpoint(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 6;
		name = "Checkpoint";
	}

	@Override
	public void interact(Player player)
	{
		player.startX = x + width/2;
		player.startY = y - height - player.height;
		player.setActiveCheckpoint(this);
		active = true;
	}

	@Override
	public void doYourThing()
	{
	}

	@Override
	public void draw()
	{
		glColor3d(0, 1, 0);
		glRectd(x, y, x + width, y + height);
		drawBorder();
		
		glColor3d(0, 1, 0);
		glBegin(GL_LINES);
			glVertex2d(x + width/2, y);
			glVertex2d(x, y - height/2);
			glVertex2d(x + width/2, y);
			glVertex2d(x + width, y - height/2);
		glEnd();
		
		if (active)
		{
			glColor3d(1, 1, 0);
			glRectd(x + width/4,  y - height/4, x + width*3/4, y - 2*height);
		}
		
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 6 is the code for Checkpoint
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
	public Shape load(ObjectInputStream IS)
	{
		Checkpoint temp = new Checkpoint(0,0,0,0);
		try
		{
			temp = new Checkpoint(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
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
