import java.sql.*;

public class DBInitializer{
	private QueryConnector queryConnector;

	public DBInitializer(QueryConnector queryConnector){
		this.queryConnector = queryConnector;

	}
	public void theaterTableInitialize(){

	}

	public void dropAllTables(){
		String query = "drop table customer"
		this.queryConnector.executeWith(query);
		System.out.println("drop table customer done.")

	}
	public void deleteAllTupleInTable(){

	}

}
