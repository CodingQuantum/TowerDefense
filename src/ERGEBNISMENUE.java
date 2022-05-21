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
	
	//erzeugt das Ergebnismenue
	ERGEBNISMENUE()
	{
		position = new VEKTOR(-960, 540);
		hintergrund = new BILD("grafiken/ergebnismenue/hintergrund.png", position, 0, 7);
		ok = new TASTER("grafiken/ergebnismenue/ok1.png", "grafiken/ergebnismenue/ok2.png", "grafiken/ergebnismenue/ok3.png", position.plus(new VEKTOR(0, 300)), this, "ok", 8);
		this.kartenauswahl = kartenauswahl;
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
			//aktuell nur zum Testen
			FENSTER.schliessen();
		}
	}
	
	//wird beim Druecken eines Schalters ausgefuehrt
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
