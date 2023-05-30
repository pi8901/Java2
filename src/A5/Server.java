package A5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server
{
	int port;
	Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
	String address;

	public Server(int port, String add)
	{
		readFile();
		this.port = port;
	}

	public void readFile()
	{
		try (BufferedReader in = new BufferedReader(new FileReader("map.txt")))
		{
			String line;
			while ((line = in.readLine()) != null)
			{
				String[] parts = line.split("=", 2);
				if (parts.length >= 2)
				{
					String key = parts[0];
					String[] values = parts[1].split(",");
					map.put(key, new HashSet<>(Arrays.asList(values)));
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void store()
	{
		// Store the HashMap in a .txt file
		try (PrintWriter out = new PrintWriter(new FileWriter("map.txt")))
		{
			for (Map.Entry<String, HashSet<String>> entry : map.entrySet())
			{
				out.print(entry.getKey() + "=");
				for (String value : entry.getValue())
				{
					out.print(value + ",");
				}
				out.println();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			System.out.println("Server started. Listening on port " + port);

			while (true)
			{
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine = in.readLine();

				String s = inputLine.replace("\b", "");
				new Thread(new Runnable()
				{
					public void run()
					{
						try
						{
							out.println(handleRequest(s));
							clientSocket.close();
						} catch (IOException | InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}).start();
				store();
			}

		} catch (IOException e)
		{
			System.out.println("Exception caught when starting the server");
			System.out.println(e.getMessage());
		}
	}

	public String handleRequest(String in) throws IOException, InterruptedException
	{
		String o = "";
		if (in.toLowerCase().contains("stop"))
		{
			o = "1 ";
		}
		else if (in.toLowerCase().contains("put"))
		{
			o = put(in);
		} else if (in.toLowerCase().contains("getall"))
		{
			o = getAll();
		} else if (in.toLowerCase().contains("get "))
		{
			o = get(in);
		}
		else if (in.toLowerCase().contains("del"))
		{
			o = del(in);
		}
		return o;
	}

	private String del(String input)
	{
		String[] str = input.split(" ");
		String key = str[1];
		if (!map.containsKey(key))
		{
			return "0";
		}
		String f = get("get " + key);
		map.remove(key);
		return f;

	}

	private String getAll()
	{ 
		Set<String> keys = map.keySet();

		String f = "";
		if(keys.isEmpty())
		{
			return "0";
		}
		
		List<HashSet<String>> elements = new ArrayList<HashSet<String>>();
		
		for(String e : keys)
		{
			elements.add(map.get(e));
		}
		
		for(int i = 0; i < elements.size(); i++)
		{
			for(int j = 0 ; j < elements.size(); j++)
			{
				if(elements.get(i).containsAll(elements.get(j)) && i != j)
				{
					elements.remove(j);
				}
			}
		}
		
		return "1 " + elements.toString().substring(1, elements.toString().length() - 1).replace(" ", "");
	}

	private String get(String input)
	{
		String[] str = input.split(" ");
		String key = str[1];
		String f = "1 ";
		
		if(!map.containsKey(key))
		{
			return "0";
		}
		
		for (String e : map.get(key))
		{
			f = f  + e + ",";
		}
		return f.substring(0, f.length() - 1);
	}
	

	private String put(String input)
	{
		String[] str = input.split(" ");
		String key = str[1];
		String value = str[2];
		value.replaceAll("\\s+", "");

		String g = "1 ";
		if(map.containsKey(key)) 
		{
			g = get("get " + key);
		}
		HashSet<String> o = new HashSet<String>(Arrays.asList(value.split(",")));

		map.put(key, o);

		return g;
	}
}
