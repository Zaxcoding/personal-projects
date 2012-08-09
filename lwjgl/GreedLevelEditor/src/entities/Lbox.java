package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Lbox extends Shape
{
	
	Texture lboxi;

	public Lbox(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		lboxi = loadTexture("lboxi");
	}

	@Override
	public void draw()
	{
		textureStart();
		lboxi.bind();
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
