import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import A3.Vorlesung;
import A3.VorlesungComparator;

public class Modulbeschreibungen
{
	List<Modul> Studium = new ArrayList<Modul>();
	

	public static void main(String[] args) throws IOException
	{		
		Modulbeschreibungen s = new Modulbeschreibungen("C:\\Users\\pi890\\OneDrive\\Dokumente\\UNI\\Semester 2\\Java2\\Java2\\src\\Data");

		s.getSortierteStudiengaenge();
		System.out.println(Modul.sws);
	}
	
	public Modulbeschreibungen(String link) throws IOException
	{
		List<List<String>> result = load(link);
		for(List<String> element : result)
		{
			Studium.add(new Modul(element));
		}
	}

	public Set<String> getZertifikate(String studiengang)
	{
		Set<String> ret = new HashSet<String>();
		final Pattern pattern = Pattern.compile("[A-Za-z]+\\s[A-Za-z0-9]+.*[A-Za-z]+\\s[A-Za-z0-9]+", Pattern.CASE_INSENSITIVE);
		
		for(Modul element: Studium)
		{
			if(element.studiengang.equals(studiengang))
			{
				final Matcher matcher = pattern.matcher(element.art);
				if(matcher.matches())
				{
					ret.add(matcher.group());
				}
			}
		}
		
		return ret;
	}
	
	public Set<String> getVerzahnteModule()
	{
		Set<String> ret = new HashSet<String>();
		
		for(int i = 0; i < Studium.size(); i++)
		{
			Modul x = Studium.get(i);
			int f = 0;
			for(int j = 1; j < Studium.size() - 1; j++)
			{
				Modul y = Studium.get(j);
				if( x.bezeichnung.equals(y.bezeichnung) && x.verantwortlicher.equals(y.verantwortlicher))
				{
					f++;
				}
			}
			
			if(f > 1)
			{
				ret.add(x.bezeichnung);
			}
		}
		
		return ret;
	}
	
	int getAnzahlModule(String studiengang, Boolean pflicht)
	{
		int count = 0;

		try 
		{
		if(pflicht)
		{
			for(Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang) && !element.art.contains("Wahlpflichtmodul"))
				{
					System.out.println(element.studiengang + " " + element.art);
					count ++;
				}
			}
		}
		else if (!pflicht)
		{
			for(Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang) && element.art.contains("Wahlpflichtmodul"))
				{
					System.out.println(element.studiengang + " " + element.art);
					count ++;
				}
			}
		}
		}
		catch(Exception e)
		{
			for(Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang))
				{
					count ++;
				}
			}
		}
		
		return count;
	}
	
	int getAnzahlVeranstaltungen(String studiengang, Boolean pflicht)
	{
		int count = 0;

		try 
		{
		if(pflicht)
		{
			for(Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang) && !element.art.contains("Wahlpflichtmodul"))
				{
					count = count + element.veranstaltungen.size();
				}
			}
		}
		else if (!pflicht)
		{
			for(Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang) && element.art.contains("Wahlpflichtmodul"))
				{
					count = count + element.veranstaltungen.size();
				}
			}
		}
		}
		catch(Exception e)
		{
			for(Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang))
				{
					count = count + element.veranstaltungen.size();
				}
			}
		}
		
		return count;
	}
	
	Map<Integer, Integer> getECTS(String studiengang)
	{
		Map<Integer, Integer> ret = new HashMap<Integer, Integer>();
		for(Modul element : Studium)
		{
			if(!element.semester.contains("WPM") && element.studiengang.equals(studiengang)) 
			{
				if(ret.containsKey(Integer.parseInt(element.semester)))
				{
					System.out.println("addiere: " + element.ects + " " + ret.get(Integer.parseInt(element.semester)) );
					ret.put(Integer.parseInt(element.semester), (int) (element.ects + ret.get(Integer.parseInt(element.semester))));
				}
				
				else
				{
					ret.put(Integer.parseInt(element.semester), (int) Math.ceil(element.ects));
				}
			}
			
		}
		
		
		
		return ret;
	}
	
	Map<Integer, Integer> getSWS(String studiengang)
	{
		Map<Integer, Integer> ret = new HashMap<Integer, Integer>();
		
		for(Modul element : Studium)
		{
			if(!element.semester.contains("WPM") && element.studiengang.equals(studiengang)) 
			{
				for(int i = 0; i < element.veranstaltungen.size(); i++)
				{
					if(ret.containsKey(Integer.parseInt(element.semester)))
					{
						ret.put(Integer.parseInt(element.semester), (int) (element.veranstaltungen.get(i).sws + ret.get(Integer.parseInt(element.semester))));
					}
					else
					{
						ret.put(Integer.parseInt(element.semester), (int) Math.ceil(element.veranstaltungen.get(i).sws));
					}
				}
			}
			
		}
		
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	List<String> getSortierteStudiengaenge()
	{
		List<String> titles = new ArrayList<>();
		
		Object[] a = Modul.sws.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o1).getValue()
		                   .compareTo(((Map.Entry<String, Integer>) o2).getValue());
		    }
		});		
		
		for (Object g : a)
		{
			titles.add(((Map.Entry<String, Integer>) g).getKey());
		}
		return titles;
	}
	
	String getJSON(String studiengang)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
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
