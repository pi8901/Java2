import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharCollection
{

	List<Character> list = new ArrayList<Character>();

	public CharCollection(char... cc)
	{

		for (int i = 0; i < cc.length; i++)
		{
			this.list.add(cc[i]);
		}
	}

	public CharCollection(String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			this.list.add(s.charAt(i));
		}
	}

	int size()
	{
		return list.size();
	}

	int count(char c)
	{
		int counter = 0;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).equals(c))
			{
				counter++;
			}
		}
		return counter;
	}

	int different()
	{
		Set<String> distinctSet = new HashSet(list);
		return distinctSet.size();
	}

	char top()
	{
		if (this.size() == 0)
		{
			return 0;
		}
		char top = list.get(0);
		int max = 0;
		int max_o = 0;
		for (int i = 0; i < list.size(); i++)
		{
			max = count(list.get(i));

			if (max > max_o)
			{
				max_o = max;
				top = list.get(i);
			}
			max = 0;
		}
		return top;
	}

	@Override
	public String toString()
	{
		String out = "(";
		for (int i = 0; i < list.size(); i++)
		{
			out = out + list.get(i) + ", ";
		}
		if (list.size() == 0)
		{
			return "()";
		}
		return out.substring(0, out.length() - 2) + ")";
	}

	public CharCollection moreThan(int m)
	{
		String out = "";
		for (int i = 0; i < list.size(); i++)
		{
			if (count(list.get(i)) > m)
			{
				out = out + list.get(i);
			}
		}
		return new CharCollection(out);
	}

	public boolean equals(Object x)
	{
		try
		{
			CharCollection y = (CharCollection) x;
			this.list.sort(null);
			y.list.sort(null);
			if (this.list.equals(y.list))
			{
				return true;
			}
			return false;
		} catch (Exception e)
		{
			return false;
		}
	}

	CharCollection except(CharCollection cc)
	{
		List<Character> copy = (List<Character>) ((ArrayList<Character>) this.list).clone();
		for (int i = 0; i < cc.size(); i++)
		{
			copy.remove(cc.list.get(i));
		}

		String s = "";
		for (int i = 0; i < copy.size(); i++)
		{
			s = s + copy.get(i);
		}
		return new CharCollection(s);
	}

	boolean isSubset(CharCollection cc)
	{
		for (int i = 0; i < cc.size(); i++)
		{
			char x = cc.list.get(i);
			if (count(x) < cc.count(x))
			{
				return false;
			}
		}
		return true;
	}

}