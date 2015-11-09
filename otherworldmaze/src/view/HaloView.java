package view;

import java.awt.*;
import java.awt.geom.Point2D;

public class HaloView extends Component {

    private int x;
    private int y;


    public HaloView(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    @Override
    public void paint(Graphics graphics) {
        System.out.println("halo "+ x + " " + y);
        Graphics2D g = (Graphics2D) graphics;
////        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setColor(new Color(1, 250, 250));
//        g.fillOval(x,y,80,80);
//
        //TODO: coordinates are +20 now, should be centered automatically
        Point2D center = new Point2D.Float(x+20, y+20);
        float radius = 80;
        float[] dist = {0.3f, 0.6f, 1.0f};
        Color[] colors = {new Color(0,0,0,0), Color.WHITE, Color.BLACK};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
        g.setPaint(p);
        g.fillRect(0,0,this.getWidth(),getHeight());
    }

}
