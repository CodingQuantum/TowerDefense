class TURM
{
    int position; //abkl�ren
    BILD bild[];
    int reichweite;
    INFO info;
    int level;  //ins Klassendiagramm erg�nzen
    
    TURM()
    {
        //Position setzen
        //Bild initalisieren
        reichweite = 17; //Wert anpassen
        info = new INFO();
        level = 1;
        

    }
    
    void Aufwerten()
    {
        ++level;
    }
    
    protected void finalize()  //Name im Klassendiagramm anpassen (Destruktor)
    {
        //evtl. mit Animation
    }
    
    void InfoAnzeigen()  //Info wieder unsichtbar setzen
    {
        info.sichtbar = true;
    }
}