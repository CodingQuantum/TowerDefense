import java.awt.event.*;
import javax.swing.*;

//Klasse fuer einen Knopf mit zwei Zustaenden, der beim Drücken in den jeweils anderen wechselt
class SCHALTER implements MouseListener
{
	VEKTOR position;
	BILD [] grafiken;
	boolean zustand;
	
	JLabel label;
	UIOBJEKT empfaenger;
	String id;
	
	//erzeugt den Schalter, das erste Bild bestimmt die Groesse
	//pfad 1: Bild fuer nicht gedrückten Zustand, pfad2: Bild fuer gedrückten Zustand, pfad3: Bild fuer Ueberfahren mit Mauszeiger 
	SCHALTER(String pfad1, String pfad2, String pfad3, VEKTOR position, UIOBJEKT empfaenger, String id, int ebene)
	{
		grafiken = new BILD [] {new BILD(pfad1, position, 0, ebene), new BILD(pfad2, position, 0, ebene), new BILD(pfad3, position, 0, ebene + 1)};
		grafiken[1].sichtbarkeitSetzen(false);
		grafiken[2].sichtbarkeitSetzen(false);
		label = new JLabel();
		label.setSize(grafiken[0].groesse.x, grafiken[0].groesse.y);
		label.addMouseListener(this);
		positionSetzen(position);
		this.empfaenger = empfaenger;
		this.id = id;
		zustand = false;
		FENSTER.paneGeben().add(label);
	}
	
	//setzt die Position des Mittelpunktes
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		label.setLocation(position.x - label.getWidth() / 2, position.y - label.getHeight() / 2);
		grafiken[0].positionSetzen(position);
		grafiken[1].positionSetzen(position);
		grafiken[2].positionSetzen(position);
	}
	
	//entfernt den Taster
	void entfernen()
	{
		FENSTER.labelEntfernen(label);
		grafiken[0].entfernen();
		grafiken[1].entfernen();
		grafiken[2].entfernen();
	}
	
	//wechselt den Zustand beim Druecken, sendet Signal an Empfaenger
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(zustand == true)
		{
			zustand = false;
			grafiken[0].sichtbarkeitSetzen(true);
			grafiken[1].sichtbarkeitSetzen(false);
			empfaenger.schalterGedrueckt(id, zustand);
		}
		else
		{
			zustand = true;
			grafiken[0].sichtbarkeitSetzen(false);
			grafiken[1].sichtbarkeitSetzen(true);
			empfaenger.schalterGedrueckt(id, zustand);
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	//zeigt das Bild 3 beim Ueberfahren mit dem Mauszeiger
	@Override
	public void mouseEntered(MouseEvent e)
	{
		grafiken[2].sichtbarkeitSetzen(true);
	}
	
	//laesst Bild 3 beim Verlassen mit dem Mauszeiger wieder verschwinden
	@Override
	public void mouseExited(MouseEvent e)
	{
		grafiken[2].sichtbarkeitSetzen(false);
	}
}
