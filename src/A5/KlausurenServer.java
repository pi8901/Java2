package A5;

import java.net.*;
import java.io.*;

public class KlausurenServer {
	
    public void run(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
           
            while (( inputLine = in.readLine()) != null&& !inputLine.equalsIgnoreCase("STOP")) {
            	if (inputLine.equalsIgnoreCase("PUT")) {
        			out.println("1 "+ inputLine);
				} else if (inputLine.equalsIgnoreCase("PUT")){
					out.println(inputLine);
				}
            }
			
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}