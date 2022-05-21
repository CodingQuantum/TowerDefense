//Klasse fuer die Verwaltung eines Gegners
class GEGNER
{
    VEKTOR position;
    double rotation;
    int leben;
    int geschwindigkeit;
    int schaden;
    int belohnung;
    LEBENSANZEIGE lebensanzeige;
    BILD bild;
    
    int lebenMaximal;
    VEKTOR bewegung;
    KARTE karte;
    VEKTOR wegpunkt;
    int wegpunktNummer;
    VERWALTUNG verwaltung;
    
    //erzeugt den Gegner (am ersten Wegpunkt)
    GEGNER(KARTE karte, int gegnerId, int nummer, VERWALTUNG verwaltung)
    {
    	switch(gegnerId)
        {
        	case 0:
        		lebenMaximal = 100;
        		leben = lebenMaximal;
                geschwindigkeit = 50;
                schaden = 100;
                belohnung = 10;
        		break;
        }
    	
    	position = karte.wegpunkt(0).plus(new VEKTOR(0, -nummer * 120));
    	bewegung = new VEKTOR(0, geschwindigkeit);
        rotation = 0;
        lebensanzeige = new LEBENSANZEIGE(position, 3);
        bild = new BILD("grafiken/gegner/gegner" + gegnerId + ".png", position, rotation, 2);
        this.karte = karte;
        wegpunkt = karte.wegpunkt(0);
        wegpunktNummer = 0;
        this.verwaltung = verwaltung;
    }
    
    //wird einmal pro Frame aufgerufen
    void prozess()
    {	
    	VEKTOR zuWegpunkt = wegpunkt.plus(position.mal(-1));
    	if(Math.abs(zuWegpunkt.x) + Math.abs(zuWegpunkt.y) < geschwindigkeit)
    	{
    		position = wegpunkt;
    		wegpunkt = karte.wegpunkt(++wegpunktNummer);
    		
    		zuWegpunkt = wegpunkt.plus(position.mal(-1));
    		
    		if(zuWegpunkt.x > 0)
    		{
    			bewegung = new VEKTOR(geschwindigkeit, 0);
    			rotation = Math.PI / 2;
    		}
    		else if(zuWegpunkt.x < 0)
    		{
    			bewegung = new VEKTOR(-geschwindigkeit, 0);
    			rotation = 3 * Math.PI / 2;
    		}
    		else if(zuWegpunkt.y > 0)
    		{
    			bewegung = new VEKTOR(0, geschwindigkeit);
    			rotation = Math.PI;
    		}
    		else if(zuWegpunkt.y < 0)
    		{
    			bewegung = new VEKTOR(0, -geschwindigkeit);
    			rotation = 0;
    		}
    		bild.rotationSetzen(rotation);
    	}
    	else
    	{
    		position = position.plus(bewegung);
    	}
    	
    	bild.positionSetzen(position);
    	lebensanzeige.positionSetzen(position);
    	lebensanzeige.anzeigeSetzen((double) leben / (double) lebenMaximal);
    }
    
    //entfernt den Gegner
    void entfernen()
    {
    	bild.entfernen();
    	lebensanzeige.entfernen();
    }
}
