import java.sql.*;

public class Main {
	public static void main(String args[]){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:DBSERVER";
		String USER = "LEE";
		String PASS = "redsun";

		QueryConnector queryConnector = new QueryConnector(DRIVER, URL, USER, PASS);
		ConsoleFlow consoleflow = new ConsoleFlow(queryConnector);
		DBInitializer initializer = new DBInitializer(queryConnector);
		initializer.dropAllTables();
		//consoleflow.start();
	}
}
