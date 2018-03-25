import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MyClock extends ClockFace implements ActionListener {
    private int x,  y, width, radius, secondCounter, minuteCounter;
    private ClockHand secondHand , minuteHand, hourHand;
    private int secondLength, minuteLength, hourLength, cXM, cYM, cXS, cYS, cXH, cYH;
    private double diS, angleFrom12S, angleFrom3S, angleFrom12M, angleFrom3M, angleFrom12H, angleFrom3H;
    private double dIM, dIH;
    GeneralPath path;

    public MyClock(int x, int y, int width)
    {
        super(x, y, width);
        this.x = x;
        this.y = y;
        this.width = width;
        this.radius = width / 2;
        secondCounter = 55;
        minuteCounter = 0;

        path = new GeneralPath();

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
        secondLength = 30;
        minuteLength = 50;
        hourLength = 60;
        minuteHand.draw(g2);
        if(secondCounter <= 59)
        {
            diS = (double)secondCounter + (double)1;
            cXS = x+(width)/2;
            cYS = y+(width)/2;
            angleFrom12S = diS/60.0*2.0*Math.PI;
            angleFrom3S = Math.PI/2.0-angleFrom12S;
            secondHand.translate((int)(cXS+Math.cos(angleFrom3S)*(radius-secondLength)),
                    (int)(cYS-Math.sin(angleFrom3S)*(radius-secondLength)));
            secondHand.draw(g2);
        }
        if(secondCounter == 59)
        {

            dIM = (double)minuteCounter + (double)1;
            cXM = x+(width)/2;
            cYM = y+(width)/2;
            angleFrom12M = dIM/60.0*2.0*Math.PI;
            angleFrom3M = Math.PI/2.0-angleFrom12M;
            minuteHand.translate((int)(cXM+Math.cos(angleFrom3M)*(radius-minuteLength)),
                    (int)(cYM-Math.sin(angleFrom3M)*(radius-minuteLength)));
            minuteHand.draw(g2);

            secondCounter = -1;
            minuteCounter++;
        }
        secondCounter++;
    }
}
