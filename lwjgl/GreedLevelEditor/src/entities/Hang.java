package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Hang extends Shape
{
	
	public boolean upp = false;
	
	Texture hangi, hangv;

	public Hang(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		hangi = loadTexture("hangi");
		hangv = loadTexture("hangv");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(!this.upp){
			hangi.bind();
		}
		else{
			hangv.bind();
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
