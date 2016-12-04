import java.sql.*;

public class DBInitializer{
	private QueryConnector queryConnector;

	public DBInitializer(QueryConnector queryConnector){
		this.queryConnector = queryConnector;

	}
	public void allTableInitialize(){

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

	}

}
