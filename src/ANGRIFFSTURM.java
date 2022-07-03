//Klasse fuer Tuerme, die Gegner direkt angreifen
class ANGRIFFSTURM extends TURM
{
    int abklingzeit;
    int reichweite;
    int geschwindigkeit;
    int schaden;
    int[] geschossdaten;
    
    boolean angriffsbereit;
    int zaehler;
    GEGNER ziel;
    int anzahlUnterstuetzung;
    
    //erzeugt den Turm
    ANGRIFFSTURM(int turmId, VEKTOR position)
    {
    	super(turmId, position);
    	switch(turmId)
    	{
    		case 1:
    			abklingzeit = 50;
    	        reichweite = 240;
    	        geschwindigkeit = 60;
    	        schaden = 50;
    	        break;
    		case 2:
    			abklingzeit = 5;
    	        reichweite = 120;
    	        geschwindigkeit = 60;
    	        schaden = 15;
    	        break;
    		case 3:
    			abklingzeit = 100;
    	        reichweite = 480;
    	        geschwindigkeit = 60;
    	        schaden = 300;
    	        break;
    	}
    	geschossdaten = new int [] {turmId, geschwindigkeit, schaden};
    	angriffsbereit = true;
    	zaehler = 0;
    	anzahlUnterstuetzung = 0;
    }
    
    //wird ein mal pro Frame aufgerufen
    void prozess()
    {
    	zaehler += 1;
    	if(zaehler >= abklingzeit)
    	{
    		angriffsbereit = true;
    		zaehler = 0;
    	}
    	
    	if(ziel != null)
    	{
    		VEKTOR delta = ziel.position.plus(position.mal(-1));
    		if(delta.laenge() <= reichweite)
    		{
    			double rotationAlt = rotation;
    			rotationSetzen(Math.atan2(delta.y, delta.x) + Math.PI / 2);
    		}
    	}
    }
    
    void wirdUnterstuezt(double[] werte)
    {
    	if(anzahlUnterstuetzung < 3)
    	{
	    	abklingzeit *= werte[0];
	    	reichweite *= werte[1];
	    	schaden *= werte[2];
	    	anzahlUnterstuetzung += 1;
    	}
    }
}