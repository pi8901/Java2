
public class Veranstaltung
{

	public String titel;
	public String dozent;
	public int sws;

	public Veranstaltung(String string)
	{
		String[] x = string.split("\\|");
		this.titel = x[0];
		this.dozent = x[1];
		this.sws = Integer.parseInt(x[2]);
	}
}
