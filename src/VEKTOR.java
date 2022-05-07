//Klasse für die Verwaltung eines zweidimensionalen Vektors
class VEKTOR
{
	int x;
	int y;
	
	//erzeugt den Vektor
	VEKTOR(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	//gibt die Summe dieses Vektors und des Vektors v zurück
	VEKTOR plus(VEKTOR v)
	{
		return new VEKTOR(x + v.x, y + v.y);
	}
	
	//gibt den Vektor aus
	void print()
	{
		System.out.println("(" + x + ", " + y + ")");
	}
}
