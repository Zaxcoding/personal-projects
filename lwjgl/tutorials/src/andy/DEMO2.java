package andy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.awt.Font;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.Display; 

import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;


import entities.AbstractMoveableEntity;
import entities.Entity;



public class DEMO2 {
	public static final int width = 640;
	public static final int height = 480;
	float translate_x = 0;
	float translate_y =0;
	private long lastFrame,oldFrame;
	int x = 10,goldCount=0;
	int y =440;
	int dx =1;
	int dy =1;
	private Box2 Box2;
	private Bat[] cliff;
	private Ice[] ice;
	private Dead[] dead;
	private Grav[] grav,bit,appear;
	private Gold[] gold,light;
	private Gold ledge;
	private Ice wall;
	private Box2[] lights;
	private Gem gem;
	int cliffnum=7,lastDIR,icenum=9,deadnum=48,gravnum=17;
	int count;
	int delta = getDelta();
	boolean jump=false,fall=true,gravity=true,onGround=false,DIRlock = false,jumpvar,blueFound=false,onCliff=false;
	boolean blueFound2 = false,goldFound=false,up=false,goldFound2=false,goldFound3=false,goldFound4=false,blueFound3=false;
	boolean goldFound5 = false,win=false;
	public static void main(String [] args){
		new DEMO2();
	}
	public DEMO2(){
		cliff = new Bat[50];
		ice = new Ice[20];
		dead = new Dead[100];
		grav = new Grav[30];
		bit = new Grav[10];
		appear = new Grav[10];
		gold = new Gold[5];
		light = new Gold[5];
		lights = new Box2[5];
		
		gem = new Gem(20,270,80,80);
		ledge = new Gold(225,300,30,5);
		
		
		lights[0] = new Box2(20,230,10,20);
		lights[1] = new Box2(60,230,10,20);
		lights[2] = new Box2(100,230,10,20);
		lights[3] = new Box2(140,230,10,20);
		lights[4] = new Box2(180,230,10,20);
		
		light[0] = new Gold(22,225,6,15);
		light[1] = new Gold(62,225,6,15);
		light[2] = new Gold(102,225,6,15);
		light[3] = new Gold(142,225,6,15);
		light[4] = new Gold(182,225,6,15);
		
		wall = new Ice(2225,170,5,530);
		cliff[0]= new Bat(0,350,400,5);
		cliff[1] = new Bat(1420,200,300,5);
		cliff[2] = new Bat(1700,900,1350,5);
		cliff[3] = new Bat(1805,330,70,5);
		cliff[4] = new Bat(695,560,600,5);
		cliff[5] = new Bat(0,1200,500,5);
		cliff[6] = new Bat(1700,940,1350,5);
		
		ice[0] = new Ice(400,350,300,5);
		ice[1] = new Ice(800,200,600,5);
		ice[2] = new Ice(1810,600,60,5);
		ice[3] = new Ice(1920,550,60,5);
		ice[4] = new Ice(2030,500,60,5);
		ice[5] = new Ice(2140,450,60,5);
		ice[6] = new Ice(2030,400,50,5);
		ice[7] = new Ice(1920,350,50,5);
		ice[8] = new Ice(550,1250,1900,5);
		
		dead[0] = new Dead(695,320,5,230);
		dead[1] = new Dead(700,350,900,5);
		dead[2] = new Dead(0,170,3000,5);
		dead[3] = new Dead(1000,205,5,20);
		dead[4] = new Dead(1200,205,5,40);
		dead[5] = new Dead(1805,200,5,300);
		dead[6] = new Dead(1805,650,5,50);
		dead[7] = new Dead(1805,700,1245,5);
		dead[8] = new Dead(2205,700,5,170);
		dead[9] = new Dead(2400,700,5,50);
		dead[10] = new Dead(2400,700,5,50);
		dead[11] = new Dead(2500,800,5,50);
		dead[12] = new Dead(2600,1100,5,50);
		dead[13] = new Dead(2700,720,5,50);
		dead[14] = new Dead(2800,830,5,50);
		dead[15] = new Dead(695,550,900,5);
		dead[16] = new Dead(3050,700,5,600);
		dead[17] = new Dead(1400,560,5,400);
		dead[18] = new Dead(1250,1080,900,5);
		dead[19] = new Dead(1250,650,5,430);
		dead[20] = new Dead(1250,950,50,5);
		dead[21] = new Dead(1300,830,60,5);
		dead[22] = new Dead(1350,690,50,5);
		dead[23] = new Dead(0,170,5,1130);
		dead[24] = new Dead(400,450,5,700);
		dead[25] = new Dead(20,500,20,5);
		dead[26] = new Dead(10,575,20,5);
		dead[27] = new Dead(15,675,20,5);
		dead[28] = new Dead(5,750,20,5);
		dead[29] = new Dead(300,825,20,5);
		dead[30] = new Dead(125,900,20,5);
		dead[31] = new Dead(65,975,20,5);
		dead[32] = new Dead(399,1050,20,5);
		dead[33] = new Dead(12,1125,20,5);
		dead[34] = new Dead(0,1300,4000,5);
		dead[35] = new Dead(700,1230,5,20);
		dead[36] = new Dead(875,1220,5,30);
		dead[37] = new Dead(1100,1230,5,20);
		dead[38] = new Dead(1200,1240,5,10);
		dead[39] = new Dead(1400,1230,5,20);
		dead[40] = new Dead(1595,1210,5,40);
		dead[41] = new Dead(1800,1230,5,20);
		dead[42] = new Dead(1925,1220,5,30);
		dead[43] = new Dead(2100,1230,5,20);
		dead[44] = new Dead(400,450,250,5);
		dead[45] = new Dead(400,650,850,5);
		dead[46] = new Dead(0,250,200,5);
		dead[47] = new Dead(200,250,5,100);
		
		
		
		
		
		
		bit[0] = new Grav(695,285,5,5);
		bit[1] = new Grav(2090,385,5,5);
		bit[2] = new Grav(1800,1190,5,5);
		appear[0]= new Grav(715,340,50,5);
		appear[1] = new Grav(1810,590,60,5);
		appear[2] = new Grav(1500,540,50,5);
		
		gold[0] = new Gold(1960,340,6,6);
		gold[1] = new Gold(3000,890,6,6);
		gold[2] = new Gold(720,400,6,6);
		gold[3] = new Gold(220,360,6,6);
		gold[4] = new Gold(3000,949,6,6);
		
		
		
		grav[0] = new Grav(680,180,50,5);
		grav[1] = new Grav(1750,200,50,5);
		grav[2] = new Grav(1400,360,50,5);
		grav[3] = new Grav(1650,880,50,5);
		grav[4] = new Grav(1400,360,50,5);
		grav[5] = new Grav(1300,540,50,5);
		grav[6] = new Grav(1200,360,50,5);
		grav[7] = new Grav(1100,540,50,5);
		grav[8] = new Grav(1000,360,50,5);
		grav[9] = new Grav(900,540,50,5);
		grav[10] = new Grav(800,360,50,5);
		grav[11] = new Grav(700,540,50,5);
		grav[12] = new Grav(700,360,50,5);
		grav[13] = new Grav(1500,560,50,5);
		grav[14] = new Grav(1350,1070,50,5);
		grav[15] = new Grav(15,360,50,5);
		grav[16] = new Grav(2550,1250,50,5);
		
		
		
		Box2 = new Box2(320,250,16,16);
		
		initGL();
		
		
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,640,480,0,1,-1);
		glMatrixMode(GL_MODELVIEW);

		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
		
			checkInput();
			render();
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		
	}
	private void checkInput(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
		{
			
				Box2.setX(Box2.getX() +3);
				translate_x -= 3;
				lastDIR = 2;
			
			
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
			
				Box2.setX(Box2.getX() -3);
				translate_x += 3;
				lastDIR = 1;
			
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)&&onGround){
			
			jump = true;
			fall = false;
			onGround=false;
			jumpvar = true;
			//DIRlock = false;
		}
	}
	
	private void render(){
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		//int delta = getDelta();
		translate_x=0;
		translate_y=0;
		//System.out.println("x:" + Box2.getX());
		//System.out.println("y:" + Box2.getY());
		//System.out.println("goldcount:" + goldCount);
		if(win &&dead[47].getY()>100){
			dead[47].upp = true;
		}
		
		if(dead[47].upp){
			dead[47].setY(dead[47].getY()-1);
		}
		if(dead[47].getY()<=100){
			dead[47].upp = false;
		}
		
		if(gem.contains(Box2)||Box2.intersects(gem)){
			System.out.println("WIN");
		}
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					Box2.setY(Box2.getY()-4);
					translate_y+=4;
				}
				else{
					fall = true;
					jump = false;
					jumpvar=false;
					//DIRlock = false;
				}
			}
			else{
				if(getTime()-oldFrame <300)
				{
					Box2.setY(Box2.getY()+4);
					translate_y-=4;
				}
				else{
					fall = true;
					jump = false;
					jumpvar =false;
					//DIRlock = false;
				}
			}
			
		}
		else{
			oldFrame =getTime();
		}
		
		
		if(fall&&!jump&&!jumpvar){
			if(gravity){
				Box2.setY(Box2.getY()+4);
				if(!onGround)
					translate_y-=4;
				//System.out.println("falldown");
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				Box2.setY(Box2.getY()-4);
				if(!onGround)
					translate_y+=4;
				onGround = false;
				//DIRlock = false;
				
			}
			
		}
		
		
		
		
		
		for(int i=0; i<cliffnum;i++){
			if(Box2.on(cliff[i])&&!jump){
				Box2.setY((cliff[i]).getY()-Box2.getHeight());
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				
			}
			else if(Box2.under(cliff[i])&&!jump&&!gravity){
				Box2.setY((cliff[i]).getY()+cliff[i].getHeight());
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				
			}
			else{
				fall = true;
				onCliff= false;
				//onGround = false;
				
				
			}
			cliff[i].draw();
		}
		
		for(int i=0; i<icenum;i++){
			if(Box2.on(ice[i])&&!jump){
				Box2.setY((ice[i]).getY()-Box2.getHeight());
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
			}
			else if(Box2.under(ice[i])&&!jump&&!gravity){
				Box2.setY((ice[i]).getY()+ice[i].getHeight());
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
				
			}
			else if(!onCliff){
				fall = true;
				//onGround = false;
				
				
			}
			
			ice[i].draw();
			
			
		}
		for(int i=0;i<deadnum;i++){
			if(Box2.intersects(dead[i])){
				Display.destroy();
				new DEMO2();
			}
			dead[i].draw();
		}
		
		for(int i=9;i<15;i++){
			if(dead[i].getY()+dead[i].getHeight()>=900){
				dead[i].up=true;
			}
			
			if(dead[i].getY()<=700){
				dead[i].up=false;
			}
			if(dead[i].up)
			{
				if(i%2 ==0)
					dead[i].setY(dead[i].getY()-5);
				else
					dead[i].setY(dead[i].getY()-8);
				
			}
			else{
				
				if(i%2 ==0)
					dead[i].setY(dead[i].getY()+5);
				else
					dead[i].setY(dead[i].getY()+8);
			}
		
			dead[i].draw();
		}
		
		for(int i=25; i<34;i++)
		{
			if(dead[i].getX()>=395){
				dead[i].right=false;
			}
			
			if(dead[i].getX()<=0){
				dead[i].right=true;
			}
			if(dead[i].right)
			{
				if(i%2 ==0)
					dead[i].setX(dead[i].getX()+3);
				else
					dead[i].setX(dead[i].getX()+2);
				
			}
			else{
				
				if(i%2 ==0)
					dead[i].setX(dead[i].getX()-3);
				else
					dead[i].setX(dead[i].getX()-2);
			}
		
			dead[i].draw();
		}
		
				
		
		for(int i=0;i<gravnum;i++){
			if(Box2.under(grav[i])&&!gravity){
				gravity = !gravity;
				DIRlock = false;
				
			}
			
			if(Box2.on(grav[i])&&gravity){
				gravity = !gravity;
				DIRlock = false;
				
			}
			grav[i].draw();
		}
		
		if(Box2.intersects(bit[0])||Box2.contains(bit[1])){
			blueFound = true;
		}
		if(Box2.intersects(bit[1])||Box2.contains(bit[1])){
			blueFound2 = true;
			
		}
		if(Box2.intersects(bit[2])||Box2.contains(bit[2])){
			blueFound3 = true;
			
		}
		if(Box2.intersects(gold[0])||Box2.contains(gold[0])&&!goldFound){
			goldFound=true;
			goldCount++;
		}
		
		if(Box2.intersects(gold[1])||Box2.contains(gold[1])&&!goldFound2){
			goldFound2=true;
			goldCount++;
		}
		if(Box2.intersects(gold[2])||Box2.contains(gold[2])&&!goldFound3){
			goldFound3=true;
			goldCount++;
		}
		if(Box2.intersects(gold[3])||Box2.contains(gold[3])&&!goldFound4){
			goldFound4=true;
			goldCount++;
		}
		if(Box2.intersects(gold[4])||Box2.contains(gold[4])&&!goldFound5){
			goldFound5=true;
			goldCount++;
		}
		if(blueFound){
			appear[0].draw();
		}
		else{
			bit[0].draw();
		}
			
		if(blueFound2){
			appear[1].draw();
		}
		else{
			bit[1].draw();
		}
		if(blueFound3){
			appear[2].draw();
		}
		else{
			bit[2].draw();
		}
		if(goldFound){
			
		}
		else{
			gold[0].draw();
		}
		if(goldFound2){
			
		}
		else{
			gold[1].draw();
		}
		if(goldFound3){
			
		}
		else{
			gold[2].draw();
		}
		if(goldFound4){
			
		}
		else{
			gold[3].draw();
		}
		if(goldFound5){
			
		}
		else{
			gold[4].draw();
		}
		
		
		if(Box2.on(appear[0])&&blueFound){
			Box2.setY(appear[0].getY()-Box2.getHeight());
			gravity = !gravity;
			System.out.println("hey");
			
			fall = true;
		}
		if(Box2.on(appear[1])&&blueFound2){
			Box2.setY(appear[1].getY()-Box2.getHeight());
			gravity = !gravity;
			System.out.println("hey");
			
			fall = true;
		}
		if(Box2.on(appear[2])&&blueFound3){
			Box2.setY(appear[2].getY()-Box2.getHeight());
			gravity = !gravity;
			System.out.println("hey");
			
			fall = true;
		}
		
		if(DIRlock){
			if(lastDIR ==1){
				Box2.setX(Box2.getX() -3);
				translate_x +=3;
			}
			else if(lastDIR==2){
				Box2.setX(Box2.getX() +3);
				translate_x -=3;
			}
			
			if(Box2.intersects(wall)){
				if(lastDIR==1){
					lastDIR=2;
				}
				if(lastDIR==2){
					lastDIR=1;
				}
			}
		}
		
		for(int i=0;i<5;i++){
			lights[i].draw();
			if(lights[i].on){
				light[i].draw();
				count++;
			}
		}
		
		if(count ==5){
			
		}
		
		if(Box2.on(ledge)&&!jump){
			Box2.setY(ledge.getY()-Box2.getHeight());
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			if(goldCount>0){
				if(!lights[0].on){
					lights[0].on=true;
					light[0].draw();
					goldCount--;
				}
			}
			if(goldCount>0){
				if(!lights[1].on){
					lights[1].on=true;
					light[1].draw();
					goldCount--;
				}
			}
			if(goldCount>0){
				if(!lights[2].on){
					lights[2].on=true;
					light[2].draw();
					goldCount--;
				}
			}
			if(goldCount>0){
				if(!lights[3].on){
					lights[3].on=true;
					light[3].draw();
					goldCount--;
				}
			}
			if(goldCount>0){
				if(!lights[4].on){
					lights[4].on=true;
					light[4].draw();
					goldCount--;
					win = true;
				}
			}
			
		}
		wall.draw();
		Box2.draw();
		ledge.draw();
		gem.draw();
		
	}
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Andy's Game");
			Display.create();
		} catch (LWJGLException e) 
		{	e.printStackTrace();	}

		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (1024, 600) is the bottom right one.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		
		return delta;
		
	}
	private long getTime(){
		return(Sys.getTime() *1000/ Sys.getTimerResolution());
		
	}
	
}



class Box2 extends AbstractMoveableEntity{
	boolean on=false;
	public Box2(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	@Override
	public void draw(){
		glColor3d(.7,.7,.7);
			glRectd(x,y, x+width, y+height);
		
	}
	
	
	public boolean intersects(Entity other)
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

	boolean on(Entity other)
	{
		return(((this.getX() <= (other.getX() + other.getWidth()) || (this.getX() + this.getWidth())<=(other.getX() + other.getWidth())))&& ((this.getX() >=other.getX())||((this.getX()+this.getWidth()) >=other.getX()) ) && ((this.getY()+this.getHeight()) >=other.getY()) && ((this.getY()+this.getHeight()) <= (other.getY() + other.getHeight())));
	}
	boolean leftOf(Entity other)
	{
		return((this.getX()+this.getWidth()+10)>=other.getX() &&this.getX()<=other.getX()&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	private boolean rightOf(Entity other)
	{
		return(this.getX()>=other.getX() &&this.getX()<=(other.getX()+other.getWidth()+10)&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	
	boolean under(Entity other)
	{
		return((this.getX()+this.getWidth()) >= other.getX() && this.getX() <= (other.getX() + other.getWidth()) && (this.getY()-this.getHeight()) <= other.getY() && (this.getY()) >= other.getY());
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}
class Bat extends AbstractMoveableEntity{
	
	public Bat(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glColor3d(1.0,1,1);
			glRectd(x,y, x+width, y+height);
		
	}
	
	
}
class Ice extends AbstractMoveableEntity{
	
	public Ice(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glColor3d(0,1,1);
			glRectd(x,y, x+width, y+height);
		
	}
	
	
}
class Dead extends AbstractMoveableEntity{
	boolean up=true,upp=false,right= true;
	public Dead(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(1.0,0,0);
			glRectd(x,y, x+width, y+height);
		
	}
	
	
}



class Gem extends AbstractMoveableEntity{
	
	public Gem(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(.8,0,.6);
			glRectd(x,y, x+width, y+height);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}
class Gold extends AbstractMoveableEntity{
	
	public Gold(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(1,1,0);
			glRectd(x,y, x+width, y+height);
		
	}
	private boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}





class Grav extends AbstractMoveableEntity{
	
	public Grav(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(0,.2,.9);
			glRectd(x,y, x+width, y+height);
		
	}
	public boolean intersects(Entity other)
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

