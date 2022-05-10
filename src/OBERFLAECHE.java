import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

class OBERFLAECHE implements UIOBJEKT
{
	BILD hintergrund;
	TEXTFELD geld;
	TEXTFELD leben;
	TEXTFELD welle;
	SCHALTER [] tuerme;
	boolean bauen;
	
	OBERFLAECHE()
	{
		hintergrund = new BILD("grafiken/oberflaeche.png", new VEKTOR(960, 540), 0, 3);
		geld = new TEXTFELD("PAUL DAS ALIEN", new VEKTOR(100, 50), 50, 4);
		leben = new TEXTFELD("", new VEKTOR(100, 120), 50, 4);
		welle = new TEXTFELD("", new VEKTOR(100, 190), 50, 4);
		tuerme = new SCHALTER[1];
		for (int i = 0; i < tuerme.length; i++)
		{
			tuerme[i] = new SCHALTER("grafiken/turmSchalter" + i + "1.png", "grafiken/turmSchalter" + i + "2.png", "grafiken/turmSchalter" + i + "3.png", new VEKTOR(1770, 100 + 70 * i), this, "turmSchalter" + i, 5);
		}
		bauen = false;
		
		//TEST
		Timer t = new Timer(10, new ActionListener() {
			//Animation
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	if (bauen == true)
	        	{
	        		geld.positionSetzen(new VEKTOR((int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY()));
	        	}
	        	else
	        	{
	        		geld.positionSetzen(new VEKTOR(100, 50));
	        	}
	        }
	    });
		t.start();
	}
	
	void bauen()
	{
		
	}
	
	void ende()
	{
		
	}
	
	public void tasterGedrueckt(String id)
	{
		
	}
	
	public void schalterGedrueckt(String id, boolean zustand)
	{
		if (id.equals("turmSchalter0"))
		{
			bauen = zustand;
		}
	}
}