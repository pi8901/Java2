package A3;
import java.util.Comparator;

public class VorlesungComparator implements Comparator<Vorlesung>
{
	@Override
	public int compare(Vorlesung o1, Vorlesung o2) {
		// TODO Auto-generated method stub
		return o2.Teilnehmeranzahl - o1.Teilnehmeranzahl;
	}	
}