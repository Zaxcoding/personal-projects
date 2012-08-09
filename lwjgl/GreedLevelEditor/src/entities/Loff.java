package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Loff extends Shape
{
	public boolean on = false;
	
	Texture Loff, Lon;
	
	public Loff(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		Loff = loadTexture("Loff");
		Lon = loadTexture("Lon");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(!on)
			Loff.bind();
		else
			Lon.bind();
		
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
