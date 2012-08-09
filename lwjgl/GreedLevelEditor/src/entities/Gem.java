package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Gem extends Shape
{
	public boolean vert;
	
	Texture door, doorv;

	public Gem(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		door = loadTexture("door");
		doorv = loadTexture("doorv");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (!this.vert)
			door.bind();
		else
			doorv.bind();
		
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
