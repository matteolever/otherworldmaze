package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PlayerView extends Component {

	private int x;
	private int y;

	/**
	 * a player needs to belong to a maze which needs to be given as an argument
	 * when creating the playerview
	 * 
	 * @param mazeView
	 *            the maze to which the player belongs
	 */
	public PlayerView(int x, int y) {
		this.x = x;
		this.y = y;

		// this.setLocation(x, y);
	}

//	@Override
//	public void paint(Graphics graphics) {
//        System.out.println("player");
//        Graphics2D g = (Graphics2D) graphics;
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g.setColor(new Color(250, 250, 250));
////		g.fillOval(0, 0, 30, 30);
//		// for (CanvasItem item : items)
//		// item.paint(g);
//	}
}
