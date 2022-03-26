import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;

public class BILD
{
    ImageIcon texture;
    
    BILD(int posx, int posy, int sizex, int sizey, String pfad, int ebene)
    {
        texture = new ImageIcon(getClass().getResource(pfad));
        JLabel label = new JLabel(texture);
        FENSTER.paneGeben().add(label, new Integer(ebene));
        label.setSize(sizex, sizey);
        label.setLocation(posx, posy);
    }
}