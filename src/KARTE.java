import java.awt.image.*;
import javax.swing.*;

//Klasse fuer die Verwaltung der Karte, auf der Gegner laufen und Tuerme platziert werden
class KARTE
{
	BILD hintergrund;
	boolean [][] matrix;
	VEKTOR[] wegpunkte;
	
	String wegpunkteKarte1 = "5,-1/5,5/13,5/13,9/3,9/3,15/19,15/19,4/24,4/24,10/28,10";
	
	//erzeugt die Karte
	KARTE()
	{
		matrix = new boolean[27][18];
		hintergrund = new BILD("grafiken/empty.png", new VEKTOR(810, 540), 0, 0);
	}
	
	//initialisiert die Karte
	void karteSetzen(int kartenId)
	{
		hintergrund.entfernen();
		hintergrund = new BILD("grafiken/karten/karte" + kartenId + ".png", new VEKTOR(810, 540), 0, 0);
		matrixErzeugen(kartenId);
		wegpunkteErzeugen(kartenId);
	}
	
	//erzeugt eine Tabelle auf Basis einer Bilddatei, die angibt, wo Tuerme platziert werden koennen
	void matrixErzeugen(int kartenId)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource("grafiken/karten/karteGitter" + kartenId + ".png"));
		BufferedImage i = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		i.getGraphics().drawImage(icon.getImage(), 0,0, icon.getImageObserver());
		for(int x = 0; x < 27; ++x)
		{
			for(int y = 0; y < 18; y++)
			{
				if(i.getRGB(x, y) != 0)
				{
					matrix[x][y] = false;
				}
				else
				{
					matrix[x][y] = true;
				}
			}
		}
	}
	
	//erzeugt ein Array an Wegpunkten auf Basis eines Strings
	void wegpunkteErzeugen(int kartenId)
	{
		String karte = "";
		switch(kartenId)
		{
			case 1:
				karte = wegpunkteKarte1;
				break;
		}
		String[] vektoren = karte.split("/");
		wegpunkte = new VEKTOR[vektoren.length];
		for(int i = 0; i < wegpunkte.length; ++i)
		{
			String[] werte = vektoren[i].split(",");
			wegpunkte[i] = new VEKTOR(Integer.parseInt(werte[0]) * 60, Integer.parseInt(werte[1]) * 60);
		}
	}
	
	//gibt zurueck, ob die betreffende Stelle auf der Karte frei ist
	boolean stelleFrei(VEKTOR position)
	{
		return matrix[position.x / 60][position.y / 60];
	}
	
	//gibt den Wegpunkt mit dem angegebenen Index zurueck
	VEKTOR wegpunkt(int wegpunkt)
	{
		if(wegpunkt < wegpunkte.length)
		{
			return wegpunkte[wegpunkt].plus(new VEKTOR(30, 30));
		}
		return new VEKTOR(3000, 3000);
	}
}
