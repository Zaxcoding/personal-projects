import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel
{
	private int width, height;
	
	public MyPanel(int w, int h)
	{
		width = w;
		height = h;
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(width, height);
	}

}