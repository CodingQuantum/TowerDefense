import javax.swing.*;

public class LEBENSANZEIGE 
{
	JLabel balkenGruen;
	JLabel balkenRot;
	
	VEKTOR groesse;
	
	LEBENSANZEIGE(VEKTOR positionGegner, int ebene)
	{
		/*ImageIcon i = new ImageIcon(getClass().getResource("grafiken/lebensanzeige/balken_gruen.png"));
		balkenGruen = new JLabel(i);
		groesse = new VEKTOR(i.getIconWidth(), i.getIconHeight());
		balkenGruen.setSize(groesse.x, groesse.y);
		balkenRot = new JLabel(new ImageIcon(getClass().getResource("grafiken/lebensanzeige/balken_rot.png")));
		balkenRot.setSize(balkenGruen.getSize());
		FENSTER.paneGeben().add(balkenGruen, new Integer(ebene));
		FENSTER.paneGeben().add(balkenRot, new Integer(ebene));
		positionSetzen(positionGegner);*/
	}
	
	void positionSetzen(VEKTOR positionGegner)
	{
		balkenGruen.setLocation(positionGegner.x - 40, positionGegner.y - 50);
		balkenRot.setLocation(balkenGruen.getLocation());
	}
	
	void entfernen()
	{
		FENSTER.labelEntfernen(balkenGruen);
		FENSTER.labelEntfernen(balkenRot);
	}
	
	void anzeigeSetzen(int lebenProzent)
	{
		double breite = lebenProzent / 1.25;
		balkenGruen.setSize((int) breite, groesse.y);
	}
	
}
