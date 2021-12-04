package Mancala;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MancalaView extends JFrame {
	MancalaModel model = new MancalaModel(0);
	JFrame frame = new JFrame();
	JPanel board = new JPanel();
	JPanel gamePanel;
	private Strategy strategy = new Style1(model);
	Context context;
	int strategyNum = 1;

	//create game board
	//@param takes in model data
	public MancalaView(MancalaModel data) {
		model = data;
		context = new Context(strategy);
		gamePanel = context.displayStrategy();
		board.add(gamePanel, BorderLayout.NORTH);
	}

	//creates a state menu
	//@return returns a statement frame
	public JFrame startMenu() {
		JLabel stylelabel = new JLabel("Please select a style.");
		JLabel stonelabel = new JLabel("Please select number of stones.");
		JLabel confirmlabel = new JLabel("Please confirm your selections");
		final JFrame frame = new JFrame();
		//Styles
		JPanel stylepanel = new JPanel();
		stylepanel.setLayout(new BoxLayout(stylepanel, BoxLayout.X_AXIS));
		frame.setLayout(new GridLayout(6, 1));
		//Stones
		JPanel stonepanel = new JPanel();
		stonepanel.setLayout(new BoxLayout(stonepanel, BoxLayout.X_AXIS));
		//create buttons
		//choose style1
		JButton style1 = new JButton("Style 1");
		style1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				context = new Context(new Style1(model));
				strategyNum = 1;
				System.out.println(strategyNum);
				board.remove(gamePanel);
				strategy = new Style2(model);
				gamePanel = context.displayStrategy();
				board.add(gamePanel, BorderLayout.NORTH);
				board.revalidate();
				board.repaint();
			}
		});
		//choseStyle2
		JButton style2 = new JButton("Style 2");
		style2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strategyNum = 2;
				System.out.println(strategyNum);
				board.remove(gamePanel);
				strategy = new Style2(model);
				context = new Context(strategy);
				gamePanel = context.displayStrategy();
				board.add(gamePanel, BorderLayout.NORTH);
				board.revalidate();
				board.repaint();
			}
		});
		//confirm button closes the menu and now game starts
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		//chose 3 stones
		JButton stones3 = new JButton("3 Stones");
		stones3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new MancalaModel(3);
				board.remove(gamePanel);
				if (strategyNum == 1) {
					context = new Context(new Style1(model));
				} else {
					context = new Context(new Style2(model));
				}
				gamePanel = context.displayStrategy();
				board.add(gamePanel, BorderLayout.NORTH);
				board.revalidate();
				board.repaint();
				//method to set model with 3 stones to start
			}
		});
		//choose 4 stones
		JButton stones4 = new JButton("4 Stones");
		stones4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new MancalaModel(4);
				board.remove(gamePanel);
				if (strategyNum == 2) {
					context = new Context(new Style2(model));
				} else {
					context = new Context(new Style1(model));
				}
				gamePanel = context.displayStrategy();
				board.add(gamePanel, BorderLayout.NORTH);
				board.revalidate();
				board.repaint();
				//method to set model with 4 stones to start
			}
		});
		//add buttons to their perspective panels
		stylepanel.add(style1);
		stylepanel.add(style2);
		stonepanel.add(stones3);
		stonepanel.add(stones4);
		//add items to frame
		frame.setTitle("Start Menu");
		frame.add(stylelabel);
		frame.add(stylepanel);
		frame.add(stonelabel);
		frame.add(stonepanel);
		frame.add(confirmlabel);
		frame.add(confirm);
		//panel.add(undo);
		frame.setSize(250, 200);
		//frame.pack();
		frame.setVisible(true);
		return frame;
	}

	//displays the game
	public void display() {
		MancalaView frame = new MancalaView(model);
		frame.setTitle("Mancala!");
		frame.add(board, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
