package andy;

import static org.lwjgl.opengl.GL11.*;
import java.awt.Font;

import org.lwjgl.LWJGLException;


import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.*;
import java.awt.Font;
import java.io.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class LevelEditor 
{
	public static final int WIDTH = 1280;			// width of the level editor screen
	public static final int HEIGHT = 800;			// height of the level editor screen
	public static final int SCROLL_SPEED = 10;		// the camera scroll speed
	public static final int THICKNESS = 2;			// the thickness you adjust by
	float translate_x = 0;
	float translate_y = 0;
	int width = 75;
	int height = 10;
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont;
	public LevelEditor() 
	{	
		
		
		initGL(640,480);
		initFonts();
		
		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (1024, 600) is the bottom right one.
		

		// For the sidescrolling
		
		
		// Enter the main render loop
		while (!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			render();
			// Make sure the display stays responsive and wait until we reach 60fps.
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}
	
	public void save(List<Shape> shapes, String filename)
	{
		try
		{
			ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(filename));
			OS.writeInt(shapes.size());
			for (Shape box : shapes)
			{
				box.save(OS);
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

	public static void main(String[] args) {
		new LevelEditor();
	}
	
	@SuppressWarnings("unchecked")
	private void initFonts() {

        Font awtFont = new Font("", Font.PLAIN,55);
       

        uniFont = new UnicodeFont(awtFont, 85, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};


    }
	private void render()
    {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

        glPushMatrix(); 
            glTranslatef(-1f,1f,0);
                glScalef(0.001f,-0.001f,0.001f);
                glEnable(GL_BLEND);
                
                    uniFont.drawString(0, 0,"x ="+Mouse.getX()+"y="+(480-Mouse.getY()-1));
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                                            //EDIT.. glDisable texture is required here.
                
                glDisable(GL_BLEND);                    
        glPopMatrix();
        
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
		if (Keyboard.isKeyDown(Keyboard.KEY_L)) 
			width += THICKNESS;
		if (Keyboard.isKeyDown(Keyboard.KEY_J) && (width - THICKNESS) >=3) 
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
		for (Shape box : shapes)
		{
			box.draw();
			if(temp.intersects(box))
			{
				System.out.println("Intersect");
			}
			temp.interact(box);
		}
		
		for(int i=0;i<10;i++)
		{
			System.out.println();
		}
		// Dispose of the translations on the matrix.
		glPopMatrix();
    }
	private void initGL(int width, int height) {
        try {
            Display.setDisplayMode(new DisplayMode(width,height));
            Display.create();
            //Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        glEnable(GL11.GL_TEXTURE_2D);
        glShadeModel(GL11.GL_SMOOTH);        
        glEnable(GL11.GL_DEPTH_TEST);
        glDisable(GL11.GL_LIGHTING);                    

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        glClearDepth(1);                                       

        glEnable(GL_BLEND);
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_BLEND);
        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-1, 1, -1, 1, -1, 1);
        glMatrixMode(GL11.GL_MODELVIEW);

    }

}