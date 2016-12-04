import java.sql.*;

public class Main {
	public static void main(String args[]){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:DBSERVER";
		String USER = "LEE";
		String PASS = "redsun";
		try{
			Class.forName(DRIVER);
			Connection con1 = DriverManager.getConnection(URL, USER, PASS);
			DatabaseMetaData meta = con1.getMetaData();
			System.out.println("user: "+ meta.getUserName());
			
			String acnt_num, branch_num;
			int bal;
			
			// SELECT STATEMENT
			try{
				Statement stmt = con1.createStatement();
				String query = "select * from raccount";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					acnt_num = rs.getString(1).trim();
					branch_num = rs.getString(2).trim();
					bal = rs.getInt(3);
					System.out.println(acnt_num+","+branch_num+","+bal);
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			// DDL insert
			/*
			try{
				Statement stmt = con1.createStatement();
				String query = "insert into raccount values('A-555', 'B1', '8000')";
				int rowCount = stmt.executeUpdate(query);
				if(rowCount == 0){
					System.out.println("failed to update raccount");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			// DDL delete
			try{
				Statement stmt = con1.createStatement();
				String query = "delete from raccount where raccount_number = 'A-555'";
				int rowCount = stmt.executeUpdate(query);
				if(rowCount == 0){
					System.out.println("failed to update raccount");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			// DDL update
			try{
				Statement stmt = con1.createStatement();
				String query = "update raccount set balance=5000 where raccount_number='A-101'";
				int rowCount = stmt.executeUpdate(query);
				if(rowCount == 0){
					System.out.println("failed to update raccount");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}*/


			// Prepared Statement
			/*
			try{
				System.out.println();
				PreparedStatement pstmt2 = con1.prepareStatement(
						"update raccount set balance=? where rtrim(raccount_number)=?");
				pstmt2.setInt(1, 2000);
				pstmt2.setString(2, "A-101");
				int rowCount = pstmt2.executeUpdate();
				if(rowCount == 0)
					System.out.println("preparedstatement failed.");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}*/
			
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
