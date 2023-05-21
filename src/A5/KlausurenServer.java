package A5;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    	
    	String[] str = input.split(" ");
		String key = str[1];
		String value = str[2];
		value.replaceAll("\\s+","");
		
		
		
		ArrayList<String> oldval = map.get(key);
		if (oldval != null) {
			String list = oldval.get(oldval.size()-1);
			System.out.println(list);
		}
		
		
		if (value.contains(",")) {
			map.put(key, new ArrayList<String>());
			String[] val = value.split(",");
			System.out.println(val);
			for (int i = 0; i < val.length; i++) {
				map.get(key).add(val[i]);
			}
		}else {
			ArrayList<String> out = new ArrayList<String>();
			out.add(value);
			map.put(key, out);
		}
    	return oldval;
    }
    
    public ArrayList<String> Get(String input) {

    	String[] str = input.split(" ");
		String key = str[1];
   
    	return map.get(key);
    }
    //wenn ich map.remove(key) ausgebe ist das richtig, sobald ich es return falsch ????????
    public ArrayList<String> Del(String input) {
    	ArrayList<String> out = null;
    	try {
    	String[] str = input.split(" ");
		String key = str[1];
		out = Get(input);
		map.remove(key);
		System.out.println(out);// wenn Value 1 ist, ist out 1 und null ?
    	}catch (Exception e) {
			out.add("0");
		}
    	return out;
    }
    
    public String GetAll() {
    	
    	
    	return "GetAll";
    }
    
	public void run() throws IOException {
        
        
        int portNumber = port;
        
        try (
            ServerSocket serverSocket =
                new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
           
            while (( inputLine = in.readLine()) != null&& !inputLine.equalsIgnoreCase("STOP")) {
            	if (inputLine.toLowerCase().contains("put")) {
            		if (Put(inputLine) == null) {
            			out.println("1 ");
					}else {
        			out.print("1 ");
        			out.println(Put(inputLine));
					}
        			
				} else if (inputLine.toLowerCase().contains("getall")){
					out.println(GetAll());
					
				
					
					
				}else if(inputLine.toLowerCase().contains("get")) {
					if (Get(inputLine) == null) {
            			out.println("0");
					}else {
						out.print("1 ");
	        			out.println(Get(inputLine));
					}
									
					
				}else if(inputLine.toLowerCase().contains("del")) {
					if (Del(inputLine) == null) {
            			out.println("0");
					}else {
						out.print("1 ");
	        			out.println(Del(inputLine));
					}
					
					
					
				}else {
					out.println("Command not found!");
				}
            }
			
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}