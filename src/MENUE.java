import java.awt.event.*;
import javax.swing.*;

//Klasse fuer die Verwaltung der Menues, die beim Start angezeigt werden
class MENUE
{
	STARTBILDSCHIRM startbildschirm;
	KARTENAUSWAHL kartenauswahl;
	
	//erzeugt das Menue
	MENUE()
	{
		startbildschirm = new STARTBILDSCHIRM();
		kartenauswahl = new KARTENAUSWAHL();
		Timer t = new Timer(10, new ActionListener() {
			//Animation
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	if (startbildschirm.aktiv == false && startbildschirm.position.x < 2920)
	        	{
	        		startbildschirm.positionSetzen(new VEKTOR((int) (2000 * Math.sin(startbildschirm.x) + 920), 540));
	        		if (startbildschirm.x < Math.PI / 2 && startbildschirm.x > Math.PI / 2 - 0.05)
	        		{
	        			startbildschirm.positionSetzen(new VEKTOR(2920, 540));
	        		}
	        		startbildschirm.x += 0.05;
	        		kartenauswahl.aktiv = true;
	        		kartenauswahl.x = Math.PI / 2;
	        	}
	        	if (kartenauswahl.aktiv == false && startbildschirm.position.x > 920)
	        	{
	        		startbildschirm.positionSetzen(new VEKTOR((int) (-2000 * Math.sin(kartenauswahl.x) + 2920), 540));
	        		if (kartenauswahl.x < Math.PI / 2 && kartenauswahl.x > Math.PI / 2 - 0.05)
	        		{
	        			startbildschirm.positionSetzen(new VEKTOR(920, 540));
	        		}
	        		kartenauswahl.x += 0.05;
	        		startbildschirm.aktiv = true;
	        	}
	        }
	    });
		t.start();
	}
}
