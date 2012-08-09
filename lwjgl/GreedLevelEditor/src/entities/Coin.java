package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Coin extends Shape
{
	Texture coini;
	
	public Coin(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		coini = loadTexture("coini");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		coini.bind();
		
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
