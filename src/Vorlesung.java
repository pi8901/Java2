import java.util.List;

public class Vorlesung {

	String Gruppe;
	String Titel;
	String Dozent;
	int Teilnehmeranzahl;
	
	//Geht
	public Vorlesung(List<String> x)
	{
		for(int i = 0; i < x.size(); i++)
		{
			if(x.get(i) == "")
			{
				throw new TextFileFormatException();
			}
		}
		Gruppe = x.get(0);
		Titel = x.get(1);
		Dozent = x.get(2);
		Teilnehmeranzahl = Integer.parseInt(x.get(3));
	}
}
