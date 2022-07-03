import java.io.*;
import java.util.Vector;

class DATENBANK 
{
	int [] statistiken; //hoechste Welle Huegel, hoechste Welle Wueste, getoetete Gegner, platzierte Tuerme, geschossene Geschosse
	
	int [] allgemeinHuegel; //Geld gesamt, Leben, Welle, getoetete Gegner (in dieser Runde)
	int [][] tuermeHuegel;
	int [] allgemeinWueste; //Geld gesamt, Leben, Welle, getoetete Gegner (in dieser Runde)
	int [][] tuermeWueste;
	
	DATENBANK()
	{
		statistiken = statistikenAuslesen();
	}
	
	int [] statistikenAuslesen()
	{
		statistiken = new int[5];
		try
        {
            FileReader fileReader = new FileReader("src/daten/statistiken.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < statistiken.length; i++)
            {
            	try
            	{
            		statistiken[i] = Integer.parseInt(bufferedReader.readLine());
            	}
            	catch(NumberFormatException ex)
            	{
            		statistiken[i] = 0;
            	}
            }
            bufferedReader.close();  
        }
        catch(IOException ex)
        {
            System.out.println("Fehler");                  
        }
		return statistiken;
	}
	
	void statistikenSpeichern()
	{
		try
        {
            FileWriter fileWriter = new FileWriter("src/daten/statistiken.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < statistiken.length; i++)
            {
                bufferedWriter.write(String.valueOf(statistiken[i]));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Fehler");
        }
	}
	
	void spielstandSpeichern(int kartenId, int [] allgemein, Vector <ANGRIFFSTURM> angriffstuerme, Vector <UNTERSTUETZUNGSTURM> unterstuetzungstuerme)
	{
		try
        {
            FileWriter fileWriter = new FileWriter("src/daten/karte" + kartenId + "/allgemein.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < allgemein.length; i++)
            {
                bufferedWriter.write(String.valueOf(allgemein[i]));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            
            fileWriter = new FileWriter("src/daten/karte" + kartenId + "/tuerme.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(angriffstuerme.size()) + " " + String.valueOf(unterstuetzungstuerme.size()));
            bufferedWriter.newLine();
            for (int i = 0; i < angriffstuerme.size(); i++)
            {
            	bufferedWriter.write(String.valueOf(angriffstuerme.get(i).turmId) + " " + String.valueOf(angriffstuerme.get(i).position.x) + " " + String.valueOf(angriffstuerme.get(i).position.y));
                bufferedWriter.newLine();
            }
            for (int i = 0; i < unterstuetzungstuerme.size(); i++)
            {
                bufferedWriter.write(String.valueOf(unterstuetzungstuerme.get(i).turmId) + " " + String.valueOf(unterstuetzungstuerme.get(i).position.x) + " " + String.valueOf(unterstuetzungstuerme.get(i).position.y));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Fehler");
        }
	}
}
