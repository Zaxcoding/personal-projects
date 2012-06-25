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

public class TimerDemo {

	private long lastFrame;
	
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
	
	public TimerDemo()
	{
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Timer Demo");
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		int x = 100;
		int y = 100;
		int dx = 1;
		int dy = 1;
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		lastFrame = getTime();
	
		while (!Display.isCloseRequested())
		{
			// Render
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			int delta = getDelta();
			x += delta * .2 * dx;
			y += delta * .2 * dy;
			
			glRecti(x, y, x + 30, y + 30);
			
			
			System.out.println(getDelta());
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TimerDemo();
	}

}
