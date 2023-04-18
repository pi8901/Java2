import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
	
	public Set<String> Workaholic()
	{
		int num = 2;
		
		for (Iterator<Vorlesung> it = set.iterator(); it.hasNext(); ) 
		{
	        Vorlesung f = it.next();
	        
	        
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

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		Vorlesungsverzeichnis vor = new Vorlesungsverzeichnis(
				"C:\\Users\\pi890\\eclipse-workspace\\Java2\\src\\Vorlesungsdaten.txt");
		
	}

}
