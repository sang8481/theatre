import java.util.Scanner;
import java.sql.*;
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
			String query = "select * from customer where id = "+this.id;
			ResultSet rs = this.queryConnector.selectResultFrom(query);
			try{
				if(rs.next()){
					idFromDB = rs.getString(1);
					pwFromDB = rs.getString(2);
					System.out.println("["+this.id+"]"+"'s Password :");
					this.password = this.userInputScanner.next();
					// if password correct :
					if(this.password.equals(pwFromDB)){
						this.clientMenuFlow(idFromDB, pwFromDB);

					// if password incorrect :
					}else{
						System.out.println("invalid password. try again.");
					}

				// if id not exist in db (not registered)
				}else{
					System.out.println("invalid user id. check again.");
				}
			}catch(Exception e){
				System.out.println(e.toString()+"in login");
			}
		// end of while(3--)
		}
		System.out.println("you entered wrong password 3 times. bye.");
	}
	
	private void clientMenuFlow(String idFromDB, String pwFromDB){
		int userChoice;
		System.out.println("Hello, "+idFromDB+", Here's your choices:");
		while(true){
			System.out.println("[0] : Search Movie\n[1] : View all tickets\n[2] : Reserve tickets online\n[3] : Cancel reserved ticket\n[4] : Make payment for ticket\n[5] : Modify personal info\n[6] : withdraw from member");
			userChoice = this.userInputScanner.nextInt();
			switch(userChoice){
				case 0 :
					this.searchMovie();
					break;
				case 1 :
					this.viewAllTickets(idFromDB);
					break;
				case 2 :
					this.ticketReservation();
					break;
				case 3 :
					this.ticketCancelation();
					break;
				case 4 :
					this.makePayment();
					break;
				case 5 :
					this.modifyPersonalInfo();
					break;
				case 6 :
					this.withdrawFromMember();
					break;
				default :
					System.out.println("invalid input. try one of [0-6].");
					break;
			}
		}
	}
	private void searchMovie(){
		// Reservation rate of each movie :
		// (reservated seats / all seats in all auditorium where screening specific movie)
		
	}
	private void viewAllTickets(String client_id){
		String ticketID, clientID, theaterID, auditoriumID, time, seatID, movieID,
			   payType, payMethod, payState;
		String[] ticketFields = new String[10];
		int price;
		String query = "select * from tickets where client_id = "+ client_id;
		ResultSet resultSet = this.queryConnector.selectResultFrom(query);
		try{
			while(resultSet.next()){
				for(int i = 0; i < ticketFields.length; i++){
					ticketFields[i] = resultSet.getString(i+1);
				}
				/*ticketID = resultSet.getString(1);
				clientID = resultSet.getString(2);
				theaterID =resultSet.getString(3);
				auditoriumID = resultSet.getString(4);
				time = resultSet.getString(5);
				seatID = resultSet.getString(6);
				movieID = resultSet.getString(7);
				payType = resultSet.getString(8);
				payMethod = resultSet.getString(9);
				*/	
				for(String s : ticketFields){
					System.out.printf("%s, ", s);
				}
			}

		}catch(Exception e){
			System.out.println(e.toString()+"in viewAlltickets()");
		}
	}
	private void ticketReservation(){

	}
	private void ticketCancelation(){

	}
	private void makePayment(){
		
	}
	private void modifyPersonalInfo(){

	}
	private void withdrawFromMember(){

	}
}












