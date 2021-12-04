package Mancala;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//style 2 of gameboard, concrete strategy
public class Style2 implements Strategy {
	public JPanel board = new JPanel();// game board
	public JPanel panel = new JPanel();// panel for the following: p1 pots, space, p2 pots
	public JPanel panel1 = new JPanel(new GridLayout(1, 6)); // panel for p1 pots
	public JPanel panel2 = new JPanel(new GridLayout(1, 6)); // panel for p2 pots
	public JLabel title = new JLabel("MANCALA"); // game title goes into space panel
	public JPanel space = new JPanel(); // space panel that spaces the players pots and contains the title
	public JButton[] buttonP1 = new JButton[7];// player1 pots
	public JButton[] buttonP2 = new JButton[7];// player2 pots
	public Color lightSlateGrey = new Color(119, 136, 153);
	int[] player1;
	int[] player2;
	MancalaModel data;

//@param takes in a mancala mode for game data
	public Style2(MancalaModel model) {
		data = model;
		player1 = data.getData(0);
		player2 = data.getData(1);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		title.setFont(new Font("Times New Roman", Font.BOLD, 32));
		space.add(title, BorderLayout.CENTER);
		space.setPreferredSize(new Dimension(480, 60));
		space.setBackground(lightSlateGrey);
//adds top row
//need actionlisteners to model for updated stone data
		for (int i = 5; i >= 0; i--) {
			buttonP1[i] = new JButton(new PotIcon(player1[i])); // put data into the param
			buttonP1[i].setBackground(lightSlateGrey);
			buttonP1[i].setPreferredSize(new Dimension(80, 80));
			final int temp = i;
			buttonP1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//method to tell model that this slot has been pressed and move stones
					data.playerMove(temp, 0);
					player1 = data.getData(0);
					player2 = data.getData(1);
					for (int i = 5; i >= 0; i--) {
						buttonP1[i].setIcon(new PotIcon(player1[i]));
						buttonP1[i].repaint();
					}
					for (int i = 0; i < 6; i++) {
						buttonP2[i].setIcon(new PotIcon(player2[i]));
						buttonP2[i].repaint();
					}
					String temp1 = player1[6] + "";
					String temp2 = player2[6] + "";
					buttonP1[6].setText(temp1);
					buttonP2[6].setText(temp2);
					buttonP1[6].repaint();
					buttonP2[6].repaint();
				}
			});
			panel1.add(buttonP1[i]);
		}
//adds bottom row
//need actionlisteners to model for updated stone data
		for (int i = 0; i < 6; i++) {
			buttonP2[i] = new JButton(new PotIcon(player2[i])); // put data into the param
			buttonP2[i].setBackground(lightSlateGrey);
			buttonP2[i].setPreferredSize(new Dimension(80, 80));
			final int temp = i;
			buttonP2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//method to tell model that this slot has been pressed and move stones
					data.playerMove(temp, 1);
					player1 = data.getData(0);
					player2 = data.getData(1);
					for (int i = 5; i >= 0; i--) {
						buttonP1[i].setIcon(new PotIcon(player1[i]));
						buttonP1[i].repaint();
					}
					for (int i = 0; i < 6; i++) {
						buttonP2[i].setIcon(new PotIcon(player2[i]));
						buttonP2[i].repaint();
					}
					String temp1 = player1[6] + "";
					String temp2 = player2[6] + "";
					buttonP1[6].setText(temp1);
					buttonP2[6].setText(temp2);
					buttonP1[6].repaint();
					buttonP2[6].repaint();
				}
			});
			panel2.add(buttonP2[i]);
		}
//player 1's score
//updates when score updates
		String temp1 = player1[6] + "";
		buttonP1[6] = new JButton(temp1);
		buttonP1[6].setFont(new Font("Times New Roman", Font.BOLD, 32));
		buttonP1[6].setBackground(lightSlateGrey);
		buttonP1[6].setPreferredSize(new Dimension(80, 160));
//player 2's score
//updates when score updates
		String temp2 = player2[6] + "";
		buttonP2[6] = new JButton(temp2);
		buttonP2[6].setFont(new Font("Times New Roman", Font.BOLD, 32));
		buttonP2[6].setPreferredSize(new Dimension(80, 160));
		buttonP2[6].setBackground(lightSlateGrey);
//adds the 3 middle sections for the pots
		panel.add(panel1);
		panel.add(space);
		panel.add(panel2);
//adds the two score buttons
		board.add(buttonP1[6], BorderLayout.LINE_START);
		board.add(panel, BorderLayout.CENTER);
		board.add(buttonP2[6], BorderLayout.LINE_END);
		board.setBackground(lightSlateGrey);
		JButton undo = new JButton("UNDO");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//model calls undo and changes the buttons
				data.undoTurn();
				player1 = data.getData(0);
				player2 = data.getData(1);
				for (int i = 5; i >= 0; i--) {
					buttonP1[i].setIcon(new PotIcon(player1[i]));
					buttonP1[i].repaint();
				}
				for (int i = 0; i < 6; i++) {
					buttonP2[i].setIcon(new PotIcon(player2[i]));
					buttonP2[i].repaint();
				}
				String temp1 = player1[6] + "";
				String temp2 = player2[6] + "";
				buttonP1[6].setText(temp1);
				buttonP2[6].setText(temp2);
				buttonP1[6].repaint();
				buttonP2[6].repaint();
			}
		});
		board.add(undo, BorderLayout.SOUTH);
	}

//@return the game board panel
	public JPanel displayPanel() {
		return board;
	}
}