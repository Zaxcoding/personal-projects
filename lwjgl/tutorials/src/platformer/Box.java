package platformer;

import static org.lwjgl.opengl.GL11.*;

public class Box extends AbstractMoveableEntity
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

}
