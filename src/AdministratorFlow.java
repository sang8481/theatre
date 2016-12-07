
import java.util.Scanner;
import java.sql.*;

public class AdministratorFlow{
	private Scanner userInputScanner;
	private int userIntInput;
	private String userStringInput;
	private QueryConnector queryConnector;

	public AdministratorFlow(QueryConnector queryConnector){
		this.userInputScanner = new Scanner(System.in);
		this.queryConnector = queryConnector;
	}
	public void start(){

	}
}
