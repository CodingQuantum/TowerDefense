public class GEGNER
{
    int leben;
    int geschwindigkeit;
    LEBENSANZEIGE lebensanzeige;
    BILD bild;
    double rotation;
    VEKTOR position; 
    VERWALTUNG referenz;
    int gegnerNummer;
    
    GEGNER( VERWALTUNG verwaltung, int nummer)
    {
    	position = new VEKTOR(0, 0);
        leben = 100;
        geschwindigkeit = 1;
        referenz = verwaltung;
        bild = new BILD("../Grafik/Tank2", position, rotation, 1);
        lebensanzeige = new LEBENSANZEIGE();
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
    	lebensanzeige.anzeigeSetzen(leben); 
    }
    
   
}
