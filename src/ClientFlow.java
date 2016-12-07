import java.util.Scanner;
import java.sql.*;
public class ClientFlow{
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
			String query = "select * from customer where id_ ='"+this.id+"'";
			ResultSet rs = this.queryConnector.selectResultFrom(query);
			try{
				if(rs.next()){
					idFromDB = rs.getString(1).trim();
					pwFromDB = rs.getString(2).trim();
					System.out.println("["+this.id+"]"+"'s Password :");
					this.password = this.userInputScanner.next();
					// if password correct :
					System.out.println("received pw : "+pwFromDB+", your pw : "+this.password);
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
		System.out.println("Hello "+idFromDB+", Here's your choices:");
		// loop forever
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
					this.ticketReservation(idFromDB);
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
	private void viewAllTickets(String customer_id){
		String ticketID, theaterID, auditoriumID, time, seatID, movieID,
			   payType, payMethod, payState;
		String[] ticketFields = new String[10];
		int price;
		String query = "select * from ticket where customer_id = '"+ customer_id+"'";
		ResultSet resultSet = this.queryConnector.selectResultFrom(query);
		try{
			while(resultSet.next()){
				for(int i = 0; i < ticketFields.length; i++){
					ticketFields[i] = resultSet.getString(i+1);
				}
				/*ticketID = resultSet.getString(1);
				theaterID =resultSet.getString(3);
				auditoriumID = resultSet.getString(4);
				time = resultSet.getString(5);
				seatID = resultSet.getString(6);
				movieID = resultSet.getString(7);
				payType = resultSet.getString(8);
				payMethod = resultSet.getString(9);
				*/	
				for(String s : ticketFields){
					System.out.printf("%s, ", s.trim());
				}
				System.out.println();
				
			}

		}catch(Exception e){
			System.out.println(e.toString()+"in viewAlltickets()");
		}
	}
	private void ticketReservation(String client_id){
		String movie_id, theater_id, date, auditorium_id, time, seat_id;
		movie_id = this.chooseAvailableMovie();
		System.out.println("movieid user selected : "+movie_id);
		theater_id = this.chooseAvailableTheater(movie_id);
		System.out.println("theaterid user selected : "+theater_id);
		date = this.chooseAvailableDate(movie_id, theater_id);
		System.out.println("date user selected : " + date);


	}
	

	private void ticketCancelation(){

	}
	private void makePayment(){
		
	}
	private void modifyPersonalInfo(){

	}
	private void withdrawFromMember(){

	}
	private String chooseAvailableMovie(){
		String movie_id, movie_name;
		String query = "select distinct movie.id_, movie.name"
						+" from movie inner join schedule"
						+" on movie.id_ = schedule.movie_id";	
		ResultSet rs = this.queryConnector.selectResultFrom(query);
		try{
			if(!rs.isBeforeFirst()){
				System.out.println("cannot find any movies in DB");
				return null;
			}
			while(rs.next()){
				movie_id = rs.getString(1).trim();
				movie_name = rs.getString(2).trim();
				System.out.println(movie_id + ": "+ movie_name);
			}
			System.out.println("select one of movie_ids you want to see.");
			String userSelectedMovie = this.userInputScanner.next();
			String queryByUser = "select id_, name from movie where id_='"+userSelectedMovie+"'";
			ResultSet rs_b = this.queryConnector.selectResultFrom(queryByUser);
			if(!rs_b.isBeforeFirst()){
				System.out.println("You choose wrong movie_id. try agiain.");
			}else{
				return userSelectedMovie;
			}

			
		}catch(Exception e){
			System.out.println(e.toString()+"in login");
		}
		return null;
	}
	private String chooseAvailableTheater(String movie_id) {
		String theaterID, theaterName, userSelectedTheater;
		String query = "select distinct theater.id_, theater.name from schedule "
						+" inner join theater"
						+" on theater.id_ = schedule.theater_id"
						+" where movie_id = '"+movie_id+"'";	
		ResultSet rs = this.queryConnector.selectResultFrom(query);
		try{
			if(!rs.isBeforeFirst()){
				System.out.println("there's no theater that screens such movie");
				return null;
			}
			while(rs.next()){
				theaterID = rs.getString(1).trim();
				theaterName = rs.getString(2).trim();
				System.out.println(theaterID + " : "+ theaterName);
			}
			System.out.println("select theater numbers: ");
			userSelectedTheater = this.userInputScanner.next();
			String queryByUser = "select id_, name from theater where id_='"+userSelectedTheater+"'";
			ResultSet rs_b = this.queryConnector.selectResultFrom(queryByUser);
			if(!rs_b.isBeforeFirst()){
				System.out.println("You choose wrong theater number. try agiain.");
			}else{
				return userSelectedTheater;
			}
		}catch(Exception e){
			System.out.println(e.toString()+" in choose theater");
		}
		return null;
	}
	private String chooseAvailableDate(String movie_id, String theater_id){
		
		return null;
	}
}










