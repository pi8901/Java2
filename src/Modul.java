import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Modul
{

	public String bezeichnung;
	public String kuerzel;
	public String studiengang;
	public String semester;
	public String art;
	public double ects;
	public String pruefungsform;
	public String verantwortlicher;
	public List<Veranstaltung> veranstaltungen;
	public static Map<String, Integer> sws = new HashMap<String, Integer>();
	
	public Modul(List<String> element)
	{
		List<String> temp = Arrays.asList(element.get(0).split("\\|"));
		veranstaltungen = new ArrayList<Veranstaltung>();
		
		this.bezeichnung = temp.get(0); 
		this.kuerzel = temp.get(1);
		this.studiengang = temp.get(2);
		this.semester = temp.get(3);
		this.art = temp.get(4);
		this.ects = Double.parseDouble(temp.get(5).replace(',', '.'));
		this.pruefungsform = temp.get(6);
		this.verantwortlicher = temp.get(7);
		
		for(int i = 1; i < element.size(); i++)
		{
			veranstaltungen.add(new Veranstaltung(element.get(i)));
		}
		getSWS();
	}
	
	
	void getSWS()
	{
		if(!this.semester.contains("WPM")) 
		{
			for(int i = 0; i < this.veranstaltungen.size(); i++)
			{
				if(sws.containsKey(this.studiengang))
				{
					sws.put(this.studiengang, this.veranstaltungen.get(i).sws + sws.get(this.studiengang));
				}
				else
				{
					sws.put(this.studiengang, this.veranstaltungen.get(i).sws);
				}
			}
		}
	}
}
