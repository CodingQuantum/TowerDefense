import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;



public class VERWALTUNG {
	Timer timerProzess, timerWelle;
	KARTE karte;
	DATENBANK  datenbank;
	OBERFLAECHE oberflaeche;
	Vector <ANGRIFFSTURM> angriffstuerme;
	Vector <UNTERSTUETZUNGSTURM> unterstuetzungstuerme;
	Vector <GEGNER> gegner;
	int geld;
	int leben;
	int wellennummer;
	
	VERWALTUNG(int kartenId)
	{
		karte = new KARTE(kartenId);
		oberflaeche = new OBERFLAECHE(this);
        angriffstuerme = new Vector<>();
        unterstuetzungstuerme = new Vector<>();
        gegner = new Vector<>();
        geld = 0;
        leben = 100;
        wellennummer = 0;
		timerProzess = new Timer(10, new ActionListener(){public void actionPerformed(ActionEvent e) {prozess();}});
		timerProzess.start();
		timerWelle = new Timer(10000, new ActionListener(){public void actionPerformed(ActionEvent e) {welle(wellennummer);}});
		timerWelle.start();

	}
	
	void prozess()
	{
		oberflaeche.prozess();
		oberflaeche.geld.textSetzen(geld);
		oberflaeche.leben.textSetzen(leben);
		oberflaeche.welle.textSetzen(wellennummer);
	}
	
	void pause()
	{
		
	}
	
	void speichern()
	{
		
	}
	
	
	
	void welle(int welle)
	{
		int anzahlGegner = (welle + 1) * 2;
		for(; anzahlGegner > 0; --anzahlGegner)
			gegner.add(new GEGNER(this, gegner.size()));
		wellennummer += 1;
	}
	
	void bauen(int id, VEKTOR position)
	{
		if(karte.stelleFrei(position))
			angriffstuerme.add(new ANGRIFFSTURM(position));
	}
	
	void ende()
	{
		
	}
}
