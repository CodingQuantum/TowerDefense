import java.awt.image.*;
import javax.swing.*;

class KARTE
{
	BILD karte;
	boolean [][] matrix;
	VEKTOR[] wegpunkte;
	
	String karte1 = "300,0/1560,600";
	
	KARTE(int kartenId)
	{
		matrix = new boolean[27][18];
		bildErzeugen(kartenId);
		matrixErzeugen(kartenId);
		wegpunkteErzeugen(kartenId);
	}
	
	void bildErzeugen(int kartenId)
	{
		karte = new BILD("grafiken/karten/karte" + kartenId + ".png", new VEKTOR(810, 540), 0, 0);
	}
	
	void matrixErzeugen(int kartenId)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource("grafiken/karten/karteGitter" + kartenId + ".png"));
		BufferedImage i = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		i.getGraphics().drawImage(icon.getImage(), 0,0, icon.getImageObserver());
		for (int x = 0; x < 27; ++x)
		{
			for (int y = 0; y < 18; y++)
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
	
	void wegpunkteErzeugen(int kartenId)
	{
		String karte = "";
		switch(kartenId)
		{
			case 1:
				karte = karte1;
				break;
		}
		String[] vektoren = karte.split("/");
		wegpunkte = new VEKTOR[vektoren.length];
		for (int i = 0; i < wegpunkte.length; ++i)
		{
			String[] werte = vektoren[i].split(",");
			wegpunkte[i] = new VEKTOR(Integer.parseInt(werte[0]), Integer.parseInt(werte[1]));
		}
	}
	
	boolean stelleFrei(VEKTOR position)
	{
		if (matrix[position.x / 60][position.y / 60] == false)
		{
			return false;
		}
		return true;
	}
	
	VEKTOR naechsterWegpunkt(int letzterWegpunkt)
	{
		if (letzterWegpunkt + 1 < wegpunkte.length)
		{
			return wegpunkte[letzterWegpunkt + 1];
		}
		return new VEKTOR(131072, 131072);
	}
}
