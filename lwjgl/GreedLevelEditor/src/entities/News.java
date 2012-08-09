package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class News extends Shape
{

	public boolean alive = true;
	
	Texture news;
	
	public News(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		news = loadTexture("news");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		news.bind();
		
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
