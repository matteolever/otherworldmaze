package view;

import javax.swing.JPanel;

import enums.CellEnum;

/**
 * represents one cell in the playingfield
 * 
 * @author verena
 *
 */
public class CellView extends JPanel {


	/**
	 * the type of the cell. 
	 */
	private CellEnum cellType;

	/**
	 * in
	 * @param cellType
	 */
	public CellView(CellEnum cellType) {
		this.cellType = cellType;
	}

	
	
}
