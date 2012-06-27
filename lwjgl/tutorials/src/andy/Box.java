package andy;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entities.Entity;

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

	public void interact(Shape other)
	{
		// bounce off the sides/bottom, stay on the top
		if(this.on(other))
		{
			System.out.println("on");
		}
		else if(this.leftOf(other))
		{
			System.out.println("left of");
			//in my game i just disabled the ability to go right here as in 
			//if(Keyboard.isKeyPressed(Keyboard.right())&&!box.leftOf())
			//{
			//		box.setX(alskjdf;lasjf);
			//}
			
		}
		else if(this.rightOf(other))
		{
			System.out.println("right of");
			//in my game i just disabled the ability to go left here as in 
			//if(Keyboard.isKeyPressed(Keyboard.right())&&!box.rightOf())
			//{
			//		box.setX(alskjdf;lasjf);
			//}
		}
	}
	public void interact()
	{
		//nothing
	}

	public boolean intersects(Shape other)
	{
		boolean intersect = false;
		if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&this.getY()>=other.getY()&&this.getY()<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if(this.getX()>= other.getX()&&this.getX()<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if(this.getX()>= other.getX()&&this.getX()<=other.getX()+other.getWidth()&&this.getY()>=other.getY()&&this.getY()<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}	
		return intersect;
	}
	

	@Override
	public void load(ObjectInputStream OS)
	{
		// don't have time for this either
	}
	
	private boolean on(Shape other)
	{
		return((this.getX() <= (other.getX() + other.getWidth()) &&((this.getX()+this.getWidth()) >=other.getX()) ) && ((this.getY()+this.getHeight()) >=other.getY()) && ((this.getY()+this.getHeight()) <= (other.getY() + other.getHeight())));
	}
	private boolean leftOf(Shape other)
	{
		int buffer=10;
		return((this.getX()+this.getWidth()+buffer)>=other.getX()&&(this.getX()+this.getWidth())<=other.getX() && this.getY()>=other.getY() && this.getY()<=( other.getY()+other.getHeight()));
	}
	private boolean rightOf(Shape other)
	{
		int buffer = 10;
		return(this.getX()<=(other.getX()+other.getWidth()+buffer)&&this.getX()>=(other.getX()+other.getWidth()) &&this.getY()>=other.getY() && this.getY()<=( other.getY()+other.getHeight()));
	}
	
}