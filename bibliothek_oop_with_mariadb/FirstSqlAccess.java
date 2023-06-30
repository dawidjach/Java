package jdbcmariadb;

import java.sql.*;

public class FirstSqlAccess {
	private Connection con;

	public FirstSqlAccess() throws SQLException {
		String url = "jdbc:mariadb://localhost/Bibliothek";
		String user = "root";
		String pass = "Di1sP4MySQL!";
		{
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				con = DriverManager.getConnection(url, user, pass);
				System.out.println("***Connected***");
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Keine Treiber-Klasse!");
				e.printStackTrace();
			}
		}
	}

	public void run() throws SQLException {
		try {
			BibliothekVerwalten bv = new BibliothekVerwalten(con);
			bv.untermenu();
		} finally {
			if (con != null)
				try {
					con.close();
					System.out.println("\n***Disconnected***\n");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) throws SQLException {
		FirstSqlAccess firstSqlAccess = new FirstSqlAccess();
		firstSqlAccess.run();
	}
}