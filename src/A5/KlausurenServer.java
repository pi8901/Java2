package A5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KlausurenServer {
	public static void main(String[] args) {
	    ServerSocket serverSo = null;
	    int port = Integer.valueOf(args[0]).intValue();
	    
	    try {
	    	serverSo = new ServerSocket(port);
	    }
	    catch (IOException e) {}
	    
	    System.out.println("Server lauscht auf Port: "+port);
	    
	    int clientNr = 0;
	    while(true) {
	    	try {
				Socket clientSo = serverSo.accept();
				clientNr++;
				System.out.println("Mit CLient"+clientNr+ " verbunden");
				new EchoServerThread(clientSo, clientNr).start();
			} catch (IOException e) {
				// TODO: handle exception
			}
	    }
	}
}
