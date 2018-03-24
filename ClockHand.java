import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ClockHand implements MoveableShape{

    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private int strokeWidth;
    private Color color;

    public ClockHand(int xStart, int yStart, int xEnd, int yEnd, int strokeWidth, Color color)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.strokeWidth = strokeWidth;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.setColor(color);
        g2.draw(new Line2D.Double(xStart, yStart, xEnd, yEnd));
    }

    @Override
    public void translate(int dx, int dy) {
        xEnd = dx;
        yEnd = dy;
    }

    public int getXEnd()
    {
        return xEnd;
    }

    public int getYEnd()
    {
        return yEnd;
    }
}