import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import javax.swing.*;

public class PrimaryClock extends ClockFace implements ActionListener {
    private int x;
    private int y;
    private int width;
    private int radius;
    private int secondCounter;
    private int minuteCounter;
    private ClockHand secondHand;
    private ClockHand minuteHand;
    private ClockHand hourHand;

    public PrimaryClock(int x, int y, int width)
    {
        super(x, y, width);
        this.x = x;
        this.y = y;
        this.width = width;
        this.radius = width / 2;
        secondCounter = 1;
        minuteCounter = 1;

        secondHand = new ClockHand(radius, radius, radius, 250, 5, Color.RED);
        minuteHand = new ClockHand(radius, radius, radius, 150, 5, Color.BLACK);
        hourHand = new ClockHand(radius, radius, radius, 100, 5, Color.BLACK);

        Timer time = new Timer(1000, this);
        time.start();

        setOpaque(false);
        setPreferredSize(new Dimension(width, width));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        int secondLength = 30;
        int minuteLength = 50;
        int hourLength = 60;
        int cX = x+(width)/2;
        int cY = y+(width)/2;
        double di = (double)1;
        double angleFrom12 = di/60.0*2.0*Math.PI;
        double angleFrom3 = Math.PI/2.0-angleFrom12;

        GeneralPath path = new GeneralPath();
        secondHand.translate((int)(cX+Math.cos(angleFrom3)*(radius-secondLength)),
                (int)(cY-Math.sin(angleFrom3)*(radius-secondLength)));
        secondHand.draw(g2);
        di++;
        //minuteHand.draw(g2);
        //hourHand.draw(g2);
    }
}
