class ANGRIFFSTURM extends TURM
{
    int rate;
    int geschwindigkeit;
    int schaden;
    GESCHOSS geschossdaten[];
    
    ANGRIFFSTURM()
    {
    	position = new VEKTOR(0,0);
        bild = new BILD("../Grafiken/Turm1", position, rate, geschwindigkeit)//initialisieren von rate, geschwindigkeit, schaden
        //Geschossdaten
    }
    
    void angreifen(/*ZIEL*/) //Zielformat abklären
    {
        
    }
}