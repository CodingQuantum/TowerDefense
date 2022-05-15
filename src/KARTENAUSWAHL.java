//Menue fuer die Auswahl der Karte
class KARTENAUSWAHL implements UIOBJEKT
{
	int anzahlKarten = 1; //Variable, um einfach die Anzahl der Karten aendern zu koennen
	
	BILD hintergrund;
	TASTER [] karten;
	TASTER zurueck;
	
	boolean aktiv;
	VEKTOR position;
	double x;
	
	//erzeugt das Auswahlmenue
	KARTENAUSWAHL()
	{
		hintergrund = new BILD("grafiken/kartenauswahl/hintergrund.png", new VEKTOR(960, 540), 0, 0);
		karten = new TASTER[anzahlKarten];
		for (int i = 0; i < karten.length; ++i)
		{
			karten[i] = new TASTER("grafiken/kartenauswahl/karte" + i + "1.png", "grafiken/kartenauswahl/karte" + i + "2.png", "grafiken/kartenauswahl/karte" + i + "3.png", new VEKTOR(350 + 500 * i, 880), this, "karte" + i, 1);
		}
		zurueck = new TASTER("grafiken/kartenauswahl/zurueck1.png", "grafiken/kartenauswahl/zurueck2.png", "grafiken/kartenauswahl/zurueck3.png", new VEKTOR(1570, 880), this, "zurueck", 1);
		aktiv = false;
		position = new VEKTOR(-960, 540);
		x = Math.PI / 2;
	}
	
	//setzt die Position
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		for (int i = 0; i < karten.length; ++i)
		{
			karten[i].positionSetzen(position);
		}
		zurueck.positionSetzen(position.plus(new VEKTOR(610, 340)));
	}
	
	@Override
	public void tasterGedrueckt(String id)
	{
		if (id == "zurueck" && x > Math.PI / 2 - 0.05)
		{
			aktiv = false;
			x = 0;
		}
	}

	@Override
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
