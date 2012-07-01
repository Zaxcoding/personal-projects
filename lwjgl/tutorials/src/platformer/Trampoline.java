package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Trampoline extends Shape
{
	
	public Trampoline(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 7;
		name = "Trampoline";
	}

	@Override
	public void interact(Player player)
	{
		player.onTramp = true;
	}

	@Override
	public void doYourThing()
	{
	}

	@Override
	public void draw()
	{
		glColor3d(.35, .28, .50);
		glRectd(x, y, x + width, y + height);
		drawBorder();
	}

	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 7 is the code for Trampoline
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
		Trampoline temp = new Trampoline(0,0,0,0);
		try
		{
			temp = new Trampoline(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
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

}
