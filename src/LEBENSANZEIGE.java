import javax.swing.*;

//Klasse fuer einen Balken ueber Gegnern, der deren Leben anzeigt
class LEBENSANZEIGE 
{
	JLabel balkenGruen;
	JLabel balkenRot;
	
	VEKTOR groesse;
	
	//erzeugt die Lebensanzeige
	LEBENSANZEIGE(VEKTOR positionGegner, int ebene)
	{
		ImageIcon i = new ImageIcon(getClass().getResource("grafiken/lebensanzeige/balken_gruen.png"));
		balkenGruen = new JLabel(i);
		groesse = new VEKTOR(i.getIconWidth(), i.getIconHeight());
		balkenGruen.setSize(groesse.x, groesse.y);
		balkenRot = new JLabel(new ImageIcon(getClass().getResource("grafiken/lebensanzeige/balken_rot.png")));
		balkenRot.setSize(balkenGruen.getSize());
		FENSTER.paneGeben().add(balkenGruen, new Integer(ebene));
		FENSTER.paneGeben().add(balkenRot, new Integer(ebene));
		positionSetzen(positionGegner);
	}
	
	//setzt die Position der Lebensanzeige auf Basis der angegbenen Position des Gegners
	void positionSetzen(VEKTOR positionGegner)
	{
		balkenGruen.setLocation(positionGegner.x - 40, positionGegner.y - 70);
		balkenRot.setLocation(balkenGruen.getLocation());
	}
	
	//setzt den dargestellten Wert der Lebensanzeige
	void anzeigeSetzen(double lebenProzent)
	{
		double breite = lebenProzent * groesse.x;
		balkenGruen.setSize((int) breite, groesse.y);
	}
	
	//entfernt die Lebensanzeige
	void entfernen()
	{
		FENSTER.labelEntfernen(balkenGruen);
		FENSTER.labelEntfernen(balkenRot);
	}
}
