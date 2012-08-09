package entities;

import static org.lwjgl.opengl.GL11.glColor4d;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.newdawn.slick.opengl.Texture;

public class Cloud extends Shape
{
	
	public int type, startx;

	Texture cloud1,cloud2,cloud3,cloud4,cloud5,cloud6,cloud7,cloud8,cloud9,cloud10;
	
	public Cloud(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		this.startx = (int) x;
		cloud1 = loadTexture("cloud1");
		cloud2 = loadTexture("cloud2");
		cloud3 = loadTexture("cloud3");
		cloud4 = loadTexture("cloud4");
		cloud5 = loadTexture("cloud5");
		cloud6 = loadTexture("cloud6");
		cloud7 = loadTexture("cloud7");
		cloud8 = loadTexture("cloud8");
		cloud9 = loadTexture("cloud9");
		cloud10 = loadTexture("cloud10");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.dx ==7){
			glColor4d(1.0,1,1,.7);
		}
		if(this.type==1){
			cloud1.bind();
		}
		else if(this.type==2){
			cloud2.bind();
		}
		else if(this.type==3){
			cloud3.bind();
		}
		else if(this.type==4){
			cloud4.bind();
		}
		else if(this.type==5){
			cloud5.bind();
		}
		else if(this.type==6){
			cloud6.bind();
		}
		else if(this.type==7){
			cloud7.bind();
		}
		else if(this.type==8){
			cloud8.bind();
		}
		else if(this.type==9){
			cloud9.bind();
		}
		else if(this.type==10){
			cloud10.bind();
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
