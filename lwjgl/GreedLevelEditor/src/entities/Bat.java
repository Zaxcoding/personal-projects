package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Bat extends Shape
{
	public boolean on = false, up = false, vert = false;
	
	Texture cliffv, cliffi;

	public Bat(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		cliffv = loadTexture("cliffv");
		cliffi = loadTexture("cliffi");

	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (this.vert)
			cliffv.bind();
		else
			cliffi.bind();

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
