class STATISTIKEN 
{
	DATENBANK datenbank;
	BILD hintergrund;
	TEXTFELD[] werte;
	TEXTFELD[] bezeichner;
	
	VEKTOR position;
	
	STATISTIKEN(int index, DATENBANK datenbank, VEKTOR position, int ebene)
	{
		this.datenbank = datenbank;
		this.position = position;
		//TEST
		int[] daten = new int [] {836737, 311205, 8964, 43926, 519624};
		String[] namen = new String[0];
		
		switch(index)
		{
			case 0:
				//int[] daten = datenbank.irgendwas();
				namen = new String [] {"Höchste Welle", "Meistes Geld", "Getötete Gegner", "Platzierte Türme", "Geschossene Geschosse"};
				break;
		}
		
		hintergrund = new BILD("grafiken/statistiken/hintergrund.png", position, 0, ebene);
		werte = new TEXTFELD[5];
		bezeichner = new TEXTFELD[werte.length];
		for(int i = 0; i < werte.length; ++i)
		{
			werte[i] = new TEXTFELD(String.valueOf(daten[i]), position.plus(new VEKTOR(100, -220 + i * 100)), 30, ebene + 1);
			bezeichner[i] = new TEXTFELD(namen[i], position.plus(new VEKTOR(-270, -220 + i * 100)), 30, ebene + 1);
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
