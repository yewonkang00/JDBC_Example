import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test3 {
	private String username = "database";
	private String password = "1234";
	private static Connection dbTest;
	
	Test3() {
		connectDB();
	}
	
	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, password);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public void execute_update() throws SQLException {
		String sqlStr = "UPDATE PC set price = (price - 100) WHERE price >= 2000";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		stmt.executeUpdate(sqlStr);
	}
	
	public void execute_query() throws SQLException {
		String sqlStr = "SELECT price FROM pc WHERE price > 2000";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		System.out.println("PRICE\n----------");
		while(rs.next()) {
			System.out.println(rs.getString("PRICE"));
		}
		
		rs.close();
		stmt.close();
	}
	
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		try {
			t3.execute_update();
			t3.execute_query();
			dbTest.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		}
	}
}
