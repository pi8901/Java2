import java.io.*;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;

public class Vorlesungsverzeichnis
{

	Set<Vorlesung> set = new HashSet<Vorlesung>();

	public Vorlesungsverzeichnis(String dateiname)
	{
		try
		{
			List<List<String>> str = load(dateiname);
			
			for(int i = 0; i < str.size(); i++)
			{
				Vorlesung a = new Vorlesung(str.get(i));
				set.add(a);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("TextFileFormatException : " + e);
		}
	}
	
	public List<String> titles()
	{
		Set<String> titles = new HashSet<String>();
		
		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext(); ) 
		{
	        titles.add( it.next().Titel);
		}
		List<String> temp = new ArrayList<String>(titles);
		
		Collections.sort(temp);
		return temp;
	}
	
	public Set<String> workaholics()
	{
		Set<String> work = new HashSet<String>();
		int min = 2;
		
		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext(); ) 
		{
			String s =it.next().Dozent;
			int c = 0;
			for (Iterator<Vorlesung> it2 = set.iterator(); it2.hasNext(); ) 
			{
				if(s.equals( it2.next().Dozent))
				{
					c++;
				}
				
				if(c >= min)
				{
					work.add(s);
				}
			}
		}
		return work;
	}
	
	public Map<String, List<String>> groupToTitles()
	{
		Map<String, List<String>> ret = new HashMap<String, List<String>>();
		
		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext(); ) 
		{
			String x = it.next().Gruppe;
			List<String> temp = new ArrayList<String>();
			for(Iterator<Vorlesung> it2 = set.iterator(); it2.hasNext();)
			{
				System.out.println(x);
				Vorlesung v = it2.next();
				if(v.Gruppe.equals(x))
				{
					temp.add(v.Titel);
				}
			}
			ret.put(x, temp);
		}
		
		return ret;
	}
	
	public Map<String, List<String>> multipleTitles()
	{
		return null;
	}
	
	public List<String> descendingTitles()
	{
		List<Vorlesung> titles = new ArrayList<Vorlesung>(set);
		
		Collections.sort(titles, new Comparator<Vorlesung>() {
			  @Override
			  public int compare(Vorlesung u1, Vorlesung u2) {
			    return u1.Teilnehmeranzahl.compareTo(u2.Teilnehmeranzahl);
			  }
			});
		
		
		
		
		
		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext(); ) 
		{
	        titles.add( it.next().Titel);
		}
		
	}
	
	
	public static List<List<String>> load(String filename) throws IOException
	{
		List<List<String>> result = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for (String line = br.readLine(); line != null; line = br.readLine())
			result.add(Arrays.asList(line.split(":")));
		br.close();
		return result;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext(); ) 
		{
			s = s + "\n" + it.next().toString();
			
		}
		
		return s;
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		Vorlesungsverzeichnis vor = new Vorlesungsverzeichnis(
				"C:\\Users\\pi890\\eclipse-workspace\\Java2\\src\\Vorlesungsdaten.txt");
		
		System.out.println(vor.groupToTitles());
		
		
		
	}

}