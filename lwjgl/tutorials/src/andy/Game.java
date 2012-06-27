package andy;

import java.awt.Font;

import org.lwjgl.LWJGLException;


import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;



import static org.lwjgl.opengl.GL11.*;


@SuppressWarnings("deprecation")
public class Game {


    private UnicodeFont uniFont;
    private TrueTypeFont truFont;

    public static void main(String[] argv) {    

        Game game = new Game();
        game.start(); 
    }



    public void start()
    {




        initGL(600, 600);
        initFonts();
        while(!Display.isCloseRequested()) //display not closed
        {
            render();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);

    }


    @SuppressWarnings("deprecation")
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
            glTranslatef(-0.25f,0,0);
                glColor3f(0.5f, 0f, 0f);
                    glBegin(GL_TRIANGLE_STRIP); 
                        glVertex3f(0, 0,0.0f);
                        glVertex3f(0.5f, 0,0f);
                        glVertex3f(0f,0.5f,0f);
                        glVertex3f(0.5f, 0.5f,0f);
                    glEnd();
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


    @SuppressWarnings({ "deprecation", "unchecked" })
	private void initFonts() {

        Font awtFont = new Font("", Font.PLAIN,55);
        truFont = new TrueTypeFont(awtFont, true); 

        uniFont = new UnicodeFont(awtFont, 85, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};


    }

}