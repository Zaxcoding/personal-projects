package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Doorjam extends Shape
{
	public boolean pause, upp = false, vert = false;
	
	Texture doorjam, doorjamv;
	
	public Doorjam(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		doorjam = loadTexture("doorjam");
		doorjamv = loadTexture("doorjamv");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(!this.vert){
			doorjam.bind();
		}
		else{
			doorjamv.bind();
		}
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
