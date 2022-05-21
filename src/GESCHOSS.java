//Klasse fuer Geschosse von Tuermen
class GESCHOSS
{
	VEKTOR position;
	int rotation;
	BILD bild;
	int geschwindigkeit;
	int schaden;
	GEGNER ziel;
	VEKTOR richtung;
	
	//erzeugt das Geschoss
	GESCHOSS(VEKTOR position, GEGNER ziel, int[] daten)	
	{
		this.position = position;
		rotation = 0;
		bild = new BILD("grafiken/geschoss/geschoss" + daten[0] + ".png", position, 0, 2);
		geschwindigkeit = daten[1];
		schaden = daten[2];
		this.ziel = ziel;
		double entfernung = position.abstand(ziel.position);
		richtung = new VEKTOR((int) ((ziel.position.x - position.x) / entfernung * geschwindigkeit), (int) ((ziel.position.y - position.y) / entfernung * geschwindigkeit));
	}
	
	//wird ein mal pro Frame aufgerufen
	void prozess()
	{
		position = position.plus(richtung);
		bild.positionSetzen(position);
	}
	
	//entfernt das Geschoss
	void entfernen()
	{
		bild.entfernen();
	}
}

