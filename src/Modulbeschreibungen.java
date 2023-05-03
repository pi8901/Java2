import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Modulbeschreibungen
{
	List<Studiengang> Studium = new ArrayList<Studiengang>();
	

	public static void main(String[] args) throws IOException
	{		
		Modulbeschreibungen s = new Modulbeschreibungen("C:\\Users\\pi890\\OneDrive\\Dokumente\\UNI\\Semester 2\\Java2\\Java2\\src\\Data");
	}
	
	public Modulbeschreibungen(String link) throws IOException
	{
		List<List<String>> result = load(link);
		for(List<String> element : result)
		{
			Studium.add(new Studiengang(element));
		}
	}

	public Set<String> getZertifikate(String studiengang)
	{
		return null;
	}
	
	public Set<String> getVerzahnteModule()
	{
		return null;
	}
	
	int getAnzahlModule(String studiengang, Boolean pflicht)
	{
		return 0;
	}
	
	int getAnzahlVeranstaltungen(String studiengang, Boolean pflicht)
	{
		return 0;
	}
	
	Map<Integer, Integer> getECTS(String studiengang)
	{
		return null;
	}
	
	Map<Integer, Integer> getSWS(String studiengang)
	{
		return null;
	}
	
	List<String> getSortierteStudiengaenge()
	{
		return null;
	}
	
	String getJSON(String studiengang)
	{
		return null;
	}
	
	public static List<List<String>> load(String filename) throws IOException 
	{
		List<List<String>> result = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for (String line = br.readLine(); line != null; line = br.readLine())
		{
			List<String> temp = new ArrayList<String>();
			while(line != null && !line.equals(""))
			{
				temp.add(line);
				line = br.readLine();
			}
			result.add(temp);		
		}
		br.close();
		return result;
	}
}
