import java.awt.event.*;

//Interface f�r Kommunikation zwischen Knoepfen etc. und Empfaenger
interface UIOBJEKT
{
	void tasterGedrueckt(VEKTOR mausposition);
	void schalterGedrueckt(VEKTOR mausposition, boolean zustand);
}