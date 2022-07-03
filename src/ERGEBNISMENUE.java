//Klasse fuer das Menue, das nach dem Tod angezeigt wird
class ERGEBNISMENUE implements UIOBJEKT
{
	VEKTOR position;
	BILD hintergrund;
	TASTER weiter;
	TASTER erneut;
	int ergebnisse[];
	TASTER ok;
	TEXTFELD welle;
	TEXTFELD geldGesamt;
	TEXTFELD gegnerGetoetet;
	
	MENUE menue;
	double x;
	
	//erzeugt das Ergebnismenue
	ERGEBNISMENUE()
	{
		position = new VEKTOR(-960, 540);
		hintergrund = new BILD("grafiken/ergebnismenue/hintergrund.png", position, 0, 7);
		ok = new TASTER("grafiken/ergebnismenue/ok1.png", "grafiken/ergebnismenue/ok2.png", "grafiken/ergebnismenue/ok3.png", position.plus(new VEKTOR(0, 300)), this, "ok", 8);
		welle = new TEXTFELD("", new VEKTOR(-500, -240), 50, 8);
		geldGesamt = new TEXTFELD("", new VEKTOR(-500, -170), 50, 8);
		gegnerGetoetet = new TEXTFELD("", new VEKTOR(-500, -100), 50, 8);
		x = 0;
	}
	
	//setzt die Position
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		ok.positionSetzen(position.plus(new VEKTOR(0, 300)));
		welle.positionSetzen(position.plus(new VEKTOR(-500, -240)));
		geldGesamt.positionSetzen(position.plus(new VEKTOR(-500, -170)));
		gegnerGetoetet.positionSetzen(position.plus(new VEKTOR(-500, -100)));
	}
	
	//wird beim Druecken eines Tasters ausgefuehrt
	public void tasterGedrueckt(String id)
	{
		if(id == "ok")
		{
			menue.kartenauswahl.x = 0;
			menue.kartenauswahl.aktiv = true;
			menue.kartenauswahl.karteAusgewaehlt = false;
			menue.startbildschirm.statistiken.anzeigeSetzen();
		}
	}
	
	//wird beim Druecken eines Schalters ausgefuehrt
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
