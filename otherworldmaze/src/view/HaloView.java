package view;

import enums.CellEnum;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * 
 * The Look of the Halo, which is a radial gradient, with a transparent center.
 */
public class HaloView extends Component {

	private static final long serialVersionUID = 7162280948620233488L;

	/**
	 * the x position of the halo.
	 */
	private int x;
	/**
	 * the y position of the halo.
	 */
	private int y;

	/**
	 * the radius of the Halo.
	 */
	private int radius;

	private Color BG = new Color(34, 44, 77);

	/**
	 * The Look of the Halo, which is a radial gradient, with a transparent
	 * center.
	 * 
	 * @param x
	 *            x position of the Halo.
	 * @param y
	 *            y position of the Halo.
	 * @param radius
	 *            the radius of the visible part of the Halo.
	 */
	public HaloView(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		Point2D center = new Point2D.Float(x + CellEnum.SIZE.getType() / 2, y + CellEnum.SIZE.getType() / 2);
		float[] dist = { 0.1f, 0.6f, 1.0f };
		Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 0), BG };
		RadialGradientPaint p = new RadialGradientPaint(center, this.radius, dist, colors);
		g.setPaint(p);
		g.fillRect(0, 0, this.getParent().getWidth(), this.getParent().getHeight());
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}
}
