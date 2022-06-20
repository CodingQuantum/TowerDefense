import java.io.*;

class DATENBANK 
{
	int highscore;
	SPIELSTAND Spielstand;
	File file;
	
	DATENBANK(File Dateiname)
	{
		file = Dateiname;
		try //Highscore einlesen
		{
			FileReader reader = new FileReader(file.toString());
			BufferedReader bufReader = new BufferedReader(reader);
			String highscoreString = bufReader.readLine();
			bufReader.close();
			if(highscoreString != null)
				highscore = Integer.parseInt(highscoreString);
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			try 
			{
				file.createNewFile();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
                System.err.println("Error creating " + file.toString());
			}
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			highscore = 0;
		}
		
		// TODO Auslesen des Spielstandes, falls vorhanden

	}
	
	void setHighscore(int welle)
	{
		if(highscore <= welle)
			highscore = welle;
		try 
		{
			FileWriter writer = new FileWriter(file.toString());
			BufferedWriter bufWriter = new BufferedWriter(writer);
			System.out.println(highscore);

			bufWriter.write(65);
			bufWriter.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Ergänzen Methode, die Highscore einer bestimmter Karte zurückgibt
	
	void saveGamestate(VERWALTUNG Verwaltung)//Referenz auf Verwaltung; muss angepasst werden
	{
		//Abspeichern der Daten in der Datei
	}
	
}
