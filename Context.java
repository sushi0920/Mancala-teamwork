package Mancala;

import javax.swing.JPanel;

public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public JPanel displayStrategy() {
		return this.strategy.displayPanel();
	}
}
