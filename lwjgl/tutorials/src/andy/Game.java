package andy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.Font;




import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.Display; 

import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;




import entities.AbstractMoveableEntity;
import entities.Entity;



public class game {
	public static final int width = 640;
	public static final int height = 480;
	float translate_x = 0;
	float translate_y =0;
	private long lastFrame,oldFrame;
	
	//variables for level 1
	private Texture left,right,gright,gleft,icel,cliffi,cliffv,icev,deadi,deadi1,deadi2,deadi3,deadi4,deadv,deadv1,deadv2,deadv3,deadv4,coini,door,gravflip,Lon,Loff,ski;
	private Texture cloud1,cloud2,cloud3,cloud4,cloud5,cloud6,cloud7,cloud8,cloud9,cloud10,lboxi,doorjam,woodi,ledgei,ropei,hangi,wheeli,wheeli2;
	private Texture left1,right1,gright1,gleft1,left2,right2,gleft2,gright2,left3,right3,gleft3,gright3,left4,right4,gleft4,gright4,left5,right5,gleft5,gright5;
	int x = 10,goldCount=0;
	int y =440;
	int dx =1;
	int dy =1;
	int endHeight =220;
	private Back back;
	private Sky skyi;
	private Box box;
	private Bat[] cliff;
	private Ice[] ice;
	private Dead[] dead;
	private Grav[] grav,bit,appear;
	private Lon[] light;
	private Coin[] gold;
	private Ice wall;
	private Loff[] lights;
	private Gem gem;
	private Cloud []clouds;
	private Lbox lightbox;
	private Doorjam doorj;
	private Wood wood;
	private Rope rope,rope1;
	private Hang hang,hang1;
	private Wheel wheel;
	private Ledge ledge;
	int cliffnum=8,lastDIR,icenum=10,deadnum=46,gravnum=17;
	int count;
	int delta = getDelta();
	int cliffWidth =12,iceWidth =12,deadWidth =18,gravWidth=12,lightWidth =16;
	boolean jump=false,fall=true,gravity=true,onGround=false,DIRlock = false,jumpvar,blueFound=false,onCliff=false;
	boolean blueFound2 = false,goldFound=false,up=false,goldFound2=false,goldFound3=false,goldFound4=false,blueFound3=false;
	boolean goldFound5 = false,win=false,ledgeFall=false;
	
	//variables for main menu
	
	private Back[] bat;
	int batnum;
	boolean moveLeft = true;
	
	UnicodeFont uniFont;
	private static enum State {
		INTRO, MAIN_MENU,LEVEL1,LEVEL_SELECT,ABOUT,CONTROLS,EXIT,GAMEOVER,WIN,LEVEL2;
		
	}
	
	State state = State.MAIN_MENU;
	public static void main(String [] args){
		new game();
	}
	public game(){
		
		initMain();
		initGL();
		initFonts();
		left = loadTexture("bag");
		right = loadTexture("bagi1");
		gleft = loadTexture("bagv1");
		gright = loadTexture("bagv2");
		left1 = loadTexture("bagsi1");
		right1 =loadTexture("bagsi1_2");
		gleft1 =loadTexture("bagsv1");
		gright1 =loadTexture("bagsv1_2");
		
		
		left2 = loadTexture("bagsi2");
		right2 =loadTexture("bagsi2_2");
		gleft2 =loadTexture("bagsv2");
		gright2 =loadTexture("bagsv2_2");
		
		
		left3 = loadTexture("bagsi3");
		right3 =loadTexture("bagsi3_2");
		gleft3 =loadTexture("bagsv3");
		gright3 =loadTexture("bagsv3_2");
		
		
		left4 = loadTexture("bagsi4");
		right4 =loadTexture("bagsi4_2");
		gleft4 =loadTexture("bagsv4");
		gright4 =loadTexture("bagsv4_2");
		
		
		left5 = loadTexture("bagsi5");
		right5 =loadTexture("bagsi5_2");
		gleft5 =loadTexture("bagsv5");
		gright5 =loadTexture("bagsv5_2");
		
		
		
		icel = loadTexture("air");
		cliffi = loadTexture("cliff");
		cliffv = loadTexture("cliff2");
		icev = loadTexture("air1");
		deadi = loadTexture("dead");
		coini = loadTexture("coin");
		gravflip = loadTexture("gravflip");
		Lon = loadTexture("lighton");
		Loff = loadTexture("lightoff");
		ski = loadTexture("sky");
		cloud1 =loadTexture("cloud1");
		cloud2 =loadTexture("cloud2");
		cloud3 =loadTexture("cloud3");
		cloud4 =loadTexture("cloud4");
		cloud5 =loadTexture("cloud5");
		cloud6 =loadTexture("cloud6");
		cloud7 =loadTexture("cloud7");
		cloud8 =loadTexture("cloud8");
		cloud9 =loadTexture("cloud9");
		cloud10 =loadTexture("cloud10");
		lboxi = loadTexture("lbox");
		doorjam = loadTexture("doorjam");
		woodi = loadTexture("wood");
		
		
		deadi1 = loadTexture("deadi1");
		deadi2 = loadTexture("deadi2");
		deadi3 = loadTexture("deadi3");
		deadi4 = loadTexture("deadi4");
		deadv = loadTexture("deadv");
		deadv1 = loadTexture("deadv1");
		deadv2 = loadTexture("deadv2");
		deadv3 = loadTexture("deadv3");
		deadv4 = loadTexture("deadv4");
		door = loadTexture("door");
		ropei = loadTexture("rope");
		hangi = loadTexture("hang");
		ledgei = loadTexture("ledge");
		wheeli = loadTexture("wheel");
		wheeli2 = loadTexture("wheel1");
		
		
		skyi = new Sky(-600,-200,5000,2000);
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
		lastDIR =2;
		translate_x=0;
		translate_y=0;
		goldCount =0;
		jump=false;fall=true;gravity=true;onGround=false;
		bat = new Back[20];
		bat[0] = new Back(0,0,2000,5);
		bat[1] = new Back(0,0,5,200);
		bat[1].up = true;
		bat[2] = new Back(0,200,100,5);
		bat[3] = new Back(100,200,5,100);
		bat[3].up = true;
		bat[4] = new Back(100,300,55,5);
		bat[5] = new Back(150,200,5,100);
		bat[5].up = true;
		bat[6] = new Back(150,200,150,5);
		bat[7] = new Back(300,200,5,100);
		bat[7].up = true;
		bat[8] = new Back(300,300,55,5);
		bat[9] = new Back(350,200,5,100);
		bat[9].up = true;
		bat[10] = new Back(350,200,150,5);
		bat[11] = new Back(500,200,5,100);
		bat[11].up = true;
		bat[12] = new Back(500,300,55,5);
		bat[13] = new Back(550,200,5,100);
		bat[13].up = true;
		bat[14] = new Back(550,200,150,5);
		bat[15] = new Back(635,0,5,200);
		bat[15].up = true;
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
		
		
		//System.out.println("y:"+box.getY());
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
		skyi.draw();
		//System.out.println(translate_y);
		
		//System.out.println("gold:" +goldCount);
		for(int i=0;i<20;i++){
			clouds[i].draw();
			
			clouds[i].setX(clouds[i].getX()+dx);
			if(clouds[i].getX()>=3300){
				clouds[i].setX(-500);
			}
		}
		
		if(goldCount==1){
			endHeight = 208;
		}
		else if(goldCount ==2){
			endHeight = 196;
		}
		else if(goldCount ==3){
			endHeight = 184;
		}
		else if(goldCount ==4){
			endHeight =172;
		}
		else if(goldCount ==5){
			endHeight =160;
		}
		
		hang1.draw();
		wheel.draw();
		rope.draw();
		rope1.draw();
		ledge.draw();
		wall.draw();
		//System.out.println("x:" + box.getX());
		
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
		
		if(ledgeFall)
			System.out.println("ledgefall is true");
		else
			System.out.println("no ledgefall");
		*/
		
		
		if(win &&doorj.getY()>endHeight&&ledgeFall){
			doorj.upp = true;
		}
		
		if(doorj.upp){
			doorj.setY(doorj.getY()-.2);
			ledge.setY(ledge.getY()+.2);
			rope.setHeight(rope.getHeight()+.2);
			translate_y -=.22;
		
		}
		if(doorj.getY()<=endHeight){
			doorj.upp = false;
			doorj.pause = true;
		}
		if(!ledgeFall){
			doorj.pause =false;
		}
		if(!doorj.upp&&doorj.getY()<230&&!doorj.pause){
			doorj.setY(doorj.getY()+.7);
			ledge.setY(ledge.getY()-.7);
			rope.setHeight(rope.getHeight()-.7);
			translate_y +=.1;
			
			
		}
		
		
		if(gem.contains(box)||box.intersects(gem)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
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
			doorj.upp = false;
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
				if(!onGround){
					translate_y+=4;
					
				}
				onGround = false;
				//DIRlock = false;
				
			}
				if(ledgeFall)
				{
					ledgeFall = false;
					
				}
				//doorj.upp = false;
		}
		
		
		
		
		
		for(int i=0; i<cliffnum;i++){
			if(box.on(cliff[i])&&!jump&&gravity){
				if(i ==0){
					if(box.on(cliff[i]))
						translate_y= -76;
				}
				else if(i==6)
				{}
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
			if(box.on(ice[i])&&!jump&&gravity){
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
				System.out.println(i);
				state = State.GAMEOVER;
				initVars();
			}
			dead[i].draw();
		}
		if(box.intersects(doorj)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			
			state = State.GAMEOVER;
			initVars();
		}
		
		for(int i=9;i<15;i++){
			if(dead[i].getY()+dead[i].getHeight()>=900){
				dead[i].up=true;
			}
			
			if(dead[i].getY()<=718){
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
		//wood.draw();
		for(int i=25; i<34;i++)
		{
			if(dead[i].getX()>=378){
				dead[i].right=false;
			}
			
			if(dead[i].getX()<=18){
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
			if(box.under(grav[i])&&!gravity&&!jump){
				gravity = !gravity;
				DIRlock = false;
				
				fall = true;
			}
			
			if(box.on(grav[i])&&gravity&&!jump){
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
			
			if(!goldFound)
				goldCount++;
			goldFound=true;
		}
		
		if(box.intersects(gold[1])||box.contains(gold[1])&&!goldFound2){
			
			if(!goldFound2)
				goldCount++;
			goldFound2=true;
		}
		if(box.intersects(gold[2])||box.contains(gold[2])&&!goldFound3){
			
			if(!goldFound3)
				goldCount++;
			goldFound3=true;
		}
		if(box.intersects(gold[3])||box.contains(gold[3])&&!goldFound4){
			
			if(!goldFound4)
				goldCount++;
			goldFound4=true;
		}
		if(box.intersects(gold[4])||box.contains(gold[4])&&!goldFound5){
			if(!goldFound5)
				goldCount++;
			goldFound5=true;
			
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
			
			
			fall = true;
		}
		if(box.on(appear[1])&&blueFound2){
			box.setY(appear[1].getY()-box.getHeight());
			gravity = !gravity;
		
			
			fall = true;
		}
		if(box.on(appear[2])&&blueFound3){
			box.setY(appear[2].getY()-box.getHeight());
			gravity = !gravity;
			
			
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
		/*
		for(int i=0;i<5;i++){
			lights[i].draw();
			if(lights[i].on){
				//light[i].draw();
				
			}
		}
		
		*/
		if(box.on(ledge)&&!jump){
			box.setY(ledge.getY()-box.getHeight());
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			win = true;
			ledgeFall = true;
		}
		
		
		/*
		if(box.on(ledge)&&!jump){
			box.setY(ledge.getY()-box.getHeight());
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			if(goldFound){
				if(!lights[0].on){
					lights[0].on=true;
					//light[0].draw();
					goldCount--;
				}
			}
			if(goldFound2){
				if(!lights[1].on){
					lights[1].on=true;
					//light[1].draw();
					goldCount--;
				}
			}
			if(goldFound3){
				if(!lights[2].on){
					lights[2].on=true;
					//light[2].draw();
					goldCount--;
				}
			}
			if(goldFound4){
				if(!lights[3].on){
					lights[3].on=true;
					//light[3].draw();
					goldCount--;
				}
			}
			if(goldFound5){
				if(!lights[4].on){
					lights[4].on=true;
					//light[4].draw();
					goldCount--;
					
				}
			}
			
			if(goldFound&&goldFound2&&goldFound3&&goldFound4&&goldFound5){
				win=true;
			}
			
		}
		*/
		doorj.draw();
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
		uniFont.drawString(240, 350, "Welcome to");
		uniFont.drawString(120, 400, "The Adventures of Bo$$ Greed");
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
		endHeight =220;
		lastDIR =1;
		goldCount =0;
		jump=false;fall=true;gravity=true;onGround=false;DIRlock = false;blueFound=false;onCliff=false;
		blueFound2 = false;goldFound=false;up=false;goldFound2=false;goldFound3=false;goldFound4=false;blueFound3=false;
		goldFound5 = false;win=false;
		cliff = new Bat[50];
		ice = new Ice[20];
		dead = new Dead[100];
		grav = new Grav[30];
		bit = new Grav[10];
		appear = new Grav[10];
		gold = new Coin[5];
		light = new Lon[5];
		lights = new Loff[5];
		clouds = new Cloud[20];
		
		gem = new Gem(20,284,64,64);
		ledge = new Ledge(353,260,48,12);
	
		hang1 = new Hang(211,75,170,85);
		rope = new Rope(375,160,2,105);
		rope1 = new Rope(213,160,2,75);
		doorj = new Doorjam(200,230,32,128);
		wheel = new Wheel(277,118,32,32);
		
		clouds[0] = new Cloud(300,222,128,128);
		clouds[1] = new Cloud(0,500,128,128);
		clouds[2] = new Cloud(0,1000,128,128);
		clouds[3] = new Cloud(0,1500,128,128);
		clouds[4] = new Cloud(0,2000,128,128);
		clouds[5] = new Cloud(0,2500,128,128);
		clouds[6] = new Cloud(500,250,128,128);
		clouds[7] = new Cloud(500,750,128,128);
		clouds[8] = new Cloud(500,1250,128,128);
		clouds[9] = new Cloud(500,1750,128,128);
		clouds[10] = new Cloud(500,2250,128,128);
		clouds[11] = new Cloud(500,2750,128,128);
		clouds[12] = new Cloud(1000,0,128,128);
		clouds[13] = new Cloud(1000,500,128,128);
		clouds[14] = new Cloud(1000,1000,128,128);
		clouds[15] = new Cloud(1000,1500,128,128);
		clouds[16] = new Cloud(1000,2000,128,128);
		clouds[17] = new Cloud(1000,2500,128,128);
		clouds[18] = new Cloud(1000,3000,128,128);
		clouds[19] = new Cloud(2800,200,128,128);
		
		
		clouds[0].type =1;
		clouds[1].type =2;
		clouds[2].type =3;
		clouds[3].type =4;
		clouds[4].type =5;
		clouds[5].type =6;
		clouds[6].type =7;
		clouds[7].type =8;
		clouds[8].type =9;
		clouds[9].type =10;
		clouds[10].type =1;
		clouds[11].type =2;
		clouds[12].type =3;
		clouds[13].type =4;
		clouds[14].type =5;
		clouds[15].type =6;
		clouds[16].type =7;
		clouds[17].type =8;
		clouds[18].type =9;
		clouds[19].type =10;
		
		clouds[0].dx =.0001;
		clouds[1].dx =.0002;
		clouds[2].dx =.00003;
		clouds[3].dx =.0004;
		clouds[4].dx =.000005;
		clouds[5].dx =.00006;
		clouds[6].dx =.00007;
		clouds[7].dx =.00008;
		clouds[8].dx =.0009;
		clouds[9].dx =.00010;
		clouds[10].dx =.00001;
		clouds[11].dx =.0002;
		clouds[12].dx =.0003;
		clouds[13].dx =.004;
		clouds[14].dx =.00005;
		clouds[15].dx =.0006;
		clouds[16].dx =.000007;
		clouds[17].dx =.008;
		clouds[18].dx =.0009;
		clouds[19].dx =.010;
		
		
		
		/*
		lights[0] = new Loff(38,235,lightWidth,lightWidth);
		lights[1] = new Loff(78,235,lightWidth,lightWidth);
		lights[2] = new Loff(116,235,lightWidth,lightWidth);
		lights[3] = new Loff(153,235,lightWidth,lightWidth);
		lights[4] = new Loff(190,235,lightWidth,lightWidth);
		
		light[0] = new Lon(38,235,lightWidth,lightWidth);
		light[1] = new Lon(78,235,lightWidth,lightWidth);
		light[2] = new Lon(116,235,lightWidth,lightWidth);
		light[3] = new Lon(153,235,lightWidth,lightWidth);
		light[4] = new Lon(190,235,lightWidth,lightWidth);
		*/
		wall = new Ice(2225,188,iceWidth,530);
		wall.up = true;
		cliff[0]= new Bat(18,350,400,cliffWidth);
		cliff[1] = new Bat(1420,200,300,cliffWidth);
		cliff[1].vert =true;
		cliff[2] = new Bat(1700,900,1350,cliffWidth);
		cliff[3] = new Bat(1823,330,52,cliffWidth);
		cliff[4] = new Bat(695,567,600,cliffWidth);
		cliff[4].vert = true;
		cliff[5] = new Bat(18,1200,800,cliffWidth);
		cliff[6] = new Bat(1700,940,1350,cliffWidth);
		cliff[6].vert = true;
		cliff[7] = new Bat(290,290,30,cliffWidth);
		
		ice[0] = new Ice(400,350,300,iceWidth);
		ice[1] = new Ice(800,200,620,iceWidth);
		ice[2] = new Ice(1810,600,60,iceWidth);
		ice[3] = new Ice(1920,550,60,iceWidth);
		ice[4] = new Ice(1920,350,50,iceWidth);
		ice[5] = new Ice(2030,500,60,iceWidth);
		ice[6] = new Ice(2030,400,50,iceWidth);
		ice[7] = new Ice(2140,450,60,iceWidth);
		
		
		ice[8] = new Ice(850,1250,800,iceWidth);
		ice[9] = new Ice(1650,1250,800,iceWidth);
		for(int i=0;i<icenum;i++){
			ice[i].up = false;
		}
		
		
		dead[0] = new Dead(695,320,deadWidth,230);
		dead[0].vert=true;
		dead[1] = new Dead(718,350,900,deadWidth);
		dead[2] = new Dead(670,170,1555,deadWidth);
		dead[3] = new Dead(1000,212,deadWidth,13);
		dead[3].vert=true;
		dead[4] = new Dead(1200,212,deadWidth,23);
		dead[4].vert=true;
		dead[5] = new Dead(1805,200,deadWidth,300);
		dead[5].vert=true;
		dead[6] = new Dead(1805,650,deadWidth,50);
		dead[6].vert=true;
		dead[7] = new Dead(1805,700,1245,deadWidth);
		dead[8] = new Dead(2205,718,deadWidth,152);
		dead[8].vert=true;
		dead[9] = new Dead(2400,700,deadWidth,50);
		dead[9].vert=true;
		dead[10] = new Dead(2400,700,deadWidth,50);
		dead[10].vert=true;
		dead[11] = new Dead(2500,800,deadWidth,50);
		dead[11].vert=true;
		dead[12] = new Dead(2600,1100,deadWidth,50);
		dead[12].vert=true;
		dead[13] = new Dead(2700,720,deadWidth,50);
		dead[13].vert=true;
		dead[14] = new Dead(2800,830,deadWidth,50);
		dead[14].vert=true;
		dead[15] = new Dead(695,550,900,deadWidth);
		dead[16] = new Dead(3050,700,deadWidth,600);
		dead[16].vert=true;
		dead[17] = new Dead(1400,572,deadWidth,388);
		dead[17].vert=true;
		dead[18] = new Dead(1250,1080,900,deadWidth);
		dead[19] = new Dead(1250,668,deadWidth,412);
		dead[19].vert=true;
		dead[20] = new Dead(1268,950,32,deadWidth);
		dead[21] = new Dead(1300,830,60,deadWidth);
		dead[22] = new Dead(1350,690,50,deadWidth);
		dead[23] = new Dead(0,188,deadWidth,1130);
		dead[23].vert=true;
		dead[24] = new Dead(400,468,deadWidth,682);
		dead[24].vert=true;
		dead[25] = new Dead(20,500,20,deadWidth);
		dead[26] = new Dead(10,575,20,deadWidth);
		dead[27] = new Dead(15,675,20,deadWidth);
		dead[28] = new Dead(5,750,20,deadWidth);
		dead[29] = new Dead(300,825,20,deadWidth);
		dead[30] = new Dead(125,900,20,deadWidth);
		dead[31] = new Dead(65,975,20,deadWidth);
		dead[32] = new Dead(399,1050,20,deadWidth);
		dead[33] = new Dead(12,1125,20,deadWidth);
		dead[34] = new Dead(18,1300,3050,deadWidth);
		dead[35] = new Dead(1700,912,deadWidth,28);
		dead[35].vert=true;
		dead[36] = new Dead(875,1220,deadWidth,30);
		dead[36].vert=true;
		dead[37] = new Dead(1100,1230,deadWidth,20);
		dead[37].vert=true;
		dead[38] = new Dead(1200,1240,deadWidth,10);
		dead[38].vert=true;
		dead[39] = new Dead(1400,1230,deadWidth,20);
		dead[39].vert=true;
		dead[40] = new Dead(1595,1220,deadWidth,30);
		dead[40].vert=true;
		dead[41] = new Dead(1800,1230,deadWidth,20);
		
		dead[42] = new Dead(1925,1220,deadWidth,30);
		
		dead[43] = new Dead(2100,1230,deadWidth,20);
		
		dead[44] = new Dead(400,450,250,deadWidth);
		dead[45] = new Dead(418,650,850,deadWidth);
		
		//lightbox = new Lbox(18,250,200,deadWidth);
		//dead[46] = new Dead(200,268,deadWidth,82);
		dead[41].vert=true;
		dead[42].vert=true;
		dead[43].vert=true;
		//dead[46].vert=true;
		//wood = new Wood(18,250,200,deadWidth);
		
		
		bit[0] = new Grav(695,285,5,5);
		bit[1] = new Grav(2090,385,5,5);
		bit[2] = new Grav(1800,1190,5,5);
		appear[0]= new Grav(715,340,50,gravWidth);
		appear[1] = new Grav(1810,590,60,gravWidth);
		appear[2] = new Grav(1500,540,50,gravWidth);
		
		gold[0] = new Coin(1960,330,16,16);
		gold[1] = new Coin(3000,880,16,16);
		gold[2] = new Coin(720,400,16,16);
		gold[3] = new Coin(220,370,16,16);
		gold[4] = new Coin(3000,955,16,16);
		
		
		
		grav[0] = new Grav(680,185,50,gravWidth);
		grav[1] = new Grav(1750,200,50,gravWidth);
		grav[2] = new Grav(1400,370,50,gravWidth);
		grav[3] = new Grav(1650,880,50,gravWidth);
		grav[4] = new Grav(1400,370,50,gravWidth);
		grav[5] = new Grav(1300,533,50,gravWidth);
		grav[6] = new Grav(1200,370,50,gravWidth);
		grav[7] = new Grav(1100,533,50,gravWidth);
		grav[8] = new Grav(1000,370,50,gravWidth);
		grav[9] = new Grav(900,533,50,gravWidth);
		grav[10] = new Grav(800,370,50,gravWidth);
		grav[11] = new Grav(718,533,50,gravWidth);
		grav[12] = new Grav(718,370,50,gravWidth);
		grav[13] = new Grav(1500,565,50,gravWidth);
		grav[14] = new Grav(1350,1070,50,gravWidth);
		grav[15] = new Grav(15,370,50,gravWidth);
		grav[16] = new Grav(2550,1250,50,gravWidth);
		
		
		
		box = new Box(325,250,24,24);
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
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
			glColor4d(1.0,1,1,1);
			//boxDraw(this.x,this.y,this.width,this.height);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
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
			*/
			
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
			//GL11.glDisable(GL11.GL_TEXTURE_2D);
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2d(this.x,this.y);
				glTexCoord2f(1,0);
				glVertex2d(this.x+this.width,this.y);
				glTexCoord2f(1,1);
				glVertex2d(this.x+this.width,this.y+this.height);
				glTexCoord2f(0,1);
				glVertex2d(this.x,this.y+this.height);
			glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			glDisable(GL_BLEND);
			
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
	
	
	class Ice extends AbstractMoveableEntity{
		boolean up=false;
		public Ice(double x, double y , double width, double height){
			super(x,y,width,height);
		}
		
		public void draw(){
			glColor3d(1,1,1);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			if(this.up){
				icev.bind();
			}
			else{
				
				icel.bind();
			}
			
			
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2d(this.x,this.y);
				glTexCoord2f(1,0);
				glVertex2d(this.x+this.width,this.y);
				glTexCoord2f(1,1);
				glVertex2d(this.x+this.width,this.y+this.height);
				glTexCoord2f(0,1);
				glVertex2d(this.x,this.y+this.height);
			glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
		}
		
		
	}
	
	class Bat extends AbstractMoveableEntity{
		boolean on=false,up=false,vert = false;
		
		public Bat(double x, double y , double width, double height){
			super(x,y,width,height);
		}
		
		public void draw(){
			glColor3d(1.0,1,1);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			if(this.vert){
				cliffv.bind();
			}
			else{
				cliffi.bind();
			}
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2d(this.x,this.y);
				glTexCoord2f(1,0);
				glVertex2d(this.x+this.width,this.y);
				glTexCoord2f(1,1);
				glVertex2d(this.x+this.width,this.y+this.height);
				glTexCoord2f(0,1);
				glVertex2d(this.x,this.y+this.height);
			glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
		}
		
		
	}
	class Dead extends AbstractMoveableEntity{
		boolean up=true,upp=false,right= true,vert=false;
		int i=0,j=0;
		public Dead(double x, double y , double width, double height){
			super(x,y,width,height);
		}
		
		public void draw(){
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
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2d(this.x,this.y);
				glTexCoord2f(1,0);
				glVertex2d(this.x+this.width,this.y);
				glTexCoord2f(1,1);
				glVertex2d(this.x+this.width,this.y+this.height);
				glTexCoord2f(0,1);
				glVertex2d(this.x,this.y+this.height);
			glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			glDisable(GL_BLEND);
			
		}
		
		
	}
	
class Coin extends AbstractMoveableEntity{
		
		public Coin(double x, double y , double width, double height){
			super(x,y,width,height);
		}
		
		public void draw(){
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4d(1,1,1,1);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			coini.bind();
			
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2d(this.x,this.y);
				glTexCoord2f(1,0);
				glVertex2d(this.x+this.width,this.y);
				glTexCoord2f(1,1);
				glVertex2d(this.x+this.width,this.y+this.height);
				glTexCoord2f(0,1);
				glVertex2d(this.x,this.y+this.height);
			glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			glDisable(GL_BLEND);
		}
		private boolean contains(Entity other)
		{
			return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
		}
		
	}
	
class Gem extends AbstractMoveableEntity{
	
	public Gem(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		door.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}


class Back extends AbstractMoveableEntity{
	boolean up,on;
	public Back(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
			glColor3d(1,1,1);
			glRectd(x,y, x+width, y+height);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Loff extends AbstractMoveableEntity{
	boolean on=false;
	public Loff(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		if(!on)
			Loff.bind();
		else
			Lon.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Lon extends AbstractMoveableEntity{
	
	public Lon(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Lon.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Doorjam extends AbstractMoveableEntity{
	public boolean pause;
	boolean upp=false;
	public Doorjam(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		doorjam.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Wood extends AbstractMoveableEntity{
	boolean upp=false;
	public Wood(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		woodi.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Ledge extends AbstractMoveableEntity{
	boolean upp=false;
	public Ledge(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		ledgei.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Rope extends AbstractMoveableEntity{
	boolean upp=false;
	public Rope(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		ropei.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Hang extends AbstractMoveableEntity{
	boolean upp=false;
	public Hang(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		hangi.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Lbox extends AbstractMoveableEntity{
	
	public Lbox(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		lboxi.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Wheel extends AbstractMoveableEntity{
	boolean switch1=true;
	public Wheel(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
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
		
		if(switch1){
			wheeli.bind();
		}
		else{
			wheeli2.bind();
		}
			
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Cloud extends AbstractMoveableEntity{
	int type,startx;
	double dx;
	public Cloud(double x, double y , double width, double height){
		super(x,y,width,height);
		this.startx =(int) x;
	}
	
	public void draw(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
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
		//cloud1.bind();
		
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Sky extends AbstractMoveableEntity{
	
	public Sky(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		
		
		glColor3d(.6,.9,.9);
		
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		
		
	}
	boolean contains(Entity other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
}

class Grav extends AbstractMoveableEntity{
	
	public Grav(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glColor3d(1.0,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		gravflip.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
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
}


//classes


class Ice1 extends AbstractMoveableEntity{
	
	public Ice1(double x, double y , double width, double height){
		super(x,y,width,height);
	}
	
	public void draw(){
		glColor3d(0,1,1);
			glRectd(x,y, x+width, y+height);
		
	}
	
	

	
}




class Gem1 extends AbstractMoveableEntity{
	
	public Gem1(double x, double y , double width, double height){
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

class Gold1 extends AbstractMoveableEntity{
	
	public Gold1(double x, double y , double width, double height){
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



