package platformer;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.*;


import java.io.*;

public abstract class Shape
{
	protected boolean visible, touched, selected, user, removeMe = false;
	protected double dx, dy, x, y, width, height;
	protected int code;
	protected String name;
	
	public static int BORDER = 7;

	public Shape(double x, double y, double width, double height) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.touched = false;
		this.visible = true;
	}
	
	public abstract void interact(Player player);
	public abstract void doYourThing();
	public abstract void draw();
	public abstract void save(ObjectOutputStream OS);
	public abstract Shape load(ObjectInputStream IS);
	public abstract boolean intersects(Shape other);
	
	public static Shape load(ObjectInputStream IS, int shapeCode)
	{
		Shape temp = new Box(0,0,0,0);		
		if (shapeCode == 1)
			temp = new Box(0,0,0,0);
		else if (shapeCode == 2)
			temp = new DisappearingBox(0,0,0,0);
		else if (shapeCode == 4)
			temp = new MovingPlatform(0,0,0,0);
		else if (shapeCode == 5)
			temp = new Teleporter(0,0,0,0);
		else if (shapeCode == 6)
			temp = new Checkpoint(0,0,0,0);
		else if (shapeCode == 7)
			temp = new Trampoline(0,0,0,0);
		
		temp = temp.load(IS);
		return temp;
	}
	
	public void drawBorder()
	{
		if (selected)
		{
			// copied most of the following from http://forums.inside3d.com/viewtopic.php?t=1326
			glColor3f(1,0,0); // red

			glLineWidth(2);  // Set line width to 2
			glLineStipple(1, (short)0xf0f0);  // Repeat count, repeat pattern
			glEnable(GL_LINE_STIPPLE); // Turn stipple on

			glBegin(GL_LINE_LOOP); 
				glVertex2d(x - BORDER, y - BORDER);
				glVertex2d(x + BORDER + width, y - BORDER);
				glVertex2d(x + BORDER + width, y + BORDER + height);
				glVertex2d(x - BORDER, y + BORDER + height);
			glEnd ();
			glDisable(GL_LINE_LOOP); // Turn it back off
			glEnd();
		}
	}
	
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
