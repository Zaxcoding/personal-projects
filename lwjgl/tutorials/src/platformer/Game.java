package platformer;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import platformer.Box;
import platformer.Shape;

public class Game
{	
	public static final int WIDTH = 1280;			// width of the level editor screen
	public static final int HEIGHT = 800;			// height of the level editor screen
	public static final int SCROLL_SPEED = 10;		// the camera scroll speed
	public static final int THICKNESS = 2;			// the thickness you adjust by
	public static final int FONT_SIZE = 25;			// font size of the x,y coords.
	public static final double ACCEL = .0022;			// for gravity
	public static final double INIT_VELOCITY = -.83;	// for jumping

	private float translate_x = 0, translate_y = 0;
	private int width = 25, height = 25;
	
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont;
	
	private Shape player = new Box(100, 250, width, height);
	
	private double hangTime = 0, currTime = 0;;
	
	private long lastFrame;
	
	private double gravity = 0; 
	
	private boolean firstJump = true;
		
	public Game()
	{
		player.user = true;
		
		loadLevel();
	//	initFonts();
		initGL();
		
		// Enter the main render loop
				while (!Display.isCloseRequested())
				{
					
					glClear(GL_COLOR_BUFFER_BIT);
					glPushMatrix();
					
					glTranslatef(translate_x, 0, 0);
					glTranslatef(0, translate_y, 0);

					input();
				//	gravity();
				//	onGround();
					render();

					glPopMatrix();

					// Make sure the display stays responsive and wait until we reach 60fps.
					Display.update();
					Display.sync(60);
				}

				Display.destroy();
				System.exit(0);
	}
		
		
	
	public void input()
	{
	
		// WASD to move the camera
		if ((Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) && (onGround() || firstJump))  
			jump(player);
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			player.x -= 5;
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			player.x += 5;
	}
	
	public void gravity()
	{
		if (onGround())
			gravity = 0;
		else
			gravity = ACCEL;
		
		player.dy += gravity;
		player.update(getDelta());
		
		System.out.println(player.y);
	}
	
	public boolean onGround()
	{
		double left = player.x + player.width/2;
		double right = player.x - player.width/2;
		double bottom = player.y + player.height;
		
		boolean ans = false;
		
		for (Shape shape : shapes)
		{
			if ((shape.x <= left && (shape.x + shape.width >= right)) &&
					((-20 <= bottom - shape.y - shape.height) && (bottom - shape.y - shape.height <= 5))
					&& shape.user == false)
			{
				player.y = shape.y - player.height;
				System.out.println("BREAK!!!!");
				System.out.println("HIT! - " + left + "  " + right + "  " + bottom);
				System.out.println("\t" + shape.x + "  " + (shape.x + shape.width) + "  " + (shape.y + shape.height));
				firstJump = false;
				currTime = 0;
				return true;
			}
				
		}
		System.out.println( "" + left + "  " + right + "  " + bottom);

		return ans;
	}
	
	public void jump(Shape player)
	{
		player.setDY(INIT_VELOCITY);
		
		if (currTime == 0)
		{
			currTime = getTime();
			hangTime = 0;
			getDelta();
		}
		else
		{
			hangTime = getTime() - currTime;
			player.setDY(INIT_VELOCITY + ACCEL*hangTime);
			player.update(getDelta());
			System.out.println("ZZZZZ" + player.dy);
		}
		
	}
	
	public void moveLeft(Shape player)
	{
		System.out.println("left");
	}
	
	public void moveRight(Shape player)
	{
		System.out.println("right");
	}
	
	public void render()
	{				
		// draw the placed boxes
		for (Shape box : shapes)
			box.draw();
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

		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (1024, 600) is the bottom right one.
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
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(filename));
			int size = IS.readInt();
			for (int i = 0; i < size; i++)
			{
				Box temp = new Box(IS.readDouble(), IS.readDouble(), IS.readDouble(), IS.readDouble());
				shapes.add(temp);
			}
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
		new Game();
	}
	
	
	
}
