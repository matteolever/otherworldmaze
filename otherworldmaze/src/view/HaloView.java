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

//    @Override
//    public void paint(Graphics graphics) {
//        System.out.println("halo "+ x + " " + y);
//        Graphics2D g = (Graphics2D) graphics;
////        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setColor(new Color(1, 250, 250));
//        g.fillOval(x,y,80,80);
////
////        Point2D center = new Point2D.Float(50, 50);
////        float radius = 25;
////        float[] dist = {0.0f, 0.2f, 1.0f};
////        Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
////        RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
////        g.setPaint(p);
//    }

}
