package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObstacleView extends Component {

	private static final long serialVersionUID = -2149030456213433584L;

	private final int FOREST = 0;
	private final int MOUNTAIN = 1;
	private final int VILLAGE = 2;
	private final int RIVER = 3;

	private int type;
	private BufferedImage img = null;

	public ObstacleView(int type) {
		this.type = type;
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		String src = "";
		switch (type) {
		case FOREST:
			src = "appdata/forest.png";
			break;
		case MOUNTAIN:
			src = "appdata/mountains.png";
			break;

		case VILLAGE:
			src = "appdata/village.png";
			break;

		default:
			src = "appdata/forest.png";
			break;
		}

		try {
			img = ImageIO.read(new File(src));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(img, 0, 0, null);
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));

	}

	public void setImgSize() {
		if (img != null) {
			this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
			System.out.println("set the size to " + new Dimension(img.getWidth(), img.getHeight()));
		}
	}
}
