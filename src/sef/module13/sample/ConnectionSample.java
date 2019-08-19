package sef.module13.sample;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSample {

	// This source code tries to establish a connection to a MySQL server
	// and displays a message concerning the error if connection fails
	public static void main(String[] args) {
		Connection cn = null;
		try {
			String hostName = "10.103.229.19";
			String port = "8082";
			String sid = "test"; //name of database
			String username = "SA";
			String password = "";
			
			DriverManager.registerDriver(new OracleDriver());
			
			String url = "jdbc:oracle:thin:@" + hostName + ":" + port + ":"+ sid;
			
			cn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connection successfully established! \n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
