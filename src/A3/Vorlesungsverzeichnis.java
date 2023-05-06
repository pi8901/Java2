package A3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Vorlesungsverzeichnis
{

	Set<Vorlesung> set = new HashSet<>();

	// Geht
	public Vorlesungsverzeichnis(String dateiname) throws TextFileFormatException
	{
		try
		{
			List<List<String>> str = load(dateiname);

			for (List<String> element : str)
			{
				set.add(new Vorlesung(element));
			}

		} catch (Exception e)
		{
			throw new TextFileFormatException("Hier ist Error");
		}
	}

	// Geht
	public List<String> titles()
	{
		Set<String> titles = new HashSet<>();

		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext();)
		{
			titles.add(it.next().Titel);
		}
		List<String> temp = new ArrayList<>(titles);

		Collections.sort(temp);
		return temp;
	}

	// Geht
	public Set<String> workaholics()
	{
		Set<String> work = new HashSet<>();
		int min = 2;

		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext();)
		{
			String s = it.next().Dozent;
			int c = 0;
			for (Iterator<Vorlesung> it2 = set.iterator(); it2.hasNext();)
			{
				if (s.equals(it2.next().Dozent))
				{
					c++;
				}

				if (c >= min)
				{
					work.add(s);
				}
			}
		}
		return work;
	}

	// Geht
	public Map<String, List<String>> groupToTitles()
	{
		Map<String, List<String>> ret = new HashMap<>();

		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext();)
		{
			String x = it.next().Gruppe;
			List<String> temp = new ArrayList<>();
			for (Vorlesung v : set)
			{
				System.out.println(x);
				if (v.Gruppe.equals(x))
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
		Map<String, List<String>> ret = new HashMap<>();

		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext();)
		{
			String x = it.next().Titel;
			List<String> temp = new ArrayList<>();
			for (Vorlesung y : set)
			{
				if (x.equals(y.Titel))
				{
					temp.add(y.Dozent);
				}

			}
			if (temp.size() > 1)
			{
				ret.put(x, temp);
			}

		}
		return ret;
	}

	// Geht
	public List<String> descendingTitles()
	{
		List<String> titles = new ArrayList<>();
		TreeSet<Vorlesung> sorted = new TreeSet<>(new VorlesungComparator());
		sorted.addAll(set);

		for (Vorlesung g : sorted)
		{
			titles.add(g.Titel);
		}

		return titles;

	}

	// Geht
	public static List<List<String>> load(String filename) throws IOException
	{
		List<List<String>> result = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for (String line = br.readLine(); line != null; line = br.readLine())
			result.add(Arrays.asList(line.split(":")));
		br.close();
		return result;
	}
}