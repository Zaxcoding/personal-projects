package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Skyline extends Shape
{
	public int that;
	
	Texture sky1,sky2,sky3,sky4,sky5,sky6;
	
	public Skyline(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		sky1 = loadTexture("sky1");
		sky2 = loadTexture("sky2");
		sky3 = loadTexture("sky3");
		sky4 = loadTexture("sky4");
		sky5 = loadTexture("sky5");
		sky6 = loadTexture("sky6");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.that ==0){
			sky1.bind();
		}
		else if(this.that ==1){
			sky2.bind();
		}
		else if(this.that ==2){
			sky3.bind();
		}
		else if(this.that ==3){
			sky4.bind();
		}
		else if(this.that ==4){
			sky5.bind();
		}
		else if(this.that ==5){
			sky6.bind();
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
