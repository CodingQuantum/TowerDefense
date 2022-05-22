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
    
    //erzeugt den Turm
    ANGRIFFSTURM(int turmId, VEKTOR position)
    {
    	super(turmId, position);
    	switch(turmId)
    	{
    		case 1:
    			abklingzeit = 40;
    	        reichweite = 240;
    	        geschwindigkeit = 30;
    	        schaden = 20;
    	        geschossdaten = new int [] {turmId, geschwindigkeit, schaden};
    	        break;
    	}
    	angriffsbereit = true;
    	zaehler = 0;
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
    }
}