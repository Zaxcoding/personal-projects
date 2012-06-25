// c for new box, d for color change, click and right click


import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureDemo {

	private List<Box> shapes = new ArrayList<Box>(16);
	private boolean somethingIsSelected = false;
	private volatile boolean randomColorCooldown = false;
	Texture wood;
	
	public TextureDemo()
	{
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Texture Demo");
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		wood = loadTexture("grass");
		
		
		shapes.add(new Box(15, 15));
		shapes.add(new Box(100, 150));
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	
		while (!Display.isCloseRequested())
		{
			// Render
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			wood.bind();
			
			while (Keyboard.next())
			{
				if (Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState())
				{
					shapes.add(new Box(30, 30));
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_D && Keyboard.getEventKeyState())
				{
					for (Box box : shapes)
					{
						box.randomizeColors();
					}
				}
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				Display.destroy();
				System.exit(0);
			}
			
			for (Box box : shapes)
			{
				if (Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(), 480 - Mouse.getY() - 1) && !somethingIsSelected)
				{
					somethingIsSelected = true;
					box.selected = true;
				}
				if (Mouse.isButtonDown(1))
				{
					somethingIsSelected = false;
					box.selected = false;
				}
				if (box.selected)
				{
					box.update(Mouse.getDX(), -Mouse.getDY());
				}
				box.draw();
			}
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	private Texture loadTexture(String key)
	{
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + key + ".png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static class Box
	{
		public int x, y;
		public boolean selected = false;
		private float colorRed, colorBlue, colorGreen;
		
		Box(int x, int y)
		{
			this.x = x;
			this.y = y;
			randomizeColors();
		}
		
		boolean inBounds(int mousex, int mousey)
		{
			if (mousex > x && mousex < x + 50
				&& mousey > y && mousey < y + 50)
				return true;
			else
				return false;
		}
		
		void update(int dx, int dy)
		{
			x += dx;
			y += dy;
		}
		
		void randomizeColors()
		{
			Random rng = new Random();
			colorRed = rng.nextFloat();
			colorBlue = rng.nextFloat();
			colorGreen = rng.nextFloat();	
		}
		
		void draw()
		{
			glColor3f(colorRed, colorGreen, colorBlue);
			
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2f(x, y);
				glTexCoord2f(1, 0);
				glVertex2f(x + 50, y);
				glTexCoord2f(1, 1);
				glVertex2f(x + 50, y + 50);
				glTexCoord2f(0, 1);
				glVertex2f(x, y + 50);
			glEnd();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TextureDemo();
	}

}
