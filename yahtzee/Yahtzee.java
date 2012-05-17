/* YAHTZEE!! Everyone (in the Sadler household)'s favorite game!
 * Implemented through Java's Swing/AWT GUI system. 
 * Initial kickstart taken from Assig4 (graphical hangman). */

// verbatim from Assig4
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;		// except for this
import java.awt.*;
import java.awt.event.*;

public class Yahtzee
{
	// for the GUI
	JFrame theWindow;
	Container thePane;
	MyPanel gamePanel;				// the main panel
	MyPanel buttonPanel, leftPanel, dicePanel, scorePanel;
	MyPanel grid [];
	JLabel boxes [][]; 
	JLabel currRoll, highScore;
	JButton buttons [];
	JButton diceButtons [];
	JButton newGame, roll, zero, quit;
	MyListener theListener;

	// for customizability - you can adjust the sides to 6, 12, 20, etc
	public static final int SIDES = 6;
	
	Die dice [] = new Die[5];					// always five dice
	ScoreCard scoreCard [] = new ScoreCard[6];
	public int rollNum = 0, round = 1, tempScore = 0;
	public boolean scored = false, zeroing = false;
	
	public static void main(String [] args)
	{
		new Yahtzee();
	}
	
	public Yahtzee()
	{
		for (int i = 0; i < 5; i++)
			dice[i] = new Die(SIDES);
		for (int i = 0; i < 6; i++)
			scoreCard[i] = new ScoreCard();
		setup();
		forceReRoll();
	}
	
	public void setup()
	{
		theWindow = new JFrame("YAHTZEE - by Zaxcoding");	
		thePane = theWindow.getContentPane();					
		thePane.setLayout(new GridLayout(1, 2));
		
		gamePanel = new MyPanel(1000, 540);		
		gamePanel.setLayout(new GridLayout(1, 2));
		gamePanel.addKeyListener(new InputHandler());
		gamePanel.setFocusable(true);
		
		leftPanel = new MyPanel(550, 540);
		leftPanel.setLayout(new GridLayout(3, 1));
		leftPanel.addKeyListener(new InputHandler());
		leftPanel.setFocusable(true);
		
		JLabel yahtzee = new JLabel("        YAHTZEE");
		yahtzee.setFont(new Font("Helvetica", Font.ITALIC, 72));
		yahtzee.setForeground(Color.RED);
		leftPanel.add(yahtzee);
		
		theListener = new MyListener();
		
		setupDicePanel();
		leftPanel.add(dicePanel);
		
		setupButtonPanel();
		leftPanel.add(buttonPanel);
		
		gamePanel.add(leftPanel);

		setupScorePanel();
		scorePanel.addKeyListener(new InputHandler());
		scorePanel.setFocusable(true);
		gamePanel.add(scorePanel);
		
		thePane.add(gamePanel);
//		thePane.add(infoPanel);
		theWindow.pack();
		theWindow.setVisible(true);
		
	}
	
	public void setupDicePanel()
	{
		dicePanel = new MyPanel(400, 180);
		dicePanel.setLayout(new GridLayout(1,7));
		
		diceButtons = new JButton[5];
	
		dicePanel.add(new JPanel());
			
		diceButtons[0] = new JButton("?");
		diceButtons[0].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dicePanel.add(diceButtons[0]);
		diceButtons[1] = new JButton("?");
		diceButtons[1].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dicePanel.add(diceButtons[1]);
		diceButtons[2] = new JButton("?");
		diceButtons[2].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dicePanel.add(diceButtons[2]);
		diceButtons[3] = new JButton("?");
		diceButtons[3].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dicePanel.add(diceButtons[3]);
		diceButtons[4] = new JButton("?");
		diceButtons[4].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dicePanel.add(diceButtons[4]);
		
		for (int i = 0; i < 5; i++)
			diceButtons[i].addActionListener(theListener);
		
		dicePanel.add(new JPanel());
		
	}		

	public void setupButtonPanel()
	{
		buttonPanel = new MyPanel(550, 40);
		buttonPanel.setLayout(new GridLayout(2,4));
		
		currRoll = new JLabel("  Current Roll: " + rollNum);
		currRoll.setFont(new Font("Helvetica", Font.PLAIN, 16));
		buttonPanel.add(currRoll);
		buttonPanel.add(new JLabel());
		buttonPanel.add(new JLabel());
		
		highScore = new JLabel("High Score: 315");	
		buttonPanel.add(highScore);
		
		newGame = new JButton("New Game [N]");
		newGame.addActionListener(theListener);
		buttonPanel.add(newGame);
		roll = new JButton("Roll [R]");		
		roll.addActionListener(theListener);
		buttonPanel.add(roll);
		zero = new JButton("Zero [Z]");
		zero.addActionListener(theListener);
		buttonPanel.add(zero);
		quit = new JButton("Quit");
		quit.addActionListener(theListener);
		
		buttonPanel.add(quit);
		
	}
	
	public void setupScorePanel()
	{
		
		scorePanel = new MyPanel(400,540);
		grid = new MyPanel[7];
		boxes = new JLabel[22][7];
		JLabel l0;
		buttons = new JButton[14];
		
		grid[0] = new MyPanel(120, 540);
		grid[1] = new MyPanel(60, 540);
		for (int i = 1; i < 7; i++)
		{
			grid[i] = new MyPanel(40, 540);
			grid[i].setLayout(new GridLayout(22, 1));
			l0 = new JLabel("  #" + i);
			l0.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
			boxes[0][i] = l0;
			for (int j = 1; j < 10; j++)
			{
				boxes[j][i] = new JLabel();
				boxes[j][i].setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
			}
			boxes[10][i] = new JLabel();
			boxes[10][i].setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
			
			for (int j = 11; j < 22; j++)
			{
				boxes[j][i] = new JLabel();
				boxes[j][i].setBorder(
									new MatteBorder(0, 1, 1, 1, Color.BLACK));
			}
			for (int j = 0; j < 22; j++)
				grid[i].add(boxes[j][i]);
		}
			
		grid[0].setLayout(new GridLayout(22, 1));
        
		JLabel l1 = new JLabel("  Upper Section");
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
		JLabel l2 = new JLabel("  TOTAL SCORE");
		l2.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l2.setBorder(new MatteBorder(2, 2, 1, 2, Color.BLACK));
		grid[0].add(l2);
		JLabel l3 = new JLabel("  BONUS");
		l3.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l3.setBorder(new MatteBorder(1, 2, 1, 2, Color.BLACK));
		grid[0].add(l3);
		JLabel l4 = new JLabel("  TOTAL");
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
			grid[0].add(buttons[i]);
			
		JLabel l6 = new JLabel("  Total Lower");
		l6.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l6.setBorder(new MatteBorder(2, 2, 1, 2, Color.BLACK));
		grid[0].add(l6);
		JLabel l7 = new JLabel("  Total Upper");
		l7.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l7.setBorder(new MatteBorder(1, 2, 1, 2, Color.BLACK));
		grid[0].add(l7);
		JLabel l8 = new JLabel("  GRAND TOTAL");
		l8.setFont(new Font("Helvetica", Font.BOLD, 14));
		l8.setBorder(new MatteBorder(1, 2, 2, 2, Color.BLACK));
		grid[0].add(l8); 
		
		for (int i = 0; i < 7; i++) 
			scorePanel.add(grid[i]);	
		for (int i = 0; i < 14; i++)
			buttons[i].addActionListener(theListener);
			
	}
	
	public void enableButtons()
	{
		for (int i = 0; i < 6; i++)
		{
			buttons[i].setEnabled(scoreCard[round-1].num(i+1, dice));
		}
		buttons[6].setEnabled(scoreCard[round-1].numOfAKind(3, dice));
		buttons[7].setEnabled(scoreCard[round-1].numOfAKind(4, dice));
		buttons[8].setEnabled(scoreCard[round-1].fullHouse(dice));
		buttons[9].setEnabled(scoreCard[round-1].numStraight(4, dice));
		buttons[10].setEnabled(scoreCard[round-1].numStraight(5, dice));
		buttons[11].setEnabled(scoreCard[round-1].numOfAKind(5, dice));
		buttons[12].setEnabled(scoreCard[round-1].chance());
		buttons[13].setEnabled(scoreCard[round-1].yahtzeeBonus(dice));
		
		for (int i = 0; i < 13; i++)
			if (scoreCard[round-1].getScore(i) > -1)
				buttons[i].setEnabled(false);
		
		zero.setEnabled(true);
	}
	
	public void roll()
	{
		if (rollNum == 3 && !scored)
			JOptionPane.showMessageDialog(null, "Select a score first!");
		
		if (rollNum < 3 || (rollNum == 3 && scored))
		{
			for (int i = 0; i < 5; i++)
				if (!dice[i].held() || diceButtons[i].getText() == "?")
				{
					if (dice[i].held())
						toggleDie(i);
					dice[i].roll();
					diceButtons[i].setText(Integer.toString(dice[i].value()));
				}
				
			rollNum = (rollNum + 1) % 4;
		}			
		
		if (rollNum == 3)
			roll.setEnabled(false);
		
		currRoll.setText("  Current Roll: " + rollNum);

		enableButtons();
		gamePanel.grabFocus();
	}
	
	public void zero()
	{
		for (int i = 0; i < 13; i++)
			if (scoreCard[round-1].getScore(i) == -1)
					buttons[i].setEnabled(true);
		roll.setEnabled(false);
		zero.setEnabled(false);
	}
		
	public void questionMarks()
	{
		for (int i = 0; i < 5; i++)
			diceButtons[i].setText("?");
	}

	public void toggleDie(int n)
	{
		if (!dice[n].held())
			diceButtons[n].setFont(new Font("Helvetica", Font.BOLD, 65));
		else
			diceButtons[n].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dice[n].toggleHeld();
	}

	public void score(int n, int score)
	{
		scoreCard[round-1].setScore(n, score);		// backend - not GUI
		if (n > 5)
			n += 4;
		for (int i = 0; i < 5; i++)
			if (dice[i].held())
				toggleDie(i);
		zeroing = false;
		if (n != 17)
			scored = true;
		tempScore = 0;
		
		// score the one they just did
		boxes[n+1][round].setText("  " + Integer.toString(score));
		
		// and recalculate the totals and display those
		boxes[7][round].setText("  " +
				Integer.toString(scoreCard[round-1].upperScore()));
		boxes[8][round].setText("  " +	
				Integer.toString(scoreCard[round-1].bonusScore()));
		boxes[9][round].setText("  " +
				Integer.toString(scoreCard[round-1].totalUpperScore()));
		boxes[19][round].setText("  " +	
				Integer.toString(scoreCard[round-1].lowerScore()));
		boxes[20][round].setText("  " +	
				Integer.toString(scoreCard[round-1].totalUpperScore()));
		boxes[21][round].setText("  " +	
				Integer.toString(scoreCard[round-1].grandTotal()));	
		if (n != 17)				
			forceReRoll();
		scored = false;	
		questionMarks();	
		gamePanel.grabFocus();
	}
	
	public void forceReRoll()
	{
		for (int i = 0; i < 14; i++)	
			buttons[i].setEnabled(false);
		zero.setEnabled(false);
		roll.setEnabled(true);
		rollNum = 0;
		currRoll.setText("  Current Roll: " + rollNum);
		scored = false;
		if (scoreCard[round-1].filled())
			forceNewGame();
	}
	
	public void forceNewGame()
	{
		for (int i = 0; i < 14; i++)	
			buttons[i].setEnabled(false);
		zero.setEnabled(false);
		roll.setEnabled(false);
	}
	
	public int sum()
	{
		return (dice[0].value() + dice[1].value() + dice[2].value() 
					+ dice[3].value() + dice[4].value());
	}

	private class InputHandler extends KeyAdapter
	{
	    public void keyPressed(KeyEvent e) 
		{
	        if (e.getKeyCode() == KeyEvent.VK_R && roll.isEnabled()) 
		        roll();
		    if (e.getKeyCode() == KeyEvent.VK_A && diceButtons[0].isEnabled()) 
		        toggleDie(0);                  
		    if (e.getKeyCode() == KeyEvent.VK_S && diceButtons[1].isEnabled()) 
		        toggleDie(1);                  
		    if (e.getKeyCode() == KeyEvent.VK_D && diceButtons[2].isEnabled()) 
		        toggleDie(2);                 
		    if (e.getKeyCode() == KeyEvent.VK_F && diceButtons[3].isEnabled()) 
		        toggleDie(3);                  
		    if (e.getKeyCode() == KeyEvent.VK_G && diceButtons[4].isEnabled()) 
		        toggleDie(4);
			if (e.getKeyCode() == KeyEvent.VK_Z && zero.isEnabled())
			{
				zero();
				zeroing = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_N && newGame.isEnabled())
			{
				if (JOptionPane.showConfirmDialog(null, "Are you sure you"
				 	 + " want to start a new game?",	"New Game", 
						JOptionPane.YES_NO_OPTION) == 0)
				{	
					if (round < 6)
					{
						round++;
						questionMarks();
						forceReRoll();
						gamePanel.grabFocus();
					}
					else
						new Yahtzee();
				}
			}
			
	    }
	}

	private class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (zeroing)
			{
				for (int i = 0; i < 13; i++)					
					if (e.getSource() == buttons[i])
					{
						score(i, 0);
						zeroing = false;
						scored = true;	
						roll.setEnabled(true);
						forceReRoll();
					}
			}
			else
			{
				for (int i = 0; i < 5; i++)
					if (e.getSource() == diceButtons[i])
						toggleDie(i);
				if (e.getSource() == newGame)
				{
					if (JOptionPane.showConfirmDialog(null, "Are you sure you"
					 	 + " want to start a new game?",	"New Game", 
							JOptionPane.YES_NO_OPTION) == 0)
					{	
						if (round < 6)
						{
							round++;
							questionMarks();
							forceReRoll();
							gamePanel.grabFocus();
						}
						else
							new Yahtzee();
					}
				}
					
				if (e.getSource() == roll)
					roll();
				if (e.getSource() == zero)
				{
					zero();
					zeroing = true;
				}	
				if (e.getSource() == quit)
					System.exit(0);
				for (int i = 0; i < 6; i++)
					if (e.getSource() == buttons[i])
					{
						for (int j = 0; j < 5; j++)
							if (dice[j].value() == i+1)
								tempScore += i+1;
						score(i, tempScore);
					}
				if (e.getSource() == buttons[6])
					score(6, sum());
				if (e.getSource() == buttons[7])
					score(7, sum());
				if (e.getSource() == buttons[8])
					score(8, 25);
				if (e.getSource() == buttons[9])
					score(9, 30);
				if (e.getSource() == buttons[10])
					score(10, 40);
				if (e.getSource() == buttons[11])
					score(11, 50);
				if (e.getSource() == buttons[12])
					score(12, sum());
				if (e.getSource() == buttons[13])
					score(13, 100);
			}
		}
	}
}