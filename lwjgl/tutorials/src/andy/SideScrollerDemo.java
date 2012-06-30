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

public class SideScrollerDemo {
	private Texture welcome,escape;
	private Texture space; 
	float translate_x = 0;
	float translate_y =0;
	private long lastFrame,oldFrame;
	int x = 10;
	int y =440;
	int dx =1;
	int dy =1;
	int goldCount =1;
	UnicodeFont uniFont,uniFont2;
	public static final int width = 640;
	public static final int height = 480;
	private Bat bat,bat3,bat4,bat5,bat6;
	int lives =5;
	private Bat end1,end2,end3,end4,end5,end6,end7,end8,end9,end10,end11;
	private boolean jump=false,fall = true,onGround=false,fallPoint = true,bounce =false,bounce2=false,go=false;
	private boolean notMoved=true,blueFound = false,blueFound2=false,goldFound=false,goldFound2=false,goldFound3=false,goldFound4=false,goldFound6=true;
	private boolean onGold7=false,onGold8=false,onGold9=false,onGold10=false,onGold11=false,newWorld=false;
	boolean moving;
	float mousex = Mouse.getX() - translate_x;
	float mousey = 480 - Mouse.getY() -1;
	private Box box;
	private Grav grav,grav2,key,key2,grav3,grav4,grav5;
	private Bat [] cliff = new Bat[43];
	private Dead [] dead= new Dead[23];
	double groundY=445;
	boolean gravity = true;
	private Gold gold,gold2,gold3,gold4,gold5,gold6,gold7,gold8,gold9,gold10,gold11;
	private Gem gem;
	
	private static enum State {
		INTRO, MAIN_MENU,LEVEL1,LEVEL_SELECT,ABOUT,EXIT,GAMEOVER,WIN;
		
	}
	private State state = State.INTRO ;
	
	private long getTime(){
		return(Sys.getTime() *1000/ Sys.getTimerResolution());
		
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		
		return delta;
		
	}
	public static void main(String[] args){
		
		new SideScrollerDemo();
	}

	public SideScrollerDemo(){
		initGL();
		initFonts();
		welcome = loadTexture("welcome");
		space= loadTexture("space");
		escape = loadTexture("escape");
		
		bat = new Bat(0, 475, 400, 480);
		
		bat3 = new Bat(940,270,400,200);
		bat4 = new Bat(2110,100,35,7700);
		bat5 = new Bat(2510,100,35,7600);
		bat6 = new Bat(5690,7700,20,100);
		
		end1 = new Bat(16000,1000,10,300);
		end2 = new Bat(16000,1000,100,10);
		end3 = new Bat(16000,1150,60,10);
		end4 = new Bat(16000,1300,100,10);
		
		end5 = new Bat(16170,1000,10,300);
		end6 = new Bat(16170,1000,100,10);
		end7 = new Bat(16270,1000,10,300);
		
		end8 = new Bat(16340,1000,10,300);
		end9 = new Bat(16340,1000,100,10);
		end10 = new Bat(16340,1300,100,10);
		end11 = new Bat(16442,1005,10,290);
		
		box = new Box(320,100,30,30);
		cliff[1] = new Bat(510,430,80,15);
		cliff[2]= new Bat(730,400,69,15);
		cliff[3]= new Bat(910,370,92,15);
		cliff[4]= new Bat(1410,260, 84,15);
		cliff[5]= new Bat(1560,310,110,15);
		cliff[6]= new Bat(1875,190,60,15);
		cliff[7]= new Bat(1820,350,100,15);
		cliff[8]= new Bat(2000,270,90,15);
		cliff[9]= new Bat(2020,160,40,15);
		cliff[10] = new Bat(2110,7800,464,15);
		cliff[11] = new Bat(2575,7801,100,14);
		cliff[12] = new Bat(2676,7800,300,15);
		cliff[13] = new Bat(2977,7801,100,14);
		cliff[14] = new Bat(3058,7800,600,15);
		cliff[15] = new Bat(3400,7400,500,25);
		cliff[16] = new Bat(2575,7802,100,13);
		cliff[17] = new Bat(2977,7802,100,13);
		cliff[18] = new Bat(6659,7800,400,15);
		cliff[19] = new Bat(3950,7430,100,15);
		cliff[20] = new Bat(4120,7490,100,15);
		cliff[21] = new Bat(4270,7540,100,15);
		cliff[22] = new Bat(4420,7600,100,15);
		cliff[23] = new Bat(4570,7630,100,15);
		cliff[24] = new Bat(4690,7700,1000,15);
		cliff[25] = new Bat(6000,7740,100,15);
		cliff[26] = new Bat(6190,7690,100,15);
		cliff[27] = new Bat(5980,7620,100,15);
		cliff[28] = new Bat(5800,7550,100,15);
		cliff[29] = new Bat(6000,7480,100,15);
		cliff[30] = new Bat(6200,7410,100,15);
		cliff[31] = new Bat(6000,7350,100,15);
		cliff[32] = new Bat(5800,7280,100,15);
		cliff[33] = new Bat(6000,7210,100,15);
		cliff[34] = new Bat(6200,7140,100,15);
		cliff[35] = new Bat(6000,7070,100,15);
		cliff[36] = new Bat(5800,7000,100,15);
		cliff[37] = new Bat(6000,6930,100,15);
		cliff[38] = new Bat(6200,6860,100,15);
		cliff[39] = new Bat(6000,6790,100,15);
		cliff[40] = new Bat(5800,6720,100,15);
		cliff[41] = new Bat(5300,1400,1000,15);
		cliff[42] = new Bat(8000,1400,500,15);
		dead[1] = new Dead(400,476,1700,20);
		dead[2] = new Dead(2545,7400,200,15);
		dead[3] = new Dead(2700,7400,100,15);
		dead[4] = new Dead(2800,7400,600,15);
		dead[5] = new Dead(3658,7800,2700,15);
		dead[6] = new Dead(2977,7400,100,15);
		dead[7] = new Dead(3800,7800,100,15);
		dead[8] = new Dead(3900,7400,1865,15);
		dead[9] = new Dead(6358,1650,30,6150);
		dead[10] = new Dead(5750,7800,608,15);
		dead[11] = new Dead(5750,6700,15,700);
		dead[12] = new Dead(5300,500,15,6900);
		dead[13] = new Dead(5750,2000,15,4000);
		dead[14] = new Dead(5750,4800,608,15);
		dead[15] = new Dead(-5,20, 10, 38000);
		dead[16] = new Dead(5400,4200,100,15);
		dead[17] = new Dead(5575,3400,100,15);
		dead[18] = new Dead(5450,2700,100,15);
		dead[19] = new Dead(5300,1500,1000,15);
		dead[20] = new Dead(7500,1430,3000,15);
		dead[21] = new Dead(7900,800,2000,15);
		dead[22] = new Dead(5300,1000,3000,15);
		
		grav = new Grav(3500,7765,100,15);
		grav2 = new Grav(4420,7420,100,15);
		key = new Grav(5590,7730 ,5,5);
		key2 = new Grav(5650,7200,5,5);
		grav3 = new Grav(5500,7350,200,15);
		grav4 = new Grav(5950,4850,200,15);
		grav5 = new Grav(6200,1100,100,15);
		gold = new Gold(5850,5230,10,10);
		gold2 = new Gold(5360,4200,10,10);
		gold3 = new Gold(5360,3400,10,10);
		gold4 = new Gold(5610,2650,10,10);
		gold5 = new Gold(5330,1280,70,130);
		gold6 = new Gold(8420,1360,10,10);
		gold7 = new Gold(8500,1360,100,15);
		gold8 = new Gold(8600,1320,100,15);
		gold9 = new Gold(8700,1280,100,15);
		gold10 = new Gold(8800,1240,100,15);
		gold11 = new Gold(8900,1200,1000,15);
		gem = new Gem(9800,1120,60,100);
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
	

//classes
private void drawIntro(){
	
	glColor3f(.3f,0.7f,0.1f);
	glRectf(0,0,640,480);
	
	glColor3f(0f,0f,0f);
	glRectf(160,50,480,430);
	glEnable(GL_BLEND);
    
	uniFont.drawString(260, 60, "Welcome");
	uniFont.drawString(300, 90, "to");
	uniFont.drawString(280, 120, "GAME");
	uniFont.drawString(180, 380, "PRESS SPACE TO START");
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
	glDisable(GL_BLEND);
	
}

private void drawAbout(){
	
	glColor3f(.7f,0.3f,0.1f);
	glRectf(0,0,640,480);
	
	glColor3f(0f,0f,0f);
	glRectf(160,50,480,430);
	glEnable(GL_BLEND);
	uniFont.drawString(170,60,"ABOUT");
	uniFont2.drawString(170, 120, "This game was developed by Andrew Beers.");
	uniFont2.drawString(170, 180, "It is meant for anyone who would like to play.");
	uniFont2.drawString(170, 240, "A Special Thanks to Zach Sadler.");
	uniFont.drawString(170,310,"M-Main Menu");
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
	glDisable(GL_BLEND);
	
}

private void draw() {
	
	glPushMatrix();
	glTranslatef(translate_x,translate_y,0);
	int delta = getDelta();
	if(gravity)
	{
		groundY = 8500;
	}
	else{
		groundY = 0;
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
	{
		if(box.leftOf(bat3))
		{}
		else if(box.leftOf(bat4))
		{}
		else if(box.leftOf(bat5))
		{}
		else if(box.leftOf(bat6))
		{}
		else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			box.setX(box.getX() +delta*dx*.8);
			translate_x -= 12.9;
		}
		else{
			box.setX(box.getX() +delta*dx*.5);
			translate_x -= 8.3;
		}
		
	}
	else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
		
		if(box.rightOf(bat3))
		{}
		else if(box.rightOf(bat4))
		{}
		else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			box.setX(box.getX() -delta*dx*.8);
			translate_x += 12.9;
		}
		else
		{
			box.setX(box.getX() -delta*dx*.5);
			translate_x += 8.3;
		}
		
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_UP)&&!jump){
		
			jump = true;
			fall = false;
	}

	if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
		
		translate_y += 5;
		
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
	{
		translate_y-=5;
	}
	
	
	if(jump)
	{
		if(gravity){
			
			if(getTime()-oldFrame <200)
			{
				box.setY(box.getY()-delta*dy*.5);
				
			}
			else{
				fall = true;
			}
		}
		else{
			if(getTime()-oldFrame <200)
			{
				box.setY(box.getY()+delta*dy*.5);
				
			}
			else{
				fall = true;
			}
		}
		
	}
	else{
		oldFrame =getTime();
		fall = true;
	}
	

	System.out.println("Y = " + box.getY());
	System.out.println("X = " + box.getX());
	System.out.println("gold = " + goldCount);
	if(box.getX() > 200 &&dead[15].getX()<5200)
	{
		dead[15].setX(dead[15].getX()+delta*dx*.05 );
		
	}
	
	if(box.getX()>5600 && box.getY()<7400 &&box.getY()>5000&&!bounce&&dead[10].getY()>6700)
	{
		
		translate_y+=1.4;
		dead[10].setY(dead[10].getY()-delta*dy*.12);
	}
	
	if(box.getY()>480 && box.getY()<7699 && fallPoint)
	{
		translate_y-=8.5;
		
	}
	else if(box.getY()>7649)
	{
		translate_y+=0;
		fallPoint = false;
	}
	
	
	for(int i=1;i<43;i++){
		if(box.on(cliff[i])&&gravity)
		{
			groundY = (cliff[i].getY()-box.getHeight());
			if(i==11||i==13){
				cliff[i].setY(cliff[i].getY()-7);
				box.setY(box.getY()-7);
			}
			onGround = true;
			jump = false;
		}
	
		cliff[i].draw();
	}
	
	
	if(gravity)
	{
		
		if(box.on(bat)){
			groundY =  (bat.getY()-box.getHeight());
		
			onGround = true;
			jump = false;
		}
		else if(box.on(bat3)){
			groundY = (bat3.getY()-box.getHeight());
		
			onGround = true;
			jump = false;
		}	
		else if(box.on(bat4)){
			groundY = (bat4.getY()-box.getHeight());
		
			onGround = true;
			jump = false;
		}
		else if(box.on(grav)){
			groundY = (grav.getY()-box.getHeight());
			gravity = false;
			onGround = true;
			jump = false;
			fall = true;
		}
		else if(box.on(grav3)&&blueFound2){
			groundY = (grav3.getY()-box.getHeight());
			gravity = false;
			onGround = true;
			jump = true;
			fall = true;
		}
		else if(box.on(gold7)&&goldFound6&&goldCount>=0){
			if(!onGold7){
				goldCount--;
			}
			groundY = (gold7.getY()-box.getHeight());
			onGround = true;
			jump = false;
			fall = true;
			onGold7 = true;
			
		}
		else if(box.on(gold8)&&goldCount>=0){
			if(!onGold8){
				goldCount--;
			}
			groundY = (gold8.getY()-box.getHeight());
			onGround = true;
			jump = false;
			fall = true;
			onGold8=true;
			
		}
		else if(box.on(gold9)&&goldCount>=0){
			if(!onGold9){
				goldCount--;
			}
			groundY = (gold9.getY()-box.getHeight());
			onGround = true;
			jump = false;
			fall = true;
			onGold9=true;
			
		}
		else if(box.on(gold10)&&goldCount>=0){
			if(!onGold10){
				goldCount--;
			}
			System.out.println("10");
			groundY = (gold10.getY()-box.getHeight());
			onGround = true;
			jump = false;
			fall = true;
			onGold10=true;
			
		}
		else if(box.on(gold11)&&goldCount>=0){
			if(!onGold11){
				goldCount--;
			}
			groundY = (gold11.getY()-box.getHeight());
			onGround = true;
			jump = false;
			fall = true;
			onGold11 = true;
		}
	}
	else
	{	
		if(box.under(cliff[15]))
		{
			groundY = cliff[15].getY()+box.getHeight();
			jump = false;
			onGround = true;
		}
		else if(box.under(cliff[19]))
		{
			groundY = cliff[19].getY()+20;
			jump = false;
			onGround = true;
		}
		else if(box.under(cliff[20]))
		{	
			groundY = cliff[20].getY()+20;
			jump = false;
			onGround = true;
		}
		else if(box.under(cliff[21]))
		{	
			groundY = cliff[21].getY()+20;
			jump = false;
			onGround = true;
		}
		else if(box.under(cliff[22]))
		{	
			groundY = cliff[22].getY()+20;
			jump = false;
			onGround = true;
		}
		else if(box.under(cliff[23]))
		{
			groundY = cliff[23].getY()+20;
			jump = false;
			onGround = true;
		}
		else if(box.under(cliff[24]))
		{
			groundY = cliff[24].getY()+20;
			jump = false;
			onGround = true;
		}
		else if(box.under(grav2)&&blueFound){
			groundY = (grav2.getY()+box.getHeight());
			gravity = true;
			onGround = true;
			jump = false;
		}
		else if(box.under(grav4)){
			groundY = (grav4.getY()+box.getHeight());
			gravity = true;
			onGround = true;
			jump = false;
			bounce = true;
		}
		else if(box.under(grav5)){
			groundY = (grav5.getY()+box.getHeight());
			gravity = true;
			onGround = true;
			jump = false;
			bounce2 = true;
		}
	}
	if(box.getY()< groundY && fall && gravity){
			
			box.setY(box.getY()+delta*dy*.5);
		
	}
	else if(box.getY() > groundY && fall && !gravity){
		
		box.setY(box.getY()-delta*dy*.5);
	}

	if(box.getX()>2680 && box.getX() < 2850)
	{
		dead[3].setY(dead[3].getY()+12);
	}
	
	if(box.getX()>2930 && box.getX()<3120)
	{
		dead[6].setY(dead[6].getY()+12);
	}
	
	if(box.getX()>3760 && box.getX()<3950)
	{
		dead[7].setY(dead[7].getY()-12);
	}
	
	if(box.getY()<1360)
	{}
	else if(box.getX()>5300 && box.getX()<5750 && box.getY()>6700 && box.getY()<7200&&!bounce&&!blueFound2)
	{
		translate_y-=delta*dy*.4;
	}
	else if(box.getX()>5300 && box.getX()<7050 && box.getY()<7500&&box.getY()>6000&&!gravity&&bounce)
	{
			translate_y+=delta*dy*.55;
	}
	else if(box.getX()>5000 && box.getX()<8050 && box.getY()<7500&&!gravity&&bounce)
	{
		if(box.getY()<1350)
		{}
		else{
			translate_y+=delta*dy*.5;
		}
	}
	else if(box.getX()>5300 && box.getX()<5750 && box.getY()<7500&&!gravity&&!bounce)
	{
		if(box.getY()<6000)
		{
			translate_y+=delta*dy*.5;
		}
		else{
			translate_y+=delta*dy*.53;
		}
	}
	
	else if(box.getX()>5300 && box.getX()<7000 && box.getY()<7500&&!gravity&&!bounce&&!newWorld)
	{
		translate_y+=delta*dy*.5;
	}
	else if(box.getX()>5300 && box.getX()<7050 && box.getY()<7500&&gravity &&bounce&&!bounce2)
	{
		translate_y-=delta*dy*.55;
	}
	if(box.contains(key)||key.intersects(box)||box.contains(key))
	{
		blueFound = true;
	}
	
	if(box.intersects(key2)||key2.intersects(box)||box.contains(key2))
	{
		blueFound2 = true;
		
	}
	if((box.intersects(gold)||box.contains(gold))&&!goldFound)
	{
		goldFound = true;
		goldCount++;
	}
	if((box.intersects(gold2)||box.contains(gold2))&&!goldFound2)
	{
		goldFound2 = true;
		goldCount++;
	}
	if((box.intersects(gold3)||box.contains(gold3))&&!goldFound3)
	{
		goldFound3 = true;
		goldCount++;
	}
	if((box.intersects(gold4)||box.contains(gold4))&&!goldFound4)
	{
		goldFound4 = true;
		goldCount++;
	
	}
	if(gold5.contains(box))
	{
		box.setX(8100);
		box.setY(1300);
		groundY = (cliff[42].getY()-box.getHeight());
		translate_x-=2800;
		newWorld=true;
	}
	if(box.contains(gold6)&&!goldFound6)
	{
		goldFound6=true;
		goldCount++;
		
	}
	if(box.contains(gem)||gem.contains(box))
	{
		System.out.println("Win");
		gem.setX(0);
		gem.setY(0);
		dead[21].setY(0);
		dead[21].setX(0);
		state = State.WIN;
		
	}
	
	if(newWorld)
	{
		dead[21].setY(dead[21].getY()+delta*dy*.03);
	}
	bat.draw();
	bat3.draw();
	bat4.draw();
	bat5.draw();
	bat6.draw();
	box.draw();
	
	for(int i=1;i<23;i++){
		if(box.intersects(dead[i]))
		{
			lives--;
			initialize();
			if(lives==0){
				state = State.GAMEOVER;
			}
		}
		dead[i].draw();
	}
	
	grav.draw();
	grav4.draw();
	grav5.draw();
	gold5.draw();
	gem.draw();
	end1.draw();
	end2.draw();
	end3.draw();
	end4.draw();
	end5.draw();
	end6.draw();
	end7.draw();
	end8.draw();
	end9.draw();
	end10.draw();
	end11.draw();
	
	
	if(blueFound)
	{
		grav2.draw();
	}
	else{
		key.draw();
	}
	
	if(blueFound2)
	{
		grav3.draw();
		
	}
	else{
		key2.draw();
		
	}
	
	if(goldFound)
	{}
	else{
		gold.draw();
	}
	if(goldFound2)
	{}
	else{
		gold2.draw();
	}
	if(goldFound3)
	{}
	else{
		gold3.draw();
	}
	if(goldFound4)
	{}
	else{
		gold4.draw();
	}
	if(goldFound6)
	{
		gold7.draw();
	}
	else{
		gold6.draw();
	}
	
	if(onGold7&&goldCount>=1)
	{
		gold8.draw();
	}
	else if(onGold7)
	{
		gold7.draw();
	}
	if(onGold8&&goldCount>=1)
	{
		gold9.draw();
	}
	else if(onGold8)
	{
		gold8.draw();
	}
	if(onGold9&&goldCount>=1)
	{
		gold10.draw();
	}
	else if(onGold9)
	{
		gold9.draw();
	}
	if(onGold10&&goldCount>=1)
	{
		gold11.draw();
	}
	else if(onGold10)
	{
		gold10.draw();
	}
	
	if(onGold11){
		gold11.draw();
		System.out.println("OnGold11 is true");
	}
	else{
		System.out.println("OnGold11 is false");
	}
	
	glPopMatrix();
	
}

private void drawMain()
{
	glColor3f(.1f,0.3f,0.7f);
	glRectf(0,0,640,480);
	
	glColor3f(0f,0f,0f);
	glRectf(160,50,480,430);
	
glEnable(GL_BLEND);
    
	uniFont.drawString(260, 50, "MAIN MENU");
	uniFont2.drawString(200, 90, "Press the key next to the item to select");
	uniFont.drawString(250, 200, "L-Level Select");
	uniFont.drawString(250, 280, "A-About");
	uniFont.drawString(250, 360, "E-Exit");
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
glDisable(GL_BLEND);
	
}

private void drawGameOver(){
	glColor3f(.3f,0.1f,0.7f);
	glRectf(0,0,640,480);
	
	glColor3f(0f,0f,0f);
	glRectf(160,50,480,430);
	
glEnable(GL_BLEND);
    
	uniFont.drawString(260, 50, "GAME OVER");
	uniFont.drawString(250, 150, "Start Over?");
	uniFont.drawString(250, 210, "Y-Yes");
	uniFont.drawString(250, 270, "M-Main Menu");
	uniFont.drawString(250, 330, "E-Exit");
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
glDisable(GL_BLEND);
}

private void drawWin(){
	glColor3f(.8f,0.8f,0.3f);
	glRectf(0,0,640,480);
	
	glColor3f(0f,0f,0f);
	glRectf(160,50,480,430);
	
glEnable(GL_BLEND);
    
	uniFont.drawString(260, 50, "You WON!");
	uniFont.drawString(250, 150, "Try Level 2?");
	uniFont.drawString(250, 210, "Y-Yes");
	uniFont.drawString(250, 270, "M-Main Menu");
	uniFont.drawString(250, 330, "E-Exit");
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
glDisable(GL_BLEND);
}

private void drawLives(){
glEnable(GL_BLEND);
    
	uniFont2.drawString(0, 0, "Lives :"+ lives);
	uniFont2.drawString(100,0,"Gold : "+(goldCount));
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
glDisable(GL_BLEND);
}


private void drawLevel(){
	glColor3f(.7f,0.1f,0.3f);
	glRectf(0,0,640,480);
	
	glColor3f(0f,0f,0f);
	glRectf(160,50,480,430);
	
glEnable(GL_BLEND);
    
	uniFont.drawString(260, 50, "LEVEL SELECT");
	uniFont2.drawString(200, 90, "Press the key next to the item to select");
	uniFont.drawString(200, 150, "1-Level 1");
	uniFont.drawString(200, 210, "2-Level 2(in progress)");
	uniFont.drawString(200, 270, "M-Main Menu");
	uniFont.drawString(200, 330, "E-Exit");
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	//EDIT.. glDisable texture is required here.
	        
glDisable(GL_BLEND);
}
@SuppressWarnings("unchecked")
private void initFonts() {

    Font awtFont = new Font("", Font.PLAIN,55);
    Font awtFont2 = new Font("",Font.PLAIN,55);
    uniFont2 = new UnicodeFont(awtFont2,14,false,false);
    uniFont = new UnicodeFont(awtFont, 25, false, false);
    uniFont.addAsciiGlyphs();
    uniFont2.addAsciiGlyphs();
    uniFont.addGlyphs(400,600);           // Setting the unicode Range
    uniFont2.addGlyphs(400, 600);
    uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
    uniFont2.getEffects().add(new ColorEffect(java.awt.Color.white));
    try {
        uniFont.loadGlyphs();
        uniFont2.loadGlyphs();
    } catch (SlickException e) {};


}

public void initGL()
{
	try 
	{
		Display.setDisplayMode(new DisplayMode(640, 480));
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


private void checkInput(){

	switch(state){
	case INTRO:
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			state = State.MAIN_MENU;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Display.destroy();
			System.exit(0);
		}
		
		break;
	case MAIN_MENU:
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			state = State.LEVEL_SELECT;
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			state = State.ABOUT;
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			state = State.EXIT;
			
		}
		
		break;
	case LEVEL_SELECT:
		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			state = State.LEVEL1;
			lastFrame = getTime();
			oldFrame = getTime();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			state = State.LEVEL1;
			lastFrame = getTime();
			oldFrame = getTime();
			initialize();
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			state = State.MAIN_MENU;
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			state = State.EXIT;
			
		}
		break;
	case ABOUT:
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			state = State.MAIN_MENU;
		}
	case GAMEOVER:
		if(Keyboard.isKeyDown(Keyboard.KEY_Y))
		{
			state = State.LEVEL1;
			lastFrame = getTime();
			oldFrame = getTime();
			initialize();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			state = State.MAIN_MENU;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			state = State.EXIT;
			
		}
	case WIN:
		if(Keyboard.isKeyDown(Keyboard.KEY_Y))
		{
			state = State.LEVEL1;
			lastFrame = getTime();
			oldFrame = getTime();
			initialize();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			state = State.MAIN_MENU;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			state = State.EXIT;
			
		}
		
	}
	
}
private void initialize(){
	jump=false;fall = true;onGround=false;fallPoint = true;bounce =false;bounce2=false;go=false;
	notMoved=true;blueFound = false;blueFound2=false;goldFound=false;goldFound2=false;goldFound3=false;goldFound4=false;goldFound6=true;
	onGold7=false;onGold8=false;onGold9=false;onGold10=false;onGold11=false;newWorld=false;
	gravity = true;
	translate_x=0;
	translate_y=0;
	bat = new Bat(0, 475, 400, 480);
	
	bat3 = new Bat(940,270,400,200);
	bat4 = new Bat(2110,100,35,7700);
	bat5 = new Bat(2510,100,35,7600);
	bat6 = new Bat(5690,7700,20,100);
	
	end1 = new Bat(16000,1000,10,300);
	end2 = new Bat(16000,1000,100,10);
	end3 = new Bat(16000,1150,60,10);
	end4 = new Bat(16000,1300,100,10);
	
	end5 = new Bat(16170,1000,10,300);
	end6 = new Bat(16170,1000,100,10);
	end7 = new Bat(16270,1000,10,300);
	
	end8 = new Bat(16340,1000,10,300);
	end9 = new Bat(16340,1000,100,10);
	end10 = new Bat(16340,1300,100,10);
	end11 = new Bat(16442,1005,10,290);
	
	box = new Box(320,100,30,30);
	cliff[1] = new Bat(510,430,80,15);
	cliff[2]= new Bat(730,400,69,15);
	cliff[3]= new Bat(910,370,92,15);
	cliff[4]= new Bat(1410,260, 84,15);
	cliff[5]= new Bat(1560,310,110,15);
	cliff[6]= new Bat(1875,190,60,15);
	cliff[7]= new Bat(1820,350,100,15);
	cliff[8]= new Bat(2000,270,90,15);
	cliff[9]= new Bat(2020,160,40,15);
	cliff[10] = new Bat(2110,7800,464,15);
	cliff[11] = new Bat(2575,7801,100,14);
	cliff[12] = new Bat(2676,7800,300,15);
	cliff[13] = new Bat(2977,7801,100,14);
	cliff[14] = new Bat(3058,7800,600,15);
	cliff[15] = new Bat(3400,7400,500,25);
	cliff[16] = new Bat(2575,7802,100,13);
	cliff[17] = new Bat(2977,7802,100,13);
	cliff[18] = new Bat(6659,7800,400,15);
	cliff[19] = new Bat(3950,7430,100,15);
	cliff[20] = new Bat(4120,7490,100,15);
	cliff[21] = new Bat(4270,7540,100,15);
	cliff[22] = new Bat(4420,7600,100,15);
	cliff[23] = new Bat(4570,7630,100,15);
	cliff[24] = new Bat(4690,7700,1000,15);
	cliff[25] = new Bat(6000,7740,100,15);
	cliff[26] = new Bat(6190,7690,100,15);
	cliff[27] = new Bat(5980,7620,100,15);
	cliff[28] = new Bat(5800,7550,100,15);
	cliff[29] = new Bat(6000,7480,100,15);
	cliff[30] = new Bat(6200,7410,100,15);
	cliff[31] = new Bat(6000,7350,100,15);
	cliff[32] = new Bat(5800,7280,100,15);
	cliff[33] = new Bat(6000,7210,100,15);
	cliff[34] = new Bat(6200,7140,100,15);
	cliff[35] = new Bat(6000,7070,100,15);
	cliff[36] = new Bat(5800,7000,100,15);
	cliff[37] = new Bat(6000,6930,100,15);
	cliff[38] = new Bat(6200,6860,100,15);
	cliff[39] = new Bat(6000,6790,100,15);
	cliff[40] = new Bat(5800,6720,100,15);
	cliff[41] = new Bat(5300,1400,1000,15);
	cliff[42] = new Bat(8000,1400,500,15);
	dead[1] = new Dead(400,476,1700,20);
	dead[2] = new Dead(2545,7400,200,15);
	dead[3] = new Dead(2700,7400,100,15);
	dead[4] = new Dead(2800,7400,600,15);
	dead[5] = new Dead(3658,7800,2700,15);
	dead[6] = new Dead(2977,7400,100,15);
	dead[7] = new Dead(3800,7800,100,15);
	dead[8] = new Dead(3900,7400,1865,15);
	dead[9] = new Dead(6358,1650,30,6150);
	dead[10] = new Dead(5750,7800,608,15);
	dead[11] = new Dead(5750,6700,15,700);
	dead[12] = new Dead(5300,500,15,6900);
	dead[13] = new Dead(5750,2000,15,4000);
	dead[14] = new Dead(5750,4800,608,15);
	dead[15] = new Dead(-5,20, 10, 38000);
	dead[16] = new Dead(5400,4200,100,15);
	dead[17] = new Dead(5575,3400,100,15);
	dead[18] = new Dead(5450,2700,100,15);
	dead[19] = new Dead(5300,1500,1000,15);
	dead[20] = new Dead(7500,1430,3000,15);
	dead[21] = new Dead(7900,800,2000,15);
	dead[22] = new Dead(5300,1000,3000,15);
	
	grav = new Grav(3500,7765,100,15);
	grav2 = new Grav(4420,7420,100,15);
	key = new Grav(5590,7730 ,5,5);
	key2 = new Grav(5650,7200,5,5);
	grav3 = new Grav(5500,7350,200,15);
	grav4 = new Grav(5950,4850,200,15);
	grav5 = new Grav(6200,1100,100,15);
	gold = new Gold(5850,5230,10,10);
	gold2 = new Gold(5360,4200,10,10);
	gold3 = new Gold(5360,3400,10,10);
	gold4 = new Gold(5610,2650,10,10);
	gold5 = new Gold(5330,1280,70,130);
	gold6 = new Gold(8420,1360,10,10);
	gold7 = new Gold(8500,1360,100,15);
	gold8 = new Gold(8600,1320,100,15);
	gold9 = new Gold(8700,1280,100,15);
	gold10 = new Gold(8800,1240,100,15);
	gold11 = new Gold(8900,1200,1000,15);
	gem = new Gem(9800,1120,60,100);
}



private void render(){
	switch(state){
	case INTRO:
		drawIntro();
		break;
	case MAIN_MENU:
		drawMain();
		break;
	case LEVEL_SELECT:
		drawLevel();
		break;
	case LEVEL1:
		draw();
		drawLives();
		//drawGold();
		break;
	case ABOUT:
		drawAbout();
		break;
	case EXIT:
		System.out.println("dead");
		Display.destroy();
		System.exit(0);
		break;
	case GAMEOVER:
		drawGameOver();
		break;
	case WIN:
		drawWin();
		break;
			
	}
	
}

private Texture loadTexture(String key){
	try {
		return TextureLoader.getTexture("png",new FileInputStream(new File("res/" + key + ".png")));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}





class Box extends AbstractMoveableEntity{
	
	public Box(double x, double y , double width, double height){
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

	private boolean on(Entity other)
	{
		return(((this.getX() <= (other.getX() + other.getWidth()) || (this.getX() + this.getWidth())<=(other.getX() + other.getWidth())))&& ((this.getX() >=other.getX())||((this.getX()+this.getHeight()) >=other.getX()) ) && ((this.getY()+this.getHeight()) >=other.getY()) && ((this.getY()+this.getHeight()) <= (other.getY() + other.getHeight())));
	}
	private boolean leftOf(Entity other)
	{
		return((this.getX()+this.getWidth()+10)>=other.getX() &&this.getX()<=other.getX()&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	private boolean rightOf(Entity other)
	{
		return(this.getX()>=other.getX() &&this.getX()<=(other.getX()+other.getWidth()+10)&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	
	private boolean under(Entity other)
	{
		return((this.getX()+this.getWidth()) >= other.getX() && this.getX() <= (other.getX() + other.getWidth()) && (this.getY()-this.getHeight()-10) <= other.getY() && (this.getY()) >= other.getY());
	}
	private boolean contains(Entity other)
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
class Dead extends AbstractMoveableEntity{
	
	public Dead(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(1.0,0,0);
			glRectd(x,y, x+width, y+height);
		
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
class Gem extends AbstractMoveableEntity{
	
	public Gem(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(.8,0,.6);
			glRectd(x,y, x+width, y+height);
		
	}
	private boolean contains(Entity other)
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

}
