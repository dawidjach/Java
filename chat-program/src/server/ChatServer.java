package server;// import class
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    // 11. neues Objekt-Attribut um den Client in einer Liste zu speichern
    private List<ClientHandler> clients;

    // 3. neues Objekt-Attribut um Client mit Server zu verbinden (serverSocket = Variable)
    private ServerSocket serverSocket;
    // 2. ein Konstruktor der Klasse "server.ChatServer" mit Parameter "int port" erzeugen
    public ChatServer(int port) {

        clients = new CopyOnWriteArrayList<>(); // 12A

        // alles was in dem Konstruktor sich befindet, wird über das Objekt "server.ChatServer" aufgerufen
        // 4.Um Variable "serverSocket" einen Wert zu bekommen, schreib man einen neuen Konstruktor
        try {
            serverSocket = new ServerSocket(port); // Aufruf des Objekt-Attributes
            System.out.println("Started chat server on port: " + port);

            // 10. Verbindung mit Punkt 9 von server.ClientHandler.java
            while(true) {

                System.out.println("Waiting for a new client"); // 12C

                Socket connectionToClient = serverSocket.accept();//.accept = wartet bis mit dem Server verbunden wird
                ClientHandler client = new ClientHandler(this, connectionToClient);

                clients.add(client);// 12B
                System.out.println("Accepted new client");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Methoden:
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message) {
        // 13
        System.out.println(message);
        if(message != null) {
            for(ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. ein neues Objekt der Klasse "server.ChatServer" erzeugen
        // das Objekt "server.ChatServer()" wird des Konstruktors "server.ChatServer()" aufrufen
        new ChatServer(3141);   // Aufruf 3141 als port , Thread also Zeitlinie
    }
}