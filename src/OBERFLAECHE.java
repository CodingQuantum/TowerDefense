import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

class OBERFLAECHE implements UIOBJEKT
{
	BILD hintergrund;
	TEXTFELD geld;
	TEXTFELD leben;
	TEXTFELD welle;
	TASTER [] tuerme;
	TURMVORSCHAU vorschau;
	boolean bauen;
	VERWALTUNG verwaltung;
	
	OBERFLAECHE(VERWALTUNG verwaltung)
	{
		hintergrund = new BILD("grafiken/oberflaeche/oberflaeche.png", new VEKTOR(960, 540), 0, 3);
		geld = new TEXTFELD("", new VEKTOR(100, 50), 50, 4);
		leben = new TEXTFELD("", new VEKTOR(100, 120), 50, 4);
		welle = new TEXTFELD("", new VEKTOR(100, 190), 50, 4);
		tuerme = new TASTER[1];
		for (int i = 0; i < tuerme.length; i++)
		{
			tuerme[i] = new TASTER("grafiken/oberflaeche/turmSchalter" + i + "1.png", "grafiken/oberflaeche/turmSchalter" + i + "2.png", "grafiken/oberflaeche/turmSchalter" + i + "3.png", new VEKTOR(1775, 145 + 220 * i), this, "turmSchalter" + (i + 1), 5);
		}
		vorschau = new TURMVORSCHAU(0, this, "vorschau", 4);
		bauen = false;
		this.verwaltung = verwaltung;
	}
	
	void prozess()
	{
		if (bauen == true)
		{
			VEKTOR mausposition = new VEKTOR((int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY());
			vorschau.positionSetzen(new VEKTOR(mausposition.x / 60 * 60 + 30, mausposition.y / 60 * 60 + 30));
			if ((vorschau.position.x - 30) / 60 <= 0) vorschau.positionSetzen(new VEKTOR(90, vorschau.position.y));
			if ((vorschau.position.x - 30) / 60 >= 25) vorschau.positionSetzen(new VEKTOR(1530, vorschau.position.y));
			if ((vorschau.position.y - 30) / 60 <= 0) vorschau.positionSetzen(new VEKTOR(vorschau.position.x, 90));
			if ((vorschau.position.y - 30) / 60 >= 17) vorschau.positionSetzen(new VEKTOR(vorschau.position.x, 990));
		}
	}
	
	void bauen(int id, VEKTOR position)
	{
		
	}
	
	void ende()
	{
		
	}
	
	void turmvorschau(int turmId)
	{
		if (vorschau.turmId != turmId)
		{
			vorschau.entfernen();
			vorschau = new TURMVORSCHAU(turmId, this, "vorschau", 4);
			bauen = true;
		}
		else
		{
			bauen = !bauen;
			vorschau.positionSetzen(new VEKTOR(-100, -100));
		}
	}
	
	public void tasterGedrueckt(String turmId)
	{
		switch(turmId)
		{
			case "turmSchalter1":
				turmvorschau(1);
				break;
			case "vorschau":
				verwaltung.bauen(vorschau.turmId, vorschau.position);
				break;
		}
	}
	
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}