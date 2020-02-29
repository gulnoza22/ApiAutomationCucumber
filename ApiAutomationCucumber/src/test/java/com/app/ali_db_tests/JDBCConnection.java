package com.app.ali_db_tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class JDBCConnection {

	String oracleDbUrl = "jdbc:oracle:thin:@ec2-13-59-48-97.us-east-2.compute.amazonaws.com:1521:xe";
	String username = "hr";
	String password = "hr";
	
	@Test
	public void oracleJDBC() throws SQLException {
		Connection connection = DriverManager.getConnection(oracleDbUrl, username, password);
//		Statement statement = connection.createStatement();
		Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery("select * from employees");
		
		while(resultSet.next()) {
		
			System.out.println(resultSet.getString(1)+" | "+resultSet.getString("First_Name")+" | "+resultSet.getString("Last_Name"));
		}
		
		resultSet.last();
		System.out.println(resultSet.getRow());

		
		
		resultSet.close();
		statement.close();
		connection.close();
	}
}
