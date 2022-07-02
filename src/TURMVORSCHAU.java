//Klasse fuer eine Vorschau, die im Baumodus angezeugt wird
class TURMVORSCHAU extends TASTER
{
	int turmId;
	
	//erzeugt die Turmvorschau
	TURMVORSCHAU(int turmId, UIOBJEKT empfaenger, String id, int ebene)
	{
		super("grafiken/turmvorschau/turm" + turmId + ".png", "grafiken/turmvorschau/turm" + turmId + ".png", "grafiken/empty.png", new VEKTOR(-1000, -1000), empfaenger, id, ebene);
		this.turmId = turmId;
	}
	
	//aendert den in der Vorschau dargestellten Turm
	void turmAendern(int turmId)
	{
		this.turmId = turmId;
		bilderAendern("grafiken/turmvorschau/turm" + turmId + ".png", "grafiken/turmvorschau/turm" + turmId + ".png", "grafiken/turmvorschau/turm" + turmId + "3.png");
	}
}