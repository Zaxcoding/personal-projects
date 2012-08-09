package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Coin extends Shape
{

	public Coin(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 10;
		name = "Coin";
	}

	@Override
	public void interact(Player player)
	{
	//	player.score += 1;
	}

	@Override
	public void doYourThing()
	{
	}

	@Override
	public void draw()
	{
		glColor3d(7, 7, 0);
		glRectd(x, y, x + width, y + height);
		drawBorder();
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
		try
		{
			OS.writeInt(code);			// 10 is the code for Coin
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
		Coin temp = new Coin(0,0,0,0);
		try
		{
			temp = new Coin(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
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
		player.score += 1;
		this.removeMe = true;
	}

}
