class STATISTIKEN 
{
	DATENBANK datenbank;
	BILD hintergrund;
	TEXTFELD[] werte;
	TEXTFELD[] bezeichner;
	
	VEKTOR position;
	
	STATISTIKEN(DATENBANK datenbank, VEKTOR position, int ebene)
	{
		this.datenbank = datenbank;
		this.position = position;
		//TEST
		int[] daten = new int [5];
		String[] namen = new String[0];
		
		daten = datenbank.statistiken;
		namen = new String [] {"Höchste Welle Hügel", "Höchste Welle Wüste", "Getötete Gegner", "Platzierte Türme", "Geschossene Geschosse"};
		
		hintergrund = new BILD("grafiken/statistiken/hintergrund.png", position, 0, ebene);
		werte = new TEXTFELD[5];
		bezeichner = new TEXTFELD[werte.length];
		for(int i = 0; i < werte.length; ++i)
		{
			werte[i] = new TEXTFELD(String.valueOf(daten[i]), position.plus(new VEKTOR(100, -220 + i * 100)), 30, ebene + 1);
			bezeichner[i] = new TEXTFELD(namen[i], position.plus(new VEKTOR(-270, -220 + i * 100)), 30, ebene + 1);
		}
	}
	
	void anzeigeSetzen()
	{
		for(int i = 0; i < werte.length; ++i)
		{
			werte[i].textSetzen(datenbank.statistiken[i]);
		}
	}
	
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		hintergrund.positionSetzen(position);
		for(int i = 0; i < werte.length; ++i)
		{
			werte[i].positionSetzen(position.plus(new VEKTOR(100, -220 + i * 100)));
			bezeichner[i].positionSetzen(position.plus(new VEKTOR(-270, -220 + i * 100)));
		}
	}
}
