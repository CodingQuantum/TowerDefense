import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

//Klasse fuer das Anzeigen von Text
class TEXTFELD
{
	String schriftart = "Impact"; //Variable, um die Standardschriftart einfach ändern zu können
	
	VEKTOR position;
	String text;
	
	JLabel label;
	int schriftgroesse;
	
	//erzeugt das Textfeld
	TEXTFELD(String text, VEKTOR position, int schriftgroesse, int ebene)
	{
		label = new JLabel();
		label.setForeground(new Color(255, 255, 255));
		this.schriftgroesse = schriftgroesse;
		textSetzen(text);
		positionSetzen(position);
		FENSTER.paneGeben().add(label, new Integer(ebene));
	}
	
	//setzt die Position der linken oberen Ecke
	void positionSetzen(VEKTOR position)
	{
		this.position = position;
		label.setLocation(position.x, position.y);
	}
	
	//setzt den Text
	void textSetzen(String text)
	{
		this.text = text;
		label.setText(text);
		Font schrift = new Font(schriftart, Font.PLAIN, schriftgroesse);
		label.setFont(schrift);
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		label.setSize((int) schrift.getStringBounds(text, frc).getWidth() + 10, (int) schrift.getStringBounds(text, frc).getHeight());
	}
	
	//setzt den Text auf Basis einer Zahl
	void textSetzen(int zahl)
	{
		textSetzen(String.valueOf(zahl));
	}
	
	//entfernt das Textfeld von der Darstellungsflaeche
	void entfernen()
	{
		FENSTER.labelEntfernen(label);
	}
}
