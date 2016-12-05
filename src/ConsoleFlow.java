import java.util.Scanner;
public class ConsoleFlow{
	private Scanner userInputScanner;
	private AdministatorFlow administratorFlow;
	private ClientFlow clientFlow; 
	private QueryConnector queryConnector;

	public ConsoleFlow(QueryConnector queryConnector){
		this.userInputScanner = new Scanner(System.in);
		this.queryConnector = queryConnector;
		this.administratorFlow = new AdministatorFlow(this.queryConnector);
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

class AdministatorFlow{
	private Scanner userInputScanner;
	private int userIntInput;
	private String userStringInput;
	private QueryConnector queryConnector;

	public AdministatorFlow(QueryConnector queryConnector){
		this.userInputScanner = new Scanner(System.in);
		this.queryConnector = queryConnector;
	}
	public void start(){

	}
}
class ClientFlow{
	private Scanner userInputScanner;	
	private int userIntInput;
	private String userStringInput;
	private String id, password, name, birth, address, phone, email;
	private QueryConnector queryConnector;

	public ClientFlow(QueryConnector queryConnector){
		this.userInputScanner = new Scanner(System.in);
		this.queryConnector = queryConnector;
	}
	
	private void refreshClientFields(){
		this.id = null; this.password = null; this.name = null; this.birth = null;
		this.address = null; this.phone = null;
	}

	public void start(){
		System.out.println("Client menu : \n[0] : Login\n[1] : Sign up");
		int userInput = this.userInputScanner.nextInt();
		// Login
		if(userInput == 0){
			this.loginFlow();
		// Sign up
		}else{
			System.out.println("Sign up. input valid id :");
			this.id = this.userInputScanner.next();
			// id check from db
			while(true){
				System.out.println("id:["+this.id+"]"+", input password : ");
				this.password = this.userInputScanner.next();
				System.out.println("confirm password again: ");
				String secondPassword = this.userInputScanner.next();
				if(!this.password.equals(secondPassword)){
					System.out.println("password confirm failed. try again");
					continue;
				}else{
					break;
				}
			}
			System.out.println("input your name :");
			this.name = userInputScanner.next();
			System.out.println("input your E-mail");
			this.email = userInputScanner.next();
			System.out.println("input your Phone number :");
			this.phone = userInputScanner.next();
			System.out.println("input your Address : ");
			this.address = userInputScanner.next();
			System.out.println("input your Birthday");
			this.birth = userInputScanner.next();
			// input customer tuple into db
			System.out.println("Congratulations. you're our client from now.");
		}
		this.refreshClientFields();
	}
	private void loginFlow(){
		int tryCount = 3;
		while(tryCount-- > 0){
			String idFromDB, pwFromDB;
			System.out.println("Login. input ID:");
			this.id = this.userInputScanner.next();
			String query = "select * from customer where id = "+this.id
			ResultSet rs = this.queryConnector.selectResultFrom(query);
			if(rs.next()){
				idFromDB = rs.getString(1);
				pwFromDB = rs.getString(2);
				System.out.println("["+this.id+"]"+"'s Password :");
				this.password = this.userInputScanner.next();
				if(this.password.equals(pwFromDB)){

				}
			}else{
				System.out.println("invalid user id. check again.");
			}
		}
		System.out.println("you entered wrong password 3 times. bye.");
	}
}
