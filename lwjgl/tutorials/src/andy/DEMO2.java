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
	
	//variables for level 1
	private Texture mainc;
	
	int x = 10,goldCount=0;
	int y =440;
	int dx =1;
	int dy =1;
	private Box box;
	private Bat[] cliff;
	private Ice[] ice;
	private Dead[] dead;
	private Grav[] grav,bit,appear;
	private Gold[] gold,light;
	private Gold ledge;
	private Ice wall;
	private Bat[] lights;
	private Gem gem;
	int cliffnum=7,lastDIR,icenum=9,deadnum=48,gravnum=17;
	int count;
	int delta = getDelta();
	boolean jump=false,fall=true,gravity=true,onGround=false,DIRlock = false,jumpvar,blueFound=false,onCliff=false;
	boolean blueFound2 = false,goldFound=false,up=false,goldFound2=false,goldFound3=false,goldFound4=false,blueFound3=false;
	boolean goldFound5 = false,win=false;
	
	//variables for main menu
	
	private Bat[] bat;
	int batnum;
	boolean moveLeft = true;
	
	UnicodeFont uniFont;
	private static enum State {
		INTRO, MAIN_MENU,LEVEL1,LEVEL_SELECT,ABOUT,CONTROLS,EXIT,GAMEOVER,WIN,LEVEL2;
		
	}
	
	State state = State.MAIN_MENU;
	public static void main(String [] args){
		new DEMO2();
	}
	public DEMO2(){
		
		initMain();
		initGL();
		initFonts();
		mainc = loadTexture("bag");
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,640,480,0,1,-1);
		glMatrixMode(GL_MODELVIEW);

		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
		
			checkInput();
			//render();
			renderer();
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		
	}
	private void checkInput(){
		switch(state){
		case INTRO:
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{	
				state = State.MAIN_MENU;
				initMain();
			}
			break;
		case LEVEL_SELECT:
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
				
				if(box.leftOf(bat[5])||box.leftOf(bat[9])||box.leftOf(bat[13])||box.leftOf(bat[15]))
				{}
				else{
					box.setX(box.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				}
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				if(box.rightOf(bat[1])||box.rightOf(bat[3])||box.rightOf(bat[7])||box.rightOf(bat[11]))
				{}
				else{
					box.setX(box.getX() -3);
					translate_x += 3;
					lastDIR = 1;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)&&onGround){
				
				jump = true;
				fall = false;
				onGround=false;
				jumpvar = true;
				//DIRlock = false;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				translate_y+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				translate_x+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				translate_y-=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				translate_x-=4;
			}
			break;
		case MAIN_MENU:
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
				
				if(box.leftOf(bat[5])||box.leftOf(bat[9])||box.leftOf(bat[13])||box.leftOf(bat[15]))
				{}
				else{
					box.setX(box.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				}
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				if(box.rightOf(bat[1])||box.rightOf(bat[3])||box.rightOf(bat[7])||box.rightOf(bat[11]))
				{}
				else{
					box.setX(box.getX() -3);
					translate_x += 3;
					lastDIR = 1;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)&&onGround){
				
				jump = true;
				fall = false;
				onGround=false;
				jumpvar = true;
				//DIRlock = false;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				translate_y+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				translate_x+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				translate_y-=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				translate_x-=4;
			}
			break;
		case LEVEL1:
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
					box.setX(box.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				
				box.setX(box.getX() -3);
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
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				translate_y+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				translate_x+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				translate_y-=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				translate_x-=4;
			}

			break;
		case LEVEL2:
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				state = State.MAIN_MENU;
				initMain();
			}
			break;
		case ABOUT:
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				state = State.MAIN_MENU;
				initMain();
			}
			break;
		case EXIT:
			break;
		case GAMEOVER:
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{	
				state = State.LEVEL1;
				initVars();
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				state = State.MAIN_MENU;
				initMain();
			}
			break;
		case WIN:
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				state = State.MAIN_MENU;
				initMain();
			}
			break;
		}
		
	}
	
	private void renderer(){
		switch(state){
		case INTRO:
			drawIntro();
			break;
		case MAIN_MENU:
			drawMain();
			renderMain();
			break;
		case LEVEL_SELECT:
			drawLevel();
			renderMain();
			break;
		case LEVEL1:
			
			render();
			break;
		case LEVEL2:
			drawLevel2();
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
	
	
	private void initMain(){
		translate_x=0;
		translate_y=0;
		jump=false;fall=true;gravity=true;onGround=false;
		bat = new Bat[20];
		bat[0] = new Bat(0,0,2000,5);
		bat[1] = new Bat(0,0,5,200);
		bat[2] = new Bat(0,200,100,5);
		bat[3] = new Bat(100,200,5,100);
		bat[4] = new Bat(100,300,55,5);
		bat[5] = new Bat(150,200,5,100);
		bat[6] = new Bat(150,200,150,5);
		bat[7] = new Bat(300,200,5,100);
		bat[8] = new Bat(300,300,55,5);
		bat[9] = new Bat(350,200,5,100);
		bat[10] = new Bat(350,200,150,5);
		bat[11] = new Bat(500,200,5,100);
		bat[12] = new Bat(500,300,55,5);
		bat[13] = new Bat(550,200,5,100);
		bat[14] = new Bat(550,200,150,5);
		bat[15] = new Bat(635,0,5,200);
		batnum=16;
		box = new Box(20,20,26,26);
		
	}
	
	private void renderMain(){
		translate_x=0;
		translate_y=0;
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		//int delta = getDelta();
		translate_x=0;
		translate_y=0;
		
		System.out.println("x:" +box.getX());
		System.out.println("y:"+box.getY());
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					box.setY(box.getY()-4);
					translate_y+=4;
					System.out.println("thtsdfsht");
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
					box.setY(box.getY()+4);
					translate_y-=4;
					System.out.println("thththt");
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
				box.setY(box.getY()+4);
				
				if(!onGround){
					translate_y-=4;
				
				}
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				box.setY(box.getY()-4);
				if(!onGround)
					translate_y+=4;
				onGround = false;
				//DIRlock = false;
				
			}
			
		}
		
		for(int i=0; i<batnum;i++){
			if(box.on(bat[i])&&!jump&&i!=1&&i!=3&&i!=7&&i!=11&&i!=5&&i!=9&&i!=13&&i!=15){
				box.setY((bat[i]).getY()-box.getHeight());
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				if(i==4){
					if(state == State.MAIN_MENU){
						initMain();
						//box.setX(320);
						//box.setY(240);
						state = State.LEVEL_SELECT;
						//translate_y+=200;
						
						
					}
					else if(state == State.LEVEL_SELECT){
						initVars();
						state = State.LEVEL1;
						//translate_x=+370;
						//translate_y+=300;
					}
				}
				else if(i==8){
					if(state == State.LEVEL_SELECT){
						state = State.LEVEL2;
						//translate_y+=200;
						//translate_x=+770;
					}
					else
						state = State.ABOUT;
					
					
				}
				else if(i==12){
					if(state == State.LEVEL_SELECT){
						initMain();
						//box.setX(320);
						//box.setY(240);
						state = State.MAIN_MENU;
						//translate_y+=200;
					}
					else
						state = State.EXIT;
				}
				
				
				
			}
			else{
				fall = true;
				onCliff= false;
				//onGround = false;
				
				
			}
			bat[i].draw();
		}
		
		
		box.draw();
		
	}
	
	private void render(){
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		
		//System.out.println("x:" + box.getX());
		System.out.println("translate_x:" + translate_x);
		System.out.println("translate_y:" + translate_y);
		/*if(fall){
			System.out.println("fall:true");
		}
		if(onGround){
			System.out.println("onGround:true");
		}
		if(jump){
			System.out.println("jump:true");
		}
		if(jumpvar){
			System.out.println("jumpvar:true");
		}
		*/
		
		
		
		
		
		
		if(win &&dead[47].getY()>100&&box.on(ledge)){
			dead[47].upp = true;
		}
		
		if(dead[47].upp){
			dead[47].setY(dead[47].getY()-1);
		}
		if(dead[47].getY()<=100){
			dead[47].upp = false;
		}
		
		if(gem.contains(box)||box.intersects(gem)){
			state = State.WIN;
		}
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					box.setY(box.getY()-4);
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
					box.setY(box.getY()+4);
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
				box.setY(box.getY()+4);
				System.out.println("falldown1");
				if(!onGround){
					translate_y-=4;
				System.out.println("falldown2***");
				}
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				box.setY(box.getY()-4);
				if(!onGround){
					translate_y+=4;
					System.out.println("wtffff");
				}
				onGround = false;
				//DIRlock = false;
				
			}
			
		}
		
		
		
		
		
		for(int i=0; i<cliffnum;i++){
			if(box.on(cliff[i])&&!jump){
				box.setY((cliff[i]).getY()-box.getHeight());
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				
			}
			else if(box.under(cliff[i])&&!jump&&!gravity){
				box.setY((cliff[i]).getY()+cliff[i].getHeight());
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
			if(box.on(ice[i])&&!jump){
				box.setY((ice[i]).getY()-box.getHeight());
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
			}
			else if(box.under(ice[i])&&!jump&&!gravity){
				box.setY((ice[i]).getY()+ice[i].getHeight());
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
				
			}
			else{
				fall = true;
				//onGround = false;
				
				
			}
			
			ice[i].draw();
			
			
		}
		for(int i=0;i<deadnum;i++){
			if(box.intersects(dead[i])){
				//translate_x=0;
				//translate_y=0;
				if(jump){
					fall = true;
					jump = false;
					jumpvar =false;
				}
				state = State.GAMEOVER;
				initVars();
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
			if(box.under(grav[i])&&!gravity){
				gravity = !gravity;
				DIRlock = false;
				
				fall = true;
			}
			
			if(box.on(grav[i])&&gravity){
				gravity = !gravity;
				DIRlock = false;
				
				fall=true;
			}
			grav[i].draw();
		}
		
		if(box.intersects(bit[0])||box.contains(bit[0])){
			blueFound = true;
		}
		if(box.intersects(bit[1])||box.contains(bit[1])){
			blueFound2 = true;
			
		}
		if(box.intersects(bit[2])||box.contains(bit[2])){
			blueFound3 = true;
			
		}
		if(box.intersects(gold[0])||box.contains(gold[0])&&!goldFound){
			goldFound=true;
			goldCount++;
		}
		
		if(box.intersects(gold[1])||box.contains(gold[1])&&!goldFound2){
			goldFound2=true;
			goldCount++;
		}
		if(box.intersects(gold[2])||box.contains(gold[2])&&!goldFound3){
			goldFound3=true;
			goldCount++;
		}
		if(box.intersects(gold[3])||box.contains(gold[3])&&!goldFound4){
			goldFound4=true;
			goldCount++;
		}
		if(box.intersects(gold[4])||box.contains(gold[4])&&!goldFound5){
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
		
		
		if(box.on(appear[0])&&blueFound){
			box.setY(appear[0].getY()-box.getHeight());
			gravity = !gravity;
			System.out.println("hey");
			
			fall = true;
		}
		if(box.on(appear[1])&&blueFound2){
			box.setY(appear[1].getY()-box.getHeight());
			gravity = !gravity;
			System.out.println("hey");
			
			fall = true;
		}
		if(box.on(appear[2])&&blueFound3){
			box.setY(appear[2].getY()-box.getHeight());
			gravity = !gravity;
			System.out.println("hey");
			
			fall = true;
		}
		
		if(DIRlock){
			if(lastDIR ==1){
				box.setX(box.getX() -3);
				translate_x +=3;
			}
			else if(lastDIR==2){
				box.setX(box.getX() +3);
				translate_x -=3;
			}
			
			if(box.intersects(wall)){
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
		
		if(box.on(ledge)&&!jump){
			box.setY(ledge.getY()-box.getHeight());
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			if(goldFound){
				if(!lights[0].on){
					lights[0].on=true;
					light[0].draw();
					goldCount--;
				}
			}
			if(goldFound2){
				if(!lights[1].on){
					lights[1].on=true;
					light[1].draw();
					goldCount--;
				}
			}
			if(goldFound3){
				if(!lights[2].on){
					lights[2].on=true;
					light[2].draw();
					goldCount--;
				}
			}
			if(goldFound4){
				if(!lights[3].on){
					lights[3].on=true;
					light[3].draw();
					goldCount--;
				}
			}
			if(goldFound5){
				if(!lights[4].on){
					lights[4].on=true;
					light[4].draw();
					goldCount--;
					
				}
			}
			
			if(goldFound&&goldFound2&&goldFound3&&goldFound4&&goldFound5){
				win=true;
			}
			
		}
		wall.draw();
		
		ledge.draw();
		gem.draw();
		
		box.draw();
		glPopMatrix();
		
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
	
	@SuppressWarnings("unchecked")
	private void initFonts() {

	    Font awtFont = new Font("", Font.PLAIN,55);
	    //Font awtFont2 = new Font("",Font.PLAIN,55);
	    //uniFont2 = new UnicodeFont(awtFont2,14,false,false);
	    uniFont = new UnicodeFont(awtFont, 25, false, false);
	    uniFont.addAsciiGlyphs();
	    //uniFont2.addAsciiGlyphs();
	    uniFont.addGlyphs(400,600);           // Setting the unicode Range
	    //uniFont2.addGlyphs(400, 600);
	    uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
	    //uniFont2.getEffects().add(new ColorEffect(java.awt.Color.white));
	    try {
	        uniFont.loadGlyphs();
	        //uniFont2.loadGlyphs();
	    } catch (SlickException e) {};


	}
	
	private void drawIntro(){
		
		glEnable(GL_BLEND);
	    
		uniFont.drawString(260, 60, "Welcome");
		uniFont.drawString(300, 90, "to");
		uniFont.drawString(280, 120, "GAME");
		uniFont.drawString(180, 250, "Arrow Keys to Move");
		uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
		
	}
	
	private void drawLevel2(){
		glEnable(GL_BLEND);
	    
		uniFont.drawString(60, 100, "Sorry Level 2 is currently in progress");
		uniFont.drawString(50, 300, "Press Escape Key to go back to the Main Menu");
		//uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
	}
	
	private void drawAbout(){
		glEnable(GL_BLEND);
	    
		uniFont.drawString(260, 50, "Controls");
		uniFont.drawString(10, 120, "Left Arrow: Left");
		uniFont.drawString(220, 120, "Right Arrow: Right");
		uniFont.drawString(480, 120, "Up Arrow: Up");
		uniFont.drawString(280, 200, "Goal");
		uniFont.drawString(200, 270, "Reach the Purple Door");
		uniFont.drawString(190, 380, "Thank you for playing!");
		uniFont.drawString(50, 430, "Press Escape Key to go back to the Main Menu");
		//uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
		
	}
	private void drawWin(){
		glEnable(GL_BLEND);
	    
		uniFont.drawString(210, 200, "You have beaten the level!");
	
		uniFont.drawString(50, 430, "Press Escape Key to go back to the Main Menu");
		//uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
	}
	private void drawGameOver(){
		glEnable(GL_BLEND);
	    
		uniFont.drawString((int)box.getX()-50,(int) box.getY()-100, "You died!");
		uniFont.drawString(180, 300, "Press Space Bar to try again");
		uniFont.drawString(50, 430, "Press Escape Key to go back to the Main Menu");
		//uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
	}
	
	private void drawMain(){
		glEnable(GL_BLEND);
	    
		uniFont.drawString(60, 60, "Level Select");
		uniFont.drawString(300, 60, "About");
		uniFont.drawString(500, 60, "Exit");
		//uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);	
	}
	
	private void drawLevel(){
		glEnable(GL_BLEND);
	    
		uniFont.drawString(80, 60, "Level 1");
		uniFont.drawString(270, 60, "Level 2");
		uniFont.drawString(450, 60, "Back to Main");
		//uniFont.drawString(180, 380, "PRESS SPACE TO START");
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
	}
	private void initVars(){
		translate_x=0;
		translate_y=0;
		
		jump=false;fall=true;gravity=true;onGround=false;DIRlock = false;blueFound=false;onCliff=false;
		blueFound2 = false;goldFound=false;up=false;goldFound2=false;goldFound3=false;goldFound4=false;blueFound3=false;
		goldFound5 = false;win=false;
		cliff = new Bat[50];
		ice = new Ice[20];
		dead = new Dead[100];
		grav = new Grav[30];
		bit = new Grav[10];
		appear = new Grav[10];
		gold = new Gold[5];
		light = new Gold[5];
		lights = new Bat[5];
		
		gem = new Gem(20,270,80,80);
		ledge = new Gold(225,300,30,5);
		
		
		lights[0] = new Bat(20,230,10,20);
		lights[1] = new Bat(60,230,10,20);
		lights[2] = new Bat(100,230,10,20);
		lights[3] = new Bat(140,230,10,20);
		lights[4] = new Bat(180,230,10,20);
		
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
		dead[4] = new Dead(1200,205,5,30);
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
		
		
		
		box = new Box(320,250,24,24);
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
	public void boxDraw(double x, double y , double width, double height){
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		mainc.bind();
		//GL11.glDisable(GL11.GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(x,y);
			glTexCoord2f(1,0);
			glVertex2d(x+width,y);
			glTexCoord2f(1,1);
			glVertex2d(x+width,y+height);
			glTexCoord2f(0,1);
			glVertex2d(x,y+height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	Texture loadTexture(String key){
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
		boolean on=false;
		//Texture main;
		
		public Box(double x, double y , double width, double height){
			super(x,y,width,height);
			
		}
		@Override
		public void draw(){
			glColor3d(1.0,1,1);
			boxDraw(this.x,this.y,this.width,this.height);
			
			//glColor3d(.7,.7,.7);
				//glRectd(x,y, x+width, y+height);
			
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
		boolean rightOf(Entity other)
		{
			return(this.getX()>=other.getX() &&this.getX()<=(other.getX()+other.getWidth()+10)&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
		}
		
		boolean under(Entity other)
		{
			return((this.getX()+this.getWidth()) >= other.getX() && this.getX() <= (other.getX() + other.getWidth()) && (this.getY()-this.getHeight()) <= other.getY()-other.getHeight() && (this.getY()) >= other.getY());
		}
		boolean contains(Entity other)
		{
			return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
		}
		
		Texture loadTexture(String key){
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
		
	}
}


//classes

class Bat extends AbstractMoveableEntity{
	boolean on=false;
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

