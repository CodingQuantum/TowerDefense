import javax.swing.*;

//Ausgabefenster
class FENSTER
{
	String name = "Tower Defense"; //Variable, um den Namen des Spiels einfach ?ndern zu k?nnen
	
	static JFrame frame;
	static JLayeredPane pane;
	
	//erzeugt das Ausgabefenster
	FENSTER()
	{
		frame = new JFrame(name);
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(getClass().getResource("grafiken/icon.png")).getImage());
		pane = frame.getLayeredPane();
	}
	
	//gibt die Darstellungsflaeche zurueck
	static JLayeredPane paneGeben()
	{
		return pane;
	}
	
	//schliesst das Ausgabefenster
	static void schliessen()
	{
		System.exit(0);
	}
	
	//entfernt ein BILD von der Darstellungsflaeche
	static void labelEntfernen(JLabel l)
    {
        pane.remove(l);
        pane.repaint();
    }
	
	//main-Methode, erzeugt das Ausgabefenster und das Spiel
	public static void main(String [] args)
	{
		System.setProperty("sun.java2d.uiScale", "1.0");
		new FENSTER();
		new MENUE(new VERWALTUNG());
		FENSTER.frame.setVisible(true);
	}
}
