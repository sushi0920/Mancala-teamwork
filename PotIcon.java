package Mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

//prints an icon with number of stones in a pot
class PotIcon implements Icon {
	int numStones;
	int height;
	int width;

//constructor
//@ param takes in number of stones for the pot
	public PotIcon(int x) {
		numStones = x;
		height = 70;
		width = 70;
	}

	@Override
// paints the pot icon with number of stones in the pots
	public void paintIcon(Component c, Graphics g, int x, int y) {
//int center = 38;
		int sw = 18, sh = 18;
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double pot = new Ellipse2D.Double(5, 5, width, height);
		Ellipse2D.Double stone;
		if (numStones == 0) {
			g2.setColor(Color.BLACK);
			g2.draw(pot);
		} else {
			if (numStones < 26) {
				for (int i = 1; i <= numStones; i++) {
					stone = new Ellipse2D.Double(sw, sh, 4, 4);
					sw += 10;
					if (i >= 5) {
						if (i % 5 == 0) {
							sw = 18;
							sh += 10;
						}
					}
					g2.setColor(Color.BLACK);
					g2.draw(stone);
					g2.fill(stone);
				}
				g2.setColor(Color.BLACK);
				g2.draw(pot);
			}
		}
	}

	@Override
//@return returns the width of the icon
	public int getIconWidth() {
		return width;
	}

	@Override
//@return returns the height of the icon
	public int getIconHeight() {
		return height;
	}
}