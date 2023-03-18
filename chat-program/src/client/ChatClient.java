// import class
package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class ChatClient extends JFrame implements KeyListener {    // 21. Extends JFrame importieren, 27C.KeyListener-Methode
    // 17. neue Variable "address" und "port"
    private String address;
    private int port;
    private BufferedReader fromServerReader;    // connection
    private PrintWriter toServerWriter;         // connection

    // 19B
    private Socket connectionToServer;      // connection

    // 27A.Zwei Variable um Texte zu schreiben und tatsächlich chatten
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    // 31A.Objekt für scrollbare Chat-Fenster
    private JScrollPane outputScrollPane;

    // 16.ein Konstruktor erstellen
    public ChatClient(int port) {
        // 22.'super()' aufrufen (von der Oberklasse 'JFrame' und Unterklasse 'ChatClient', d.h.
        // der Konstruktor 'super' bekommt Parameter 'title' von der Oberklasse 'JFrame'.
        super("Chat");
        this.port = port;
        // ein Fester öffnen (grafische Oberfläche)
        address = JOptionPane.showInputDialog("IP-Address: ");

        // 18. Jetzt Buttons 'OK' und 'Cancel' in der neuen Methode 'receiveMessage()' einstellen
        if(address != null) {
            receiveMessages();
        }
    }

    private void initGui() {
        // 27B
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);  // output-Feld blockieren vor schreiben
        outputTextArea.setBorder(BorderFactory.createTitledBorder("Chat"));
        // 31B.outputScrollPane
        outputScrollPane = new JScrollPane(outputTextArea);
        // 27B
        inputTextField = new JTextField();
        inputTextField.setBorder(BorderFactory.createTitledBorder("Your message"));
        // drei Key-Methoden aus KeyListener aufrufen
        inputTextField.addKeyListener(this);

        // 27B, teilweise 31B
        add(outputScrollPane, BorderLayout.CENTER); // outputTextArea mit Schritt 31B auf outputScrollPane wechseln
        add(inputTextField, BorderLayout.SOUTH);    // add-Methode aus JFrame

        // 23.Sichtbar starten (automatisch aus JFrame)
        setVisible(true);
        // 24.Mit 'X' komplett beenden den Programm
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 25.Höhe und Breite des Fensters einstellen
        setSize(400,300);
        // 26.Das Fenster in der Mitte des Bildschirms
        setLocationRelativeTo(null);

    }

    private void receiveMessages() {
        // 19A. Eine Verbindung aufbauen (mit Socket-Objekt) und in einer neuen Objekt-Variable speichern
        try {
            connectionToServer = new Socket(address, port);
            fromServerReader = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
            toServerWriter = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));

            // 20.Neue Methode 'initGui()' erstellen
            initGui();

            // 30.Nachrichten bei Absenden und Lesen automatisieren mit endloser while-Schleife (darum 'true')
            while(true) {
                String message = fromServerReader.readLine();
                outputTextArea.append(message + "\n");
                // 32.Scroll-Option automatisieren
                outputScrollPane.getVerticalScrollBar().setValue(outputScrollPane.getVerticalScrollBar().getMaximum());
            }
            // catch gehört zu Nr 19A
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Connection to server \"" + address + "\" failed.");
            // 34. Chat-Fenster zerstören, wenn der Server fehlgeschlagen wird (failed) - wichtig! vor finally/connection close!
            dispose();
            // 33. alle Connections mit finally schließen (closen)
        } finally {
            if(connectionToServer != null) {
                try {
                    connectionToServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fromServerReader != null) {
                try {
                    fromServerReader.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(toServerWriter != null) {
                toServerWriter.close();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {  // diese Methode aufgerufen wird, wenn die Taste erst gedrückt und dann losgelassen wird
        // bleibt leer
    }

    // 29.keyPressed ausfüllen / einstellen
    @Override
    public void keyPressed(KeyEvent e) {    // diese Methode aufgerufen wird, wenn die Taste gedrückt wird
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {   // VK_ENTER = const
            String message = inputTextField.getText();

            // ausführen wenn die Message nicht leer(Empty) ist
            if(!message.isEmpty()) {
                toServerWriter.println(message);
                toServerWriter.flush();
                // löschen nach Absenden (tatsächlich Text auf leere Nachricht tauschen)
                inputTextField.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {   // diese Methode aufgerufen wird, wenn die Taste losgelassen wird
        // bleibt leer
    }

    public static void main(String[] args) {
        // 15.ChatClient Objekte erstellen
        new ChatClient(3141);
    }
}
