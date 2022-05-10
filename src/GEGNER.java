public class GEGNER
{
    int leben;
    int geschwindigkeit;
    LEBENSANZEIGE lebensanzeige;
    BILD bild;
    int rotation; //Datentyp abzusprechen mit Grafik 
    VEKTOR position; 
    VERWALTUNG referenz;
    int gegnerNummer;
    
    GEGNER( VERWALTUNG verwaltung, int nummer)
    {
        leben = 100;
        geschwindigkeit = 1;
        referenz = verwaltung;
        // bild und Lebensanzeige einfügen
        rotation = 1;
        gegnerNummer = nummer;
    }
    
    void bewegen()
    {
       
    }
    
    void schaden(int schaden)
    {
        leben = leben - schaden;
        if ( leben <= 0)
        {
           referenz.gegner.remove(gegnerNummer);
        }
    }
    
    
    void lebensanzeigeSetzen(int leben)
    {
       LEBENSANZEIGE.anzeigeSetzen(leben); 
    }
    
   
}
