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
		label = new JLabel(text);
		this.text = text;
		this.schriftgroesse = schriftgroesse;
		Font schrif = new Font(schriftart, Font.PLAIN, schriftgroesse);
		label.setFont(schrif);
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		label.setSize((int) schrif.getStringBounds(text, frc).getWidth() + 1, (int) schrif.getStringBounds(text, frc).getHeight() + 1);
		label.setForeground(new Color(0, 0, 0));
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
		Font schrif = new Font(schriftart, Font.PLAIN, schriftgroesse);
		label.setFont(schrif);
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		label.setSize((int) schrif.getStringBounds(text, frc).getWidth() + 1, (int) schrif.getStringBounds(text, frc).getHeight() + 1);
	}
	
	//entfernt das Textfeld von der Darstellungsflaeche
	void entfernen()
	{
		FENSTER.labelEntfernen(label);
	}
}
