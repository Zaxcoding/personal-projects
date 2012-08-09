package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Ledge extends Shape
{
	public boolean upp = false;
	
	Texture ledgei;

	public Ledge(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		ledgei = loadTexture("ledgei");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		ledgei.bind();
		
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
