import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		String fromServer, fromUser;
				
		while((fromServer = in.readLine()) != null) {
			
			System.out.println("Server: " + fromServer);
			
			fromUser = userIn.readLine();
			out.println(fromUser);
			
		}

		in.close();
		userIn.close();
		out.close();
		serverSocket.close();
		
		System.out.println("...Connessione Terminata...");
	}

}
