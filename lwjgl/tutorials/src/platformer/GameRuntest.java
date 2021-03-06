package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class GameRuntest
{	
	public static final int WIDTH = 1280;			// width of the level editor screen
	public static final int HEIGHT = 800;			// height of the level editor screen
	public static final int SCROLL_SPEED = 10;		// the camera scroll speed
	public static final int THICKNESS = 2;			// the thickness you adjust by
	public static final int FONT_SIZE = 25;			// font size of the x,y coords.
	public static  int MOVEMENT_AMOUNT = 5;	// for moving left, right, and translating
	public static final int COLLISION_HELP_2 = 15;	// for help landing from great heights
	public static final double ACCEL = .0022;			// for gravity
	public static final double TERMINAL_VELOCITY = 200;	// for falling
	public static boolean WALLJUMPING = false;		// enable/disable walljumping
	
	private double hangTime = 0, currTime = 0, gravity = 0;
	private float translateX = 50, translateY = 0;
	private float startX, startY;
	private int width = 25, height = 25, gravityMod = 1;	// gravityMod = 1 for normal, -1 for reverse
	private long lastFrame;
	
	private double cameraL, cameraR, cameraU, cameraD;
		
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont;
	
	private Player player;
	
	boolean grounded;
	
	public GameRuntest()
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
			grounded = onGround();
			gravity();
			
			
			update();
			everybodyDoYourThing();
			render();
						
			glPopMatrix();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}	
	
	public void everybodyDoYourThing()
	{
		for (Shape shape: shapes)
			shape.doYourThing();
	}
	
	public void input()
	{
		// W or up arrow to jump
		if ((Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) && grounded)
		{
			player.jump();
			currTime = getTime();
		}
		// move the player and the camera left
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			moveLeft(player);
		// move the player and the camera right
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			moveRight(player);
		// reset the player's position, the camera, and the gravity
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			restart();
		if (Keyboard.isKeyDown(Keyboard.KEY_J))
			run(player);
		if (!Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT) 
				&& !Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			player.direction = 0;
	}
	
	public void run(Player player)
	{
		player.running = true;
	}
	
	public void gravity()
	{		
		if (!player.jumping && grounded)
			currTime = getTime();
		
		if (grounded)
		{
			player.velocity = 0;
			gravity = 0;
			hangTime = 0;
			getDelta();
			player.dy = 0;
			if (player.onTramp)
			{
				player.jump();
				player.onTramp = false;
			}
		}
		else		
		{
			player.groundPiece = null;
			gravity = ACCEL;
			hangTime = getTime() - currTime;
		}
		if (hangTime >= 360)
			player.jumping = false;
				
		player.setDY(player.velocity + gravity*hangTime*gravityMod);
		player.update(getDelta());
		
		translateY = (float) (HEIGHT/2 - player.y);
		translateX = (float) (WIDTH/2 - player.x);
	}
	
	public boolean onGround()
	{
		// if we're already on the ground, then just check that we're still on top of the groundPiece
		// note the extra 3/4 width cushion for the player (to make it more fun)
		if (player.groundPiece != null)
		{
			if ((player.groundPiece.x - player.width < player.x && 
					(player.groundPiece.x + player.groundPiece.width > player.x)))
			{
				player.groundPiece.interact(player);
				return true;
			}
		}
		
		// if we're not on the ground, time to see if we're on the ground somewhere else
		for (Shape shape : shapes)
		{				
			if ((shape.x < player.x  && 
				(shape.x + shape.width > player.x )) &&	
					(shape.y + shape.height + COLLISION_HELP_2 > player.y + player.height) &&
						 (player.y + player.height > shape.y - 1) &&
						 	!shape.user && !player.jumping)
			{	
					player.y = shape.y - player.height;
					shape.interact(player);
					player.groundPiece = shape;
					return true;
			}
		}
		return false;
	}
	
	public void restart()
	{
		player.x = startX;
		player.y = startY + player.height;
		player.dy = 0;
		player.jumping = false;
		translateX = 0;
		translateY = 0;
		player.velocity = 0;
		gravity = 0;
		hangTime = 0;
	}
	
	public void moveLeft(Player player)
	{
		if (!player.onIce)
			player.x -= MOVEMENT_AMOUNT;
		if (player.direction != -1)
			MOVEMENT_AMOUNT = 2;
		player.direction = -1;
		
	}
	
	public void moveRight(Player player)
	{
		if (!player.onIce)
			player.x += MOVEMENT_AMOUNT;
		if (player.direction != 1)
			MOVEMENT_AMOUNT = 2;
		player.direction = 1;
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
		{
			if (shape.removeMe)
				temp = shape;
			if (shape.name.equals("Checkpoint") && shape != player.activeCheckpoint)
			{
				Checkpoint temp2 = (Checkpoint) shape;
				temp2.active = false;
			}
			shape.collision(player);
		}
		if (temp.removeMe)
			shapes.remove(temp);
		if (player.y > 5000)
		{
			player.alive = false;
			player.velocity = 0;
			gravity = 0;
			hangTime = 0;
			currTime = getTime();
		}		
		if (player.direction != 0)
		{
			if (MOVEMENT_AMOUNT < 15 || (player.running && MOVEMENT_AMOUNT < 30))
				MOVEMENT_AMOUNT += 1;
		}
		else
		{
			if (MOVEMENT_AMOUNT >= 1)
				MOVEMENT_AMOUNT -= 1;
		}
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
		String filename = JOptionPane.showInputDialog("Which level would you like to play?");

		System.out.println("Loading...");
		
		load(shapes, filename);	
		shapes.add(player);
	}
	
	public void load(List<Shape> shapes, String filename)
	{
		if (filename == null)
			System.exit(0);
		try
		{
			// clear the array
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(filename));
			int size = IS.readInt();
			
			for (int i = 0; i < size; i++)
			{
				int code = IS.readInt();
				Shape temp = Shape.load(IS, code);
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
			System.out.println("Didn't find that level.");
			loadLevel();
		} catch (IOException e)
		{
			System.out.println(e);
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
		new GameRuntest();
	}
	
}
