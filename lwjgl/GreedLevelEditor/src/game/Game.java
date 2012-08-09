package game;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.Display; 


import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;



import entities.*;




public class Game {
	public static final int width = 640;
	public static final int height = 480;
	float translate_x = 0;
	float translate_y =0;
	private long lastFrame,oldFrame;
	
	// ******Variables for LEVEL1 and LEVEL2************
	private Texture left,right,gright,gleft,icel,cliffi,cliffv,icev,deadi,deadi1,deadi2,deadi3,deadi4,deadv,deadv1,deadv2,deadv3,deadv4,coini,door,doorv,gravflip,gravflip2,gravflip3,gravflip4,gravflip5,gravflip6,gravflip7,gravflip8,gravflip9,gravflip10,gravflip11,gravflip12,gravflip13,gravflip14,gravflip15,gravflip16,gravflip17,gravflip18,Lon,Loff,brick,brickv,wallpaper;
	private Texture cloud1,cloud2,cloud3,cloud4,cloud5,cloud6,cloud7,cloud8,cloud9,cloud10,lboxi,doorjam,doorjamv,woodi,ledgei,ropei,hangi,hangv,wheeli,wheeli2,sky1,sky2,sky3,sky4,sky5,sky6,a1,a2,a3,a4,a5,esc,space,words,words2,words3,words4,words5,words6,words7,words8,words9,words10,words11,words12,words13,words14,words15,words16,words17,words18,words19,words20,words21,words22;
	private Texture p,pr,pre,pres,press,news;
	//private Texture left1,right1,gright1,gleft1,left2,right2,gleft2,gright2,left3,right3,gleft3,gright3,left4,right4,gleft4,gright4,left5,right5,gleft5,gright5,ski;
	int x = 10,goldCount=0;
	int y =440;
	int dx =1;
	int dy =1;
	int currLevel=0;
	int endHeight =220,bumper=4,endHeight1 = -1285;
	
	private Sky skyi,skimain;
	private Box box,box1;
	private Bat[] cliff;
	private Ice[] ice;
	private Dead[] dead;
	private Dead window;
	private Grav[] grav,bit,appear;
	
	private Coin[] gold;
	private Ice wall;
	
	private Gem gem;
	private Cloud []clouds;
	
	private Doorjam doorj,doorj1;
	
	private Rope rope,rope1,rope2,rope3;
	private Hang hang1,hang11;
	private Wheel wheel,wheel1;
	private Ledge ledge,ledge1;
	private Wall wallp;
	private News [] newspaper;
	int cliffnum=8,lastDIR,icenum=10,deadnum=46,gravnum=17;
	int count;
	int delta = getDelta();
	int cliffWidth =12,iceWidth =12,deadWidth =18,gravWidth=12,lightWidth =16,brickWidth =80,coinWidth=16,coinHeight=2*coinWidth,bitSize=10;
	boolean jump=false,fall=true,gravity=true,onGround=false,DIRlock = false,jumpvar,blueFound=false,onCliff=false;
	boolean blueFound2 = false,goldFound=false,up=false,goldFound2=false,goldFound3=false,goldFound4=false,blueFound3=false;
	boolean goldFound5 = false,win=false,win1=false,ledgeFall=false,ledgeFall1=false;
	boolean wallhit=false,spaceDraw=false;
	
	
	
	//********variables for main menu***********
	private Text []text;
	private Brick[] bat;
	private Skyline[] skyl;
	int batnum;
	boolean moveLeft = true;
	private Arrow[] arrow;
	private ArrowKey[] keys;
	
	// ********declare states of the game ********
	private static enum State {
		INTRO, MAIN_MENU,LEVEL1,LEVEL_SELECT,ABOUT,CONTROLS,EXIT,GAMEOVER,WIN,LEVEL2,WELCOME,WALLPAPER;
		
	}
	
	State state = State.WALLPAPER;
	
	
	
	
	//********main********
	public static void main(String [] args){
		
		new Game();
	}
	
	
	//********actual game********
	public Game(){
		//initialize main menu variables [some of these variables are used throughout the main program, not necessarily in main so there is some inconsistency here]
		initMain();
		
		//initialize window and opengl stuff
		initGL();
		
		
		//******** initialize all textures********
		left = loadTexture("bag");
		right = loadTexture("bagi1");
		gleft = loadTexture("bagv1");
		gright = loadTexture("bagv2");
		
		
		
		/*
		 * these top 4 are an attempt at a new boss greed but he is too small for it to be useful
		left = loadTexture("newbagleft");
		right = loadTexture("newbag");
		gleft = loadTexture("newbagleftv");
		gright = loadTexture("newbagrightv");
		
		
		these next 20 are him 'gaining weight' as coins are acquired, not good enough to use right now
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
		*/
		
		sky1 = loadTexture("skyline1");
		sky2 = loadTexture("skyline2");
		sky3 = loadTexture("skyline3");
		sky4 = loadTexture("skyline4");
		sky5 = loadTexture("skyline5");
		sky6 = loadTexture("skyline6");
		icel = loadTexture("air");
		cliffi = loadTexture("cliff");
		cliffv = loadTexture("cliff2");
		icev = loadTexture("air1");
		deadi = loadTexture("dead");
		coini = loadTexture("coin2");
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
		Lon = loadTexture("lighton");
		Loff = loadTexture("lightoff");
		//ski = loadTexture("sky");
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
		brick = loadTexture("brick");
		brickv = loadTexture("brickv");
		a1 = loadTexture("arrow");
		a2 = loadTexture("arrow1");
		words = loadTexture("words");
		words2 = loadTexture("wordsLevel");
		words3 = loadTexture("wordsAbout");
		words4 = loadTexture("wordsExit");
		words5 = loadTexture("wordsControls");
		words6 = loadTexture("wordsMove");
		words7 = loadTexture("wordsJump");
		words8 = loadTexture("wordsMainMenu");
		words9 = loadTexture("level1");
		words10 = loadTexture("level2");
		words11 = loadTexture("wordsGameOver");
		words12 = loadTexture("wordsRestart");
		words13 = loadTexture("wordsYouWon");
		words14 = loadTexture("wordsWelcome");
		words15 = loadTexture("wordsIntroLevel");
		words16 = loadTexture("wordsto");
		words17 = loadTexture("wordsAvoidFire");
		words18 = loadTexture("wordsBossGreed");
		words19 = loadTexture("wordsSlidesonIce");
		words20 = loadTexture("wordsGravityFlipper");
		words21 = loadTexture("wordsCoinsAddWeightto");
		words22 = loadTexture("wordsAvoidspike");
		wallpaper = loadTexture("bgwallpaper3");
		p = loadTexture("wordsP");
		pr = loadTexture("wordsPr");
		pre = loadTexture("wordsPre");
		pres = loadTexture("wordsPres");
		press = loadTexture("wordsPress");
		news = loadTexture("news");
		
		
		a3 = loadTexture("arrowup");
		a4 = loadTexture("arrowleft");
		a5 = loadTexture("arrowright");
		esc = loadTexture("esc");
		space = loadTexture("spacebar");
		
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
		doorv = loadTexture("doorv");
		ropei = loadTexture("rope");
		hangi = loadTexture("hang");
		doorjamv = loadTexture("doorjamv");
		hangv = loadTexture("hangv");
		ledgei = loadTexture("ledge");
		wheeli = loadTexture("wheel");
		wheeli2 = loadTexture("wheel1");
		
		
		skyi = new Sky(-10000,-10000,20000,20000);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,width,height,0,1,-1);
		glMatrixMode(GL_MODELVIEW);

		
		//main loop
		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			checkInput();
			renderer();
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		System.exit(0);
		
	}
	
	//check input of whatever state you may be in
	private void checkInput(){
		switch(state){
		case WALLPAPER:
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				state = State.WELCOME;
			}
			break;
		case WELCOME:
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			{	
				state = State.MAIN_MENU;
				initMain();
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			{	
				initTrial();
				initAbout();
				state = State.INTRO;
				
			}
			break;
		case INTRO:
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
					box.setX(box.getX() +3);
					box1.setX(box1.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				
				box.setX(box.getX() -3);
				box1.setX(box1.getX() -3);
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
		case LEVEL_SELECT:
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
				
				if(box.leftOf(bat[5])||box.leftOf(bat[9])||box.leftOf(bat[13])||box.leftOf(bat[15]))
				{}
				else{
					box.setX(box.getX() +3);
					box1.setX(box1.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				}
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				if(box.rightOf(bat[1])||box.rightOf(bat[3])||box.rightOf(bat[7])||box.rightOf(bat[11]))
				{}
				else{
					box.setX(box.getX() -3);
					box1.setX(box1.getX() -3);
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
					box1.setX(box1.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				}
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				if(box.rightOf(bat[1])||box.rightOf(bat[3])||box.rightOf(bat[7])||box.rightOf(bat[11]))
				{}
				else{
					box.setX(box.getX() -3);
					box1.setX(box1.getX() -3);
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
					box1.setX(box1.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				
				box.setX(box.getX() -3);
				box1.setX(box1.getX() -3);
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
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
					box.setX(box.getX() +3);
					box1.setX(box1.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				
				box.setX(box.getX() -3);
				box1.setX(box1.getX() -3);
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
				if(currLevel==1){
					state = State.LEVEL1;
				}
				else if(currLevel ==0){
					state =State.INTRO;
				}
				else if(currLevel ==2){
					state =State.LEVEL2;
				}
					
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
	//what to render depending on the state of the game
	private void renderer(){
		switch(state){
		case WALLPAPER:
			drawWallpaper();
			break;
		case WELCOME:
			drawWelcome();
			break;
		case INTRO:
			renderTrial();
			break;
		case MAIN_MENU:
			
			renderMain();
			drawMain();
			break;
		case LEVEL_SELECT:
			
			renderMain();
			drawLevel();
			break;
		case LEVEL1:
			
			render();
			break;
		case LEVEL2:
			render2();
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
	//initialize opengl
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
	
	//Initialize intro level[initially called trial level but that didnt really fit, so all variables right now are trial]
	private void initTrial(){
		currLevel=0;
		translate_x=0;
		translate_y=0;
		endHeight =270;
		lastDIR =1;
		goldCount =0;
		jump=false;fall=true;gravity=true;onGround=false;DIRlock = false;blueFound=false;onCliff=false;
		blueFound2 = false;goldFound=false;up=false;goldFound2=false;goldFound3=false;goldFound4=false;blueFound3=false;
		goldFound5 = false;win=false;
		wallhit=false;
		cliff = new Bat[50];
		ice = new Ice[20];
		dead = new Dead[100];
		grav = new Grav[30];
		bit = new Grav[10];
		appear = new Grav[10];
		gold = new Coin[5];
		clouds = new Cloud[20];
		clouds[0] = new Cloud(300,222,128,128);
		clouds[1] = new Cloud(0,200,128,128);
		clouds[2] = new Cloud(465,1000,128,128);
		clouds[3] = new Cloud(79,300,128,128);
		clouds[4] = new Cloud(110,-100,128,128);
		clouds[5] = new Cloud(32,2500,128,128);
		clouds[6] = new Cloud(1400,250,128,128);
		clouds[7] = new Cloud(432,750,128,128);
		clouds[8] = new Cloud(1040,-50,128,128);
		clouds[9] = new Cloud(2000,-150,128,128);
		clouds[10] = new Cloud(400,150,128,128);
		clouds[11] = new Cloud(1600,50,128,128);
		clouds[12] = new Cloud(795,0,128,128);
		clouds[13] = new Cloud(1340,500,128,128);
		clouds[14] = new Cloud(10,40,128,128);
		clouds[15] = new Cloud(1,1500,128,128);
		clouds[16] = new Cloud(106,2000,128,128);
		clouds[17] = new Cloud(1000,-300,128,128);
		clouds[18] = new Cloud(1000,3000,128,128);
		clouds[19] = new Cloud(2800,-200,128,128);
		
		
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
		
		clouds[0].setDX(.0001);
		clouds[1].setDX(.0002);
		clouds[2].setDX(.00003);
		clouds[3].setDX(.0004);
		clouds[4].setDX(.000005);
		clouds[5].setDX(.00006);
		clouds[6].setDX(.00007);
		clouds[7].setDX(.00008);
		clouds[8].setDX(.0009);
		clouds[9].setDX(.00010);
		clouds[10].setDX(.00001);
		clouds[11].setDX(.0002);
		clouds[12].setDX(.0003);
		clouds[13].setDX(.004);
		clouds[14].setDX(.00005);
		clouds[15].setDX(.0006);
		clouds[16].setDX(.000007);
		clouds[17].setDX(.008);
		clouds[18].setDX(.0009);
		clouds[19].setDX(.010);
		
		
		arrow[1] = new Arrow(2920,190,48,96);
		ledge = new Ledge(2923,310,48,12);
		
		hang1 = new Hang(2942,125,170,85);
		rope = new Rope(2946,210,2,105);
		rope1 = new Rope(3106,210,2,75);
		doorj = new Doorjam(3093,280,32,128);
		wheel = new Wheel(3008,168,32,32);
		/*
		ledge = new Ledge(353,260,48,12);
		
		hang1 = new Hang(211,75,170,85);
		rope = new Rope(375,160,2,105);
		rope1 = new Rope(213,160,2,75);
		doorj = new Doorjam(200,230,32,128);
		wheel = new Wheel(277,118,32,32);
		*/
		arrow[2] = new Arrow(3200,230,48,96);
		gem = new Gem(3200,336,64,64);
		
		cliff[0] = new Bat(0,400,700,cliffWidth);
		cliff[1]= new Bat(1700,400,1700,cliffWidth);
		cliff[2] = new Bat(1700,0,700,cliffWidth);
		cliff[3] = new Bat(2830,350,50,cliffWidth);
		
		gold[0] = new Coin(2300,350,coinWidth,coinHeight);
		gold[1] = new Coin(2400,350,coinWidth,coinHeight);
		gold[2] = new Coin(2500,350,coinWidth,coinHeight);
		gold[3] = new Coin(2600,350,coinWidth,coinHeight);
		gold[4] = new Coin(2700,350,coinWidth,coinHeight);
		
		
		
		
		dead[0] = new Dead(500,370,deadWidth,30);
		dead[0].vert=true;
		dead[1] = new Dead(0,0,deadWidth,400);
		dead[1].vert=true;
		dead[2] = new Dead(2100,150,deadWidth,262);
		dead[2].vert=true;
		dead[3]= new Dead(1700,cliffWidth,deadWidth,250);
		dead[3].vert=true;
		
		ice[0] = new Ice(700,400,1000,iceWidth);
		ice[1] = new Ice(3750,400,1000,iceWidth);
		ice[2] = new Ice(2300,-100,iceWidth,100);
		ice[2].up=true;
		ice[3] = new Ice(2800,-100,iceWidth,100);
		ice[3].up=true;
		ice[4] = new Ice(2300,-100,500,iceWidth);
		
		grav[0] = new Grav(1800,380,100,gravWidth);
		grav[1] = new Grav(1800,20,100,gravWidth);
		
		
		arrow[0] = new Arrow(2300,-200,48,96);
		bit[0] = new Grav(2320,-85,bitSize,bitSize);
		appear[0] = new Grav(2730,-85,50,gravWidth);
		
		
		box = new Box(325,370,26,26);
		box1 = new Box(329,374,18,18);
	}
	
	//initialize all variables for LEVEL1 
	private void initVars(){
		currLevel=1;
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
		//light = new Lon[5];
		//lights = new Loff[5];
		
		
		gem = new Gem(20,284,64,64);
		ledge = new Ledge(353,260,48,12);
	
		hang1 = new Hang(211,75,170,85);
		rope = new Rope(375,160,2,105);
		rope1 = new Rope(213,160,2,75);
		doorj = new Doorjam(200,230,32,128);
		wheel = new Wheel(277,118,32,32);
		clouds = new Cloud[20];
		clouds[0] = new Cloud(300,222,128,128);
		clouds[1] = new Cloud(0,500,128,128);
		clouds[2] = new Cloud(0,1000,128,128);
		clouds[3] = new Cloud(0,1500,128,128);
		clouds[4] = new Cloud(0,2000,128,128);
		clouds[5] = new Cloud(0,200,128,128);
		clouds[6] = new Cloud(500,250,128,128);
		clouds[7] = new Cloud(500,750,128,128);
		clouds[8] = new Cloud(500,1250,128,128);
		clouds[9] = new Cloud(500,1750,128,128);
		clouds[10] = new Cloud(500,2250,128,128);
		clouds[11] = new Cloud(500,50,128,128);
		clouds[12] = new Cloud(1000,0,128,128);
		clouds[13] = new Cloud(1000,500,128,128);
		clouds[14] = new Cloud(1000,1000,128,128);
		clouds[15] = new Cloud(1000,1500,128,128);
		clouds[16] = new Cloud(1000,2000,128,128);
		clouds[17] = new Cloud(1000,300,128,128);
		clouds[18] = new Cloud(1000,0,128,128);
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
		
		clouds[0].setDX(.0001);
		clouds[1].setDX(.0002);
		clouds[2].setDX(.00003);
		clouds[3].setDX(.0004);
		clouds[4].setDX(.000005);
		clouds[5].setDX(.00006);
		clouds[6].setDX(.00007);
		clouds[7].setDX(.00008);
		clouds[8].setDX(.0009);
		clouds[9].setDX(.00010);
		clouds[10].setDX(.00001);
		clouds[11].setDX(.0002);
		clouds[12].setDX(.0003);
		clouds[13].setDX(.004);
		clouds[14].setDX(.00005);
		clouds[15].setDX(.0006);
		clouds[16].setDX(.000007);
		clouds[17].setDX(.008);
		clouds[18].setDX(.0009);
		clouds[19].setDX(.010);
		
		
		
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
		
		
		bit[0] = new Grav(695,285,bitSize,bitSize);
		bit[1] = new Grav(2090,385,bitSize,bitSize);
		bit[2] = new Grav(1800,1190,bitSize,bitSize);
		appear[0]= new Grav(715,340,50,gravWidth);
		appear[1] = new Grav(1810,590,60,gravWidth);
		appear[2] = new Grav(1500,542,50,gravWidth);
		
		gold[0] = new Coin(1960,320,coinWidth,coinHeight);
		gold[1] = new Coin(3000,870,coinWidth,coinHeight);
		gold[2] = new Coin(720,400,coinWidth,coinHeight);
		gold[3] = new Coin(220,370,coinWidth,coinHeight);
		gold[4] = new Coin(3000,955,coinWidth,coinHeight);
		
		
		
		grav[0] = new Grav(680,185,50,gravWidth);
		grav[1] = new Grav(1750,200,50,gravWidth);
		grav[2] = new Grav(1400,365,50,gravWidth);
		grav[3] = new Grav(1650,880,50,gravWidth);
		grav[4] = new Grav(1400,365,50,gravWidth);
		grav[5] = new Grav(1300,542,50,gravWidth);
		grav[6] = new Grav(1200,365,50,gravWidth);
		grav[7] = new Grav(1100,542,50,gravWidth);
		grav[8] = new Grav(1000,365,50,gravWidth);
		grav[9] = new Grav(900,542,50,gravWidth);
		grav[10] = new Grav(800,365,50,gravWidth);
		grav[11] = new Grav(718,542,50,gravWidth);
		grav[12] = new Grav(718,365,50,gravWidth);
		grav[13] = new Grav(1500,565,50,gravWidth);
		grav[14] = new Grav(1350,1070,50,gravWidth);
		grav[15] = new Grav(15,365,50,gravWidth);
		grav[16] = new Grav(2550,1250,50,gravWidth);
		
		
		
		box = new Box(325,250,26,26);
		box1 = new Box(329,254,18,18);
	}
	//initialize all variables for LEVEL2
	private void initVars2(){
		currLevel=2;
		translate_x=0;
		translate_y=0;
		endHeight =-35;
		lastDIR =2;
		goldCount =0;
		jump=false;fall=true;gravity=true;onGround=false;DIRlock = false;blueFound=false;onCliff=false;
		blueFound2 = false;goldFound=false;up=false;goldFound2=false;goldFound3=false;goldFound4=false;blueFound3=false;
		goldFound5 = false;win=false;
		wallhit=false;
		ledgeFall=false;
		
		cliff = new Bat[50];
		ice = new Ice[20];
		dead = new Dead[100];
		grav = new Grav[30];
		bit = new Grav[10];
		appear = new Grav[10];
		gold = new Coin[5];
		clouds = new Cloud[20];
		clouds[0] = new Cloud(300,222,128,128);
		clouds[1] = new Cloud(0,200,128,128);
		clouds[2] = new Cloud(465,1000,128,128);
		clouds[3] = new Cloud(79,300,128,128);
		clouds[4] = new Cloud(110,-100,128,128);
		clouds[5] = new Cloud(32,2500,128,128);
		clouds[6] = new Cloud(1400,250,128,128);
		clouds[7] = new Cloud(432,750,128,128);
		clouds[8] = new Cloud(1040,-50,128,128);
		clouds[9] = new Cloud(2000,-150,128,128);
		clouds[10] = new Cloud(400,150,128,128);
		clouds[11] = new Cloud(1600,50,128,128);
		clouds[12] = new Cloud(795,0,128,128);
		clouds[13] = new Cloud(1340,500,128,128);
		clouds[14] = new Cloud(10,40,128,128);
		clouds[15] = new Cloud(1,1500,128,128);
		clouds[16] = new Cloud(106,2000,128,128);
		clouds[17] = new Cloud(1000,-300,128,128);
		clouds[18] = new Cloud(1000,3000,128,128);
		clouds[19] = new Cloud(2800,-200,128,128);
		
		
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
		
		
		
		
		ledge = new Ledge(2923,5,48,12);
		
		hang1 = new Hang(2942,-180,170,85);
		rope = new Rope(2946,-95,2,105);
		rope1 = new Rope(3106,-95,2,75);
		doorj = new Doorjam(3093,-25,32,128);
		doorj.upp=false;
		wheel = new Wheel(3008,-137,32,32);
		
		ledge1 = new Ledge(2970,-1200,48,12);
		
		hang11 = new Hang(2831,-1097,170,85);
		hang11.upp=true;
		rope2 = new Rope(2993,-1200,2,105);
		rope3 = new Rope(2836,-1170,2,75);
		doorj1 = new Doorjam(2823,-1295,32,128);
		doorj1.vert=true;
		wheel1 = new Wheel(2928,-1088,-32,32);
		
		
		/*
		ledge = new Ledge(353,260,48,12);
		
		hang1 = new Hang(211,75,170,85);
		rope = new Rope(375,160,2,105);
		rope1 = new Rope(213,160,2,75);
		doorj = new Doorjam(200,230,32,128);
		wheel = new Wheel(277,118,32,32);
		*/
		
		
		cliff[0] = new Bat(0,500,740,cliffWidth);
		cliff[1] = new Bat(800,450,60,cliffWidth);
		cliff[2] = new Bat(900,400,60,cliffWidth);
		cliff[3] = new Bat(800,350,60,cliffWidth);
		cliff[4] = new Bat(900,300,60,cliffWidth);
		cliff[5] = new Bat(1000,250,60,cliffWidth);
		cliff[6] = new Bat(1700,280,400,cliffWidth);
		cliff[7] = new Bat(500,700,800,cliffWidth);
		cliff[8] = new Bat(2300,450,100,cliffWidth);
		cliff[9] = new Bat(2450,100,800,cliffWidth);
		cliff[10] = new Bat(2450,680,40,cliffWidth);
		cliff[11] = new Bat(0,-200,2300,cliffWidth);
		cliff[11].vert=true;
		cliff[12] = new Bat(2850,50,30,cliffWidth);
		cliff[13] = new Bat(3310,100,200,cliffWidth);
		cliff[14] = new Bat(3700,-500,900,cliffWidth);
		cliff[14].vert=true;
		cliff[15]= new Bat(3570,-600,200,cliffWidth);
		cliff[15].vert=true;
		cliff[16] = new Bat(3400,-700,370,cliffWidth);
		cliff[16].vert=true;
		cliff[17]= new Bat(2000,-1300,3000,cliffWidth);
		cliff[17].vert=true;
		cliff[18] = new Bat(3400,-800,310,cliffWidth);
		cliff[18].vert=true;
		cliff[19] = new Bat(3070,-1250,40,cliffWidth);
		cliff[19].vert=true;
		cliff[20] = new Bat(3900,-800,550,cliffWidth);
		
		
		dead[0] = new Dead(-deadWidth,-200,deadWidth,700+deadWidth);
		dead[0].vert=true;
		dead[1] = new Dead(0,500+deadWidth,1100,deadWidth);
		dead[2] = new Dead(865,500,deadWidth,40);
		dead[2].vert=true;
		dead[3] = new Dead(965,500,deadWidth,40);
		dead[3].vert=true;
		dead[4] = new Dead(1100,250+deadWidth,deadWidth,150);
		dead[4].vert=true;
		dead[5] = new Dead(1700,330,deadWidth,60);
		dead[5].vert=true;
		dead[6] = new Dead(500,500+deadWidth,deadWidth,200-deadWidth);
		dead[6].vert=true;
		dead[7]= new Dead(500,1000+2*deadWidth,4300,deadWidth);
		dead[8] = new Dead(2500,100+deadWidth,deadWidth,900+deadWidth);
		dead[8].vert=true;
		dead[9] = new Dead(1100,-180,20,deadWidth);
		dead[10] = new Dead(1300,-180,20,deadWidth);
		dead[11] = new Dead(700, -180,20,deadWidth);
		dead[12] = new Dead(500,-180,20,deadWidth);
		dead[13] = new Dead(0,-180,20,deadWidth);
		dead[14] = new Dead(2000,-180,20,deadWidth);
		dead[15] = new Dead(300,-180,20,deadWidth);
		dead[16] = new Dead(3170,120,200,deadWidth);
		dead[17] = new Dead(3400,-900,deadWidth,172);
		dead[17].vert=true;
		dead[18]= new Dead(3570,-600,deadWidth,400);
		dead[18].vert=true;
		dead[19] = new Dead(3700,-500,deadWidth,300);
		dead[19].vert=true;
		dead[20] = new Dead(4600,-1100,deadWidth,600);
		dead[20].vert=true;
		dead[21] = new Dead(4800,-1300,deadWidth,3000);
		dead[21].vert=true;
		dead[22] = new Dead(3770-deadWidth,-700,deadWidth,100);
		dead[22].vert=true;
		dead[23] = new Dead(1900,450+deadWidth,550,deadWidth);
		dead[24]=new Dead(2450-deadWidth,270,deadWidth,300);
		dead[24].vert=true;
		dead[25] = new Dead(3400,-700,deadWidth,650);
		dead[25].vert=true;
		dead[26] = new Dead(3950,-650,500,deadWidth);
		dead[27] = new Dead(4450,-1100,150,deadWidth);
		dead[28] = new Dead(4450,-1100,deadWidth,300);
		dead[28].vert=true;
		dead[29] = new Dead(2300,-200,500,deadWidth);
		
		
		
		
		
		
		
		window= new Dead(2450,600,50,deadWidth);
		
		
		
		
		ice[0] = new Ice(1100,400+deadWidth,iceWidth,100);
		ice[0].up = true;
		ice[1] = new Ice(1100,500+deadWidth,600,iceWidth);
		ice[2] = new Ice(1700,370,iceWidth,130+deadWidth);
		ice[2].up=true;
		ice[3] = new Ice(1380,470,60,iceWidth);
		ice[4] = new Ice(1460,420,60,iceWidth);
		ice[5] = new Ice(1560,370,60,iceWidth);
		ice[6] = new Ice(1660,320,60,iceWidth);
		ice[7] = new Ice(1900,400,300,iceWidth);
		ice[8] = new Ice(1300,700,1100,iceWidth);
		
		grav[0] = new Grav(2150,150,50,gravWidth);
		grav[1] = new Grav(10,-160, 50,gravWidth);
		grav[2] = new Grav(3510,100,50,gravWidth);
		grav[3] = new Grav(3200,-1280,30,gravWidth);
		grav[4] = new Grav(4500,-1050,50,gravWidth);
		
		bit[0] = new Grav(90,-180,bitSize,bitSize);
		bit[1] = new Grav(3700,-680,bitSize,bitSize);
		
		appear[0] = new Grav(200,470,50,gravWidth);
		appear[1] = new Grav(3450,-680,50,gravWidth);
		
		newspaper = new News[2];
		
		newspaper[0] = new News(720,670,24,24);
		
		newspaper[1] = new News(3490,90,24,24);
		
		gold[0] = new Coin(300,250,coinWidth,coinHeight);
		gold[1] = new Coin(600,670,coinWidth,coinHeight);
		gold[2] = new Coin(3260,40,coinWidth,coinHeight);
		gold[3] = new Coin(3500,-740,coinWidth,coinHeight);
		gold[4] = new Coin(4700,-700,coinWidth,coinHeight);
		
		gem = new Gem(2500,-1300+cliffWidth,64,64);
		gem.vert = true;
		
		box = new Box(325,300,26,26);
		box1 = new Box(329,304,18,18);
	}
	//initialize all variables for MAIN
	private void initMain(){
		lastDIR =2;
		translate_x=0;
		translate_y=0;
		goldCount =0;
		jump=false;fall=true;gravity=true;onGround=false;
		
		skimain = new Sky(-100,-200,1000,600);
		clouds = new Cloud[3];
		clouds[0] = new Cloud(40,50,128,128);
		
		clouds[1] = new Cloud(350,20,128,128);
		clouds[2] = new Cloud(600,70,128,128);
		clouds[0].type =1;
		clouds[2].type =7;
		clouds[1].type =3;
		
		wallp = new Wall(0,0,640,400);
		
		text = new Text[25];
		text[0] = new Text(200,10,440,64);
		text[1] = new Text(65,190,128,48);
		text[1].type =1;
		text[2] = new Text(254,190,128,48);
		text[2].type=2;
		text[3] = new Text(470,190,98,48);
		text[3].type=3;
		text[4] = new Text(64,30,512,48);
		text[4].type=4;
		text[5] = new Text(380,115,256,48);
		text[5].type=5;
		text[6] = new Text(0,100,256,96);
		text[6].type=6;
		text[7] = new Text(70,330,512,48);
		text[7].type=7;
		text[8] = new Text(65,190,128,48);
		text[8].type =8;
		text[9] = new Text(254,190,128,48);
		text[9].type=9;
		text[10] = new Text(400,190,250,48);
		text[10].type=7;
		text[11] = new Text(120,30,512,48);
		text[11].type=10;
		text[12] = new Text(64,150,512,48);
		text[12].type=11;
		text[13] = new Text(64,30,512,48);
		text[13].type=12;
		text[14] = new Text(120,10,400,100);
		text[14].type=13;
		text[15] = new Text(-25,330,375,48);
		text[15].type=14;
		text[16] = new Text(256,110,128,64);
		text[16].type=15;
		text[17] = new Text(200,450,512,64);
		text[17].type=16;
		text[18] = new Text(700,200,256,64);
		text[18].type=17;
		text[19] = new Text(1050,200,512,64);
		text[19].type=18;
		text[20] = new Text(1703,250,400,75);
		text[20].type=19;
		text[21] = new Text(2250,250,400,75);
		text[21].type =20;
		text[22] = new Text(2570,250,256,40);
		text[22].type=17;
		text[23] = new Text(2800,450,512,96);
		text[23].type=21;
		text[24] = new Text(-10,410,512,48);
		text[24].type =22;
		
		
		
		
		bat = new Brick[20];
		skyl = new Skyline[6];
		arrow = new Arrow[3];
		keys = new ArrowKey[5];
		keys[0] = new ArrowKey(100,200,64,64);
		keys[0].type=0;
		keys[1] = new ArrowKey(426,200,64,64);
		keys[1].type=1;
		keys[2] = new ArrowKey(526,200,64,64);
		keys[2].type=2;
		keys[3] = new ArrowKey(288,400,64,64);
		keys[3].type=3;
		keys[4] = new ArrowKey(188,230,256,48);
		keys[4].type=4;
		arrow[0] = new Arrow(98,250,48,96);
		arrow[1] = new Arrow(298,250,48,96);
		arrow[2] = new Arrow(498,250,48,96);
		arrow[1].i = 10;
		skyl[0] = new Skyline(-35,144,128,256);
		skyl[0].that = 0;
		skyl[1] = new Skyline(80,12,128,388);
		skyl[1].that=1;
		skyl[2] = new Skyline(195,50,135,350);
		skyl[2].that=2;
		skyl[3] = new Skyline(315,120,168,280);
		skyl[3].that=3;
		skyl[4] = new Skyline(467,144,118,256);
		skyl[4].that=4;
		skyl[5] = new Skyline(575,70,100,330);
		skyl[5].that=5;
		
		
		bat[0] = new Brick(0,0,2000,0);
		bat[1] = new Brick(-5,200,5,200);
		bat[1].up = true;
		bat[2] = new Brick(0,400,100,brickWidth);
		bat[3] = new Brick(95,400,5,100);
		bat[3].up = true;
		bat[4] = new Brick(100,500,55,brickWidth);
		bat[5] = new Brick(150,400,5,100);
		bat[5].up = true;
		bat[6] = new Brick(150,400,150,brickWidth);
		bat[7] = new Brick(295,400,5,100);
		bat[7].up = true;
		bat[8] = new Brick(300,500,55,brickWidth);
		bat[9] = new Brick(350,400,5,100);
		bat[9].up = true;
		bat[10] = new Brick(350,400,150,brickWidth);
		bat[11] = new Brick(495,400,5,100);
		bat[11].up = true;
		bat[12] = new Brick(500,500,55,brickWidth);
		bat[13] = new Brick(550,400,5,100);
		bat[13].up = true;
		bat[14] = new Brick(550,400,150,brickWidth);
		bat[15] = new Brick(640,200,5,200);
		bat[15].up = true;
		batnum=16;
		box = new Box(20,200,26,26);
		box.type = 0;
		box1 = new Box(22,202,18,18);
		box1.type=1;
		
	}
	//initialize all variables for ABOUT
	private void initAbout(){
		keys = new ArrowKey[5];
		keys[0] = new ArrowKey(100,200,64,64);
		keys[0].type=0;
		keys[1] = new ArrowKey(426,200,64,64);
		keys[1].type=1;
		keys[2] = new ArrowKey(526,200,64,64);
		keys[2].type=2;
		keys[3] = new ArrowKey(288,400,64,64);
		keys[3].type=3;
		text[7] = new Text(70,330,512,48);
		text[7].type=7;
		
		
	}
	
	//initialze all variables for GAMEOVER
	private void initGameOver(){
		text[7] = new Text(70,330,512,48);
		text[7].type=7;
		keys[4] = new ArrowKey(188,230,256,48);
		keys[4].type=4;
	}
	
	//rendering function for the main menu
	private void renderMain(){
		translate_x=0;
		translate_y=0;
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		//int delta = getDelta();
		translate_x=0;
		translate_y=0;
		skimain.draw();
		
		for(int i=0;i<3;i++){
			clouds[i].draw();
			
			clouds[i].setX(clouds[i].getX()+1);
			if(clouds[i].getX()>800){
				clouds[i].setX(-140);
			}
		}
		
		skyl[0].draw();
		skyl[1].draw();
		skyl[2].draw();
		skyl[3].draw();
		skyl[4].draw();
		skyl[5].draw();
		
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
						initVars2();
						state = State.LEVEL2;
						//#####level2#####s
						//translate_y+=200;
						//translate_x=+770;
					}
					else{
						initAbout();
						state = State.ABOUT;
					}
						
					
					
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
		arrow[0].draw();
		arrow[1].draw();
		arrow[2].draw();
		
		
		bat[2].draw();
		bat[6].draw();
		bat[10].draw();
		bat[14].draw();
		
		
		
		box.draw();
		
	}
	//rendering function for LEVEL1
	private void render(){
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		skyi.draw();
		
		//Scrolling clouds
		for(int i=0;i<20;i++){
			clouds[i].draw();
			
			clouds[i].setX(clouds[i].getX()+dx);
			if(clouds[i].getX()>=3300){
				clouds[i].setX(-500);
			}
		}
		
		//changes the point at which the spike will stop
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
		
		
		//these 5 ifs are the logic for moving the door up and down
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
		
		//criterion for winning the game
		if(gem.contains(box)||box.intersects(gem)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			initGameOver();
			state = State.WIN;
		}
		
		
		//How jumping is handled
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					box.setY(box.getY()-4);
					box1.setY(box1.getY()-4);
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
					box1.setY(box1.getY()+4);
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
		
		//How falling is handled
		if(fall&&!jump&&!jumpvar){
			if(gravity){
				box.setY(box.getY()+4);
				box1.setY(box1.getY()+4);
				if(!onGround){
					translate_y-=4;
				
				}
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				box.setY(box.getY()-4);
				box1.setY(box1.getY()-4);
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
				doorj.upp = false;
		}
		
		
		
		
		//a cliff is a grass texture and this is how they are drawn and dealt with interactions
		for(int i=0; i<cliffnum;i++){
			if(box.on(cliff[i])&&!jump&&gravity){
				if(i ==0){
					if(box.on(cliff[i]))
						translate_y= -76;
				}
				else if(i==6)
				{}
				box.setY((cliff[i]).getY()-box.getHeight());
				box1.setY((cliff[i]).getY()-box1.getHeight()-bumper);
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				
				
			}
			else if(box.under(cliff[i])&&!jump&&!gravity){
				box.setY((cliff[i]).getY()+cliff[i].getHeight());
				box1.setY((cliff[i]).getY()+cliff[i].getHeight()+bumper);
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
		
		
		//a ice is a icy texture and this is how they are drawn and dealt with interactions
		for(int i=0; i<icenum;i++){
			if(box.on(ice[i])&&!jump&&gravity){
				box.setY((ice[i]).getY()-box.getHeight());
				box1.setY((ice[i]).getY()-box1.getHeight()-bumper);
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
			}
			else if(box.under(ice[i])&&!jump&&!gravity){
				box.setY((ice[i]).getY()+ice[i].getHeight());
				box1.setY((ice[i]).getY()+ice[i].getHeight()+bumper);
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
		
		
		//a dead is a fire texture and this is how they are drawn and dealt with interactions
		for(int i=0;i<deadnum;i++){
			if(box1.intersects(dead[i])){
				//translate_x=0;
				//translate_y=0;
				if(jump){
					fall = true;
					jump = false;
					jumpvar =false;
				}
				System.out.println(i);
				initGameOver();
				state = State.GAMEOVER;
				initVars();
			}
			dead[i].draw();
		}
		
		//a doorj is the spike, and i just copied the dying process from dead 
		if(box1.intersects(doorj)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			
			state = State.GAMEOVER;
			initVars();
		}
		
		// moving deads for the gauntlet type part of the level
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
		
		//moving for the falling gauntlet part of the level
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
		
				
		//a grav is a gravity texture and this is how they are drawn and dealt with interactions
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
		
		
		//a bit is a blip that spawns gravity flippers and they are monitored with blueFounds
		if(box.intersects(bit[0])||box.contains(bit[0])){
			blueFound = true;
		}
		if(box.intersects(bit[1])||box.contains(bit[1])){
			blueFound2 = true;
			
		}
		if(box.intersects(bit[2])||box.contains(bit[2])){
			blueFound3 = true;
			
		}
		
		//a gold is a coin texture and they are monitored with goldFounds
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
		
		//drawing of found and unfound coins
		if(!goldFound){
			gold[0].draw();
		}
		if(!goldFound2){
			gold[1].draw();
		}
		
		if(!goldFound3){
			gold[2].draw();
		}
		
		if(!goldFound4){
			gold[3].draw();
		}
		if(!goldFound5){
			gold[4].draw();
		}
		
			
	
		
		//a appear is a gravity flip that is spawned by a blip, this is how interactions are handled
		if(box.on(appear[0])&&blueFound){
			box.setY(appear[0].getY()-box.getHeight());
			box1.setY(appear[0].getY()-box1.getHeight()-bumper);
			gravity = !gravity;
			DIRlock = false;
			
			fall = true;
		}
		if(box.on(appear[1])&&blueFound2){
			box.setY(appear[1].getY()-box.getHeight());
			box1.setY(appear[1].getY()-box1.getHeight()-bumper);
			gravity = !gravity;
			DIRlock = false;
			
			fall = true;
		}
		if(box.on(appear[2])&&blueFound3){
			box.setY(appear[2].getY()-box.getHeight());
			box1.setY(appear[2].getY()-box1.getHeight()-bumper);
			gravity = !gravity;
			DIRlock = false;
			
			fall = true;
		}
		
		
		//this is how ice sliding is handled
		if(DIRlock){
			if(lastDIR ==1){
				box.setX(box.getX() -3);
				box1.setX(box1.getX() -3);
				translate_x +=3;
			}
			else if(lastDIR==2){
				box.setX(box.getX() +3);
				box1.setX(box.getX() +3);
				translate_x -=3;
			}
			//bounce off wall
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
		
		
		//this is the ledge attatched to the spike
		if(box.on(ledge)&&!jump){
			box.setY(ledge.getY()-box.getHeight());
			box1.setY(ledge.getY()-box1.getHeight()-bumper);
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
		box1.draw();
		box.draw();
		
		glPopMatrix();
		
	}
	
	
	
	//rendering function for LEVEL2
	private void render2(){
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		skyi.draw();
		
		
		
		//cloud movement
		for(int i=0;i<20;i++){
			clouds[i].draw();
			
			clouds[i].setX(clouds[i].getX()+dx);
			if(clouds[i].getX()>=5500){
				clouds[i].setX(-500);
			}
		}
		
		
		//same as level1
		if(goldCount==1){
			endHeight = -65;
			endHeight1=-1273;
		}
		else if(goldCount ==2){
			endHeight = -95;
			endHeight1=-1261;
		}
		else if(goldCount ==3){
			endHeight = -95;
			endHeight1 =-1249;
		}
		else if(goldCount ==4){
			endHeight = -95;
			endHeight=-1237;
		}
		else if(goldCount ==5){
			endHeight = -95;
			endHeight1=-1225;
		}
		
		
		//same as level1
		if(win&&doorj.getY()>endHeight&&ledgeFall){
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
		if(!doorj.upp&&doorj.getY()<-25&&!doorj.pause){
			doorj.setY(doorj.getY()+.7);
			ledge.setY(ledge.getY()-.7);
			rope.setHeight(rope.getHeight()-.7);
			translate_y +=.1;
			
			
		}
		
		if(win1&&doorj1.getY()<endHeight1&&ledgeFall1){
			doorj1.upp = true;
		}
		
		if(doorj1.upp){
			doorj1.setY(doorj1.getY()+.2);
			ledge1.setY(ledge1.getY()-.2);
			rope2.setY(rope2.getY()-.2);
			rope2.setHeight(rope2.getHeight()+.2);
			translate_y +=.22;
		
		}
		if(doorj1.getY()>=endHeight1){
			doorj1.upp = false;
			doorj1.pause = true;
		}
		if(!ledgeFall1){
			doorj1.pause =false;
		}
		if(!doorj1.upp&&doorj1.getY()>-1295&&!doorj1.pause){
			doorj1.setY(doorj1.getY()-.7);
			ledge1.setY(ledge1.getY()+.7);
			rope2.setY(rope2.getY()+.7);
			rope2.setHeight(rope2.getHeight()-.7);
			translate_y -=.1;
			
			
		}
		
		
		if(gem.contains(box)||box.intersects(gem)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			initGameOver();
			state = State.WIN;
		}
		
		
		
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					box.setY(box.getY()-4);
					box1.setY(box1.getY()-4);
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
					box1.setY(box1.getY()+4);
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
			doorj1.upp=false;
		}
		
		
		if(fall&&!jump&&!jumpvar){
			if(gravity){
				box.setY(box.getY()+4);
				box1.setY(box1.getY()+4);
				if(!onGround){
					translate_y-=4;
				
				}
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				box.setY(box.getY()-4);
				box1.setY(box1.getY()-4);
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
				if(ledgeFall1){
					ledgeFall1=false;
				}
				//doorj.upp = false;
		}
		
		
		//cliffs for level 2
		for(int i=0;i<21;i++){
			
			cliff[i].draw();
			if(box.on(cliff[i])&&!jump&&gravity){
				//hardcoded for spike camera issue
				if(i==9){
					translate_y=210;
				}
				box.setY((cliff[i]).getY()-box.getHeight());
				box1.setY((cliff[i]).getY()-box1.getHeight()-bumper);
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
			}
			else if(box.under(cliff[i])&&!jump&&!gravity){
				if(i==17){
					translate_y=1500;
				}
				box.setY((cliff[i]).getY()+cliff[i].getHeight());
				box1.setY((cliff[i]).getY()+cliff[i].getHeight()+bumper);
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
		
		//this is the elevator functionality
		if(box.on(cliff[8])){
			if(cliff[8].getY()>100){
				cliff[8].setY(cliff[8].getY()-1);
				translate_y+=1;
			}
			
		}
		else if(cliff[8].getY()<450){
			cliff[8].setY(cliff[8].getY()+1);
		}
		
		if(box.on(cliff[10])){
			if(cliff[10].getY()>260){
				cliff[10].setY(cliff[10].getY()-1);
				translate_y+=1;
			}
		}
		else if(cliff[10].getY()<680){
			cliff[10].setY(cliff[10].getY()+1);
		}
		
		
		
		//deads for level2
		for(int i =0;i<30;i++){

			if(box1.intersects(dead[i])){
				
				if(jump){
					fall = true;
					jump = false;
					jumpvar =false;
				}
				System.out.println(i);
				initGameOver();
				state = State.GAMEOVER;
				initVars2();
			}
			dead[i].draw();
		}
		
		
		//up and down deads in beginning of the level
		for(int i=2;i<4;i++){
			if(dead[i].getY()+dead[i].getHeight()>=520){
				dead[i].up=true;
			}
			
			if(dead[i].getY()<=100){
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
		
		
		
		if(box1.intersects(doorj)||box1.intersects(doorj1)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			initGameOver();
			state = State.GAMEOVER;
			initVars2();
		}
		
		
		//side ways deads on the roof
		for(int i=9;i<16;i++){
			if(dead[i].getX()>=2300){
				dead[i].up=true;
			}
			
			if(dead[i].getX()<=0){
				dead[i].up=false;
			}
			if(dead[i].up)
			{
				if(i%2 ==0)
					dead[i].setX(dead[i].getX()-.3);
				else if(i%3==0){
					dead[i].setX(dead[i].getX()-3.5);
				}
				else{
					dead[i].setX(dead[i].getX()-2.6);
				}
				
			}
			else{
				
				if(i%2 ==0)
					dead[i].setX(dead[i].getX()+.3);
				else if(i%3==0){
					dead[i].setX(dead[i].getX()+3.5);
				}
				else{
					dead[i].setX(dead[i].getX()+2.6);
				}
		
			dead[i].draw();
			}
		}	
		
		
		//ice for level2
		for(int i=0; i<9;i++){
			if(box.on(ice[i])&&!jump&&gravity){
				if(i!=0&&i!=2)
				{
					box.setY((ice[i]).getY()-box.getHeight());
					box1.setY((ice[i]).getY()-box1.getHeight()-bumper);
					fall = false;
					onGround=true;
					
				}
				DIRlock= true;
				
			}
			else if(box.under(ice[i])&&!jump&&!gravity){
				box.setY((ice[i]).getY()+ice[i].getHeight());
				box1.setY((ice[i]).getY()+ice[i].getHeight()+bumper);
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
		
		
		//gravs for level2
		for(int i=0;i<5;i++){
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
		
		
		//appears for level 2
		if(box.on(appear[0])&&blueFound){
			box.setY(appear[0].getY()-box.getHeight());
			box1.setY(appear[0].getY()-box1.getHeight()-bumper);
			gravity = !gravity;
			
			DIRlock=false;
			fall = true;
		}
		if(box.under(appear[1])&&blueFound2){
			box.setY(appear[1].getY()+appear[1].getHeight());
			box1.setY(appear[1].getY()+appear[1].getHeight()+bumper);
			gravity = !gravity;
			
			DIRlock=false;
			fall = true;
		}
		
		if((box.intersects(bit[0])||box.contains(bit[0]))){
			blueFound = true;
		}
		if((box.intersects(bit[1])||box.contains(bit[1]))){
			blueFound2 = true;
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
		
		
		//wall bouncing for beginning of level
		if(box.intersects(ice[0])||box.intersects(ice[2])){
			if(lastDIR==1){
				lastDIR=2;
				DIRlock=true;
				//wallhit=true;
			}
			else if(lastDIR==2){
				lastDIR=1;
				DIRlock=true;
				wallhit=true;
			}
			
		}
		
		
		
		//spike ledges
		if(box.on(ledge)&&!jump){
			box.setY(ledge.getY()-box.getHeight());
			box1.setY(ledge.getY()-box1.getHeight()-bumper);
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			win = true;
			ledgeFall = true;
			
		}
		if(box.under(ledge1)&&!jump){
			box.setY(ledge1.getY()+ledge1.getHeight());
			box1.setY(ledge1.getY()+ledge1.getHeight()+bumper);
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			win1 = true;
			ledgeFall1 = true;
			
			
		}
		
		if(DIRlock){
			if(lastDIR ==1){
				box.setX(box.getX() -3);
				box1.setX(box1.getX() -3);
				translate_x +=3;
			}
			else if(lastDIR==2){
				box.setX(box.getX() +3);
				box1.setX(box.getX() +3);
				translate_x -=3;
			}
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
		
		if(box1.intersects(window)&&!goldFound2){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			initGameOver();
			state = State.GAMEOVER;
			initVars2();
		}
		if(goldFound){
			
		}
		else{
			gold[0].draw();
		}
		if(goldFound2){
			
		}
		else{
			window.draw();
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
		
		
		//newspaper logic
		for(int i=0;i<newspaper.length;i++){
			if(newspaper[i].alive){
				double difference = newspaper[i].getX()-box.getX();
				double difference1 = newspaper[i].getY()-box.getY();
		
				if(difference1>=-40 && difference1<=40&&difference>=-200&&difference<=200){
					if(difference>0){
						newspaper[i].setX(newspaper[i].getX()-1.5);
					}
					else{
						newspaper[i].setX(newspaper[i].getX()+1.5);
				
					}
					if(difference1<0){
						newspaper[i].setY(newspaper[i].getY()+.6);
				
					}
					else{
						newspaper[i].setY(newspaper[i].getY()-.6);
					}	
			
				}
				newspaper[i].draw();
			}
			
			
			
			if(box.on(newspaper[i])&&newspaper[i].alive){
				jump = true;
				newspaper[i].alive=false;
			}
			else if(box.intersects(newspaper[i])&&newspaper[i].alive){
				System.out.println("dead");
				if(jump){
					fall = true;
					jump = false;
					jumpvar =false;
				}
				initGameOver();
				state = State.GAMEOVER;
				initVars2();
			}
		}
		
		
		
		
		
		
		hang1.draw();
		hang11.draw();
		wheel1.draw();
		wheel.draw();
		rope.draw();
		rope2.draw();
		rope3.draw();
		rope1.draw();
		doorj.draw();
		doorj1.draw();
		ledge.draw();
		ledge1.draw();
		gem.draw();
		box1.draw();
		box.draw();
		//box1.draw();
		glPopMatrix();
	}
	
	//rendering method for the intro level
	private void renderTrial(){
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		skyi.draw();
		
		
		
		
		for(int i=0;i<20;i++){
			clouds[i].draw();
			
			clouds[i].setX(clouds[i].getX()+dx);
			if(clouds[i].getX()>=3300){
				clouds[i].setX(-500);
			}
		}
		
		
		text[5].draw();
		text[6].draw();
		text[17].draw();
		text[18].draw();
		text[19].draw();
		text[20].draw();
		text[23].draw();
		for(int i=0;i<3;i++){
			keys[i].draw();
		}
		
		if(gem.contains(box)||box.intersects(gem)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			initGameOver();
			state = State.WIN;
		}
		
		
		if(goldCount==1){
			endHeight = 258;
		}
		else if(goldCount ==2){
			endHeight = 246;
		}
		else if(goldCount ==3){
			endHeight = 234;
		}
		else if(goldCount ==4){
			endHeight =222;
		}
		else if(goldCount ==5){
			endHeight =210;
			arrow[2].draw();
		}
		
		
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
		if(!doorj.upp&&doorj.getY()<280&&!doorj.pause){
			doorj.setY(doorj.getY()+.7);
			ledge.setY(ledge.getY()-.7);
			rope.setHeight(rope.getHeight()-.7);
			translate_y +=.1;
			
			
		}
		
		
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					box.setY(box.getY()-4);
					box1.setY(box1.getY()-4);
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
					box1.setY(box1.getY()+4);
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
				box1.setY(box1.getY()+4);
				if(!onGround){
					translate_y-=4;
				
				}
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				box.setY(box.getY()-4);
				box1.setY(box1.getY()-4);
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
		
		
		
		
		
		for(int i=0;i<4;i++){
			
			cliff[i].draw();
			if(box.on(cliff[i])&&!jump&&gravity){
				if(i ==0||i==1){
					if(box.on(cliff[i]))
						translate_y= -76;
				}
				
				box.setY((cliff[i]).getY()-box.getHeight());
				box1.setY((cliff[i]).getY()-box1.getHeight()-bumper);
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
			}
			else if(box.under(cliff[i])&&!jump&&!gravity){
				box.setY((cliff[i]).getY()+cliff[i].getHeight());
				box1.setY((cliff[i]).getY()+cliff[i].getHeight()+bumper);
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
		for(int i=0; i<5;i++){
			if(box.on(ice[i])&&!jump&&gravity){
				if(i==0||i==1)
				{
					box.setY((ice[i]).getY()-box.getHeight());
					box1.setY((ice[i]).getY()-box1.getHeight()-bumper);
					fall = false;
					onGround=true;
					
				}
				DIRlock= true;
				
			}
			else if(box.under(ice[i])&&!jump&&!gravity){
				box.setY((ice[i]).getY()+ice[i].getHeight());
				box1.setY((ice[i]).getY()+ice[i].getHeight()+bumper);
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
		for(int i =0;i<4;i++){
			if(box1.intersects(dead[i])){
				//translate_x=0;
				//translate_y=0;
				if(jump){
					fall = true;
					jump = false;
					jumpvar =false;
				}
				System.out.println(i);
				initGameOver();
				state = State.GAMEOVER;
				initTrial();
			}
			dead[i].draw();
		}
		
		if(box1.intersects(doorj)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
			initGameOver();
			state = State.GAMEOVER;
			initTrial();
		}
		
		for(int i=0;i<2;i++){
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
		
		
		if(DIRlock){
			if(lastDIR ==1){
				box.setX(box.getX() -3);
				box1.setX(box1.getX() -3);
				translate_x +=3;
			}
			else if(lastDIR==2){
				box.setX(box.getX() +3);
				box1.setX(box.getX() +3);
				translate_x -=3;
			}
			
			if(box.intersects(ice[2])||box.intersects(ice[3])){
				if(lastDIR==1){
					lastDIR=2;
					DIRlock=true;
					//wallhit=true;
				}
				else if(lastDIR==2){
					lastDIR=1;
					DIRlock=true;
					wallhit=true;
				}
				
			}
		}
		if((box.intersects(bit[0])||box.contains(bit[0]))&&wallhit){
			blueFound = true;
		}
		
		if(blueFound){
			appear[0].draw();
			
		}
		else if(!blueFound&&wallhit){
			bit[0].draw();
			arrow[0].draw();
		}
		
		if(box.under(appear[0])&&blueFound){
			box.setY(appear[0].getY()+appear[0].getHeight());
			box1.setY(appear[0].getY()+appear[0].getHeight()+bumper);
			gravity = !gravity;
			
			DIRlock=false;
			fall = true;
		}
		
		
		if(box.on(ledge)&&!jump){
			box.setY(ledge.getY()-box.getHeight());
			box1.setY(ledge.getY()-box1.getHeight()-bumper);
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			win = true;
			ledgeFall = true;
			
		}
		
		hang1.draw();
		wheel.draw();
		rope.draw();
		rope1.draw();
		doorj.draw();
		ledge.draw();
		gem.draw();
		
		
		
		
		if(win){
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
			text[21].draw();
			text[22].draw();
		}
		else{
			arrow[1].draw();
		}
		
		
		
		box1.draw();
		box.draw();
		glPopMatrix();
	}
	
	//drawing for about state
	private void drawAbout(){
		glEnable(GL_BLEND);
	    
		skimain= new Sky(0,0,700,700);
		skimain.draw();
		text[4].draw();
		text[5].draw();
		text[6].draw();
		text[7].draw();
		for(int i=0;i<4;i++){
			keys[i].draw();
		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
		
	}
	
	//drawing for welcome state
	private void drawWelcome(){
		skimain= new Sky(0,0,700,700);
		skimain.draw();
		text[0]= new Text(64,170,512,64);
		keys[1] = new ArrowKey(100,400,64,64);
		keys[1].type=1;
		keys[2] = new ArrowKey(486,400,64,64);
		keys[2].type=2;
		text[7] = new Text(300,330,400,48);
		text[7].type=7;
		glEnable(GL_BLEND);
		text[0].draw();
		text[14].draw();
		text[15].draw();
		text[16].draw();
		text[7].draw();
		keys[1].draw();
		keys[2].draw();
		
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_BLEND);
	}
	
	//drawing for wallpaper state
	private void drawWallpaper(){
		keys[4] = new ArrowKey(330,410,256,48);
		keys[4].type =4;
		wallp.draw();
		text[24].draw();
		if(spaceDraw){
			keys[4].draw();
		}
	}
	
	//drawing for winning state
	private void drawWin(){
		skimain= new Sky(0,0,700,700);
		skimain.draw();
		glEnable(GL_BLEND);
		text[13].draw();
		text[7].draw();
		keys[3].draw();
	   
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
	}
	
	//drawing for gameover state
	private void drawGameOver(){
		glEnable(GL_BLEND);
		skimain= new Sky(0,0,700,700);
		skimain.draw();
	    text[11].draw();
	    text[12].draw();
	    text[7].draw();
	    keys[3].draw();
	    keys[4].draw();
	    
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
	}
	
	//drawing for main state
	private void drawMain(){
		glEnable(GL_BLEND);
	    
		
		for(int i=0;i<4;i++){
			text[i].draw();
		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);	
	}
	
	//drawing for level select state
	private void drawLevel(){
		glEnable(GL_BLEND);
		
		text[8].draw();
		text[9].draw();
		text[10].draw();
		text[0].draw();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND);
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
	
	
	//loading textures
	public Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("png",new FileInputStream(new File("res/" + key + ".png")));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}