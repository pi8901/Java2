package A5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoServerThread extends Thread {
private Socket clientSo;
private int clientNr;

public EchoServerThread(Socket clientSo, int clientNr) {
	this.clientSo = clientSo;
	this.clientNr = clientNr;
}

public void run() {
	try {
		BufferedReader einSo = new BufferedReader(new InputStreamReader(clientSo.getInputStream()));
		PrintWriter ausSo = new PrintWriter(clientSo.getOutputStream());
		
		String zeile;
		while ((zeile = einSo.readLine()) != null) {
			ausSo.println("Client"+clientNr+": "+zeile);
		}
		clientSo.close();
	} catch (Exception e) {}
	finally {
		System.out.println("Client" + clientNr + "beendet");
	}
}

}
