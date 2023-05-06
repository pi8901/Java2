package A3;
import java.util.List;

public class Vorlesung {

	String Gruppe;
	String Titel;
	String Dozent;
	int Teilnehmeranzahl;

	//Geht
	public Vorlesung(List<String> x)
	{
		for (String element : x) {
			if(element == "" || x.size() != 4)
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
