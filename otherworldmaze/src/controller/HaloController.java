package controller;


import model.Halo;
import view.HaloView;

public class HaloController {

    private MazeController mazeController;
    private HaloView haloView;
    private Halo haloModel;
    private int haloX, haloY;

    public HaloController(int x, int y, MazeController mazeController) {
        this.haloX = x;
        this.haloY = y;
        haloView = new HaloView(haloX, haloY);

        haloModel = new Halo(haloX, haloY, mazeController.mazeModel);
        this.mazeController = mazeController;
        mazeController.mazeView.add(haloView);
    }

    public void refreshHalo(int newX, int newY){
        this.haloX = newX;
        this.haloY = newY;
        haloView.setX(haloX);
        haloView.setY(haloY);
        this.haloView.repaint();
    }


//    public HaloController(MazeController mazeController) {
//        this.mazeController = mazeController;
//        haloView = new HaloView();
//        mazeController.mazeView.add(haloView);
//
//        haloModel = new Halo();
//    }
}