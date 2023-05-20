package A5;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.net.*;

public class KlausurenServer {
	Map<String,Integer> map = new HashMap<>();
	int port;
	
    public KlausurenServer(int port) {
		this.port = port;
	}

    public String Put() {
    	
    	return "Put";
    }
    
    public String Get() {
    	
    	return "Get";
    }
    
    public String Del() {
    	
    	return "Del";
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
            	if (inputLine.toLowerCase().contains("PUT")) {
        			out.println(Put());
        			String[] str = inputLine.split("\\s+");
        			
        			
        			
				} else if (inputLine.toLowerCase().contains("GET")){
					out.println(Get());
					String[] str = inputLine.split("\\s+");
					
					
					
				}else if(inputLine.toLowerCase().contains("GETALL")) {
					out.println(GetAll());
					String[] str = inputLine.split("\\s+");
					
					
					
				}else if(inputLine.toLowerCase().contains("DEL")) {
					out.println(Del());
					String[] str = inputLine.split("\\s+");
					
					
					
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