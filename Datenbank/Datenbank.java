package Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datenbank {
    private Connection connection;
    String oid, bezeichnung;

    public Datenbank() throws Exception {
        String url = "jdbc:mariadb://localhost/pflegearbeiten";
        String user = "root";
        String password = "";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("***Connected***\n");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Keine Treiber-Klasse!");
            e.printStackTrace();
            throw e;
        }
    }

    public void run() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select oid, bezeichnung from objekt group by oid asc;");
            System.out.printf("OID: \tBezeichnung: \n");

            while (rs.next()) {
                oid = rs.getString("OID");
                bezeichnung = rs.getString("Bezeichnung");
                System.out.println(oid + "\t" + bezeichnung);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("\n***Disconnected***\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Datenbank db = new Datenbank();
        db.run();
    }
}
