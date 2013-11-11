package tools;

import java.sql.Connection;
import java.sql.ResultSet;


public class ConnectionAndResultSet {
	private Connection connection;
	private ResultSet resultSet;
	public ConnectionAndResultSet(Connection connection, ResultSet resultSet) {
		this.connection = connection;
		this.resultSet = resultSet;
	} 
	public Connection getConnection(){
		return connection;
	}
	public ResultSet getResultSet(){
		return resultSet;
	}
}
