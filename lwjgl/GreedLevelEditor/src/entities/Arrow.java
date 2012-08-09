package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Arrow extends Shape
{

	public boolean pause = false;
	
	public int i;
	
	Texture a1, a2;
	
	public Arrow(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		a1 = loadTexture("arrow");
		a2 = loadTexture("arrow1");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (this.i < 10)
		{
			a1.bind();
		}
		else if (this.i >= 10 && this.i <= 20)
		{
			a2.bind();
			if (this.i == 20)
			{
				this.i = 0;
			}
		}
		this.i++;
		
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
