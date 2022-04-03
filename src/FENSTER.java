import javax.swing.*;

public class FENSTER
{
    private static JLayeredPane pane; //Darstellungsfläche bestehend aus mehreren Ebenen
    private static JFrame fenster; //Ausgabefenster
   
    //Erzeugung des Ausgabefensters
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
    
    //gibt die Darstellungsfläche zurück
    public static JLayeredPane paneGeben()
    {
        return pane;
    }
    
    //Methode, die von der Klasse BILD zum Entfernen genutzt wird
    public static void bildEntfernen(JLabel l)
    {
        pane.remove(l);
        pane.repaint();
    }
    
    //schließt das Ausgabefenster
    public static void schliessen()
    {
        fenster.dispose();
    }
    
    //main-Methode/Start-Methode
    public static void main(String [] args)
    {
        System.setProperty("sun.java2d.uiScale", "1.0");
        new FENSTER();
        BILD b = new BILD(50, 50, "paul.png", 1);
    }
}
