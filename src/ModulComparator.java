import java.util.Comparator;


public class ModulComparator implements Comparator<Modul>
{
	@Override
	public int compare(Object o1, Object o2) 
	{
		// TODO Auto-generated method stub
		return o2.sws - o1.sws;
	}	
}
