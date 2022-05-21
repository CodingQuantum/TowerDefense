class ERGEBNISMENUE implements UIOBJEKT
{
	VEKTOR position;
	BILD hintergrund;
	TASTER ok;
	
	KARTENAUSWAHL kartenauswahl;
			
	ERGEBNISMENUE(KARTENAUSWAHL kartenauswahl)
	{
		position = new VEKTOR(-960, 540);
		hintergrund = new BILD("grafiken/ergebnismenue/hintergrund.png", position, 0, 7);
		ok = new TASTER("grafiken/ergebnismenue/ok1.png", "grafiken/ergebnismenue/ok2.png", "grafiken/ergebnismenue/ok3.png", position.plus(new VEKTOR(0, 300)), this, "ok", 8);
		this.kartenauswahl = kartenauswahl;
	}
	
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		ok.positionSetzen(position.plus(new VEKTOR(0, 300)));
	}
	
	//entfernt das Ergebnismenue
	void entfernen()
	{
		hintergrund.entfernen();
		ok.entfernen();
	}
	
	public void tasterGedrueckt(String id)
	{
		if(id == "ok")
		{
			kartenauswahl.x = 0;
			kartenauswahl.aktiv = true;
			kartenauswahl.karteAusgewaehlt = false;
		}
	}
	
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
