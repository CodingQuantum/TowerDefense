//Oberklasse fuer alle Tuerme
class TURM
{
    VEKTOR position;
    double rotation;
    BILD bild;
    int level;
    
    //erzeugt den Turm
    TURM(int turmId, VEKTOR position)
    {
    	this.position = position;
    	rotation = 0;
    	bild = new BILD("grafiken/turm/turm" + turmId + ".png", position, 0, 1);
    	level = 0;
    }
    
    //entfernt den Turm
    void entfernen()
    {
    	bild.entfernen();
    }

    //setzt die Rotation
    void rotationSetzen(double rotation)
    {
    	this.rotation = rotation;
    	bild.rotationSetzen(rotation);
    }
}