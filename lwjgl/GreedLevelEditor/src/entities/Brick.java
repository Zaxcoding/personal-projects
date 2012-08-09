package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Brick extends Shape
{
	public boolean up,on;

	Texture brick, brickv;

	public Brick(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		brick = loadTexture("brick");
		brickv = loadTexture("brickv");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.up){
			brickv.bind();
		}
		else{
			brick.bind();
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