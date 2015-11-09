package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class PlayerView extends Component {

	private int x;
	private int y;

	/**
	 * a player needs to belong when creating the playerview
	 * 
	 * @param mazeView
	 *            the maze to which the player belongs
	 */
	public PlayerView(int x, int y) {
		this.x = x;
		this.y = y;
		this.setBounds(x, y, (int) CellView.CELLSIZE.getWidth(), (int) CellView.CELLSIZE.getHeight());
		// this.setLocation(x, y);
	}

	@Override
	public void paint(Graphics graphics) {
		// System.out.println("player");
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(120, 120, 120));

		Ellipse2D p = new Ellipse2D.Double(x, y, CellView.CELLSIZE.getWidth(), CellView.CELLSIZE.getHeight());
		g.fill(p);
	}
}
