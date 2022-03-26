import javax.swing.*;

public class FENSTER
{
    private static JLayeredPane pane;
    private static JFrame fenster;
    
    public FENSTER()
    {
        fenster = new JFrame("TowerDefense");
        fenster.setUndecorated(true);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setResizable(false);
        fenster.setVisible(true);
        fenster.setLayout(null);
        fenster.setSize(1920, 1080);
        pane = fenster.getLayeredPane();
    }
    
    public static JLayeredPane paneGeben()
    {
        return pane;
    }
    
    public static void bildEntfernen(JLabel l)
    {
        pane.remove(l);
        pane.repaint();
    }
    
    public static void schliessen()
    {
        fenster.dispose();
    }
    
    public static void main(String [] args)
    {
        System.setProperty("sun.java2d.uiScale", "1.0");
        new FENSTER();
        new BILD(50, 50, 200, 200, "test.png", 1);
    }
}
