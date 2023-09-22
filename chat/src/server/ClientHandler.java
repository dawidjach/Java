package server;

// import Class
import server.ChatServer;

import java.io.*;
import java.net.Socket;

//  6. zwei Sachen gleichzeitig machen: Verbindung halten und neue Clients verbinden = 'implements Runnable' schreiben
public class ClientHandler implements Runnable {
    private ChatServer chatServer;
    private Socket connectionToClient;
    private String name; // um IPs als Clientsnamen zu 'speichern'

    // 7. zwei neue Objekt-Attribute erzeugen
    private BufferedReader fromClientReader;    // sorgt um Nachrichten zu lesen
    private PrintWriter toClientWriter;         // sorgt um Nachrichten zu schreiben


    // 5. neuen Konstruktor erstellen (Parameter "server.ChatServer" ermöglicht das Objekt aus server.ChatServer aufzurufen,
    // Verbindung mit public static void main(String[] args) / new server.ChatServer(3141);
    public ClientHandler(ChatServer chatServer, Socket connectionToClient) {
        this.chatServer = chatServer;
        this.connectionToClient = connectionToClient;
        name = connectionToClient.getInetAddress().getHostAddress();
        // 7. override public void run() aufrufen - wichtig! immer mit new Thread
        new Thread(this).start();

    }

    // Der override automatisch nach 'implements Runnable' erzeugt
    @Override
    public void run() {
        // 8
        try {
            fromClientReader = new BufferedReader(new InputStreamReader(connectionToClient.getInputStream()));
            toClientWriter = new PrintWriter(new OutputStreamWriter(connectionToClient.getOutputStream()));

            //14. Hier auch: entfernen die Clients, die disconnected sind und diese Nachricht-Info anderen Clients schicken
            chatServer.broadcastMessage(name + " connected.");

            // 9. String message auf alle andere Clients weiter leiten mit unendlicher while-Schleife durch (true)
            String message = fromClientReader.readLine();
            while(message != null) {
                chatServer.broadcastMessage(name + ": " + message);
                message = fromClientReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 14. Entfernen die Clients, die disconnected sind und diese Nachricht-Info anderen Clients schicken
            chatServer.removeClient(this);
            chatServer.broadcastMessage(name + " disconnected.");

            if(fromClientReader != null) {
                try {
                    fromClientReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(toClientWriter != null) {
                toClientWriter.close();
            }
        }
    }

    // Methode sendMessage
    public void sendMessage(String message) {
        toClientWriter.println(message);
        toClientWriter.flush();
    }
}
