import java.sql.*;

public class QueryConnector{

	private String DRIVER, URL, USER, PASS;
	private Connection connection;
	
	public QueryConnector(String driver, String url, String user, String pass){
		this.DRIVER = driver;
		this.URL = url;
		this.USER = user;
		this.PASS = pass;
		this.connection = this.getConnection();
	}

	private Connection getConnection(){
		try{
			Class.forName(this.DRIVER);
			return DriverManager.getConnection(this.URL, this.USER, this.PASS);
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

	public ResultSet selectResultFrom(String query){
		ResultSet resultSet = null;
		try{
			Statement statement = this.connection.createStatement();
			resultSet = statement.executeQuery(query);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return resultSet;
	}
	

	public void executeWith(String query){
		try{
			Statement statement = this.connection.createStatement();
			int rowCount = statement.executeUpdate(query);
			if(rowCount == 0){
				System.out.println("rowcount = 0, "+query);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	// rtrim() required at string attributes
	public boolean executePreparedStatement(PreparedStatement preparedStatement){
		try{
			int rowCount = preparedStatement.executeUpdate();
			if(rowCount == 0){
				return false;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	

}
