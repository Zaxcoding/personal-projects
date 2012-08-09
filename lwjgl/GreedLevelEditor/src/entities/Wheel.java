package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Wheel extends Shape
{

	public boolean switch1 = true;
	Texture wheeli, wheeli2;
	
	public Wheel(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		wheeli = loadTexture("wheeli");
		wheeli2 = loadTexture("wheeli2");
	}

	@Override
	public void draw()
	{
		textureStart();
	/*
		if(ledge.getY()<265){
			switch1= true;
		}
		else if(ledge.getY()>265 &&ledge.getY()<275){
			switch1 = false;
		}
		else if(ledge.getY()>275 &&ledge.getY()<285){
			switch1 = true;
		}
		else if(ledge.getY()>285 &&ledge.getY()<295){
			switch1 = false;
		}
		else if(ledge.getY()>295 &&ledge.getY()<305){
			switch1 = true;
		}
		else if(ledge.getY()>305 &&ledge.getY()<315){
			switch1 = false;
		}
		else if(ledge.getY()>315 &&ledge.getY()<325){
			switch1 = true;
		}
		else if(ledge.getY()>325 &&ledge.getY()<335){
			switch1 = false;
		}
		else if(ledge.getY()>335 &&ledge.getY()<345){
			switch1 = true;
		}
		else if(ledge.getY()>345 &&ledge.getY()<355){
			switch1 = false;
		}
		else if(ledge.getY()>355 &&ledge.getY()<365){
			switch1 = true;
		}
		else if(ledge.getY()>365 &&ledge.getY()<375){
			switch1 = false;
		}
	*/
		if(switch1){
			wheeli.bind();
		}
		else{
			wheeli2.bind();
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
