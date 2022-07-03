import java.io.*;
import java.util.Vector;

class DATENBANK 
{
	int [] statistiken; //hoechste Welle Huegel, hoechste Welle Wueste, getoetete Gegner, platzierte Tuerme, geschossene Geschosse
	int [][] allgemein; //Geld gesamt, Leben, Welle, getoetete Gegner (in dieser Runde)
	int [][] tuermeKarte1;
	int [][] tuermeKarte2;
	
	DATENBANK()
	{
		statistiken = statistikenAuslesen();
		spielstandLaden();
		System.out.println(tuermeKarte1[0][1]);
	}
	
	int [] statistikenAuslesen()
	{
		statistiken = new int[5];
		try
        {
            FileReader fileReader = new FileReader("src/daten/statistiken.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < statistiken.length; ++i)
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
            for (int i = 0; i < statistiken.length; ++i)
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
	
	void spielstandLaden()
	{
		allgemein = new int [2][4];
		try
        {
            FileReader fileReader = new FileReader("src/daten/karte1/allgemein.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < allgemein[0].length; ++i)
            {
            	try
            	{
            		allgemein[0][i] = Integer.parseInt(bufferedReader.readLine());
            	}
            	catch(NumberFormatException ex)
            	{
            		allgemein[0][i] = 0;
            	}
            }
            bufferedReader.close();
            
            fileReader = new FileReader("src/daten/karte1/allgemein.txt");
            bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < allgemein[0].length; ++i)
            {
            	try
            	{
            		allgemein[0][i] = Integer.parseInt(bufferedReader.readLine());
            	}
            	catch(NumberFormatException ex)
            	{
            		allgemein[0][i] = 0;
            	}
            }
            bufferedReader.close();
            
            fileReader = new FileReader("src/daten/karte1/tuerme.txt");
            bufferedReader = new BufferedReader(fileReader);
            int tuermeAnzahl = Integer.parseInt(bufferedReader.readLine());
            tuermeKarte1 = new int[tuermeAnzahl][3];
            for (int j = 0; j < tuermeAnzahl; ++j)
            {
            	String [] werte = bufferedReader.readLine().split(" ");
            	for (int i = 0; i < werte.length; ++i)
            		tuermeKarte1[j][i] = Integer.parseInt(werte[i]);
            }
            bufferedReader.close();
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
            bufferedWriter.write(String.valueOf(angriffstuerme.size() + unterstuetzungstuerme.size()));
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
