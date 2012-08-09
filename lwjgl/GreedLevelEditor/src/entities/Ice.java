package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Ice extends Shape
{
	Texture icev, icel;

	public boolean up=false;
	
	public Ice(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		icev = loadTexture("icev");
		icel = loadTexture("icel");
	}		
	
	@Override
	public void draw()
	{
		textureStart();
		
		if(this.up)
			icev.bind();
		else
			icel.bind();
		
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