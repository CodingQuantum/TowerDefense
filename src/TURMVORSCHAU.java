class TURMVORSCHAU extends TASTER
{
	int turmId;
	
	TURMVORSCHAU(int turmId, UIOBJEKT empfaenger, String id, int ebene)
	{
		super("grafiken/turmvorschau/turm" + turmId + "1.png", "grafiken/turmvorschau/turm" + turmId + "2.png", "grafiken/turmvorschau/turm" + turmId + "3.png", new VEKTOR(-100, -100), empfaenger, id, ebene);
		this.turmId = turmId;
	}
	
	void turmAendern(int turmId)
	{
		this.turmId = turmId;
		bilderAendern("grafiken/turmvorschau/turm" + turmId + "1.png", "grafiken/turmvorschau/turm" + turmId + "2.png", "grafiken/turmvorschau/turm" + turmId + "3.png");
	}
}