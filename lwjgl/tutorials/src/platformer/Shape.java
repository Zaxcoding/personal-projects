package platformer;

import java.io.*;

public abstract class Shape
{
	protected boolean visible, touched, user;
	protected double dx, dy, x, y, width, height;

	public Shape(double x, double y, double width, double height) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.touched = false;
		this.visible = true;
	}
	
	public abstract void interact();
	public abstract void draw();
	public abstract void save(ObjectOutputStream OS);
	public abstract void load(ObjectInputStream OS);
	public abstract boolean intersects(Shape other);
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public boolean isTouched()
	{
		return touched;
	}
	
	public void touch()
	{
		touched = true;
	}
	
	public void toggleVisibility()
	{
		visible = !visible;
	}
	
	// ---- From AbstractMoveableEntity

	public void update(int delta)
	{
		this.x += delta * dx;
		this.y += delta * dy;
	}
	
	public double getDX()
	{
		return dx;
	}
	
	public double getDY()
	{
		return dy;
	}
	
	public void setDX(double dx)
	{
		this.dx = dx;
	}
	
	public void setDY(double dy)
	{
		this.dy = dy;
	}
	
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	//-- End AbstractMoveableEntity

}
