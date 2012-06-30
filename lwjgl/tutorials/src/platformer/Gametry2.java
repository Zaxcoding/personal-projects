package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.*;
import java.util.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Gametry2
{	
	public static final int WIDTH = 1280;			// width of the level editor screen
	public static final int HEIGHT = 800;			// height of the level editor screen
	public static final int SCROLL_SPEED = 10;		// the camera scroll speed
	public static final int THICKNESS = 2;			// the thickness you adjust by
	public static final int FONT_SIZE = 25;			// font size of the x,y coords.
	public static final int MOVEMENT_AMOUNT = 5;	// for moving left, right, and translating
	public static final double ACCEL = .0022;			// for gravity
	public static final double INIT_VELOCITY = -.83;	// for jumping
	public static final double TERMINAL_VELOCITY = 200;	// for falling
	
	private double hangTime = 0, currTime = 0, gravity = 0, velocity = -.1;
	private float translateX = 50, translateY = 0;
	private float startX, startY;
	private int width = 25, height = 25, gravityMod = 1;	// gravityMod = 1 for normal, -1 for reverse
	private long lastFrame;
	
	private boolean jumping = false;
	
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont;
	
	private Player player;
	
	public Gametry2()
	{		
		loadLevel();
	//	initFonts();
		initGL();
		
		currTime = getTime();
		
		while (!Display.isCloseRequested())
		{
					
			glClear(GL_COLOR_BUFFER_BIT);
			glPushMatrix();
					
			glTranslatef(translateX, 0, 0);
			glTranslatef(0, translateY, 0);

			input();
			gravity();
			update();
			render();

			glPopMatrix();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}	
	
	public void input()
	{
		// W or up arrow to jump
		if ((Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) && onGround())  
			jump(player);
		// move the player and the camera left
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			moveLeft(player);
		// move the player and the camera right
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			moveRight(player);
		// reset the player's position, the camera, and the gravity
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			restart();
	}
	
	public void gravity()
	{		
		// sometimes things get crazy and this keeps it in check
		if (player.y < -7E7 || player.y > 7E7)
			player.y = startY;	
		
		if (!jumping && onGround())
			currTime = getTime();
		
		if (onGround())
		{
			velocity = 0;
			gravity = 0;
			hangTime = 0;
			getDelta();
		}
		else
		{
			gravity = ACCEL;
			hangTime = getTime() - currTime;
		}
		if (jumping)
		{
			velocity = INIT_VELOCITY;
			gravity = ACCEL;
			hangTime = getTime() - currTime;
		}
		if (hangTime >= 360)
			jumping = false;
		
		if (velocity > TERMINAL_VELOCITY)
			velocity = TERMINAL_VELOCITY;
				
		player.setDY(velocity + gravity*hangTime*gravityMod);
		player.update(getDelta());
		translateY = (float) (HEIGHT/2 - player.y);
	}
	
	public boolean onGround()
	{
		double left = player.x + player.width/2;
		double right = player.x;
		double bottom = player.y + player.height;
						
		for (Shape shape : shapes)
		{
			// by default, make the value you look for the shape's height
			double delta = shape.height;
			// this helps with landing from great heights onto thin strips
			
			if (20 >= shape.height)
				delta = 20;
			if ((shape.x <= left && (shape.x + shape.width >= right)) &&
					((-delta <= bottom - shape.y - shape.height) && (bottom - shape.y - shape.height <= 5))
					&& !shape.user && !jumping)
			{
				player.y = shape.y - player.height;
				shape.interact();
				return true;
			}
		}
		return false;
	}
	
	public void restart()
	{
		player.x = startX;
		player.y = startY + player.height;
		jumping = false;
		translateX = 0;
		translateY = 0;
		velocity = 0;
		gravity = 0;
	}
	
	public void jump(Shape player)
	{
		currTime = getTime();
		jumping = true;
	}
	
	public void moveLeft(Shape player)
	{
		translateX += MOVEMENT_AMOUNT;
		player.x -= MOVEMENT_AMOUNT;
	}
	
	public void moveRight(Shape player)
	{
		translateX -= MOVEMENT_AMOUNT;
		player.x += MOVEMENT_AMOUNT;
	}
	
	public void render()
	{					
		// draw the placed boxes
		for (Shape shape : shapes)
			if (shape.isVisible())
				shape.draw();
	}
	
	public void update()
	{
		// remove the Shapes that say removeMe
		Shape temp = new Box(0,0,0,0);
		for (Shape shape : shapes)
			if (shape.removeMe)
			temp = shape;
		if (temp.removeMe)
			shapes.remove(temp);
	}
		
	private long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private int getDelta()
	{
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Game!");
			Display.create();
		} catch (LWJGLException e) 
		{	e.printStackTrace();	}

		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (WIDTH, HEIGHT) is the bottom right one.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void loadLevel()
	{
		System.out.println("Which level would you like to play?");
		Scanner inScan = new Scanner(System.in);
		String filename = inScan.next();
		System.out.println("Loading...");
		
		load(shapes, filename);	
		shapes.add(player);
	}
	
	public void load(List<Shape> shapes, String filename)
	{
		try
		{
			// clear the array
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(filename));
			int size = IS.readInt();
			
			Shape temp = new Box(0,0,0,0);
			for (int i = 0; i < size; i++)
			{
				int code = IS.readInt();
				if (code == 1)
					temp = new Box(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
				if (code == 2)
					temp = new DisappearingBox(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
				shapes.add(temp);
			}
			// the last two floats in the file are the start position
			startX = IS.readFloat();
			startY = IS.readFloat();
			
			player = new Player(startX, startY, width, height);
			
			IS.close();
			System.out.println("Loaded!");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initFonts() {

        Font awtFont = new Font("", Font.PLAIN,55);
        
        uniFont = new UnicodeFont(awtFont, 45, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};
    }
	
	public static void main(String [] args)
	{
		new Gametry2();
	}
	
}
