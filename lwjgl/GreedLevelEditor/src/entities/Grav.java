package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.newdawn.slick.opengl.Texture;

public class Grav extends Shape
{
	public int i = 0, init = 5;
	Texture gravflip, gravflip2, gravflip3, gravflip4, gravflip5, gravflip6, 
			gravflip7, gravflip8, gravflip9, gravflip10, gravflip11, gravflip12, 
			gravflip13, gravflip14, gravflip15, gravflip16, gravflip17, gravflip18; 

	public Grav(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		gravflip = loadTexture("gravflip1");
		gravflip2 = loadTexture("gravflip2");
		gravflip3 = loadTexture("gravflip3");
		gravflip4 = loadTexture("gravflip4");
		gravflip5 = loadTexture("gravflip5");
		gravflip6 = loadTexture("gravflip6");
		gravflip7 = loadTexture("gravflip7");
		gravflip8 = loadTexture("gravflip8");
		gravflip9 = loadTexture("gravflip9");
		gravflip10 = loadTexture("gravflip10");
		gravflip11 = loadTexture("gravflip11");
		gravflip12 = loadTexture("gravflip12");
		gravflip13 = loadTexture("gravflip13");
		gravflip14 = loadTexture("gravflip14");
		gravflip15 = loadTexture("gravflip15");
		gravflip16 = loadTexture("gravflip16");
		gravflip17 = loadTexture("gravflip17");
		gravflip18 = loadTexture("gravflip18");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.i<=2*init){
			gravflip.bind();
		}
		else if(this.i>2*init&&this.i<=4*init)
		{
			gravflip2.bind();
		}
		else if(this.i>4*init&&this.i<=6*init)
		{
			gravflip3.bind();
		}
		else if(this.i>6*init&&this.i<=8*init)
		{
			gravflip4.bind();
		}
		else if(this.i>8*init&&this.i<=10*init)
		{
			gravflip5.bind();
		}
		else if(this.i>10*init&&this.i<=12*init)
		{
			gravflip6.bind();
		}
		else if(this.i>12*init&&this.i<=14*init)
		{
			gravflip7.bind();
		}
		else if(this.i>14*init&&this.i<=16*init)
		{
			gravflip8.bind();
		}
		else if(this.i>16*init&&this.i<=18*init)
		{
			gravflip9.bind();
		}
		else if(this.i>18*init&&this.i<=20*init)
		{
			gravflip10.bind();
		}
		else if(this.i>20*init&&this.i<=22*init)
		{
			gravflip11.bind();
		}
		else if(this.i>22*init&&this.i<=24*init)
		{
			gravflip12.bind();
		}
		else if(this.i>24*init&&this.i<=26*init)
		{
			gravflip13.bind();
		}
		else if(this.i>26*init&&this.i<=28*init)
		{
			gravflip14.bind();
		}
		else if(this.i>28*init&&this.i<=30*init)
		{
			gravflip15.bind();
		}
		else if(this.i>30*init&&this.i<=32*init)
		{
			gravflip16.bind();
		}
		else if(this.i>32*init&&this.i<=34*init)
		{
			gravflip17.bind();
		}
		else if(this.i>34*init&&this.i<=36*init)
		{
			gravflip18.bind();
			if(this.i==36*init){
				this.i=0;
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
		boolean intersect = false;
		if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&this.getY()>=other.getY()&&this.getY()<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if(this.getX()>= other.getX()&&this.getX()<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if(this.getX()>= other.getX()&&this.getX()<=other.getX()+other.getWidth()&&this.getY()>=other.getY()&&this.getY()<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}	
		return intersect;
	}

}
