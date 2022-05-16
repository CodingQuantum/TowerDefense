//Menue, das beim Starten des Spiels angezeigt wird
class STARTBILDSCHIRM implements UIOBJEKT
{
	BILD hintergrund;
	TASTER start;
	TASTER schliessen;
	
	boolean aktiv;
	VEKTOR position;
	double x;
	
	//erzeugt das Startmenue
	STARTBILDSCHIRM()
	{
		hintergrund = new BILD("grafiken/startmenue/hintergrund.png", new VEKTOR(920, 540), 0, 7);
		start = new TASTER("grafiken/startmenue/start1.png", "grafiken/startmenue/start2.png", "grafiken/startmenue/start3.png", new VEKTOR(960, 700), this, "start", 8);
		schliessen = new TASTER("grafiken/startmenue/schliessen1.png", "grafiken/startmenue/schliessen2.png", "grafiken/startmenue/schliessen3.png", new VEKTOR(960, 900), this, "schliessen", 8);
		aktiv = true;
		position = new VEKTOR(920, 540);
		x = 0;
	}
	
	//setzt die Position
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		start.positionSetzen(position.plus(new VEKTOR(0, 160)));
		schliessen.positionSetzen(position.plus(new VEKTOR(0, 360)));
	}
	
	//fuehrt Aktionen beim Druecken der Knoepfe aus
	@Override
	public void tasterGedrueckt(String id)
	{
		if (id == "schliessen")
		{
			FENSTER.schliessen();
		}
		else if (id == "start")
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
