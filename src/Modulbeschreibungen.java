import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	public static void main(String[] args) throws IOException
	{
		List<String> in = Files.readAllLines(Paths.get("C:\\Users\\pi890\\eclipse-workspace\\Java2\\src\\Data"));
		
		for(int i = 0; i < in.size(); i++)
		{
			if(in.get(i).equals(""))
			{
				int start = i;
				System.out.println(i);
				for(int j = start; j < in.size() - start; j++)
				{
					if(in.get(j).equals(""))
					{
						int end = j;
						
					}
				}
				
			}

		}
		

		
		
		
	}
	
	public Modulbeschreibungen(String link) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("C:\\Users\\pi890\\eclipse-workspace\\Java2\\src\\Data"));
		sc.useDelimiter("\n");
		while(sc.hasNext());
		{
			System.out.println(sc.next());
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
	
	public static String load(String filename) throws IOException 
	{
		List<List<String>> result = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		StringBuilder sb = new StringBuilder();

		for (String line = br.readLine(); line != null; line = br.readLine())
		{
			sb.append(line);
		}
		return sb.toString();
	}
}
