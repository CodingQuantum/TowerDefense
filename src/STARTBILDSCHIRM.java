//Menue, das beim Starten des Spiels angezeigt wird
class STARTBILDSCHIRM implements UIOBJEKT
{
	BILD hintergrund;
	TASTER start;
	TASTER schliessen;
	STATISTIKEN statistiken;
	
	boolean aktiv;
	VEKTOR position;
	double x;
	
	//erzeugt das Startmenue
	STARTBILDSCHIRM()
	{
		hintergrund = new BILD("grafiken/startmenue/hintergrund.png", new VEKTOR(920, 540), 0, 12);
		start = new TASTER("grafiken/startmenue/start1.png", "grafiken/startmenue/start2.png", "grafiken/startmenue/start3.png", new VEKTOR(960, 700), this, "start", 13);
		schliessen = new TASTER("grafiken/startmenue/schliessen1.png", "grafiken/startmenue/schliessen2.png", "grafiken/startmenue/schliessen3.png", new VEKTOR(960, 900), this, "schliessen", 13);
		statistiken = new STATISTIKEN(0, null, new VEKTOR(1600, 270), 13);
		aktiv = true;
		position = new VEKTOR(920, 540);
		x = 0;
	}
	
	//setzt die Position
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		start.positionSetzen(position.plus(new VEKTOR(40, 160)));
		schliessen.positionSetzen(position.plus(new VEKTOR(40, 360)));
		statistiken.positionSetzen(position.plus(new VEKTOR(680, -270)));
	}
	
	//fuehrt Aktionen beim Druecken der Taster aus
	public void tasterGedrueckt(String id)
	{
		if(id == "schliessen")
		{
			FENSTER.schliessen();
		}
		else if(id == "start")
		{
			aktiv = false;
			x = 0;
		}
	}
	
	//fuehrt Aktionen beim Druecken der Schalter aus
	public void schalterGedrueckt(String id, boolean zustand)
	{
		
	}
}
