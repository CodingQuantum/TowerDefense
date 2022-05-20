//Oberklasse fuer alle Tuerme
class TURM
{
    VEKTOR position;
    BILD bild;
    int level;
    
    //erzeugt den Turm
    TURM(int turmId, VEKTOR position)
    {
    	this.position = position;
    	level = 0;
        bild = new BILD("grafiken/turm/turm" + turmId + ".png", position, 0, 2);
    }
}