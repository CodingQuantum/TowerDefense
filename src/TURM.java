class TURM
{
    VEKTOR position;
    BILD bild;
    int reichweite;
    INFO info;
    int level;  //ins Klassendiagramm erg�nzen
    
    TURM()
    {
        

    }
    
    void Aufwerten()
    {
        ++level;
    }
    
    
    void InfoAnzeigen()  
    {
        info.sichtbar = true;
    }

    
    void InfoVerstecken()
    {
    	info.sichtbar = false;
    }
    
}