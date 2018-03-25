import java.awt.*;
import javax.swing.*;

/**
 This program implements an animation that moves
 a car shape.
 */
public class ClockTester
{
    private static final int CLOCK_RADIUS = 500;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        MyClock clock = new MyClock(0, 0, CLOCK_RADIUS);
        frame.setLayout(new BorderLayout());

        JPanel topNav = new JPanel(new FlowLayout());
        topNav.add(new JButton("clock"));
        topNav.add(new JButton("stopwatch"));
        frame.add(topNav, BorderLayout.NORTH);

        JPanel primary = new JPanel();
        primary.setBackground(Color.YELLOW);
        primary.add(clock);

        frame.add(primary, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
