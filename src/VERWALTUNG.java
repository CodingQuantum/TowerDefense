import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

//zentrale Verwaltungsklasse des eigentlichen Spiels
class VERWALTUNG
{
	Timer timerProzess, timerWelle;
	KARTE karte;
	DATENBANK  datenbank;
	OBERFLAECHE oberflaeche;
	Vector <ANGRIFFSTURM> angriffstuerme;
	Vector <UNTERSTUETZUNGSTURM> unterstuetzungstuerme;
	Vector <GEGNER> gegner;
	Vector <GESCHOSS> geschosse;
	int geld;
	int leben;
	int wellennummer;
	int[] preisliste;
	
	//erzeugt die Verwaltungsklasse
	VERWALTUNG(int kartenId)
	{
		karte = new KARTE(kartenId);
		oberflaeche = new OBERFLAECHE(this);
        angriffstuerme = new Vector<>();
        unterstuetzungstuerme = new Vector<>();
        gegner = new Vector<>();
        geschosse = new Vector<>();
        geld = 20;
        leben = 100;
        wellennummer = 0;
		timerProzess = new Timer(20, new ActionListener(){public void actionPerformed(ActionEvent e) {prozess();}});
		timerProzess.start();
		preisliste = new int [] {0, 10};
	}
	
	//wird ein mal pro Frame aufgerufen
	void prozess()
	{
		//Start der neuen Welle
		if(gegner.size() == 0)
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
			GEGNER z = gegnerFinden(a, gegner.size() - 1);
			if (z != null && a.angriffsbereit)
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
		}
		
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
		oberflaeche.welle.textSetzen(wellennummer);
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
		if (pause == true)
		{
			timerProzess.stop();
		}
		else
		{
			timerProzess.start();
		}
	}
	
	//laed die neue Welle
	void welle(int welle)
	{
		int anzahlGegner = welle + 1;
		for(; anzahlGegner > 0; --anzahlGegner)
		{
			gegner.add(new GEGNER(karte, 0, anzahlGegner, this));
		}
		wellennummer += 1;
		geld += 20 + welle / 10;
	}
	
	//ueberprueft auf Platz und Geld und fuegt gegbenenfalls den neuen Turm hinzu
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
