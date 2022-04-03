public class GEGNER
{
    int leben;
    int geschwindigkeit;
    //LEBENSANZEIGE lebensanzeige;
    //BILD bild;
    int rotation; //abzusprechen mit Grafik 
    int position; //Absprache mit Gruppe
    
    GEGNER()
    {
        leben = 100;
        geschwindigkeit = 10;
        // bild und Lebensanzeige einfügen
        rotation = 1;
        position = 1;
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
       //den jewiligenGegner = null setzen
    }
    
    void lebensanzeigeSetzen(int leben)
    {
       LEBENSANZEIGE.anzeigeSetzen(leben); 
    }
}