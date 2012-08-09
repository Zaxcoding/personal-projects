package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Rope extends Shape
{
	public boolean upp = false;

	Texture ropei;
	
	public Rope(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		ropei = loadTexture("ropei");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		ropei.bind();
		
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
