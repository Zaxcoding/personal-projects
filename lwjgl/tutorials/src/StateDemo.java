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

public class StateDemo {

	private static enum State 
	{
		INTRO, MAIN_MENU, GAME;
	}
	
	private State state = State.INTRO;
	
	public StateDemo()
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
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	
		while (!Display.isCloseRequested())
		{
			// Render
			checkInput();
			glClear(GL_COLOR_BUFFER_BIT);
			
			render();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	private void render()
	{
		switch(state)
		{
		case INTRO:
			glColor3f(1.0f, 0f, 0f);
			glRectf(0, 0, 640, 480);
			break;
		case GAME:
			glColor3f(0f, 1.0f, 0f);
			glRectf(0, 0, 640, 480);
			break;
		case MAIN_MENU:
			glColor3f(0f, 0f, 1.0f);
			glRectf(0, 0, 640, 480);
		}
	}
	
	private void checkInput()
	{
		switch(state)
		{
		case INTRO:
			if (Keyboard.isKeyDown(Keyboard.KEY_S))
				state = State.MAIN_MENU;
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				Display.destroy();
				System.exit(0);
			}
			break;
		case GAME:
			if (Keyboard.isKeyDown(Keyboard.KEY_BACK))
				state = State.MAIN_MENU;
			break;
		case MAIN_MENU:
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN))
				state = State.GAME;
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				state = State.INTRO;
			break;
		}
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
		new StateDemo();
	}

}
