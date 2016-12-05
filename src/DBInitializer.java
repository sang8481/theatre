import java.sql.*;

public class DBInitializer{
	private QueryConnector queryConnector;

	public DBInitializer(QueryConnector queryConnector){
		this.queryConnector = queryConnector;

	}
	public void allTableInitialize(){
		String[] querys = new String[]{
			"create table customer"
				+"(id char(20) not null,"
				+"password char(20) not null,"
				+"name char(20) not null,"
				+"phone char(20) not null,"
				+"email char(40) not null,"
				+"address char(50) not null,"
				+"birth char(10) not null,"
				+"vip char(3) default 'no',"
				+"point number,"
				+"primary key(id))",
				
			"create table theater"
				+"(id char(20) not null,"
				+"name char(20) not null,"
				+"address char(50) not null,"
				+"phone char(20) not null,"
				+"primary key(id))",
				
			"create table auditorium("
				+"id char(20) not null,"
				+"theater_id char(20) not null,"
				+"primary key(id),"
				+"foreign key(theater_id) references theater(id))",
				
			"create table seat("
				+"id char(4) not null,"
				+"theater_id char(20) not null,"
				+"auditorium_id char(20) not null,"
				+"primary key(id),"
				+"foreign key(theater_id) references theater(id),"
				+"foreign key(auditorium_id) references auditorium(id))",
				
			"create table movie("
				+"id char(20) not null,"
				+"name char(40) not null,"
				+"director char(40) not null,"
				+"grade char(20) not null,"
				+"description char(100) not null,"
				+"primary key(id))",

			"create table schedule("
				+"time char(10) not null,"
				+"auditorium_id char(20) not null,"
				+"movie_id char(20) not null,"
				+"theater_id char(20) not null,"
				+"primary key(time),"
				+"foreign key(auditorium_id) references auditorium(id),"
				+"foreign key(movie_id) references movie(id),"
				+"foreign key(theater_id) references theater(id))",
				
		   "create table ticket("
				+"id char(20) not null,"
				+"customer_id char(20) not null,"
				+"theater_id char(20) not null,"
				+"auditorium_id char(20) not null,"
				+"schedule_time char(10) not null,"
				+"seat_id char(20) not null,"
				+"movie_id char(20) not null,"
				+"pay_type char(10) not null,"
				+"pay_method char(10) not null,"
				+"pay_state char(10) not null,"
				+"price number,"
				+"primary key(id),"
				+"foreign key(customer_id) references customer(id),"
				+"foreign key(theater_id) references theater(id),"
				+"foreign key(auditorium_id) references auditorium(id),"
				+"foreign key(schedule_time) references schedule(time),"
				+"foreign key(seat_id) references seat(id),"
				+"foreign key(movie_id) references movie(id))"
			
		};
		for(String query : querys){
			this.queryConnector.executeWith(query);
		}
		System.out.println("table initialze completed.");
	}

	public void dropAllTables(){
		String[] querys = new String[]{
				"drop table customer cascade constraint",
				"drop table theater cascade constraint",
				"drop table ticket cascade constraint",
				"drop table auditorium cascade constraint",
				"drop table seat cascade constraint",
				"drop table movie cascade constraint",
				"drop table schedule cascade constraint",
		};
		for(String query : querys){
			this.queryConnector.executeWith(query);
		}
		System.out.println("drop table done.");

	}
	public void deleteAllTupleInTable(){
		String[] querys = new String[]{
				"delete * from customer",
				"delete * from theater",
				"delete * from ticket",
				"delete * from auditorium",
				"delete * from seat",
				"delete * from movie",
				"delete * from schedule"
		};
		for(String query : querys){
			this.queryConnector.executeWith(query);
		}
		System.out.println("delete all tuples in tables done.");
	}

	public void insertDummyTuples(){
		String[] querys = new String[]{
				"insert into theater values('1', 'Megabox', 'Daejeon', '000-0000')",
				"insert into theater values('2', 'CGV', 'Cheonan', '001-0001')",
				"insert into auditorium values('M-1', 'Megabox')",
				"insert into auditorium values('M-2', 'Megabox')",
				"insert into auditorium values('M-3', 'Megabox')",
				"insert into auditorium values('C-1', 'CGV')",
		};
		for(int i = 1; i <= 40; i++){
			
		}
	}
}
