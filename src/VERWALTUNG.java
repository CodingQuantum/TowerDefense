import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

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
	int geldGesamt;
	int leben;
	int wellennummer;
	int gegnerGetoetet;
	int[] preisliste;
	boolean pauseWellen;
	
	//erzeugt die Verwaltungsklasse
	VERWALTUNG()
	{
		karte = new KARTE();
		datenbank = new DATENBANK();
		oberflaeche = new OBERFLAECHE(this);
		ergebnismenue = new ERGEBNISMENUE();
        angriffstuerme = new Vector<>();
        unterstuetzungstuerme = new Vector<>();
        gegner = new Vector<>();
        geschosse = new Vector<>();
        timer = new Timer(20, new ActionListener(){public void actionPerformed(ActionEvent e) {prozess();}});
		preisliste = new int [] {0, 20, 50, 100, 100};
	}
	
	//initalisiert das Spiel
	void start(int kartenId)
	{
		datenbank.spielstandLaden();
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
	    geld = datenbank.allgemein[kartenId - 1][0];
	    geldGesamt = datenbank.allgemein[kartenId - 1][1];
	    leben = datenbank.allgemein[kartenId - 1][2];
	    wellennummer = datenbank.allgemein[kartenId - 1][3];
	    gegnerGetoetet = datenbank.allgemein[kartenId - 1][4];
	    pauseWellen = false;
	    ergebnismenue.positionSetzen(new VEKTOR(960, 1620));
	    ergebnismenue.x = 0;
	    if(oberflaeche.pause.zustand)
	    	oberflaeche.pause.mouseClicked(null);
	    if(kartenId == 1)
	    {
	    	for(int i = 0; i < datenbank.tuermeKarte1.length; ++i)
	    	{
	    		bauen(datenbank.tuermeKarte1[i][0], new VEKTOR(datenbank.tuermeKarte1[i][1], datenbank.tuermeKarte1[i][2]));
	    		geld += preisliste[datenbank.tuermeKarte1[i][0]];
	    		oberflaeche.turmvorschau(datenbank.tuermeKarte1[i][0]);
	    	}
	    }
	    else if(kartenId == 2)
	    {
	    	for(int i = 0; i < datenbank.tuermeKarte2.length; ++i)
	    	{
	    		bauen(datenbank.tuermeKarte2[i][0], new VEKTOR(datenbank.tuermeKarte2[i][1], datenbank.tuermeKarte2[i][2]));
	    		geld += preisliste[datenbank.tuermeKarte2[i][0]];
	    		oberflaeche.turmvorschau(datenbank.tuermeKarte2[i][0]);
	    	}
	    }
	    geld -= 10 * (wellennummer - 1);
    	geldGesamt -= 10 * (wellennummer - 1);
	}
	
	//wird ein mal pro Frame aufgerufen
	void prozess()
	{	
		//Start der neuen Welle
		if(gegner.size() == 0)
			if(!pauseWellen)
			{
				welle(wellennummer);
				spielstandSpeichern();
			}
		
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
				geldGesamt += g.belohnung;
				gegnerGetoetet += 1;
				g.entfernen();
				gegner.remove(i);
				datenbank.statistiken[2] += 1;
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
					a.ziel = z;
					a.angriffsbereit = false;
					a.zaehler = 0;
					geschosse.add(new GESCHOSS(a.position, z, a.geschossdaten));
					datenbank.statistiken[4] += 1;
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
			else if(Math.abs(g.position.x) + Math.abs(g.position.y) > 3000)
			{
				g.entfernen();
				geschosse.remove(l);
			}
		}
		
		//Tod
		if(leben <= 0)
		{
			ende();
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
		statistikenSpeichern();
		spielstandSpeichern();
		leben = 0;
		pauseWellen = true;
		ergebnismenue.welle.textSetzen("Du hast Welle " + (wellennummer - 1) + " erreicht.");
		ergebnismenue.geldGesamt.textSetzen("Insgesamt hast Du " + geldGesamt + " Münzen verdient.");
		ergebnismenue.gegnerGetoetet.textSetzen(gegnerGetoetet + " Gegner wurden getötet.");
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
		int anzahlGegner0 = (int) Math.pow(2, welle - 1);
		int anzahlGegner1 = (int) Math.pow(2, welle - 3);
		int anzahlGegner2 = (int) Math.pow(2, welle - 5);
		for(int c = 0; c < anzahlGegner1; ++c) //Reihenfolge wichtig, da schneller Gegner
			gegner.add(0, new GEGNER(karte, 1, c, welle));
		for(int b = 0; b < anzahlGegner0; ++b)
			gegner.add(0, new GEGNER(karte, 0, b + anzahlGegner1, welle));
		for(int a = 0; a < anzahlGegner2; ++a)
			gegner.add(0, new GEGNER(karte, 2, a + anzahlGegner0 + anzahlGegner1, welle));
		wellennummer += 1;
		geld += 10 * (welle - 1);
		geldGesamt += 10 * (welle - 1);
	}
	
	//ueberprueft auf Platz und Geld und fuegt gegebenenfalls den neuen Turm hinzu
	void bauen(int id, VEKTOR position)
	{
		if (karte.stelleFrei(position) && geld >= preisliste[id])
		{
			if (id < 4)
			{
				ANGRIFFSTURM a = new ANGRIFFSTURM(id, position);
				angriffstuerme.add(a);
				for(int i = 0; i < unterstuetzungstuerme.size(); ++i)
				{
					UNTERSTUETZUNGSTURM u = unterstuetzungstuerme.get(i);
					if(u.position.abstand(a.position) <= u.reichweite + 1)
						a.wirdUnterstuezt(u.werte);
				}
			}
			else
			{
				UNTERSTUETZUNGSTURM u = new UNTERSTUETZUNGSTURM(id, position);
				unterstuetzungstuerme.add(u);
				for(int i = 0; i < angriffstuerme.size(); ++i)
				{
					ANGRIFFSTURM a = angriffstuerme.get(i);
					if(u.position.abstand(a.position) <= u.reichweite + 1)
						a.wirdUnterstuezt(u.werte);
				}
			}
			geld -= preisliste[id];
			oberflaeche.turmvorschau(id);
			karte.matrix[position.x / 60][position.y / 60] = false;
			datenbank.statistiken[3] += 1;
		}
	}
	
	//uebergibt die gesammelten statistischen Daten an die Datenbank
	void statistikenSpeichern()
	{
		if(wellennummer - 1 > datenbank.statistiken[karte.kartenId - 1])
			datenbank.statistiken[karte.kartenId - 1] = wellennummer - 1;
		datenbank.statistikenSpeichern();
	}
	
	//uebergibt den Spielstand an die Datenbank
	void spielstandSpeichern()
	{
		if(leben <= 0)
		{
			int [] allgemein = new int [] {200, 200, 100, 1, 0};
			datenbank.spielstandSpeichern(karte.kartenId, allgemein, new Vector<>(), new Vector<>());
		}
		else
		{
			int [] allgemein = new int [] {geld, geldGesamt, leben, wellennummer - 1, gegnerGetoetet};
			datenbank.spielstandSpeichern(karte.kartenId, allgemein, angriffstuerme, unterstuetzungstuerme);
		}
	}
}
