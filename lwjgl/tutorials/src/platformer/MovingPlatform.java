package platformer;

import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glRectd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MovingPlatform extends Shape
{
	public boolean moving = false;	// by default, stationary
	public boolean upDown;
	public int left, right, bottom, top; // if it will move, these are the bounds
	public int direction;	
	public int speed;
	
	public MovingPlatform(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 4;
		name = "Moving Platform";
	}

	public void setMovement(boolean upAndDown, int start, int finish, int dir, int speed)
	{
		this.upDown = upAndDown;
		if (upDown)
		{
			top = start;
			bottom = finish;
			direction = dir;		// 1 for down, -1 for up
		}
		else
		{
			left = start;
			right = finish;
			direction = dir;		// 1 for right, -1 for left
		}
		moving = true;
		this.speed = speed;
	}
	
	public void doYourThing()
	{
		if (upDown)
		{
			y += direction * speed; 	
			if (y <= top || y >= bottom)
			{
				direction *= -1;
				System.out.println("FLIP");
			}
			System.out.println("y: " + y + "   top: " + top + "   bottom: " + bottom);
		}
		else
		{
			x += direction * speed;
			if (x <= left || x >= right)
				direction *= -1;
			System.out.println(y);
		}
	}
	
	@Override
	public void interact(Player player)
	{
		if (upDown)
			player.y += direction * speed;
		else
			player.x += direction * speed;
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
			OS.writeInt(code);			// 4 is the code for MovingPlatform
			OS.writeDouble(x);
			OS.writeDouble(y);
			OS.writeDouble(width);
			OS.writeDouble(height);
			OS.writeBoolean(upDown);
			if (upDown)
			{
				OS.writeInt(top);
				OS.writeInt(bottom);
			}
			else
			{
				OS.writeInt(left);
				OS.writeInt(right);
			}
			OS.writeInt(direction);
			OS.writeInt(speed);
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
		MovingPlatform temp = new MovingPlatform(0,0,0,0);
		try
		{
			temp = new MovingPlatform(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
			temp.setMovement(IS.readBoolean(), IS.readInt(), IS.readInt(), IS.readInt(), IS.readInt());
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
