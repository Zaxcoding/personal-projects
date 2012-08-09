package entities;

import static org.lwjgl.opengl.GL11.glColor3d;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sky extends Shape
{

	public Sky(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	@Override
	public void draw()
	{
		glColor3d(.6,.9,.9);

		textureVertices();
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
	}

	@Override
	public Shape load(ObjectInputStream IS)
	{
		return null;
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
