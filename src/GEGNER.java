public class GEGNER
{
    int leben;
    int geschwindigkeit;
    LEBENSANZEIGE lebensanzeige;
    BILD bild;
    int rotation; //Datentyp abzusprechen mit Grafik 
    VEKTOR position; 
    
    GEGNER()
    {
        leben = 100;
        geschwindigkeit = 10;
        // bild und Lebensanzeige einfügen
        rotation = 1;
    }
    
    void bewegen()
    {
       
    }
    
    void schaden(int schaden)
    {
        leben = leben - schaden;
        if ( leben <= 0)
        {
            sterben();
        }
    }
    
    void sterben()
    {
       //den jeweiligen Gegner = null setzen
    }
    
    void lebensanzeigeSetzen(int leben)
    {
       LEBENSANZEIGE.anzeigeSetzen(leben); 
    }
    
   
}
