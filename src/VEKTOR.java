//Klasse fuer die Verwaltung eines zweidimensionalen Vektors
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
	
	//Vektoraddition
	VEKTOR plus(VEKTOR v)
	{
		return new VEKTOR(x + v.x, y + v.y);
	}
	
	//Skalarmultiplikation
	VEKTOR mal(int r)
	{
		return new VEKTOR(x * r, y * r);
	}
	VEKTOR mal(double r)
	{
		return new VEKTOR((int) (x * r), (int) (y * r));
	}
	
	//Abstand der Spitzen der Orstverktoren von this und v
	double abstand(VEKTOR v)
	{
		return Math.sqrt((x - v.x) * (x - v.x) + (y - v.y) * (y - v.y));
	}
	
	//Laenge des Vektors
	double laenge()
	{
		return Math.sqrt(x * x + y * y);
	}
	
	//gibt den Vektor (schriftlich) aus
	void ausgeben()
	{
		System.out.println("(" + x + ", " + y + ")");
	}
}
