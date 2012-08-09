package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Box extends Shape
{
	public boolean on = false;		// on what?
	public boolean gravity;			// moved this here
	public int type = 0;			// not too clear on what this is
	
	public int i = 0, lastDIR = 0;
	
	public Texture left, right, gright, gleft;
	
	
	public Box(double x, double y , double width, double height)
	{
		super(x,y,width,height);
		left = loadTexture("left");
		gleft = loadTexture("gleft");
		right = loadTexture("right");
		gright = loadTexture("gright");
		
	}
	
	@Override
	public void draw(){
		textureStart();
		/*
		if(goldCount==0){
			if(gravity){
				if(lastDIR==1){
					left5.bind();
				}
				else if(lastDIR==2){
					right5.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft5.bind();
				}
				else if(lastDIR==2){
					gright5.bind();
				}
			}
		}
		else if(goldCount ==1){
			if(gravity){
				if(lastDIR==1){
					left4.bind();
				}
				else if(lastDIR==2){
					right4.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft4.bind();
				}
				else if(lastDIR==2){
					gright4.bind();
				}
			}
		}
		else if(goldCount ==2){
			if(gravity){
				if(lastDIR==1){
					left3.bind();
				}
				else if(lastDIR==2){
					right3.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft3.bind();
				}
				else if(lastDIR==2){
					gright3.bind();
				}
			}
		}
		else if(goldCount ==3){
			if(gravity){
				if(lastDIR==1){
					left2.bind();
				}
				else if(lastDIR==2){
					right2.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft2.bind();
				}
				else if(lastDIR==2){
					gright2.bind();
				}
			}
		}
		else if(goldCount ==4){
			if(gravity){
				if(lastDIR==1){
					left1.bind();
				}
				else if(lastDIR==2){
					right1.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft1.bind();
				}
				else if(lastDIR==2){
					gright1.bind();
				}
			}
		}
		else if(goldCount ==5){
			if(gravity){
				if(lastDIR==1){
					left.bind();
				}
				else if(lastDIR==2){
					right.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft.bind();
				}
				else if(lastDIR==2){
					gright.bind();
				}
			}
		}
		
		i++;
		if(i>100&&i<200){
			goldCount =1;
		}
		else if(i>200&&i<300){
			goldCount=2;
		}
		else if(i>300&&i<400){
			goldCount=3;
		}
		else if(i>400&&i<500){
			goldCount=4;
		}
		else if(i>500&&i<600){
			goldCount=5;
		}
		else if(i>600){
			goldCount=0;
			i=0;
		}
		*/
		
		if (this.type == 0){		// isn't this always the case?
			if(gravity){
				if(lastDIR==1){
					left.bind();
				}
				else if(lastDIR==2){
					right.bind();
				}	
			}
			else{
				if(lastDIR==1){
					gleft.bind();
				}
				else if(lastDIR==2){
					gright.bind();
				}
			}
		}
		else{
			// glColor4d(1,1,1,1);
			gright.bind();
		}
		
		textureVertices();
	}
	
	public boolean intersects(Shape other)
	{
		boolean intersect = false;
		double p1y = this.getY();
		double p1x = this.getX();
		double p2x = this.getX() + this.getWidth();
		double p2y = this.getY()+this.getHeight();
		double p3y = other.getY();
		double p3x = other.getX();
		double p4x = other.getX()+other.getWidth();
		double p4y = other.getY()+other.getHeight();
		
		if(p2y<p3y || p1y>p4y || p2x<p3x ||p1x>p4x){
			
		}
		else{
			intersect = true;
		}
		//! ( P2.y < P3.y || P1.y > P4.y || P2.x < P3.x || P1.x > P4.x )
		
		
		/*
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
		*/
		return intersect;
	}

	public boolean on(Shape other)
	{
		return(((this.getX() <= (other.getX() + other.getWidth()) || (this.getX() + this.getWidth())<=(other.getX() + other.getWidth())))&& ((this.getX() >=other.getX())||((this.getX()+this.getWidth()) >=other.getX()) ) && ((this.getY()+this.getHeight()) >=other.getY()) && ((this.getY()+this.getHeight()) <= (other.getY() + other.getHeight())));
	}
	
	public boolean leftOf(Shape other)
	{
		return((this.getX()+this.getWidth()+10)>=other.getX() &&this.getX()<=other.getX()&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	
	public boolean rightOf(Shape other)
	{
		return(this.getX()>=other.getX() &&this.getX()<=(other.getX()+other.getWidth()+10)&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	
	public boolean under(Shape other)
	{
		return((this.getX()+this.getWidth()) >= other.getX() && this.getX() <= (other.getX() + other.getWidth()) && (this.getY()-this.getHeight()) <= other.getY()-other.getHeight() && (this.getY()) >= other.getY());
	}
	
	public boolean contains(Shape other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
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
	
}