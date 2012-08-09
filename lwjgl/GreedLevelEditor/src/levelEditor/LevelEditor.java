package levelEditor;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;


public class LevelEditor
{
	protected static final int EDITOR_RESOLUTION_X = 1280;			// width of the level editor screen
	protected static final int EDITOR_RESOLUTION_Y = 800;			// height of the level editor screen
	protected final static int GAME_RESOLUTION_X = 640;		// game dimensions
	protected final static int GAME_RESOLUTION_Y = 480;		// of BossGreed
	
	// for convenience, dependent on the above constants
	int top = (EDITOR_RESOLUTION_Y - GAME_RESOLUTION_Y)/2 - 1;
	int bottom = (EDITOR_RESOLUTION_Y + GAME_RESOLUTION_Y)/2 + 1;
	int left = (EDITOR_RESOLUTION_X - GAME_RESOLUTION_X)/2 - 1;
	int right = (EDITOR_RESOLUTION_X + GAME_RESOLUTION_X)/2 - 1;

	// not final, so you can change within the editor
	int FONT_SIZE = 24;		
	int GRID_SIZE = 50;
	int CAMERA_SCROLL_SPEED = 5;

	
	int transX, transY, mouseX, mouseY;
	int [] Keys = new int[20];		// make an array to hold the keys for controls
	
	boolean drawGrid = true;
	
	UnicodeFont uniFont;

	
	public LevelEditor() 
	{	
		initGL();
		initFonts();
		initKeys();

		while (!Display.isCloseRequested()) 
		{			
			translate();					// wipe and translate the screen
			mouse();

			input();
			render();
			drawText();

			popAndUpdate();					// pop the matrix, update and sync display
		}

		Display.destroy();
		System.exit(0);
	}
	
	private void render()
	{	 			
		// draw the game box
		glBegin(GL_LINE_LOOP);
			glVertex2f(left - transX, top - transY);
			glVertex2f(right - transX, top - transY);
			glVertex2f(right - transX, bottom - transY);
			glVertex2f(left - transX, bottom - transY);
		glEnd();
		
		// this is where the level itself goes
				
		glColor4f(1.0f, 0, 1.0f, .5f);
		glRectd(500, 400, 700, 600);
		
		glColor4f(1.0f, 0, 1.0f, .7f);
		glRectd(600, 200, 650, 250);
		
		glColor4f(1.0f, 0, 1.0f, .8f);
		glRectd(500, 300, 550, 350);
		
		// end level

		drawBoundary();
		
		drawButtons();
		
		if (drawGrid)
			drawGrid();
		
	}
	
	private void input()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) 
			transY += CAMERA_SCROLL_SPEED;
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) 
			transY -= CAMERA_SCROLL_SPEED;
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
			transX += CAMERA_SCROLL_SPEED;
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) 
			transX -= CAMERA_SCROLL_SPEED;
	}
	
	private void mouse()
	{
		// Retrieve the "true" coordinates of the mouse.
		mouseX = Mouse.getX() - transX;
		mouseY = EDITOR_RESOLUTION_Y - Mouse.getY() - 1 - transY;
	}

	private void drawButtons()
	{
		int buttonLeft = -transX - CAMERA_SCROLL_SPEED;
		int buttonBottom = EDITOR_RESOLUTION_Y - transY + CAMERA_SCROLL_SPEED;
		int buttonWidth = 200;
		int buttonHeight = 50;
		
		glColor4f(1f, 1f, 1f, 1f);
	
		glBegin(GL_LINE_LOOP);
			glVertex2f(buttonLeft, buttonBottom - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBottom - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBottom);
			glVertex2f(buttonLeft, buttonBottom);
		glEnd();
		
		buttonLeft += buttonWidth;
		
		glBegin(GL_LINE_LOOP);
			glVertex2f(buttonLeft, buttonBottom - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBottom - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBottom);
			glVertex2f(buttonLeft, buttonBottom);
		glEnd();
		
		
		
		//glRectd(-transX - CAMERA_SCROLL_SPEED, EDITOR_RESOLUTION_Y - transY + CAMERA_SCROLL_SPEED - 25, -transX - CAMERA_SCROLL_SPEED + 75, EDITOR_RESOLUTION_Y - transY + CAMERA_SCROLL_SPEED);	// left

	//	glRectd(-transX, bottom + 1 - transY, EDITOR_RESOLUTION_X - transX, EDITOR_RESOLUTION_Y - transY + CAMERA_SCROLL_SPEED);	// bottom
		//glRectd(right + 1 - transX, -transY, EDITOR_RESOLUTION_X - transX + CAMERA_SCROLL_SPEED, EDITOR_RESOLUTION_Y - transY);	// right
	}
	
	private void drawBoundary()
	{
		// this draws a black frame around the screen size to create the 'window-in-window' illusion
		glColor4f(0f, 0f, 0f, 1f);
		glRectd(-transX, -transY - CAMERA_SCROLL_SPEED, EDITOR_RESOLUTION_X - transX, top - 1 - transY);	// top
		glRectd(-transX - CAMERA_SCROLL_SPEED, -transY, left - 1 - transX, EDITOR_RESOLUTION_Y - transY);	// left
		glRectd(-transX, bottom + 1 - transY, EDITOR_RESOLUTION_X - transX, EDITOR_RESOLUTION_Y - transY + CAMERA_SCROLL_SPEED);	// bottom
		glRectd(right + 1 - transX, -transY, EDITOR_RESOLUTION_X - transX + CAMERA_SCROLL_SPEED, EDITOR_RESOLUTION_Y - transY);	// right
	}
	
	private void drawGrid()
	{
		// draw the grid
		glBegin(GL_LINES);
	
			glColor4f(1.0f, 1.0f, 1.0f, .75f);
			for (int i = 0; top + i <  bottom; i += GRID_SIZE)
			{
				glVertex2f(left - transX, top + i - transY);
				glVertex2f(right - transX, top + i - transY);
			}
			for (int i = 0; left + i <  right; i += GRID_SIZE)
			{
				glVertex2f(left + i - transX, top - transY);
				glVertex2f(left + i - transX, bottom - transY);
			}
			glDisable(GL_BLEND); 
		glEnd();
	}
	
	private void drawText()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
		
		
		uniFont.drawString(5 - transX, 5 - transY, "Mouse: " + (mouseX + transX) + "," + (mouseY + transY));
		uniFont.drawString(5 - transX, 5 - transY + FONT_SIZE, "Game x,y: " + (mouseX - left - 1) + "," + (mouseY - top - 2));
		uniFont.drawString(-transX, EDITOR_RESOLUTION_Y - transY - 25, "Button!");
		// more text here
		
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);        
		glDisable(GL_BLEND); 
	}
	
	private void popAndUpdate()
	{
		glPopMatrix();
		Display.update();
		Display.sync(60);
	}
	

	private void translate()
	{
		glClear(GL_COLOR_BUFFER_BIT);	// wipe the screen
		
		glPushMatrix();
		glTranslatef(transX, 0, 0);
		glTranslatef(0, transY, 0);
	}
	
	private void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(EDITOR_RESOLUTION_X, EDITOR_RESOLUTION_Y));
			Display.setTitle("Level Editor");
			Display.create();
		} catch (LWJGLException e) 
		{
			e.printStackTrace();	
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, EDITOR_RESOLUTION_X, EDITOR_RESOLUTION_Y, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
	}
	
	@SuppressWarnings("unchecked")
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
	
	
	private void initKeys()
	{
		// set the default control scheme
	}
	
	
	public static void main(String[] args) {
		new LevelEditor();
	}
	
}
