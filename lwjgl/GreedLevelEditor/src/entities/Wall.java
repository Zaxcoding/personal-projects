package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Wall extends Shape
{
	
	Texture wallpaper;

	public Wall(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		wallpaper = loadTexture("wallpaper");
	}

	@Override
	public void draw()
	{
		textureStart();
		wallpaper.bind();
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
