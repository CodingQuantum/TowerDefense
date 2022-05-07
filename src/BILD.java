import java.awt.*;
import javax.swing.*;

//Klasse für die Darstellung von Bilddateien
class BILD
{
	VEKTOR position;
	VEKTOR groesse;
    double rotation;
    
	LABEL label;
    
    //erzeugt das Bild
    BILD(String pfad, VEKTOR position, double rotation, int ebene)
    {
        ImageIcon textur = new ImageIcon(getClass().getResource(pfad));
        groesse = new VEKTOR(textur.getIconWidth(), textur.getIconHeight());
        label = new LABEL(textur);
        this.position = position;
        double diagonale = Math.sqrt(groesse.x * groesse.x + groesse.y * groesse.y);
        label.setSize((int) diagonale + 1, (int) diagonale + 1);
        positionSetzen(position);
        rotationSetzen(rotation);
        FENSTER.paneGeben().add(label, new Integer(ebene));
    }
    
    //eigenes Label für Rotation
    class LABEL extends JLabel
    {
		public LABEL(ImageIcon texture)
    	{
    		super(texture);
    	}
    	
    	@Override
        public void paintComponent(Graphics g)
    	{
           Graphics2D gx = (Graphics2D) g;
           gx.rotate(rotation, getWidth() / 2, getHeight() / 2);
           super.paintComponent(g);
        }
    }
    
    //setzt die Position des Mittelpunktes
    void positionSetzen(VEKTOR position)
    {
    	this.position = position;
        label.setLocation(position.x - label.getWidth() / 2, position.y - label.getHeight() / 2);
    }
    
    //setzt die Rotation
    void rotationSetzen(double rotation)
    {
    	while (rotation >= 2 * Math.PI)
    	{
    		rotation -= 2 * Math.PI;
    	}
    	while (rotation < 0)
    	{
    		rotation += 2 * Math.PI;
    	}
    	this.rotation = rotation;
    	label.repaint();
    }
    
    //setzt die Sichtbarkeit
    void sichtbarkeitSetzen(boolean sichtbar)
    {
    	label.setVisible(sichtbar);
    }
    
    //entfernt das Bild von der Darstellungsflaeche
    void entfernen()
    {
    	FENSTER.labelEntfernen(label);
    }
}
