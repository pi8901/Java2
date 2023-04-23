import java.lang.Comparable;

public class hj implements Comparable<Vorlesung> {  
// Used for sorting in ascending order of ID  
public int compare(Vorlesung a, Vorlesung b)  
    {  
		return Integer.parseInt(a.Teilnehmeranzahl) - Integer.parseInt(b.Teilnehmeranzahl);  
    }

@Override
public int compareTo(Vorlesung o)
{
	// TODO Auto-generated method stub
	return 0;
}  
}  