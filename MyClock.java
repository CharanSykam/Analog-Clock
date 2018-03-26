import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

public class MyClock extends ClockFace implements ActionListener {
    private int x,  y, width, radius, secondCounter, minuteCounter, hourCounter;
    private ClockHand secondHand , minuteHand, hourHand;
    private int secondLength, minuteLength, hourLength, cXM, cYM, cXS, cYS, cXH, cYH, temp;
    private double diS, angleFrom12S, angleFrom3S, angleFrom12M, angleFrom3M, angleFrom12H, angleFrom3H;
    private double dIM, dIH;

    public MyClock(int x, int y, int width)
    {
        super(x, y, width);
        this.x = x;
        this.y = y;
        this.width = width;
        this.radius = width / 2;
        temp = 0;
        Calendar calendar = GregorianCalendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        secondCounter = second;
        minuteCounter = minute;
        switch(hour) {
            case 1: hour = 4; break;
            case 2: hour = 9; break;
            case 3: hour = 14; break;
            case 4: hour = 19; break;
            case 5: hour = 24; break;
            case 6: hour = 29; break;
            case 7: hour = 34; break;
            case 8: hour = 39; break;
            case 9: hour = 44; break;
            case 10: hour = 49; break;
            case 11: hour = 54; break;
            case 12: hour = 59; break;
            default: hour = 0; break;
        }
        hourCounter = hour;

        secondHand = new ClockHand(radius, radius, radius, 250, 4, Color.RED);
        minuteHand = new ClockHand(radius, radius, radius, 50,  6, Color.BLACK);
        hourHand = new ClockHand(radius, radius, radius, 40, 9, Color.BLACK);


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
        hourLength = 80;

        if(temp == 0)
        {
            minuteCounter--;
            positionMinute();
            minuteHand.draw(g2);

            positionHour();
            hourHand.draw(g2);
            temp++;
        }
        if(secondCounter <= 59)
        {
            positionSecond();
            secondHand.draw(g2);
        }
        if(secondCounter == 59)
        {
            positionMinute();
            minuteHand.draw(g2);

            secondCounter = -1;
            minuteCounter++;
        }
        if(minuteCounter == 60)
        {
            if(hourCounter == 0)
            {
                hourCounter = hourCounter + 4;
            }
            else
            {
                hourCounter = hourCounter + 5;
            }
            positionHour();
            hourHand.draw(g2);
            minuteCounter = 0;
        }

        if(hourCounter == 60)
        {
            hourCounter = 0;
        }

        secondCounter++;
        minuteHand.draw(g2);
        hourHand.draw(g2);
    }

    public void positionSecond()
    {
        diS = (double)secondCounter + (double)1;
        cXS = x+(width)/2;
        cYS = y+(width)/2;
        angleFrom12S = diS/60.0*2.0*Math.PI;
        angleFrom3S = Math.PI/2.0-angleFrom12S;
        secondHand.translate((int)(cXS+Math.cos(angleFrom3S)*(radius-secondLength)),
                (int)(cYS-Math.sin(angleFrom3S)*(radius-secondLength)));
    }

    public void positionMinute()
    {
        dIM = (double)minuteCounter + (double)1;
        cXM = x+(width)/2;
        cYM = y+(width)/2;
        angleFrom12M = dIM/60.0*2.0*Math.PI;
        angleFrom3M = Math.PI/2.0-angleFrom12M;
        minuteHand.translate((int)(cXM+Math.cos(angleFrom3M)*(radius-minuteLength)),
                (int)(cYM-Math.sin(angleFrom3M)*(radius-minuteLength)));
    }

    public void positionHour()
    {
        dIH = (double)hourCounter + (double)1;
        cXH = x+(width)/2;
        cYH = y+(width)/2;
        angleFrom12H = dIH/60.0*2.0*Math.PI;
        angleFrom3H = Math.PI/2.0-angleFrom12H;
        hourHand.translate((int)(cXH+Math.cos(angleFrom3H)*(radius-hourLength)),
                (int)(cYH-Math.sin(angleFrom3H)*(radius-hourLength)));
    }
}
