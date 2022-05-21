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
		Timer t = new Timer(20, new ActionListener() {
			//Animation
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	if(startbildschirm.aktiv == false && startbildschirm.position.x < 2920)
	        	{
	        		startbildschirm.positionSetzen(new VEKTOR((int) (1000 * Math.sin(startbildschirm.x - Math.PI / 2) + 1920), 540));
	        		if(startbildschirm.x < Math.PI && startbildschirm.x > Math.PI - 0.1)
	        			startbildschirm.positionSetzen(new VEKTOR(2920, 540));
	        		startbildschirm.x += 0.1;
	        		kartenauswahl.aktiv = true;
	        	}
	        	if(kartenauswahl.aktiv == false && startbildschirm.position.x > 920)
	        	{
	        		startbildschirm.positionSetzen(new VEKTOR((int) (-1000 * Math.sin(kartenauswahl.x - Math.PI / 2) + 1920), 540));
	        		if(kartenauswahl.x < Math.PI && kartenauswahl.x > Math.PI - 0.1)
	        			startbildschirm.positionSetzen(new VEKTOR(920, 540));
	        		kartenauswahl.x += 0.1;
	        		startbildschirm.aktiv = true;
	        	}
	        	if(kartenauswahl.karteAusgewaehlt == true && kartenauswahl.position.x < 2920)
	        	{
	        		kartenauswahl.positionSetzen(new VEKTOR((int) (1000 * Math.sin(kartenauswahl.x - Math.PI / 2) + 1920), 540));
	        		if(kartenauswahl.x < Math.PI && kartenauswahl.x > Math.PI - 0.1)
	        			kartenauswahl.positionSetzen(new VEKTOR(2920, 540));
	        		kartenauswahl.x += 0.1;
	        	}
	        	if(kartenauswahl.karteAusgewaehlt == false && kartenauswahl.position.x > 920)
	        	{
	        		kartenauswahl.positionSetzen(new VEKTOR((int) (-1000 * Math.sin(kartenauswahl.x - Math.PI / 2) + 1920), 540));
	        		if(kartenauswahl.x < Math.PI && kartenauswahl.x > Math.PI - 0.1)
	        			kartenauswahl.positionSetzen(new VEKTOR(920, 540));
	        		kartenauswahl.x += 0.1;
	        	}
	        }
	    });
		t.start();
	}
}
