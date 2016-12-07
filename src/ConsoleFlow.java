import java.util.Scanner;
import java.sql.*;
public class ConsoleFlow{
	private Scanner userInputScanner;
	private AdministratorFlow administratorFlow;
	private ClientFlow clientFlow; 
	private QueryConnector queryConnector;

	public ConsoleFlow(QueryConnector queryConnector){
		this.userInputScanner = new Scanner(System.in);
		this.queryConnector = queryConnector;
		this.administratorFlow = new AdministratorFlow(this.queryConnector);
		this.clientFlow = new ClientFlow(this.queryConnector);
	}

	public void start(){
		int userInput;
		while(true){
			System.out.println("WELCOME TO OUR THEATER");
			System.out.println("[0] : Administrator mode");
			System.out.println("[1] : Client mode");
			userInput = userInputScanner.nextInt();
			if(userInput == 0){
				this.administratorFlow.start();
			}else{
				this.clientFlow.start();
			}
		}
	}
}




