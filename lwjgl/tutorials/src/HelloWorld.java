
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class HelloWorld {

	public HelloWorld()
	{
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Hello LWJGL World!");
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	
		while (!Display.isCloseRequested())
		{
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HelloWorld();
	}

}
