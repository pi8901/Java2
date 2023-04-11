import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Vorlesungsverzeichnis {
	
	Set<Vorlesung> set = new HashSet<Vorlesung>();
	
	public Vorlesungsverzeichnis(String dateiname) {
		 try {
			List<List<String>> str = load(dateiname);
			String out = "";
			for (int i = 0; i < str.size(); i++) {
				for (int j = 0; j < 4; j++) {
					out += str.get(i).get(j);
				}
				
			}
			System.out.println(out);
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("TextFileFormatException : " + e);
		}

	}


	public static List<List<String>> load(String filename) throws IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for (String line=br.readLine(); line!=null; line=br.readLine())
			result.add(Arrays.asList(line.split(":")));
		br.close();
		return result;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vorlesungsverzeichnis vor = new Vorlesungsverzeichnis("C:\\Users\\marce\\Documents\\GitHub\\Java2\\src\\Vorlesungsdaten.txt");
	}

}
