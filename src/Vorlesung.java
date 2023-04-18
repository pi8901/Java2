import java.util.List;

public class Vorlesung {

	String Gruppe;
	String Titel;
	String Dozent;
	String Teilnehmeranzahl;
	
	
	public Vorlesung(List<String> x) {
		Gruppe = x.get(0);
		Titel = x.get(1);
		Dozent = x.get(2);
		Teilnehmeranzahl = x.get(3);
	}
	
	
}
