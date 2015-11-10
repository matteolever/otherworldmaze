package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import enums.CellEnum;

public class HaloView extends Component {

	
    private int x;
    private int y;
    private int radius;
    
    private Color BG = new Color(34, 44, 77);


    public HaloView(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }
    
    public void setRadius(int radius){
    	this.radius = radius;
    }
    

    public int getRadius() {
		return radius;
	}

	@Override
    public void paint(Graphics graphics) {
      //  System.out.println("halo "+ x + " " + y);
        Graphics2D g = (Graphics2D) graphics;
////        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setColor(new Color(1, 250, 250));
//        g.fillOval(x,y,80,80);
//
        //TODO: coordinates are +20 now, should be centered automatically
        Point2D center = new Point2D.Float(x+CellEnum.SIZE.getType()/2, y+CellEnum.SIZE.getType()/2);
        float[] dist = {0.1f, 0.6f, 1.0f};
        Color[] colors = {new Color(0,0,0,0), new Color(0,0,0,0), BG};
        RadialGradientPaint p = new RadialGradientPaint(center, this.radius, dist, colors);
        g.setPaint(p);
        g.fillRect(0,0,this.getWidth(),getHeight());
    }

}
