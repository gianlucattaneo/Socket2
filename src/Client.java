import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket serverSocket = null;
		String serverIP = "localhost";
		int serverPort = 7575;

		PrintWriter out = null;
		BufferedReader in = null;

		try {

			serverSocket = new Socket(serverIP, serverPort);
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

		}catch(Exception e) {
			e.printStackTrace();
		}

		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String fromUser;
		boolean running=true;

		while(running) {

			while(!in.ready()) {}
			while(in.ready()) {
				System.out.println("Server: " + in.readLine());
			}
//			fromServer = in.lines().collect(Collectors.toList());
//			fromServer = in.
//			for(String received : fromServer) {
//				System.out.println("Server: " + received);
//			}

			fromUser = userIn.readLine(); 
			out.println(fromUser);
			if(fromUser.equalsIgnoreCase("EXIT"))
				running = false;
			
		}


		in.close();
		userIn.close();
		out.close();
		serverSocket.close();

		System.out.println("...Connessione Terminata...");
	}

}
