import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test2 {
	private String username = "database";
	private String password = "1234";
	private static Connection dbTest;
	
	Test2() {
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
	
	public void execute_query() throws SQLException {
		String sqlStr = "select maker, model, price from pc natural join product where cd='8x' and ram >=24";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			System.out.println("maker : " + rs.getString("model") + "  " +
					"model : " + rs.getString("model") + "  " +
					"price : " + rs.getString("price"));
		}
		rs.close();
		stmt.close();
	}
	
	public static void main(String[] args) {
		Test2 t2 = new Test2();
		try {
			t2.execute_query();
			dbTest.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		}
	}
}