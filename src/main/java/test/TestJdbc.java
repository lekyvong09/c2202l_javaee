package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/c2202l_javaEE_ebook?useSSL=false";
		String user = "root";
		String pass = "ab123456..";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("connection success");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
