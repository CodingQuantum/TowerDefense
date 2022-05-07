import java.awt.event.*;

//Interface für Kommunikation zwischen Knoepfen etc. und Empfaenger
interface UIOBJEKT
{
	void tasterGedrueckt(VEKTOR mausposition);
	void schalterGedrueckt(VEKTOR mausposition, boolean zustand);
}