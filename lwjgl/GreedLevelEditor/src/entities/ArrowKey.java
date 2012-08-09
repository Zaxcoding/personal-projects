package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class ArrowKey extends Shape
{
	public int type = 0;
	
	Texture a3, a4, a5, esc, space;
	
	public ArrowKey(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		a3 = loadTexture("a3");
		a4 = loadTexture("a4");
		a5 = loadTexture("a5");
		esc = loadTexture("esc");
		space = loadTexture("spaces");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.type==0){
			a3.bind();
		}
		else if(this.type==1){
			a4.bind();
		}
		else if(this.type==2){
			a5.bind();
		}
		else if(this.type==3){
			esc.bind();
		}
		else if(this.type==4){
			space.bind();
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
