class TURM
{
    VEKTOR position;
    BILD bild;
    int reichweite;
    INFO info;
    int level;  //ins Klassendiagramm ergänzen
    
    TURM()
    {
        

    }
    
    void Aufwerten()
    {
        ++level;
    }
    
    
    void InfoAnzeigen()  //Info wieder unsichtbar setzen
    {
        info.sichtbar = true;
    }
}