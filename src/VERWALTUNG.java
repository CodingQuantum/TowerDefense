import java.util.Timer;
import java.util.Vector;


public class VERWALTUNG {
	Timer timer;
	KARTE karte;
	DATENBANK  datenbank;
	OBERFLAECHE oberflaeche;
	Vector <ANGRIFFSTURM> angriffstuerme;
	Vector <UNTERSTUETZUNGSTURM> unterstuetzungstuerme;
	Vector <GEGNER> gegner;
	int geld;
	int leben;
	int welle;
	
	VERWALTUNG()
	{
		timer = new Timer();
		karte = new KARTE();
		oberflaeche = new OBERFLAECHE();
        angriffstuerme = new Vector();
        unterstuetzungstuerme = new Vector();
        gegner = new Vector();
        geld = 0;
        leben = 100;
        welle = 0;


		
	}
	
	void pause()
	{
		
	}
	
	void speichern()
	{
		
	}
	
	
	//Methode prozess Funktion abklären
	
	void welle(int welle)
	{
		int anzahlGegner = welle * 2;
		for(; anzahlGegner > 0; --anzahlGegner)
			gegner.add(new GEGNER(this, gegner.size()));
	}
	
	void bauen()
	{
        angriffstuerme.add(new ANGRIFFSTURM());

	}
	
	void ende()
	{
		
	}
}
