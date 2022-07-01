//Menue fuer die Auswahl der Karte
class KARTENAUSWAHL implements UIOBJEKT
{
	int anzahlKarten = 2; //Variable, um einfach die Anzahl der Karten aendern zu koennen
	
	BILD hintergrund;
	TASTER [] karten;
	TASTER zurueck;
	
	boolean aktiv;
	boolean karteAusgewaehlt;
	VEKTOR position;
	double x;
	VERWALTUNG verwaltung;
	
	//erzeugt das Auswahlmenue
	KARTENAUSWAHL()
	{
		hintergrund = new BILD("grafiken/kartenauswahl/hintergrund.png", new VEKTOR(920, 540), 0, 10);
		karten = new TASTER[anzahlKarten];
		for(int i = 1; i <= karten.length; ++i)
		{
			karten[i - 1] = new TASTER("grafiken/kartenauswahl/karte" + i + "1.png", "grafiken/kartenauswahl/karte" + i + "2.png", "grafiken/kartenauswahl/karte" + i + "3.png", new VEKTOR(410 + 550 * (i - 1), 540), this, "karte" + i, 11);
		}
		zurueck = new TASTER("grafiken/kartenauswahl/zurueck1.png", "grafiken/kartenauswahl/zurueck2.png", "grafiken/kartenauswahl/zurueck3.png", new VEKTOR(1510, 540), this, "zurueck", 11);
		aktiv = false;
		karteAusgewaehlt = false;
		position = new VEKTOR(-960, 540);
		x = Math.PI / 2;
		verwaltung = new VERWALTUNG(this);
	}
	
	//setzt die Position
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		for (int i = 0; i < karten.length; ++i)
		{
			karten[i].positionSetzen(position.plus(new VEKTOR(-510 + 550 * i, 0)));
		}
		zurueck.positionSetzen(position.plus(new VEKTOR(590, 0)));
	}
	
	//wird beim Druecken eines Tasters ausgefuehrt
	public void tasterGedrueckt(String id)
	{
		if(x > Math.PI / 2 - 0.05)
		{
			switch(id)
			{
				case "zurueck":
					aktiv = false;
					x = 0;
					break;
				case "karte1":
					karteAusgewaehlt = true;
					x = 0;
					verwaltung.start(1);
			}
		}
	}

	//wird beim Druecken eines Schalters ausgefuehrt
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
