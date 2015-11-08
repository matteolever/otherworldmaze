package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import enums.CellEnum;

/**
 * represents one cell in the playingfield
 * 
 * @author verena
 *
 */
public class CellView extends JPanel {

	private Dimension CELLSIZE = new Dimension(20, 20);
	/**
	 * the type of the cell.
	 */
	private CellEnum cellType;
	
	/**
	 * the image of the cell
	 */
	private BufferedImage img = null;

	/**
	 * 
	 * @param cellType
	 */
	public CellView(CellEnum cellType) {
		this.cellType = cellType;
		this.img = loadImg();
		initCellView();
	}

	/**
	 * Initialization when only the integer of a cell is known. Will
	 * automatically set the correct enum as cellType
	 * 
	 * @param cellType
	 *            the type of this cell.
	 */
	public CellView(int typeInt) {
		setType(typeInt);
		initCellView();
	}

	public void initCellView() {
		this.setPreferredSize(CELLSIZE);
	}
	
	public BufferedImage loadImg(){
		String src = cellType.getSrc();
		if (src == null || src.isEmpty()) {
			//TODO. What happens if it does not have a graphic? paint a color?
		} else {
			try {
				System.out.println("Try reading " + src + " for enum " + cellType.toString());
				img = ImageIO.read(new File(src));
				this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
			} catch (IOException e) {
				System.out.println("ERROR: Could not find the file at " + src + " for enum " + cellType.toString());
				e.printStackTrace();
			}
		}
		return img;
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(img != null){
			g.drawImage(img, 0, 0, null);
		} else {
			if(cellType.getType() != CellEnum.EMPTY.getType())
			System.out.println("img for "+this.cellType.toString()+" is null!");
		}
	}
	
	public void setType(int typeInt){
		System.out.println("we changed the type :)");
		for (CellEnum c : CellEnum.values()) {
			if (c.getType() == typeInt) {
				this.cellType = c;
				break;
			}
		}
		if (this.cellType == null) {
			System.out.println("INVALID CELL TYPE IN CELLVIEW!!!");
		}
		this.img = loadImg();
	}

	public CellEnum getCellType() {
		return cellType;
	}

	
}
