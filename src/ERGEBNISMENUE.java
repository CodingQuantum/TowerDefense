//Klasse fuer das Menue, das nach dem Tod angezeigt wird
class ERGEBNISMENUE implements UIOBJEKT
{
	VEKTOR position;
	BILD hintergrund;
	TASTER weiter;
	TASTER erneut;
	int ergebnisse[];
	TASTER ok;
	
	KARTENAUSWAHL kartenauswahl;
	double x;
	
	//erzeugt das Ergebnismenue
	ERGEBNISMENUE(KARTENAUSWAHL kartenauswahl)
	{
		position = new VEKTOR(-960, 540);
		hintergrund = new BILD("grafiken/ergebnismenue/hintergrund.png", position, 0, 7);
		ok = new TASTER("grafiken/ergebnismenue/ok1.png", "grafiken/ergebnismenue/ok2.png", "grafiken/ergebnismenue/ok3.png", position.plus(new VEKTOR(0, 300)), this, "ok", 8);
		this.kartenauswahl = kartenauswahl;
		x = 0;
	}
	
	//setzt die Position
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		ok.positionSetzen(position.plus(new VEKTOR(0, 300)));
	}
	
	//wird beim Druecken eines Tasters ausgefuehrt
	public void tasterGedrueckt(String id)
	{
		if(id == "ok")
		{
			kartenauswahl.x = 0;
			kartenauswahl.aktiv = true;
			kartenauswahl.karteAusgewaehlt = false;
		}
	}
	
	//wird beim Druecken eines Schalters ausgefuehrt
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
