import java.sql.*;
import java.util.*;

public class DBInitializer{
	private QueryConnector queryConnector;

	public DBInitializer(QueryConnector queryConnector){
		this.queryConnector = queryConnector;

	}
	public void allTableInitialize(){
		String[] queries = new String[]{
			"create table customer"
				+"(id_ char(20) not null,"
				+"password char(20) not null,"
				+"name char(20) not null,"
				+"phone char(20) not null,"
				+"email char(40) not null,"
				+"address char(50) not null,"
				+"birth char(10) not null,"
				+"vip char(3) default 'no',"
				+"point number,"
				+"primary key(id_))",
				
			"create table theater"
				+"(id_ char(20) not null,"
				+"name char(20) not null,"
				+"address char(50) not null,"
				+"phone char(20) not null,"
				+"primary key(id_))",
				
			"create table auditorium("
				+"id_ char(20) not null,"
				+"theater_id char(20) not null,"
				+"primary key(id_),"
				+"foreign key(theater_id) references theater(id_) on delete cascade)",
				
			"create table seat("
				+"id_ char(4) not null,"
				+"theater_id char(20) not null,"
				+"auditorium_id char(20) not null,"
				+"primary key(id_, theater_id, auditorium_id),"
				+"foreign key(theater_id) references theater(id_) on delete cascade,"
				+"foreign key(auditorium_id) references auditorium(id_) on delete cascade)",
				
			"create table movie("
				+"id_ char(20) not null,"
				+"name char(40) not null,"
				+"director char(40) not null,"
				+"grade char(20) not null,"
				+"description char(100) not null,"
				+"primary key(id_))",

			"create table schedule("
				+"time_ date not null,"
				+"auditorium_id char(20) not null,"
				+"movie_id char(20) not null,"
				+"theater_id char(20) not null,"
				+"primary key(time_),"
				+"foreign key(auditorium_id) references auditorium(id_),"
				+"foreign key(movie_id) references movie(id_) on delete cascade,"
				+"foreign key(theater_id) references theater(id_) on delete cascade)",
				
		   "create table ticket("
				+"id_ char(20) not null,"
				+"customer_id char(20) not null,"
				+"theater_id char(20) not null,"
				+"auditorium_id char(20) not null,"
				+"schedule_time date not null,"
				+"seat_id char(4) not null,"
				+"movie_id char(20) not null,"
				+"pay_type char(20) not null,"
				+"pay_method char(20) not null,"
				+"pay_state char(20) not null,"
				+"price number,"
				+"primary key(id_),"
				+"foreign key(customer_id) references customer(id_) on delete cascade,"
				+"foreign key(schedule_time) references schedule(time_) on delete cascade,"
				+"foreign key(seat_id, theater_id, auditorium_id) references seat(id_, theater_id, auditorium_id) on delete cascade,"
				+"foreign key(movie_id) references movie(id_) on delete cascade)"
		};
		for(String query : queries){
			this.queryConnector.executeWith(query);
		}
		System.out.println("table initialze completed.");
	}

	public void dropAllTables(){
		String[] queries = new String[]{
				"drop table customer cascade constraint",
				"drop table theater cascade constraint",
				"drop table ticket cascade constraint",
				"drop table auditorium cascade constraint",
				"drop table seat cascade constraint",
				"drop table movie cascade constraint",
				"drop table schedule cascade constraint",
		};
		for(String query : queries){
			this.queryConnector.executeWith(query);
		}
		System.out.println("drop table done.");

	}
	public void deleteAllTupleInTable(){
		String[] queries = new String[]{
				"delete from customer",
				"delete from theater",
				"delete from ticket",
				"delete from auditorium",
				"delete from seat",
				"delete from movie",
				"delete from schedule"
		};
		for(String query : queries){
			this.queryConnector.executeWith(query);
		}
		System.out.println("delete all tuples in tables done.");
	}

	// Insert dummy tuples for testing
	public void insertDummyTuples(){
		List<String> queries = new ArrayList<String>();
		
		// customers
		queries.add("insert into customer values('userid1', 'password1', 'Brian', '000-0101', 'brian@naver.com', 'address of brian', '920517', 'no', 0)");
		queries.add("insert into customer values('userid2', 'password2', 'Charles', '002-0202', 'charles.google.com', 'address of charles', '890202', 'no', 0)");
		queries.add("insert into customer values('userid3', 'password3', 'Darwin', '003-0303', 'darwin@cs-cnu.org', 'address of darwins', '930202', 'no', 0)");

		// theaters
		queries.add("insert into theater values('1', 'Megabox', 'Daejeon', '000-0000')");
		queries.add("insert into theater values('2', 'CGV', 'Cheonan', '001-0001')");

		// auditoriums
		queries.add("insert into auditorium values('M-1', '1')");
		queries.add("insert into auditorium values('M-2', '1')");
		queries.add("insert into auditorium values('M-3', '1')");
		queries.add("insert into auditorium values('C-1', '2')");
		
		// seat
		for(int i = 1; i <= 40; i++){
			queries.add("insert into seat values('"+String.valueOf(i)+"', '1', 'M-1')");
		}
		for(int i = 1; i <= 30; i++){
			queries.add("insert into seat values('"+String.valueOf(i)+"', '1', 'M-2')");
		}
		for(int i = 1; i <= 50; i++){
			queries.add("insert into seat values('"+String.valueOf(i)+"', '2', 'C-1')");
		}
		
		// movies
		queries.add("insert into movie values('movie_1', 'Gone with the wind', 'Victor Fleming', 'GP', 'descriptions of gone with the wind')");
		queries.add("insert into movie values('movie_2', 'titanic', 'unknown director', 'RP', 'descriptions of titanic movie')");
		
		// schedules
		// extract time out of 'DATE' object : 'select to_char(time_, 'YYYY-MM-DD HH:MI') from schedule'
		queries.add("insert into schedule values(to_date('161205 11:20', 'yymmdd hh24:mi'), 'M-1', 'movie_1', '1')");
		queries.add("insert into schedule values(to_date('161205 13:40', 'yymmdd hh24:mi'), 'M-1', 'movie_1', '1')");
		queries.add("insert into schedule values(to_date('161205 11:40', 'yymmdd hh24:mi'), 'M-2', 'movie_1', '1')");
		queries.add("insert into schedule values(to_date('161205 13:00', 'yymmdd hh24:mi'), 'M-2', 'movie_1', '1')");
		queries.add("insert into schedule values(to_date('161206 11:20', 'yymmdd hh24:mi'), 'M-3', 'movie_2', '1')");
		queries.add("insert into schedule values(to_date('161206 14:00', 'yymmdd hh24:mi'), 'M-3', 'movie_2', '1')");
		queries.add("insert into schedule values(to_date('161206 09:50', 'yymmdd hh24:mi'), 'C-1', 'movie_2', '2')");
		queries.add("insert into schedule values(to_date('161206 11:40', 'yymmdd hh24:mi'), 'C-1', 'movie_2', '2')");
		
		// tickets
		queries.add("insert into ticket values('000001', 'userid2', '1', 'M-1', to_date('161205 11:20', 'yymmdd hh24:mi'), '1', 'movie_1', 'online', 'credit_card', 'unpaid', 11000)");
		queries.add("insert into ticket values('000002', 'userid2', '1', 'M-1', to_date('161205 11:20', 'yymmdd hh24:mi'), '2', 'movie_1', 'online', 'credit_card', 'unpaid', 11000)");
		queries.add("insert into ticket values('000003', 'userid2', '1', 'M-1', to_date('161205 11:20', 'yymmdd hh24:mi'), '3', 'movie_1', 'online', 'credit_card', 'unpaid', 11000)");
		queries.add("insert into ticket values('000004', 'userid2', '1', 'M-1', to_date('161205 11:20', 'yymmdd hh24:mi'), '5', 'movie_1', 'online', 'credit_card', 'unpaid', 11000)");
		queries.add("insert into ticket values('000005', 'userid2', '1', 'M-1', to_date('161205 11:20', 'yymmdd hh24:mi'), '6', 'movie_1', 'online', 'credit_card', 'unpaid', 11000)");
		queries.add("insert into ticket values('000006', 'userid2', '1', 'M-1', to_date('161205 11:20', 'yymmdd hh24:mi'), '10', 'movie_1', 'online', 'credit_card', 'unpaid', 11000)");
		
		for(String query : queries){
			this.queryConnector.executeWith(query);
		}
		System.out.println("dummy tuple insert completed");
	}
}
