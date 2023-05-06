package A1;
import java.util.Arrays;

public class GrosseZahl
{

	int[] num;

	public static void main(String[] args)
	{
	}

	//Konstruktor für String
	GrosseZahl(String d)
	{
		num = new int[d.length()];
		for (int i = d.length() - 1; i >= 0; i--)
		{
			num[i] = Character.getNumericValue((d.charAt(i)));
		}
	}

	//Konstruktor für int
	GrosseZahl(int z)
	{
		this.num = new GrosseZahl(z + "").num;
	}

	//Umwandeln in String
	public String toString()
	{
		String s = "";
		for (int i = num.length - 1; i >= 0; i--)
		{
			s = num[i] + s;
		}
		return s;
	}

	//Größer, kleiner Prüfung (ist this < x
	public boolean less(GrosseZahl x)
	{
		if (this.num.length < x.num.length)
		{
			return true;
		} 
		else if (this.num.length > x.num.length)
		{
			return false;
		}

		for (int i = 0; i < this.num.length; i++)
		{
			if (this.num[i] < x.num[i])
			{
				return true;
			} 
			else if (this.num[i] > x.num[i])
			{
				return false;
			}
		}
		return false;
	}

	//Addition
	public GrosseZahl add(GrosseZahl x)
	{
		//Beide Zahlen auf die gleiche Größe bringen (0 anhängen)
		if (x.num.length < this.num.length)
		{
			x = new GrosseZahl(String.format("%0" + (this.num.length - x.num.length) + "d%s", 0, x.toString()));
		} 
		else if (x.num.length > this.num.length)
		{
			return (x.add(this));
		}

		String out = "";
		int offset = 0;

		for (int i = x.num.length - 1; i >= 0; i--)
		{
			//Übertrag
			if (x.num[i] + this.num[i] + offset > 9)
			{
				out = (x.num[i] + this.num[i] + offset - 10) + out;
				offset = 1;
			} 
			//Kein Übertrag
			else
			{
				out = (x.num[i] + this.num[i] + offset) + out;
				offset = 0;
			}
		}
		//Bei Übertrag auf der höchsten Zahl (90 + 11 = 101)
		if (offset == 1)
		{
			out = 1 + out;
		}
		return new GrosseZahl(out);
	}

	//Subtraktion
	public GrosseZahl sub(GrosseZahl x)
	{
		//Beide Zahlen auf die gleiche Größe bringen (0 anhängen)
		if (x.num.length < this.num.length)
		{
			x = new GrosseZahl(String.format("%0" + (this.num.length - x.num.length) + "d%s", 0, x.toString()));
		} 
		else if (x.num.length > this.num.length)
		{
			return (x.sub(this));
		}

		String out = "";
		int offset = 0;

		for (int i = x.num.length - 1; i >= 0; i--)
		{
			//Übertrag
			if (this.num[i] - x.num[i] - offset < 0)
			{
				out = (this.num[i] - x.num[i] - offset + 10) + out;
				offset = 1;
			} 
			//Kein Übertrag
			else
			{
				out = (this.num[i] - x.num[i] - offset) + out;
				offset = 0;
			}
		}

		//Entstandenen Nullen entfernen (013 -> 13)
		out = out.replaceAll("^0*", "");

		return new GrosseZahl(out);
	}

	//Multiplikation
	public GrosseZahl mult(GrosseZahl x)
	{
		GrosseZahl g = new GrosseZahl(0);
		for (int i = 0; i < x.num.length; i++)
		{
			/*
			 * Addiert sich selbst x.num[i] * 10 ^ i mal
			 * 
			 * Beispiel:
			 * 5 * 32 =>
			 * x + 5 * (2 * 10 ^ 0 )->2
			 * +
			 * x + 5 * (3 * 10 ^ 1)->30
			 */
			for (int j = 0; j < x.num[x.num.length - i - 1] * Math.pow(10, i); j++)
			{
				g = g.add(this);
			}
		}
		return g;
	}

	//Größter gemeinsamer Teiler
	public GrosseZahl ggT(GrosseZahl x)
	{
		if (!Arrays.equals(x.num, this.num))
		{
			if (this.less(x))
			{
				return this.ggT(x.sub(this));
			} 
			else
			{
				return x.ggT(this.sub(x));
			}
		}
		return x;
	}
}