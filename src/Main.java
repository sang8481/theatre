import java.sql.*;

public class Main {
	public static void main(String args[]){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:DBSERVER";
		String USER = "LEE";
		String PASS = "redsun";

		ConsoleFlow consoleflow = new ConsoleFlow();
		consoleflow.start();
	}
}
