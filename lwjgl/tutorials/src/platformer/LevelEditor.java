package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;
import java.io.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LevelEditor 
{
	public static final int WIDTH = 1280;			// width of the level editor screen
	public static final int HEIGHT = 800;			// height of the level editor screen
	public static final int SCROLL_SPEED = 10;		// the camera scroll speed
	public static final int THICKNESS = 2;			// the thickness you adjust by
		
	private List<Box> shapes = new ArrayList<Box>(20);
	
	public LevelEditor() 
	{	
		int width = 75;
		int height = 10;
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

		// For the sidescrolling
		float translate_x = 0;
		float translate_y = 0;
		
		// Enter the main render loop
		while (!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT);
			glPushMatrix();
			
			// sidescroll
			glTranslatef(translate_x, 0, 0);
			glTranslatef(0, translate_y, 0);

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
			
			// IJKL to adjust height/width
			if (Keyboard.isKeyDown(Keyboard.KEY_I) && (height - THICKNESS) >= 1) 
				height -= THICKNESS;
			if (Keyboard.isKeyDown(Keyboard.KEY_K)) 
				height += THICKNESS;
			if (Keyboard.isKeyDown(Keyboard.KEY_L) && (width - THICKNESS) >= 3) 
				width += THICKNESS;
			if (Keyboard.isKeyDown(Keyboard.KEY_J)) 
				width -= THICKNESS;
			
			// Retrieve the "true" coordinates of the mouse.
			float mousex = Mouse.getX() - translate_x;
			float mousey = HEIGHT - Mouse.getY() - 1 - translate_y;
			
			if (Mouse.isButtonDown(0))
				shapes.add(new Box(mousex, mousey, width, height));
			
			// just so you can see your current box
			Box temp = new Box(mousex, mousey, width, height);
			temp.draw();
			
			// draw the placed boxes
			for (Box box : shapes)
				box.draw();

			// Dispose of the translations on the matrix.
			glPopMatrix();

			// Make sure the display stays responsive and wait until we reach 60fps.
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}
	
	public void save(List<Box> shapes, String filename)
	{
		try
		{
			ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(filename));
			OS.writeInt(shapes.size());
			for (Box box : shapes)
			{
				OS.writeDouble(box.getX());
				OS.writeDouble(box.getY());
				OS.writeDouble(box.getWidth());
				OS.writeDouble(box.getHeight());
			}
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
	
	public void load(List<Box> shapes, String filename)
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

	public static void main(String[] args) {
		new LevelEditor();
	}

}