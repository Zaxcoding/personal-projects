package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;
import java.awt.Font;
import java.io.*;

import javax.swing.JOptionPane;

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
	
	private String currShape = "Box", inputValue = "";
	
	private Shape selected;
	
	private List<Shape> shapes = new ArrayList<Shape>(20);
	
	UnicodeFont uniFont;
	
	private boolean needDest = false;
	private Teleporter tempTeleporter;
	
	private boolean mouseLockX = false, mouseLockY = false;
	
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
		if (!mouseLockX)
			mousex = Mouse.getX() - translate_x;
		if (!mouseLockY)
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
				inputValue = JOptionPane.showInputDialog("Move up/down (1) or left/right (0)?"); 
				if (inputValue != null  && !inputValue.equals(""))
				{
					boolean upDown;
					int start;
					if (inputValue.equals("1"))
					{
						upDown = true;
						start = (int) mousey;
					}
					else
					{
						upDown = false;
						start = (int) mousex;
					}
					inputValue = JOptionPane.showInputDialog("Your start position is " + start + ". Enter an end position.");
					if (inputValue != null  && !inputValue.equals(""))
					{
						int end = Integer.parseInt(inputValue);
						inputValue = JOptionPane.showInputDialog("Speed? (typically pick a low integer value like 1-10)");
						if (inputValue != null  && !inputValue.equals(""))
						{
						int speed = Integer.parseInt(inputValue);
						temp.setMovement(upDown, start, end, 1, speed);
						shapes.add(temp);
						}
					}
				}
			}
			else if (currShape == "Teleporter" && !needDest)
			{
				tempTeleporter = new Teleporter(mousex, mousey, width, height);
				shapes.add(tempTeleporter);
				JOptionPane.showMessageDialog(null, "Left click again to place paired teleporter.");
				needDest = true;
				System.out.println("Placed");
			}
			else if (currShape == "Teleporter" && needDest)
			{
				tempTeleporter.setDestination(mousex + width/2, mousey);
				double tempX = tempTeleporter.x + tempTeleporter.width/2;
				double tempY = tempTeleporter.y;
				tempTeleporter = new Teleporter(mousex, mousey, width, height);
				tempTeleporter.setDestination(tempX, tempY);
				shapes.add(tempTeleporter);
				needDest = false;
				fixMouse();
			}
			else if (currShape == "Checkpoint")
				shapes.add(new Checkpoint(mousex, mousey, width, height));
			else if (currShape == "Trampoline")
				shapes.add(new Trampoline(mousex, mousey, width, height));
			else if (currShape == "Death Stick")
				shapes.add(new DeathStick(mousex, mousey, width, height));
			else if (currShape == "Ice")
				shapes.add(new Ice(mousex, mousey, width, height));
			else if (currShape == "Coin")
				shapes.add(new Coin(mousex, mousey, width, height));
			
		}
		if (Mouse.isButtonDown(1))
		{
			Shape temp = getShape();
			if (temp != null)
			{
				selected = temp;
			}
		}
		
	}
	
	public Shape getShape()
	{
		for (Shape shape: shapes)
		{
			if (mousex >= shape.x && (mousex <= shape.x + shape.width)
					&& mousey >= shape.y && (mousey <= shape.y + shape.height))
			{
				selected = shape;
				shape.selected = true;
			}
			else
			{
				shape.selected = false;
			}
		}
		return selected;
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
		else if (currShape == "Teleporter")
			temp = new Teleporter(mousex, mousey, width, height);
		else if (currShape == "Checkpoint")
			temp = new Checkpoint(mousex, mousey, width, height);
		else if (currShape == "Trampoline")
			temp = new Trampoline(mousex, mousey, width, height);
		else if (currShape == "Death Stick")
			temp = new DeathStick(mousex, mousey, width, height);
		else if (currShape == "Ice")
			temp = new Ice(mousex, mousey, width, height);
		else if (currShape == "Coin")
			temp = new Coin(mousex, mousey, width, height);
		
		temp.draw();
					
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
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
			fixKeyboard();
			save(shapes);
		}
		else if ((Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)
				|| Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA))
				&& Keyboard.isKeyDown(Keyboard.KEY_O))
		{
			fixKeyboard();
			load(shapes);
			
			// reset the camera
			translate_x = 0;
			translate_y = 0;
		}
		else{
		
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
		if (Keyboard.isKeyDown(Keyboard.KEY_5))
			currShape = "Teleporter";
		if (Keyboard.isKeyDown(Keyboard.KEY_6))
			currShape = "Checkpoint";
		if (Keyboard.isKeyDown(Keyboard.KEY_7))
			currShape = "Trampoline";
		if (Keyboard.isKeyDown(Keyboard.KEY_8))
			currShape = "Death Stick";
		if (Keyboard.isKeyDown(Keyboard.KEY_9))
			currShape = "Ice";
		if (Keyboard.isKeyDown(Keyboard.KEY_0))
			currShape = "Coin";
		
		
		// IJKL to adjust height/width
		if (Keyboard.isKeyDown(Keyboard.KEY_I) && (height - THICKNESS) >= 1) 
			height -= THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_K)) 
			height += THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_L)) 
			width += THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_J) && (width - THICKNESS) >= 3) 
			width -= THICKNESS;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_C) && selected != null)
		{
			currShape = selected.name;
			width = (int) selected.width;
			height = (int) selected.height;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DELETE) || Keyboard.isKeyDown(Keyboard.KEY_BACK))
			delete();
		
		// for making it easier to place blocks in a straight line
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			mouseLockY = true;
		else
			mouseLockY = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
			mouseLockX = true;
		else
			mouseLockX = false;		
		}	// from the else way up
	}
	
	public void delete()
	{
		// for some reason you have to do it like this,
		// you can't just shapes.remove(selected)
		Shape temp = new Box(0,0,0,0);
		for (Shape shape : shapes)
			if (shape.selected)
				temp = shape;
		
		if (temp.selected)
			shapes.remove(temp);
		
		selected = null;
	}
	
	public void fixMouse()
	{
		Mouse.destroy();
		try
		{
			Mouse.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void fixKeyboard()
	{
		Keyboard.destroy();
		try
		{
			Keyboard.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Level Editor");
			Display.create();
		} catch (LWJGLException e) 
		{
			e.printStackTrace();	
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void save(List<Shape> shapes)
	{
		inputValue = JOptionPane.showInputDialog("Enter the desired filename please: ");
		if (inputValue != null && !inputValue.equals(""))
		{
		try
		{
			ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(inputValue));
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
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		}
	}
	
	public void load(List<Shape> shapes)
	{
		inputValue = JOptionPane.showInputDialog("Enter the filename to load please: ");
		if (inputValue != null && !inputValue.equals(""))
		{
		try
		{
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(inputValue));
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
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
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