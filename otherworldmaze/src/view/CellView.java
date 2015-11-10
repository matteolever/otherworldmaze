package view;

import enums.CellEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * represents one cell in the playingfield
 * 
 */
public class CellView extends JPanel {

	static public Dimension CELLSIZE = new Dimension(CellEnum.SIZE.getType(), CellEnum.SIZE.getType());
	/**
	 * the type of the cell.
	 */
	private CellEnum cellType;

	/**
	 * the image of the cell
	 */
	private BufferedImage img = null;

	/**
	 * the coordinates of the cell
	 */
	private int[] coordinates = new int[2];

	/**
	 * 
	 * @param cellType
	 */
	public CellView(CellEnum cellType, int row, int col) {
		this.cellType = cellType;
		this.img = loadImg();
		initCellView(row, col);
	}

	/**
	 * Initialization when only the integer of a cell is known. Will
	 * automatically set the correct enum as cellType
	 * 
	 */
	public CellView(int typeInt, int row, int col) {
		setType(typeInt);
		initCellView(row, col);

	}

	/**
	 * initializes the cell view.
	 * 
	 * @param row the row of the cell in the grid.
	 * @param col the column of the cell in the grid. 
	 */
	public void initCellView(int row, int col) {
		this.setPreferredSize(CELLSIZE);
		this.setBounds(0, 0, (int) CELLSIZE.getWidth(), (int) CELLSIZE.getHeight());
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		this.setOpaque(true);
		this.setName(String.valueOf(cellType.getType()));

		coordinates[0] = row;
		coordinates[1] = col;
	}

	/**
	 * loads the image of the cell. 
	 * @return
	 */
	public BufferedImage loadImg() {
		String src = cellType.getSrc();
		if (src == null || src.isEmpty()) {
			// TODO. What happens if it does not have a graphic? paint a color?
		} else {
			try {
				img = ImageIO.read(new File(src));
				this.setPreferredSize(new Dimension(CELLSIZE));
			} catch (IOException e) {
				System.err.println("ERROR: Could not find the file at " + src + " for enum " + cellType.toString());
				e.printStackTrace();
			}
		}
		return img;
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (img != null) {
			g.drawImage(img, 0, 0, CELLSIZE.width - 5, CELLSIZE.height - 5, null);
		} else {
			if (cellType.getType() != CellEnum.EMPTY.getType())
				System.out.println("img for " + this.cellType.toString() + " is null!");
		}

		int w = getWidth();
		int h = getHeight();

		g.setPaint(new Color(230, 230, 230));
	}

	/**
	 * sets the type of a cell. Depending on the type different graphics will be displayed. 
	 * @param typeInt the type this cell should change into. 
	 */
	public void setType(int typeInt) {
		for (CellEnum c : CellEnum.values()) {
			if (c.getType() == typeInt) {
				this.cellType = c;
				break;
			}
		}

		if (this.cellType == null) {
			System.err.println("INVALID CELL TYPE IN CELLVIEW!!!");
		} else if (this.cellType == CellEnum.EMPTY) {
			img = null;
		}
		this.setName(String.valueOf(cellType.getType()));

		this.img = loadImg();
	}

	public CellEnum getCellType() {
		return cellType;
	}

	public int[] getCoordinates() {
		return coordinates;
	}

}
