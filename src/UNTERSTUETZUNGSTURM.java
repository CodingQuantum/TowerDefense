//Klasse fuer Tuerme, die Werte anderer Tuerme erhoehen
class UNTERSTUETZUNGSTURM extends TURM
{
	int reichweite;
	double abklingzeitFaktor;
	double reichweiteFaktor;
	double schadenFaktor;
	double[] werte;
	
	//erzeugt den Unterstuetzungsturm
    UNTERSTUETZUNGSTURM(int turmId, VEKTOR position)
    {
    	super(turmId, position);
    	switch(turmId)
    	{
    		case 4:
    			reichweite = 360;
    			abklingzeitFaktor = 0.7;
    			reichweiteFaktor = 1.2;
    			schadenFaktor = 1.25;
    			break;
    	}
    	werte = new double [] {abklingzeitFaktor, reichweiteFaktor, schadenFaktor};
    }
}

