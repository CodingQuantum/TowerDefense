import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class GEGNER
{
    int leben;
    int geschwindigkeit;
    LEBENSANZEIGE lebensanzeige;
    BILD bild;
    double rotation;
    VEKTOR position; 
    VERWALTUNG referenz;
    int gegnerNummer;
    Timer timer;
    int pos;
    int bewegungsstrecke;
    
    GEGNER( VERWALTUNG verwaltung, int nummer)
    {
    	position = new VEKTOR(330, 10 + nummer * 10);
        leben = 100;
        geschwindigkeit = 1;
        referenz = verwaltung;
        lebensanzeige = new LEBENSANZEIGE(new VEKTOR(0,0), 0);
        rotation = 0;
        gegnerNummer = nummer;
        bild = new BILD("grafiken/turmvorschau/turm12.png", position, rotation, 1);
		pos = 10 + nummer * 10;
		bewegungsstrecke = 0;
        timer = new Timer(500, new ActionListener(){public void actionPerformed(ActionEvent e) {bewegen();}});
		timer.start();
    }
    
    void bewegen()
    {
    	position = new VEKTOR(330 + ++bewegungsstrecke * 10, pos );
    	bild = new BILD("grafiken/turmvorschau/turm12.png", position, rotation, 1);
    }
    
    void schaden(int schaden)
    {
        leben = leben - schaden;
        if ( leben <= 0)
        {
           referenz.gegner.remove(gegnerNummer);
        }
    }
    
    
    void lebensanzeigeSetzen(int leben)
    {
    	lebensanzeige.anzeigeSetzen(leben); 
    }
    
   
}
