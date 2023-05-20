package A5;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.*;
import java.net.*;

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
		ArrayList<String> oldvalue = map.get(key);
		//System.out.println(oldvalue);
		if (value.contains(",")) {
			map.put(key, new ArrayList<String>());
			String[] val = value.split(",");
			for (int i = 0; i < val.length; i++) {
				map.get(key).add(val[i]);
			}
		}else {
			ArrayList<String> out = new ArrayList<String>();
			out.add(value);
			map.put(key, out);
		}
    	return oldvalue;
    }
    
    public ArrayList<String> Get(String input) {

    	String[] str = input.split(" ");
		String key = str[1];
   
    	return map.get(key);
    }
    
    public ArrayList<String> Del(String input) {
    	String[] str = input.split(" ");
		String key = str[1];
    
    	return map.remove(key);
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
        			out.println("1 " + Put(inputLine));
					}
        			
				} else if (inputLine.toLowerCase().contains("getall")){
					out.println(GetAll());
					
				
					
					
				}else if(inputLine.toLowerCase().contains("get")) {
					if (Get(inputLine) == null) {
            			out.println("0");
					}else {
        			out.println("1 " + Get(inputLine));
					}
									
					
				}else if(inputLine.toLowerCase().contains("del")) {
					if (Del(inputLine) == null) {
            			out.println("0");
					}else {
        			out.println("1 " + Del(inputLine));
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