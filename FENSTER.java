import java.awt.*;
import javax.swing.*;

public class FENSTER
{
    private static JLayeredPane pane;
    
    //creates the window (size)
    public FENSTER()
    {
        JFrame fenster = new JFrame("TowerDefense");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setResizable(false);
        fenster.setVisible(true);
        fenster.setLayout(null);
        Insets i = fenster.getInsets();
        fenster.setSize(1920 + i.right + i.left, 1080 + i.top + i.bottom);
        pane = fenster.getLayeredPane();
    }
    
    //returns the pane on which graphics are displayed
    public static JLayeredPane getLayeredPane()
    {
        return pane;
    }
    
    //remove the JLabel from the window
    public static void remove(JLabel l)
    {
        pane.remove(l);
        pane.repaint();
    }
    
    public static void main(String [] args)
    {
        new FENSTER();
    }
}
