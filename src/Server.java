import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	static List<Persona> lista = new ArrayList<Persona>();

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		int serverPort = 7575;
		try {
			serverSocket = new ServerSocket(serverPort);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("server in ascolto sulla porta "+ serverPort);
		Socket clientSocket = null;
		try{
			clientSocket = serverSocket.accept();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		
		String inputLine, outputLine;

		out.println("Inserire il comando: [EXIT per terminare]");

		while (true) {
			outputLine ="";
			inputLine = in.readLine();

			if(inputLine.equalsIgnoreCase("EXIT"))
				break;

			List<String> decoded = Protocollo.processInputMatcher(inputLine);

			for(String s : decoded) {
				System.out.println(s);
			}

			switch(decoded.get(0)) {
			case(Protocollo.addSymbol):{
				if(decoded.size()>2) {
					Persona found = findName(decoded.get(1));
					if(found != null) {
						String added = "";
						for(int i = 2; i < decoded.size();i++) {
							String name = decoded.get(i);
							found.addAmico(new Persona(decoded.get(i)));
							added += name + ", ";
						}
						outputLine = "Ho Aggiunto: "+ 
								added.substring(0,added.length()-2) + 
								" agli amici di "+ found.getNome();
					}else {
						outputLine = decoded.get(1)+ " non esiste";
					}
				}else {
					lista.add(new Persona(decoded.get(1)));
					outputLine = "Ho Aggiunto: " + decoded.get(1);
				}
				break;
			}
			case(Protocollo.deleteSymbol):{
				if(decoded.size()>2) {
					Persona found = findName(decoded.get(1));
					if(found != null) {
						String removed = "";
						for(int i = 2; i < decoded.size();i++) {
							String name = decoded.get(i);
							if (found.deleteAmico(name))
								removed += name+", ";
						}
						outputLine = "Ho Rimosso: "+ 
								removed.substring(0,removed.length()-2)+
								" dagli amici di "+ found.getNome();
					}else {
						outputLine = decoded.get(1) +" non esiste";
					}
				}else {
					for(Persona p : lista) {
						if(p.getNome().equals(decoded.get(1))) {
							lista.remove(p);
							outputLine = "Ho Rimosso: " + p.getNome();
							break;
						}
					}
				}
				break;
			}
			case(Protocollo.errSymbol):{
				outputLine = decoded.get(1);
				break;
			}
			default:{
				Persona found = findName(decoded.get(0));
				if (found != null) {
					if(decoded.size()==1) {
						outputLine = "Gli amici di " + found.getNome() +
								" sono: " + found.printAmici();
					}else {
						if (found.findAmico(decoded.get(1)))
							outputLine = decoded.get(1) +
								" è presente tra gli amici di "+found.getNome();
						else 
							outputLine = decoded.get(1)+
								" non è presente tra gli amici di "+found.getNome();
					}
				}else {
					outputLine = decoded.get(0) +" non esiste";
				}
			}
			}	
			out.println("Esito: " + outputLine + "\n Inserire il comando: [EXIT per terminare]");
		}

		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
		System.out.println("Server terminato...");

	}

	private static Persona findName(String nome) {
		Persona found = null;
		for(Persona p : lista) {
			if(p.getNome().equals(nome)) {
				found = p;
				break;
			}
		}
		return found;
	}

}
