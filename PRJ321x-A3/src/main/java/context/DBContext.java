package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	
	private final String user = "TRUONGKY";
	private final String password = "@Abc111092";
	private final String localHost = "3306";
	private final String dbName = "shoppingdb";

	public Connection getContextion() throws Exception {

		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:" + localHost + "/" + dbName + "?verifyServerCertificate=false&useSSL=true";
		conn = DriverManager.getConnection(url, user, password);

		return conn;

	}

}
