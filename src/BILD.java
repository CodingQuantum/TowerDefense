import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BILD
{
    ImageIcon textur; //Textur des Bildes
    JLabel label; //Label zur Darstellung der Textur
    
    //erzeugt das Bild
    BILD(int posx, int posy, String pfad, int ebene)
    {
        textur = new ImageIcon(getClass().getResource(pfad));
        label = new JLabel(textur);
        FENSTER.paneGeben().add(label, new Integer(ebene));
        label.setSize(textur.getIconWidth(), textur.getIconHeight());
        label.setLocation(posx, posy);
    }
    
    //setzt die Position des Bildes (obere linke Ecke)
    public void positionSetzen(int posx, int posy)
    {
    	label.setLocation(posx, posy);
    }
}