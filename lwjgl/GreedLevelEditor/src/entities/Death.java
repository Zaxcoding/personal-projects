package entities;

import static org.lwjgl.opengl.GL11.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Dead extends Shape
{
	public boolean up = true, upp = false, right = true, vert = false;
		
	public int i = 0, j = 0;
	
	public Texture deadi, deadi1, deadi2, deadi3, deadi4, deadv, deadv1, deadv2, deadv3, deadv4;

	public Dead(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		deadi = loadTexture("deadi");
		deadi1 = loadTexture("deadi1");
		deadi2 = loadTexture("deadi2");
		deadi3 = loadTexture("deadi3");
		deadi4 = loadTexture("deadi4");
		deadv = loadTexture("deadv");
		deadv1 = loadTexture("deadv1");
		deadv2 = loadTexture("deadv2");
		deadv3 = loadTexture("deadv3");
		deadv4 = loadTexture("deadv4");
	}

	@Override
	public void draw()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		if(this.vert){
			if(this.j<=10){
				deadv.bind();
			}
			else if(this.j<=20){
				deadv1.bind();
			}
			else if(this.j<=30){
				deadv2.bind();
			}
			else if(this.j<=40){
				deadv3.bind();
			}
			else if(this.j<=50){
				deadv4.bind();
				if(this.j==50)
					this.j=0;
			}
			else{
				this.j = 0;
			}
			this.j++;
		}
		else{
			if(this.i<=10){
				deadi.bind();
			}
			else if(this.i<=20){
				deadi1.bind();
			}
			else if(this.i<=30){
				deadi2.bind();
			}
			else if(this.i<=40){
				deadi3.bind();
			}
			else if(this.i<=50){
				deadi4.bind();
				if(i==50)
					this.i=0;
			}
			
			this.i++;
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
