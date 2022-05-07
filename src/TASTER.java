import java.awt.event.*;
import javax.swing.*;

//Klasse für einen Knopf, der wieder in seinen Ausgangszustand zurückgeht, wenn er losgelassen wird
class TASTER implements MouseListener
{
	VEKTOR position;
	BILD [] grafiken;
	
	JLabel label;
	UIOBJEKT empfaenger;
	
	//erzeugt den Taster, das erste Bild bestimmt die Groesse
	//pfad 1: Bild für nicht gedrückten Zustand, pfad2: Bild für gedrückten Zustand, pfad3: Bild für 
	TASTER(String pfad1, String pfad2, String pfad3, VEKTOR position, UIOBJEKT empfaenger, int ebene)
	{
		grafiken = new BILD [] {new BILD(pfad1, position, 0, ebene), new BILD(pfad2, position, 0, ebene), new BILD(pfad3, position, 0, ebene + 1)};
		grafiken[1].sichtbarkeitSetzen(false);
		grafiken[2].sichtbarkeitSetzen(false);
		label = new JLabel();
		label.setSize(grafiken[0].groesse.x, grafiken[0].groesse.y);
		label.addMouseListener(this);
		positionSetzen(position);
		this.empfaenger = empfaenger;
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

	@Override
	public void mouseClicked(MouseEvent e)
	{
		empfaenger.tasterGedrueckt(new VEKTOR(e.getLocationOnScreen().x, e.getLocationOnScreen().y));
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		grafiken[0].sichtbarkeitSetzen(false);
		grafiken[1].sichtbarkeitSetzen(true);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		grafiken[0].sichtbarkeitSetzen(true);
		grafiken[1].sichtbarkeitSetzen(false);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		grafiken[2].sichtbarkeitSetzen(true);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		grafiken[2].sichtbarkeitSetzen(false);
	}
}
