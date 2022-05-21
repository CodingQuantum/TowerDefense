//Klasse fuer Tuerme, die Gegner direkt angreifen
class ANGRIFFSTURM extends TURM
{
    int rate;
    int geschwindigkeit;
    int reichweite;
    int[] geschossdaten;
    double rotation;
    
    boolean angriffsbereit;
    int zaehler;
    
    //erzeugt den Turm
    ANGRIFFSTURM(int turmId, VEKTOR position)
    {
    	super(turmId, position);
    	switch(turmId)
    	{
    		case 1:
    			rate = 40;
    	        geschwindigkeit = 1;
    	        reichweite = 240;
    	        geschossdaten = new int [] {turmId, 30, 20};
    	        break;
    	}
    	rotation = 0;
    	angriffsbereit = true;
    	zaehler = 0;
    }
    
    //wird ein mal pro Frame aufgerufen
    void prozess()
    {
    	zaehler += 1;
    	if(zaehler >= rate)
    	{
    		angriffsbereit = true;
    		zaehler = 0;
    	}
    }
    
    //setzt die Rotation
    void rotationSetzen(double rotation)
    {
    	this.rotation = rotation;
    	bild.rotationSetzen(rotation);
    }
}