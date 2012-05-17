//Tetris by bosscoding

//imports and functionality of swing stolen from Yahtzee.java(Zaxcoding) skeleton

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;	
import java.awt.*;
import java.awt.event.*;

public class Tetris
{
	JFrame theWindow;
	Container thePane;
	MyPanel gamePanel;				
	MyPanel buttonPanel, leftPanel,rightPanel,nextPanel,timePanel,linesPanel,totalPanel,nextLinePanel,levelPanel;
	JButton newGame,pause,endGame;
	MyListener theListener;

	
	
	public static void main(String [] args)
	{
		new Tetris();
	}
	
	public Tetris()
	{
		setup();
	}
	
	public void setup()
	{
		theWindow = new JFrame("Tetris- by bosscoding");	
		thePane = theWindow.getContentPane();					
		thePane.setLayout(new GridLayout(1, 1));
		
		gamePanel = new MyPanel(1500, 1000);		
		gamePanel.setLayout(new GridLayout(1, 1));
		
		leftPanel = new MyPanel(1500, 750);
		leftPanel.setLayout(new GridLayout(20, 10));
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		rightPanel = new MyPanel(1500,250);
		rightPanel.setLayout(new GridLayout(5,1));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		nextPanel = new MyPanel(250,300);
		nextPanel.setLayout(new GridLayout(2,1));
		nextPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		timePanel = new MyPanel(250,300);
		timePanel.setLayout(new GridLayout(2,1));
		timePanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		linesPanel = new MyPanel(250,300);
		linesPanel.setLayout(new GridLayout(5,1));
		linesPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		levelPanel = new MyPanel(250,300);
		levelPanel.setLayout(new GridLayout(2,1));
		levelPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		buttonPanel = new MyPanel(250,300);
		buttonPanel.setLayout(new GridLayout(3,1));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		newGame = new JButton("New Game");
		newGame.setFont(new Font("Serif", Font.ITALIC, 12));
		buttonPanel.add(newGame);
		newGame.addActionListener(theListener);
		
		pause = new JButton("Pause");
		pause.setFont(new Font("Serif", Font.ITALIC, 12));
		buttonPanel.add(pause);
		pause.addActionListener(theListener);
		
		endGame = new JButton("End Game");
		endGame.setFont(new Font("Serif", Font.ITALIC, 12));
		buttonPanel.add(endGame);
		endGame.addActionListener(theListener);
		
		
		JLabel levelText = new JLabel("Level",SwingConstants.CENTER);
		levelText.setFont(new Font("Serif", Font.ITALIC, 12));
		JLabel currentLevel = new JLabel("0",SwingConstants.CENTER);
		currentLevel.setFont(new Font("Serif", Font.ITALIC, 12));
		levelPanel.add(levelText);
		levelPanel.add(currentLevel);
		
		JLabel lineText = new JLabel("<html><b>Lines</b></html>",SwingConstants.CENTER);
		lineText.setFont(new Font("Serif", Font.ITALIC, 20));
		JLabel line1Text = new JLabel("Total",SwingConstants.CENTER);
		line1Text.setFont(new Font("Serif", Font.ITALIC, 12));
		JLabel line2Text = new JLabel("0",SwingConstants.CENTER);
		line2Text.setFont(new Font("Serif", Font.ITALIC, 12));
		JLabel line3Text = new JLabel("Next Level in",SwingConstants.CENTER);
		line3Text.setFont(new Font("Serif", Font.ITALIC, 12));
		JLabel line4Text = new JLabel("10",SwingConstants.CENTER);
		line4Text.setFont(new Font("Serif", Font.ITALIC, 12));
		linesPanel.add(lineText);
		linesPanel.add(line1Text);
		linesPanel.add(line2Text);
		linesPanel.add(line3Text);
		linesPanel.add(line4Text);
		
		JLabel timeText = new JLabel("Time",SwingConstants.CENTER);
		timeText.setFont(new Font("Serif", Font.ITALIC, 12));
		JLabel gameTime = new JLabel("0",SwingConstants.CENTER);
		timeText.setFont(new Font("Serif", Font.ITALIC, 12));
		timePanel.add(timeText);
		timePanel.add(gameTime);
		
		JLabel nextText = new JLabel("<html> <center>Next</center></html>",SwingConstants.CENTER);
	    nextText.setFont(new Font("Serif", Font.ITALIC, 12));
		JLabel nextPiece = new JLabel("0",SwingConstants.CENTER);
		nextPiece.setFont(new Font("Serif", Font.ITALIC, 12));
		nextPanel.add(nextText);
		nextPanel.add(nextPiece);
		
		rightPanel.add(nextPanel);
		rightPanel.add(timePanel);
		rightPanel.add(linesPanel);
		rightPanel.add(levelPanel);
		rightPanel.add(buttonPanel);
		
		gamePanel.add(leftPanel);
		gamePanel.add(rightPanel);
		
		thePane.add(gamePanel);
		theWindow.pack();
		theWindow.setVisible(true);
		
	}
	
	class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}