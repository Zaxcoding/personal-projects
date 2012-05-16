/* Taking a lot of cues from Assig4 of CS 0401
 * the graphical hangman program
 */

// verbatim from Assig3
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Yahtzee
{
	JFrame theWindow;
	Container thePane;
	JPanel leftPanel, rightPanel;
	MyPanel scorePanel, dicePanel;
	JLabel statusLabel, infoLabel, wordLabel, guessesLabel;
	JLabel playerName, playerWin, playerLoss;
	JLabel c1, c2, c3, c4, c5;
	JButton b1, b2, b3;
	JTextField textBox;
		
	// for customizability - you can adjust the sides to 6, 12, 20, etc
	public static final int SIDES = 6;
	
	public static void main(String [] args)
	{	
		new Yahtzee();
	}
	
	public Yahtzee()
	{
		setup();
		Die [] dice = new Die[5];
		for (int i = 0; i < 5; i++)
			dice[i] = new Die(SIDES);
		for (int i = 0; i < 10; i++)
			System.out.println(dice[0].roll() + " " + dice[1].roll() + " " + 
							dice[2].roll() + " " + dice[3].roll() + " " + 
						 	dice[4].roll() + " ");
	}
	
	public void setup()
	{
		theWindow = new JFrame("YAHTZEE - by Zaxcoding");	
		thePane = theWindow.getContentPane();					
		thePane.setLayout(new GridLayout(1, 2));
		
		setupScorePanel();
	}
	
	public void setupScorePanel()
	{				
		
			
		scorePanel = new MyPanel(1000, 540);		
		scorePanel.setLayout(new GridLayout(1, 2));
		
		MyPanel bigGrid = new MyPanel(400,540);
		MyPanel grid [] = new MyPanel[7];
		JLabel boxes [] = new JLabel[22];
		JLabel l0;
		JButton buttons [] = new JButton[14];
		
		grid[0] = new MyPanel(120, 540);
		grid[1] = new MyPanel(60, 540);
		for (int i = 1; i < 7; i++)
		{
			grid[i] = new MyPanel(40, 540);
			grid[i].setLayout(new GridLayout(22, 1));
			l0 = new JLabel("  #" + i);
			l0.setBorder(new MatteBorder(2, 1, 2, 1, Color.BLACK));
			boxes[0] = l0;
			for (int j = 1; j < 10; j++)
			{
				boxes[j] = new JLabel();
				boxes[j].setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
			}
			boxes[10] = new JLabel();
			boxes[10].setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
			
			for (int j = 11; j < 22; j++)
			{
				boxes[j] = new JLabel();
				boxes[j].setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
			}
			for (int j = 0; j < 22; j++)
				grid[i].add(boxes[j]);
		}
			
		grid[0].setLayout(new GridLayout(22, 1));
        
		JLabel l1 = new JLabel("Upper Section");
		l1.setFont(new Font("Helvetica", Font.BOLD, 14));
		l1.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		grid[0].add(l1);
		buttons[0] = new JButton("Aces   \t [1]");
		buttons[1] = new JButton("Twos   \t [2]");
		buttons[2] = new JButton("Threes \t[3]");
		buttons[3] = new JButton("Fours  \t [4]");
		buttons[4] = new JButton("Fives  \t [5]");
		buttons[5] = new JButton("Sixes  \t [6]");
		for (int i = 0; i < 6; i++)
		{
			grid[0].add(buttons[i]);
		}
		JLabel l2 = new JLabel("TOTAL SCORE");
		l2.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l2.setBorder(new MatteBorder(2, 2, 1, 2, Color.BLACK));
		grid[0].add(l2);
		JLabel l3 = new JLabel("BONUS");
		l3.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l3.setBorder(new MatteBorder(1, 2, 1, 2, Color.BLACK));
		grid[0].add(l3);
		JLabel l4 = new JLabel("TOTAL");
		l4.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l4.setBorder(new MatteBorder(1, 2, 2, 2, Color.BLACK));
		grid[0].add(l4);
		JLabel l5 = new JLabel("LOWER SECTION");
		l5.setFont(new Font("Helvetica", Font.BOLD, 14));
		grid[0].add(l5);
		buttons[6] = new JButton("3 of a Kind");
		buttons[7] = new JButton("4 of a Kind");
		buttons[8] = new JButton("Full House");
		buttons[9] = new JButton("Sm. Straight");
		buttons[10] = new JButton("Lg Straight");
		buttons[11] = new JButton("YAHTZEE");
		buttons[12] = new JButton("Chance");
		buttons[13] = new JButton("Yahtzee Bonus");
		for (int i = 6; i < 14; i++)
		{
			grid[0].add(buttons[i]);
			grid[0].setEnabled(false);
		}		
		JLabel l6 = new JLabel("Total Lower");
		l6.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l6.setBorder(new MatteBorder(2, 2, 1, 2, Color.BLACK));
		grid[0].add(l6);
		JLabel l7 = new JLabel("Total Upper");
		l7.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l7.setBorder(new MatteBorder(1, 2, 1, 2, Color.BLACK));
		grid[0].add(l7);
		JLabel l8 = new JLabel("GRAND TOTAL");
		l8.setFont(new Font("Helvetica", Font.BOLD, 14));
		l8.setBorder(new MatteBorder(1, 2, 2, 2, Color.BLACK));
		grid[0].add(l8); 
		
		for (int i = 0; i < 7; i++) 
		{
			bigGrid.add(grid[i]);	
		}
		scorePanel.add(new MyPanel(400, 540));
		scorePanel.add(bigGrid);
		
		thePane.add(scorePanel);
//		thePane.add(infoPanel);
		theWindow.pack();
		theWindow.setVisible(true);
		
				
							
	}
	
	
	
}