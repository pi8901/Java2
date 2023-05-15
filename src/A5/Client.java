package A5;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;

public class Client {
	public static void main(String[] args) {
	    Socket so = null;
	    String host = args[0];
	int port = Integer.valueOf(args[0]).intValue();
	try {
	      so = new Socket(host, port);
	    }
	catch (IOException e) {
	      System.out.println(e);
	      System.exit(-1);
	    }
	try {
	      BufferedReader ein = new BufferedReader(
	new InputStreamReader(System.in));
	      BufferedReader einSo = new BufferedReader(
	new InputStreamReader(so.getInputStream()));
	      PrintWriter ausSo = new PrintWriter(so.getOutputStream(), true);
	      System.out.println("> Eingabe:");
	      String eingabe;
	while ((eingabe = ein.readLine()) != null && !eingabe.equals("quit")) {
	        ausSo.println(eingabe);
	        System.out.println("> Antwort vom Server:");
	        System.out.println(einSo.readLine());
	        System.out.println("> Eingabe:"); 
	      }
	      ein.close();
	      so.close();
	}catch (IOException e) {
	      System.out.println(e);
	      System.exit(-1);
	}
	}
	}
