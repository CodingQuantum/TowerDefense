import java.awt.MouseInfo;

//Oberflaeche, die beim eigentlichen Spielen angezeigt wird
class OBERFLAECHE implements UIOBJEKT
{
	BILD hintergrund;
	TEXTFELD geld;
	TEXTFELD leben;
	TEXTFELD welle;
	TASTER [] tuerme;
	SCHALTER pause;
	TASTER schliessen;
	TURMVORSCHAU vorschau;
	boolean baumodus;
	VERWALTUNG verwaltung;
	
	//erzeugt die Oberflaeche
	OBERFLAECHE(VERWALTUNG verwaltung)
	{
		hintergrund = new BILD("grafiken/oberflaeche/oberflaeche.png", new VEKTOR(960, 540), 0, 3);
		geld = new TEXTFELD("", new VEKTOR(100, 50), 50, 4);
		leben = new TEXTFELD("", new VEKTOR(100, 120), 50, 4);
		welle = new TEXTFELD("", new VEKTOR(100, 190), 50, 4);
		tuerme = new TASTER[1];
		for(int i = 0; i < tuerme.length; i++)
		{
			tuerme[i] = new TASTER("grafiken/oberflaeche/turmSchalter" + i + "1.png", "grafiken/oberflaeche/turmSchalter" + i + "2.png", "grafiken/oberflaeche/turmSchalter" + i + "3.png", new VEKTOR(1775, 145 + 220 * i), this, "turmSchalter" + (i + 1), 5);
		}
		pause = new SCHALTER("grafiken/oberflaeche/pause1.png", "grafiken/oberflaeche/pause2.png", "grafiken/oberflaeche/pause3.png", new VEKTOR(1520, 100), this, "pause", 6);
		schliessen = new TASTER("grafiken/oberflaeche/schliessen1.png", "grafiken/oberflaeche/schliessen2.png", "grafiken/oberflaeche/schliessen3.png", new VEKTOR(1370, 100), this, "schliessen", 6);
		vorschau = new TURMVORSCHAU(1, this, "vorschau", 3);
		baumodus = false;
		this.verwaltung = verwaltung;
	}
	
	//wird ein mal pro Frame aufgerufen
	void prozess()
	{
		if(baumodus == true)
		{
			VEKTOR mausposition = new VEKTOR((int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY());
			VEKTOR positionAlt = vorschau.position;
			vorschau.positionSetzen(new VEKTOR(mausposition.x / 60 * 60 + 30, mausposition.y / 60 * 60 + 30));
			if ((vorschau.position.x - 30) / 60 <= 0) vorschau.positionSetzen(new VEKTOR(90, vorschau.position.y));
			if ((vorschau.position.x - 30) / 60 >= 25) vorschau.positionSetzen(new VEKTOR(1530, vorschau.position.y));
			if ((vorschau.position.y - 30) / 60 <= 0) vorschau.positionSetzen(new VEKTOR(vorschau.position.x, 90));
			if ((vorschau.position.y - 30) / 60 >= 17) vorschau.positionSetzen(new VEKTOR(vorschau.position.x, 990));
			if ((vorschau.position.x - 30) / 60 <= 3 && (vorschau.position.y - 30) / 60 <= 4) vorschau.positionSetzen(positionAlt);
		}
	}
	
	//deaktiviert die Turmvorschau oder setzt sie auf den Turm mit der neuen ID
	void turmvorschau(int turmId)
	{
		if(vorschau.turmId != turmId)
		{
			vorschau.turmAendern(turmId);
			baumodus = true;
		}
		else
		{
			baumodus = !baumodus;
			vorschau.positionSetzen(new VEKTOR(-1000, -1000));
		}
	}
	
	//wird beim Druecken eines Tasters aufgerufen
	public void tasterGedrueckt(String id)
	{
		if(pause.zustand == false)
		{
			switch(id)
			{
				case "turmSchalter1":
					turmvorschau(1);
					break;
				case "vorschau":
					verwaltung.bauen(vorschau.turmId, vorschau.position);
					break;
			}
		}
		if(id == "schliessen")
		{
			FENSTER.schliessen();
		}
	}
	
	//wird beim Druecken eines Schalters aufgerufen
	public void schalterGedrueckt(String id, boolean zustand)
	{
		if(id == "pause")
		{
			verwaltung.pause(zustand);
		}
	}
}