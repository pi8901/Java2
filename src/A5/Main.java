package A5;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Server Klaus = new Server(2000, "");
		Klaus.run();

	}

}
