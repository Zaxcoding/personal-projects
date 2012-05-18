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
	
	/* this is a nice constructor to do a common task-
	 * setting the layout immediately after creating the new panel. */
	public MyPanel(int w, int h, int a, int b)
	{
		width = w;
		height = h;
		this.setLayout(new GridLayout(a, b));
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(width, height);
	}
}