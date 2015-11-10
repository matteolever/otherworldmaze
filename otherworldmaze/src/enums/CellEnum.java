package enums;


/**
 * specifies all the objects that can be inside ONE cell.
 * 
 * @author verena
 *
 */
public enum CellEnum {
	FOREST(1, "appdata/forest.png"),
	MOUNTAIN(2, "appdata/mountain.png"),
	HOUSE(3, "appdata/house.png"),
	RIVER(4, "appdata/river.png"),
	KEY(5, "appdata/key.png"),
	DOOR(6, "appdata/door.png"),
	PLAYER(7, "appdata/ghost.png"),
	DOOR_OPENED(8, "appdata/door_opened.png"),
	EMPTY(0, null),
	SIZE(40, null);

	private int type;
	private String src;

	/**
	 * specifies all the objects that can be inside ONE cell in the grid
	 * 
	 * @param type:
	 *            the type of the object
	 * @param src:
	 *            the path to the img src of the type. is null if the component
	 *            defines it itself, e.g. is a color
	 */
	private CellEnum(int type, String src) {
		this.type = type;
		this.src = src;
	}

	public int getType() {
		return type;
	}

	public String getSrc() {
		return src;
	}

	public String getSrc(int type) {
		return src;
	}
	
//	public CellEnum getEnum(int type){
//		switch (type) {
//		case FOREST.getType():
//			return CellEnum.FOREST;
//		
//		case FOREST.getType():
//			return CellEnum.FOREST;
//
//		case FOREST.getType():
//			return CellEnum.FOREST;
//
//		default:
//			src = CellEnum.FOREST.getSrc();
//			break;
//		return null;
//	}

}
