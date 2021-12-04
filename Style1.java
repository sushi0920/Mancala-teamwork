package Mancala;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//style 1 of gameboard, concrete strategy
public class Style1 implements Strategy {
	public JPanel board = new JPanel();
	public JPanel panel = new JPanel(new GridLayout(2, 6));
	public JButton[] buttonP1 = new JButton[7];
	public JButton[] buttonP2 = new JButton[7];
	int[] player1;
	int[] player2;
	MancalaModel data;

	public Style1(final MancalaModel model) {
		data = model;
		player1 = data.getData(0);
		player2 = data.getData(1);
//adds top row
//actionlisteners to model for updated stone data
		for (int i = 5; i >= 0; i--) {
			String s = player1[i] + "";
			buttonP1[i] = new JButton(s);
			buttonP1[i].setBackground(Color.PINK);
			buttonP1[i].setPreferredSize(new Dimension(120, 120));
			buttonP1[i].setFont(new Font("", Font.BOLD, 32));
			final int temp = i;
			buttonP1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//method to tell model that this slot has been pressed and move stones
					data.playerMove(temp, 0);
					for (int i = 5; i >= 0; i--) {
						String s = player1[i] + "";
						buttonP1[i].setText(s);
						buttonP1[i].repaint();
					}
					for (int i = 0; i < 6; i++) {
						String s = player2[i] + "";
						buttonP2[i].setText(s);
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
			panel.add(buttonP1[i]);
		}
//adds bottom row
//need actionlisteners to model for updated stone data
		for (int i = 0; i < 6; i++) {
			String s = player2[i] + "";
			buttonP2[i] = new JButton(s); // put model data into the param
			buttonP2[i].setBackground(Color.PINK);
			buttonP2[i].setPreferredSize(new Dimension(120, 120));
			buttonP2[i].setFont(new Font("", Font.BOLD, 32));
			final int temp = i;
			buttonP2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//method to tell model that this slot has been pressed and move stones
					data.playerMove(temp, 1);
					player1 = data.getData(0);
					player2 = data.getData(1);
					for (int i = 5; i >= 0; i--) {
						String s = player1[i] + "";
						buttonP1[i].setText(s);
						buttonP1[i].repaint();
					}
					for (int i = 0; i < 6; i++) {
						String s = player2[i] + "";
						buttonP2[i].setText(s);
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
			panel.add(buttonP2[i]);
		}
//player 1's score
//updates when score updates
		String p1score = player1[6] + "";
		buttonP1[6] = new JButton(p1score); // put model data into the param
		buttonP1[6].setBackground(Color.PINK);
		buttonP1[6].setPreferredSize(new Dimension(120, 240));
		buttonP1[6].setFont(new Font("", Font.BOLD, 32));
//player 1's score
//updates when score updates
		String p2score = player2[6] + "";
		buttonP2[6] = new JButton(p2score);
		buttonP2[6].setPreferredSize(new Dimension(120, 240));
		buttonP2[6].setBackground(Color.PINK);
		buttonP2[6].setFont(new Font("", Font.BOLD, 32));
//add the components to the board
		board.add(buttonP1[6], BorderLayout.LINE_START);
		board.add(panel, BorderLayout.CENTER);
		board.add(buttonP2[6], BorderLayout.LINE_END);
//undo button triggers model's undo and return to the previouc state
		JButton undo = new JButton("UNDO");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//model calls undo and returns to previous state
				data.undoTurn();
				player1 = data.getData(0);
				player2 = data.getData(1);
				for (int i = 5; i >= 0; i--) {
					String s = player1[i] + "";
					buttonP1[i].setText(s);
					buttonP1[i].repaint();
				}
				for (int i = 0; i < 6; i++) {
					String s = player2[i] + "";
					buttonP2[i].setText(s);
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
