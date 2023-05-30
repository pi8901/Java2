package A5;
import static org.junit.Assert.*;

import org.junit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlausurenServer_Test {

	PrintWriter ausSo;
	BufferedReader einSo;
	Socket so;
	String line;
	
	public void opensocket() throws IOException {
		so = new Socket("localhost", 2000);	//Port 2000 wird erwartet!
		ausSo = new PrintWriter(so.getOutputStream(), true);
		einSo = new BufferedReader(new InputStreamReader(so.getInputStream()));
	}

	public void closesocket() throws IOException {
		so.close();
	}

	@Before
	public void setUp() throws IOException
	{
		System.out.println("INITIALISIERUNG");
		opensocket();
		ausSo.println("del mail1");
		line = einSo.readLine();
   		closesocket();
		opensocket();
		ausSo.println("del mail2");
		line = einSo.readLine();
   		closesocket();
		opensocket();
		ausSo.println("del mail3");
		line = einSo.readLine();
   		closesocket();
		opensocket();
		ausSo.println("del mail4");
		line = einSo.readLine();
   		closesocket();
		System.out.println("INITIALISIERUNG OK"+"\n");
    }

	@Test	//alles in einer Test-Methode, weil die richtige Reihenfolge sonst nicht gewährleistet ist!
	public void all() throws IOException 
	{

		System.out.println("TEST: del mail1");
		opensocket();
		ausSo.println("del mail1");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"0");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		
		System.out.println("TEST: put mail1 22,24");
		opensocket();
		ausSo.println("put mail1 22,24");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 ")||line.equals("1"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail2 22,23,24");
		opensocket();
		ausSo.println("put mail2 22,23,24");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 ")||line.equals("1"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail3 2");
		opensocket();
		ausSo.println("put mail3 2");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 ")||line.equals("1"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: getall");
		opensocket();
		ausSo.println("getall");
		line = einSo.readLine();
		System.out.println(line);
// 		assertTrue(line.equals("1 [2],[22,23,24]"));
   		assertTrue(line.equals("1 [2],[22,23,24]")||line.equals("1 [22,23,24],[2]"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: del mail1");
		opensocket();
		ausSo.println("del mail1");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"1 22,24");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail2 33,34,35");
		opensocket();
   		ausSo.println("put mail2 33,34,35");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"1 22,23,24");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: get mail2");
		opensocket();
		ausSo.println("get mail2");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"1 33,34,35");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail3 44,45,46");
		opensocket();
   		ausSo.println("put mail3 44,45,46");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 2")||line.equals("1"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail4 44,45");
		opensocket();
   		ausSo.println("put mail4 44,45");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 ")||line.equals("1"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: getall");
		opensocket();
		ausSo.println("getall");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 [44,45],[46],[33,34,35]")||line.equals("1 [44,45,46],[33,34,35]"));
//		assertEquals(line,"1 [33,34,35],[44,45,46]");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: del mail2");
		opensocket();
		ausSo.println("del mail2");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"1 33,34,35");
   		closesocket();
		System.out.println("TEST: OK"+"\n");
   		
		System.out.println("TEST: del mail3");
		opensocket();
		ausSo.println("del mail3");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"1 44,45,46");
   		closesocket();
		System.out.println("TEST: OK"+"\n");
   		
		System.out.println("TEST: get mail2");
		opensocket();
		ausSo.println("get mail2");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"0");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: getall");
		opensocket();
		ausSo.println("getall");
		line = einSo.readLine();
		System.out.println(line);
   		assertEquals(line,"1 [44,45]");
   		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail2 45,46");
		opensocket();
		ausSo.println("put mail2 45,46");
		line = einSo.readLine();
		System.out.println(line);
		assertTrue(line.equals("1 ")||line.equals("1"));
		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: put mail3 44,46");
		opensocket();
   		ausSo.println("put mail3 44,46");
		line = einSo.readLine();
		System.out.println(line);
		assertTrue(line.equals("1 ")||line.equals("1"));
		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: getall");
		opensocket();
		ausSo.println("getall");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 [44,45],[44,46],[45,46]")||line.equals("1 [44,45],[45,46],[44,46]")||line.equals("1 [44,46],[44,45],[45,46]")||line.equals("1 [44,46],[45,46],[44,45]")||line.equals("1 [45,46],[44,45],[44,46]")||line.equals("1 [45,46],[44,46],[44,45]")||line.equals("1 [44,45,46]"));
//		assertEquals(line,"1 [44,45],[44,46],[45,46]");
		closesocket();
		System.out.println("TEST: OK"+"\n");

		System.out.println("TEST: stop");
		opensocket();
		ausSo.println("stop");
		line = einSo.readLine();
		System.out.println(line);
   		assertTrue(line.equals("1 ")||line.equals("1"));
   		closesocket();
		System.out.println("TEST: OK"+"\n");
	}
}