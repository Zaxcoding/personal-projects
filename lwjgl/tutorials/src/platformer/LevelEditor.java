package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;
import java.awt.Font;
import java.io.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;

import platformer.Box;
import platformer.DisappearingBox;
import platformer.MovingPlatform;
import platformer.Shape;

public class LevelEditor 
{
	public static final int WIDTH = 1280;			// width of the level editor screen
	public static final int HEIGHT = 800;			// height of the level editor screen
	public static final int SCROLL_SPEED = 10;		// the camera scroll speed
	public static final int THICKNESS = 2;			// the thickness you adjust by
	public static final int FONT_SIZE = 25;			// font size of the x,y coords.
		
	float translate_x = 0, translate_y = 0;
	float mousex, mousey;
	float startX, startY;
	
	int width = 75;
	int height = 10;
	
	private String currShape = "Box";
	
	private Shape selected;
	
	private List<Shape> shapes = new ArrayList<Shape>(20);
	
	UnicodeFont uniFont;
	
	public LevelEditor() 
	{	
		
		initGL();
		initFonts();

		// Enter the main render loop
		while (!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT);
			glPushMatrix();
			
			glTranslatef(translate_x, 0, 0);
			glTranslatef(0, translate_y, 0);

			input();
			mouse();
			render();

			glPopMatrix();

			// Make sure the display stays responsive and wait until we reach 60fps.
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}
	
	public void mouse()
	{
		// Retrieve the "true" coordinates of the mouse.
		mousex = Mouse.getX() - translate_x;
		mousey = HEIGHT - Mouse.getY() - 1 - translate_y;
					
		if (Mouse.isButtonDown(0))
		{
			if (currShape == "Box")
				shapes.add(new Box(mousex, mousey, width, height));			
			else if (currShape == "Disappearing Box")
				shapes.add(new DisappearingBox(mousex, mousey, width, height));
			else if (currShape == "Start Position")
			{
				startY = mousey;
				startX = mousex;
			}
			else if (currShape == "Moving Platform")
			{
				MovingPlatform temp = new MovingPlatform(mousex, mousey, width, height);
				System.out.println("Move up/down (1) or left/right (0): ");
				Scanner inScan = new Scanner(System.in);
				int tempUpDown = inScan.nextInt();
				boolean upDown;
				if (tempUpDown == 1)
					upDown = true;
				else
					upDown = false;
				System.out.println("End position (either bottom or right): ");
				int end = inScan.nextInt();
				System.out.println("Speed (low integer values): ");
				int speed = inScan.nextInt();
				temp.setMovement(upDown, (int)mousex, end, 1, speed);
				shapes.add(temp);
			}
		}
		if (Mouse.isButtonDown(1))
		{
			
		}
	}
	
	public void render()
	{
		// just so you can see your current box
		Shape temp = new Box(0,0,0,0);
		
		if (currShape == "Box")
			temp = new Box(mousex, mousey, width, height);
		else if (currShape == "Disappearing Box")
			temp = new DisappearingBox(mousex, mousey, width, height);
		else if (currShape == "Moving Platform")
			temp = new MovingPlatform(mousex, mousey, width, height);
		
		temp.draw();
					
		glEnable(GL_BLEND);
		            
		uniFont.drawString(-translate_x, -translate_y, "Mouse: " + Mouse.getX() + ","+(HEIGHT-Mouse.getY()-1));
		uniFont.drawString(-translate_x, -translate_y + FONT_SIZE + 5, "Absolute: " + -translate_x + "," + translate_y);
		if (startX != 0 && startY != 0)
			uniFont.drawString(startX, startY, ">");
		uniFont.drawString(-translate_x, HEIGHT - translate_y - FONT_SIZE - 5, "Shape: " + currShape);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//EDIT.. glDisable texture is required here.
		        
		glDisable(GL_BLEND); 
					
		// draw the placed boxes
		for (Shape shape : shapes)
			shape.draw();
	}
	
	public void input()
	{
		if ((Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)
				|| Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA))
				&& Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			Scanner inScan = new Scanner(System.in);
			System.out.println("Enter the desired filename please: ");
			save(shapes, inScan.next());
		}
		else if ((Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)
				|| Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA))
				&& Keyboard.isKeyDown(Keyboard.KEY_O))
		{
			Scanner inScan = new Scanner(System.in);
			System.out.println("Enter the filename to load please: ");
			load(shapes, inScan.next());
			
			// reset the camera
			translate_x = 0;
			translate_y = 0;
		}
		
		// WASD to move the camera
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) 
			translate_y += SCROLL_SPEED;
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) 
			translate_y -= SCROLL_SPEED;
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
			translate_x += SCROLL_SPEED;
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) 
			translate_x -= SCROLL_SPEED;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_1))
			currShape = "Box";
		if (Keyboard.isKeyDown(Keyboard.KEY_2))
			currShape = "Disappearing Box";
		if (Keyboard.isKeyDown(Keyboard.KEY_3))
			currShape = "Start Position";
		if (Keyboard.isKeyDown(Keyboard.KEY_4))
			currShape = "Moving Platform";
		
		
		// IJKL to adjust height/width
		if (Keyboard.isKeyDown(Keyboard.KEY_I) && (height - THICKNESS) >= 1) 
			height -= THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_K)) 
			height += THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_L) && (width - THICKNESS) >= 3) 
			width += THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_J)) 
			width -= THICKNESS;
	}
	
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Level Editor");
			Display.create();
		} catch (LWJGLException e) 
		{	e.printStackTrace();	}

		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (1024, 600) is the bottom right one.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void save(List<Shape> shapes, String filename)
	{
		try
		{
			ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(filename));
			OS.writeInt(shapes.size());
			for (Shape shape : shapes)
			{
				shape.save(OS);
			}
			OS.writeFloat(startX);
			OS.writeFloat(startY);
			
			OS.close();
			System.out.println("Saved!");
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
	
	public void load(List<Shape> shapes, String filename)
	{
		try
		{
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(filename));
			int size = IS.readInt();
			for (int i = 0; i < size; i++)
			{
				int code = IS.readInt();
				Shape temp = Shape.load(IS, code);
				shapes.add(temp);
			}
			startX = IS.readFloat();
			startY = IS.readFloat();
			translate_x = 0;
			translate_y = 0;
			
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
       
        uniFont = new UnicodeFont(awtFont, FONT_SIZE, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};


    }
	
	public static void main(String[] args) {
		new LevelEditor();
	}

}