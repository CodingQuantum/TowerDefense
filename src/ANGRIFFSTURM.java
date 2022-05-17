class ANGRIFFSTURM extends TURM
{
    int rate;
    int geschwindigkeit;
    int schaden;
    GESCHOSS geschossdaten[];
    
    ANGRIFFSTURM(VEKTOR pos)
    {
    	position = pos;
        bild = new BILD("grafiken/turmvorschau/turm11.png", position, 0, 1);
        rate = 1;
        geschwindigkeit = 1;
        schaden = 7;
        //Geschossdaten
    }
    
    void angreifen(/*ZIEL*/) //Zielformat abklären
    {
        
    }
}