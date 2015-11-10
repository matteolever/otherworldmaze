package controller;


import model.Halo;
import view.CellView;
import view.HaloView;

public class HaloController {

    private MazeController mazeController;
    private HaloView haloView;
    private Halo haloModel;
    private int haloX, haloY;

    public HaloController(int x, int y, int radius, MazeController mazeController) {
        this.haloX = x;
        this.haloY = y;
        haloView = new HaloView(haloX, haloY, radius);

        haloModel = new Halo(haloX, haloY, mazeController.mazeModel);
        this.mazeController = mazeController;
        mazeController.addHaloToMaze(haloView);
    }

    public void refreshHalo(int newX, int newY){
        this.haloX = newX;
        this.haloY = newY;

        haloModel.setPos(newX, newY);
     
        haloView.setX(haloX);
        haloView.setY(haloY);
        
        this.haloView.repaint();
    }
    
    public boolean shrinkHalo(){
    	this.haloView.setRadius(haloView.getRadius()-haloModel.getShrinkFactor());
    	this.haloView.repaint();
    	this.haloModel.shrink();
    	
    	if(this.haloView.getRadius() < CellView.CELLSIZE.getWidth()/3)
    		return false;
    	return true;
    	
    	//System.out.println("HaloController.shrinkHalo(): the radius is "+haloView.getRadius());
	}


//    public HaloController(MazeController mazeController) {
//        this.mazeController = mazeController;
//        haloView = new HaloView();
//        mazeController.mazeView.add(haloView);
//
//        haloModel = new Halo();
//    }
}
