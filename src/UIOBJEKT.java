//Interface fuer Kommunikation zwischen Knoepfen etc. und Empfaenger
interface UIOBJEKT
{
	void tasterGedrueckt(String id);
	void schalterGedrueckt(String id, boolean zustand);
}