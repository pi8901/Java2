package A5;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Server Klaus = new Server(2000, "");
		//Klaus.run();
		
		KlausurenServer_Test t = new KlausurenServer_Test();
		
		
		new Thread(new Runnable()
		{
			public void run()
			{
				Klaus.run();
			}
		}).start();
		
		System.out.println("Hello");
		//t.setUp();
		//t.all();

	}

}
