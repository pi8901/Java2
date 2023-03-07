
public class GrosseZahl
{
	
	int[] num;

	public static void main(String[] args)
	{
		GrosseZahl x = new GrosseZahl(208);
		GrosseZahl y = new GrosseZahl("212");
		
		
	
		System.out.println(x.toString());
		System.out.println(y.toString());
		
		System.out.println(x.less(y));
		x.add(y);

	}
	
	//done
	GrosseZahl(String d)
	{
		num = new int[d.length()];
		d = new StringBuilder(d).reverse().toString();
		
		for(int i = 0; i < d.length(); i ++)
		{
			num[i] = Character.getNumericValue((d.charAt(i)));
		}
		
		
	}
	
	//done
	GrosseZahl(int z)
	{
		String d = z + "";
		d = new StringBuilder(d).reverse().toString();
		num = new int[d.length()];
		for(int i = 0; i < d.length(); i ++)
		{
			num[i] = Character.getNumericValue((d.charAt(i)));
		}
		
	}
	
	//done
	public String toString()
	{
		String s  = "";
		for(int i = num.length - 1; i >= 0; i--)
		{
			s = s + num[i];
		}
		return s;
	}
	
	
	//done
	private boolean less(GrosseZahl x)
	{
		if(this.num.length < x.num.length)
		{
			return true;
		}
		else if(this.num.length > x.num.length)
		{
			return false;
		}
		
		for(int i = this.num.length - 1; i >= 0; i--)
		{
			if( this.num[i] < x.num[i])
			{
				return true;
			}
			else if(this.num[i] > x.num[i])
			{
				return false;
			}
		}
		return false;
	}
	
	
	private GrosseZahl add(GrosseZahl x)
	{
		String out = "";
		int offset = 0;
		
		for(int i = 0; i < this.num.length - 1; i++)
		{
			out = out + this.num[i] + x.num[i];
			if(this.num[i] + x.num[i] + offset > 9)
			{
				offset = 1;
			}
		}
		
		return null;
	}
	
	private GrosseZahl mult(GrosseZahl x)
	{
		return null;
	}
	
	
	private GrosseZahl ggT(GrosseZahl x)
	{
		return null;
	}
	
	
	
	

}
