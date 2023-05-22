package A5;

import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;



public class KlausurenServer {
	Map<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
	int port;
	
    public KlausurenServer(int port) {
		this.port = port;
	}
    //eig ist der return oldvalue, aber irgendwie ist es die neue Value ???
    public ArrayList<String> Put(String input) {
    	
    	System.out.println("input: " + input);
    	
    	String[] str = input.split(" ");
		String key = str[1];
		String value = str[2];
		value.replaceAll("\\s+","");
		
		System.out.println("key: " + key);
		System.out.println("value: " + value);
		
		
		ArrayList<String> oldval = map.get(key);
		
		if (oldval != null) {
			String list = oldval.get(oldval.size()-1);
			System.out.println(list);
		}
		
			ArrayList<String> o = new ArrayList<String>(Arrays.asList(value.split(",")));
			if(map.containsKey(key))
			{
				o.addAll(map.get(key));
			}
			
			map.put(key, o);
			
			for(int i = 0; i < o.size(); i++)
			{
				System.out.println(o.get(i));
			}

    	return oldval;
    }
    
    public ArrayList<String> Get(String input) {

    	String[] str = input.split(" ");
		String key = str[1];
   
    	return map.get(key);
    }
    //wenn ich map.remove(key) ausgebe ist das richtig, sobald ich es return falsch ????????
    public List<List<String>> Del(String input) {
    	List<List<String>> out = new ArrayList<>();
  
    	String[] str = input.split(" ");
		String key = str[1];
		
		if (map.containsKey(key) && (!map.get(key).equals(""))) {
			out.add(map.get(key));
			out.remove(key);
			return out;
		} else {
			return null;
		}
    	
    }
    
    public String GetAll() {
    	
    	
    	return "GetAll";
    }
    
    public void handleRequest(Socket clientSocket) throws IOException {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;

            String s = "";
            while ((inputLine = in.readLine()) != null && !inputLine.equalsIgnoreCase("STOP")) {
            	s = s + inputLine;
            	
            }
            
            System.out.println(s);
                if (inputLine.toLowerCase().contains("put")) {
                    if (Put(inputLine) == null) {
                        out.println("1 ");
                    } else {
                        out.print("1 ");
                        out.println(Put(inputLine));
                    }

                } else if (inputLine.toLowerCase().contains("getall")) {
                    out.println(GetAll());

                } else if (inputLine.toLowerCase().contains("get")) {
                    if (Get(inputLine) == null) {
                        out.println("0");
                    } else {
                        out.print("1 ");
                        out.println(Get(inputLine));
                    }

                } else if (inputLine.toLowerCase().contains("del")) {
                    if (Del(inputLine) == null) {
                        out.println("0");
                    } else {
                        out.print("1 ");
                        out.println(Del(inputLine));
                    }

                } else if (inputLine.toLowerCase().contains("stop")) {
                   out.println("1");
                   clientSocket.close();

                }else {
                    out.println("Command not found!");
                }
            
        } catch (IOException e) {
            System.out.println("Exception caught when handling client request");
            System.out.println(e.getMessage());
        }
    }
    
    public void handleRequestt(Socket clientSocket) throws IOException {
        byte[] messageByte = new byte[1000];
        boolean end = false;
        String dataString = "";

        try 
        {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            int bytesRead = 0;

            messageByte[0] = in.readByte();
            messageByte[1] = in.readByte();
            ByteBuffer byteBuffer = ByteBuffer.wrap(messageByte, 0, 2);

            int bytesToRead = byteBuffer.getShort();
            System.out.println("About to read " + bytesToRead + " octets");

            //The following code shows in detail how to read from a TCP socket

            while(!end)
            {
                bytesRead = in.read(messageByte);
                dataString += new String(messageByte, 0, bytesRead);
                if (dataString.length() == bytesToRead )
                {
                    end = true;
                }
            }

            //All the code in the loop can be replaced by these two lines
            //in.readFully(messageByte, 0, bytesToRead);
            //dataString = new String(messageByte, 0, bytesToRead);

            System.out.println("MESSAGE: " + dataString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    
    
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                

                Thread thread = new Thread(() -> {
                    try {
                        handleRequest(clientSocket);
                    } catch (IOException e) {
                        System.out.println("Exception caught when handling client request");
                        System.out.println(e.getMessage());
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            System.out.println("Exception caught when closing client socket");
                            System.out.println(e.getMessage());
                        }
                    }
                });

                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when starting the server");
            System.out.println(e.getMessage());
        }
    }
}

