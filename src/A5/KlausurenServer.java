package A5;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public List<List<String>> Del(String input) {
    	List<List<String>> out = new ArrayList<>();
  
    	String[] str = input.split(" ");
		String key = str[1];
		
		if (map.containsKey(key) && (!map.get(key).equals(""))) {
			out.add(map.get(key));
			out.remove(key);
			return out;
		} else {
			return null;
		}
    	
    }
    
    public String GetAll() {
    	
    	
    	return "GetAll";
    }
    
    public void handleRequest(Socket clientSocket) throws IOException {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;

            while ((inputLine = in.readLine()) != null && !inputLine.equalsIgnoreCase("STOP")) {
                if (inputLine.toLowerCase().contains("put")) {
                    if (Put(inputLine) == null) {
                        out.println("1 ");
                    } else {
                        out.print("1 ");
                        out.println(Put(inputLine));
                    }

                } else if (inputLine.toLowerCase().contains("getall")) {
                    out.println(GetAll());

                } else if (inputLine.toLowerCase().contains("get")) {
                    if (Get(inputLine) == null) {
                        out.println("0");
                    } else {
                        out.print("1 ");
                        out.println(Get(inputLine));
                    }

                } else if (inputLine.toLowerCase().contains("del")) {
                    if (Del(inputLine) == null) {
                        out.println("0");
                    } else {
                        out.print("1 ");
                        out.println(Del(inputLine));
                    }

                } else if (inputLine.toLowerCase().contains("stop")) {
                   out.println("1");
                   clientSocket.close();

                }else {
                    out.println("Command not found!");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when handling client request");
            System.out.println(e.getMessage());
        }
    }
    
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread thread = new Thread(() -> {
                    try {
                        handleRequest(clientSocket);
                    } catch (IOException e) {
                        System.out.println("Exception caught when handling client request");
                        System.out.println(e.getMessage());
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            System.out.println("Exception caught when closing client socket");
                            System.out.println(e.getMessage());
                        }
                    }
                });

                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when starting the server");
            System.out.println(e.getMessage());
        }
    }
}

