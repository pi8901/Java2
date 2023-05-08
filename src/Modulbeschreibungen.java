import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Modulbeschreibungen
{
	List<Modul> Studium = new ArrayList<>();

	public Modulbeschreibungen(String link) throws IOException
	{
		List<List<String>> result = load(link);
		for (List<String> element : result)
		{
			Studium.add(new Modul(element));
		}
	}

	public Set<String> getZertifikate(String studiengang)
	{
		Set<String> ret = new HashSet<>();

		for (Modul element : Studium)
		{
			if (element.studiengang.equals(studiengang))
			{
				String[] g = element.art.split(" Zertifikat ");
				for (int i = 0; i < g.length; i++)
				{
					if (!g[i].contains("modul"))
					{
						if (g[i].substring(g[i].lastIndexOf(" ") + 1).equals("und"))
						{
							g[i] = g[i].substring(0, g[i].length() - 4);
						}
						ret.add(g[i]);
					}
				}
			}

		}

		return ret;
	}

	public Set<String> getVerzahnteModule()
	{
		Set<String> ret = new HashSet<>();

		for (Modul x : Studium)
		{
			int f = 0;
			for (int j = 1; j < Studium.size() - 1; j++)
			{
				Modul y = Studium.get(j);
				if (x.bezeichnung.equals(y.bezeichnung) && x.verantwortlicher.equals(y.verantwortlicher))
				{
					f++;
				}
			}

			if (f > 1)
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
			if (pflicht)
			{
				for (Modul element : Studium)
				{
					if (element.studiengang.equals(studiengang) && !element.art.contains("Wahlpflichtmodul"))
					{
						count++;
					}
				}
			} else if (!pflicht)
			{
				for (Modul element : Studium)
				{
					if (element.studiengang.equals(studiengang) && element.art.contains("Wahlpflichtmodul"))
					{
						count++;
					}
				}
			}
		} catch (Exception e)
		{
			for (Modul element : Studium)
			{
				if (element.studiengang.equals(studiengang))
				{
					count++;
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
			if (pflicht)
			{
				for (Modul element : Studium)
				{
					if (element.studiengang.equals(studiengang) && !element.art.contains("Wahlpflichtmodul"))
					{
						count = count + element.veranstaltungen.size();
					}
				}
			} else if (!pflicht)
			{
				for (Modul element : Studium)
				{
					if (element.studiengang.equals(studiengang) && element.art.contains("Wahlpflichtmodul"))
					{
						count = count + element.veranstaltungen.size();
					}
				}
			}
		} catch (Exception e)
		{
			for (Modul element : Studium)
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
		Map<Integer, Integer> ret = new HashMap<>();
		for (Modul element : Studium)
		{
			if (!element.semester.contains("WPM") && element.studiengang.equals(studiengang))
			{
				if (ret.containsKey(Integer.parseInt(element.semester)))
				{
					ret.put(Integer.parseInt(element.semester),
							(int) (element.ects + ret.get(Integer.parseInt(element.semester))));
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
		Map<Integer, Integer> ret = new HashMap<>();

		for (Modul element : Studium)
		{
			if (!element.semester.contains("WPM") && element.studiengang.equals(studiengang))
			{
				for (Veranstaltung element2 : element.veranstaltungen)
				{
					if (ret.containsKey(Integer.parseInt(element.semester)))
					{
						ret.put(Integer.parseInt(element.semester), element2.sws
								+ ret.get(Integer.parseInt(element.semester)));
					} else
					{
						ret.put(Integer.parseInt(element.semester),
								(int) Math.ceil(element2.sws));
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
		Arrays.sort(a, new Comparator()
		{
			@Override
			public int compare(Object o1, Object o2)
			{
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
		sb.append("[");
		List<Modul> mods = new ArrayList<>();
		for (int i = 0; i < Studium.size(); i++)
		{
			if (Studium.get(i).studiengang.equals(studiengang))
			{
				mods.add(Studium.get(i));
			}

		}

		for (int i = 0; i < mods.size(); i++)
		{
			if (Studium.get(i).studiengang.equals(studiengang))
			{
				Modul s = mods.get(i);
				sb.append("{\n");
				sb.append("\t\"bezeichnung\": \"" + s.bezeichnung + "\",\n");
				sb.append("\t\"kuerzel\": \"" + s.kuerzel + "\",\n");
				sb.append("\t\"studiengang\": \"" + s.studiengang + "\",\n");
				sb.append("\t\"semester\": \"" + s.semester + "\",\n");
				sb.append("\t\"art\": \"" + s.art + "\",\n");
				sb.append("\t\"ects\": " + s.ects + ",\n");
				sb.append("\t\"pruefungsform\": \"" + s.pruefungsform + "\",\n");
				sb.append("\t\"verantwortlicher\": \"" + s.verantwortlicher + "\",\n");
				sb.append("\t\"veranstaltungen\": [");
				for (int j = 0; j < s.veranstaltungen.size(); j++)
				{
					Veranstaltung v = s.veranstaltungen.get(j);
					sb.append("{\n");
					sb.append("\t\t\"titel\": \"" + v.titel + "\",\n");
					sb.append("\t\t\"dozent\": \"" + v.dozent + "\",\n");
					sb.append("\t\t\"sws\": " + v.sws + "\n");
					if (j < s.veranstaltungen.size() - 1)
					{
						sb.append("\t}, ");
					} else
					{
						sb.append("}\n");
					}
				}
				sb.append("]");
				if (i < mods.size() - 1)
				{
					sb.append("\t}, ");
				} else
				{
					sb.append("}\n");
				}

			}

		}
		sb.append("]");
		return sb.toString();
	}

	public static List<List<String>> load(String filename) throws IOException
	{
		List<List<String>> result = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for (String line = br.readLine(); line != null; line = br.readLine())
		{
			List<String> temp = new ArrayList<>();
			while (line != null && !line.equals(""))
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
