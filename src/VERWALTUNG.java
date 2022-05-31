import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import java.io.*;

//zentrale Verwaltungsklasse des eigentlichen Spiels
class VERWALTUNG
{
	Timer timer;
	KARTE karte;
	DATENBANK  datenbank;
	OBERFLAECHE oberflaeche;
	ERGEBNISMENUE ergebnismenue;
	Vector <ANGRIFFSTURM> angriffstuerme;
	Vector <UNTERSTUETZUNGSTURM> unterstuetzungstuerme;
	Vector <GEGNER> gegner;
	Vector <GESCHOSS> geschosse;
	int geld;
	int leben;
	int wellennummer;
	int[] preisliste;
	boolean pauseWellen;
	
	//erzeugt die Verwaltungsklasse
	VERWALTUNG(KARTENAUSWAHL kartenauswahl)
	{
		karte = new KARTE();
		oberflaeche = new OBERFLAECHE(this);
        angriffstuerme = new Vector<>();
        unterstuetzungstuerme = new Vector<>();
        gegner = new Vector<>();
        geschosse = new Vector<>();
        timer = new Timer(20, new ActionListener(){public void actionPerformed(ActionEvent e) {prozess();}});
		preisliste = new int [] {0, 10};
		ergebnismenue = new ERGEBNISMENUE(kartenauswahl);
		File Data = new File("./Data");
		datenbank = new DATENBANK(Data);
	}
	
	//initalisiert das Spiel
	void start(int kartenId)
	{
		for(int a = 0; a < angriffstuerme.size(); ++a)
			angriffstuerme.get(a).entfernen();
		for(int b = 0; b < unterstuetzungstuerme.size(); ++b)
			unterstuetzungstuerme.get(b).entfernen();
		for(int c = 0; c < gegner.size(); ++c)
			gegner.get(c).entfernen();
		for(int d = 0; d < geschosse.size(); ++d)
			geschosse.get(d).entfernen();
		angriffstuerme.removeAllElements();
		unterstuetzungstuerme.removeAllElements();
		gegner.removeAllElements();
		geschosse.removeAllElements();
		karte.karteSetzen(kartenId);
	    timer.start();
	    geld = 20;
	    leben = 100;
	    wellennummer = 1;
	    pauseWellen = false;
	    ergebnismenue.positionSetzen(new VEKTOR(960, 1620));
	    ergebnismenue.x = 0;
	    if(oberflaeche.pause.zustand)
	    	oberflaeche.pause.mouseClicked(null);
	}
	
	//wird ein mal pro Frame aufgerufen
	void prozess()
	{
		//Tod
		if(leben <= 0)
		{
			ende();
		}
		
		//Start der neuen Welle
		if(gegner.size() == 0)
			if(!pauseWellen)
				welle(wellennummer);
		
		//Bewegung der Gegner
		for(int i = 0; i < gegner.size(); ++i)
		{
			GEGNER g = gegner.get(i);
			g.prozess();
			if(g.wegpunkt.x == 3000)
			{
				leben -= g.schaden;
				g.entfernen();
				gegner.remove(i);
			}
			else if(g.leben <= 0)
			{
				geld += g.belohnung;
				g.entfernen();
				gegner.remove(i);
			}
		}
		
		//Aktionen der Tuerme
		for (int j = 0; j < angriffstuerme.size(); ++j)
		{
			ANGRIFFSTURM a = angriffstuerme.get(j);
			a.prozess();
			if(a.angriffsbereit)
			{
				GEGNER z = gegnerFinden(a, gegner.size() - 1);
				if(z != null)
				{
					a.angriffsbereit = false;
					a.zaehler = 0;
					geschosse.add(new GESCHOSS(a.position, z, a.geschossdaten));
					VEKTOR delta = z.position.plus(a.position.mal(-1));
					if(delta.x != 0)
					{
						a.rotationSetzen(Math.atan((double) delta.y / delta.x) + Math.PI / 2);
						if(delta.x < 0)
							a.rotationSetzen(a.rotation + Math.PI);
					}
					else
					{
						a.rotationSetzen(0);
						if(delta.y > 0)
							a.rotationSetzen(Math.PI);
					}
				}
			}		}
		
		//Bewegung der Geschosse
		for (int l = 0; l < geschosse.size(); ++l)
		{
			GESCHOSS g = geschosse.get(l);
			g.prozess();
			if(g.position.abstand(g.ziel.position) < 42)
			{
				g.ziel.leben -= g.schaden;
				g.entfernen();
				geschosse.remove(l);
			}
			if(Math.abs(g.position.x) + Math.abs(g.position.y) > 3000)
			{
				g.entfernen();
				geschosse.remove(l);
			}
		}
		
		//Aktualisierung der Oberflaeche
		oberflaeche.prozess();
		oberflaeche.geld.textSetzen(geld);
		oberflaeche.leben.textSetzen(leben);
		oberflaeche.welle.textSetzen(wellennummer - 1);
	}
	
	//Aktion, die beim Verlieren des Spiels ausgefuehrt wird
	void ende()
	{
		datenbank.setHighscore(wellennummer - 2);
		leben = 0;
		pauseWellen = true;
		if(ergebnismenue.position.y > 540)
		{
			ergebnismenue.positionSetzen(new VEKTOR(960, (int) (-540 * Math.sin(ergebnismenue.x - Math.PI / 2) + 1080)));
			if(ergebnismenue.x < Math.PI && ergebnismenue.x > Math.PI - 0.1)
			{
				ergebnismenue.positionSetzen(new VEKTOR(960, 540));
				timer.stop();
			}
			ergebnismenue.x += 0.1;
		}
	}
	
	//gibt den vordersten Gegner zurueck, der in der Reichweite eines Turms liegt
	GEGNER gegnerFinden(ANGRIFFSTURM turm, int index)
	{
		if (index < 0)
			return null;
		if (gegner.get(index).position.abstand(turm.position) <= turm.reichweite + 1)
			return gegner.get(index);
		return gegnerFinden(turm, index - 1);
	}
	
	//pausiert und startet das Spiel
	void pause(boolean pause)
	{
		if (pause)
		{
			timer.stop();
		}
		else
		{
			timer.start();
		}
	}
	
	//laedt die neue Welle
	void welle(int welle)
	{
		int anzahlGegner = welle * 2;
		for(; anzahlGegner > 0; --anzahlGegner)
		{
			gegner.add(new GEGNER(karte, 0, anzahlGegner));
		}
		wellennummer += 1;
		geld += welle - 1;
	}
	
	//ueberprueft auf Platz und Geld und fuegt gegebenenfalls den neuen Turm hinzu
	void bauen(int id, VEKTOR position)
	{
		if (karte.stelleFrei(position) && geld >= preisliste[id])
		{
			if (id < 2)
				angriffstuerme.add(new ANGRIFFSTURM(id, position));
			geld -= preisliste[id];
			oberflaeche.turmvorschau(id);
			karte.matrix[position.x / 60][position.y / 60] = false;
		}
	}
}
