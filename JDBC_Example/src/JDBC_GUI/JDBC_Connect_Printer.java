package JDBC_GUI;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JDBC_Connect_Printer {
	public void printerSearch(String modelnumber) {
		String model = "";
		String color = "";
		String type = "";
		String price = "";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			JDBC_GUI.conn = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "database", "1234");
			
			String sqlStr = "select * from printer where model=\'" + modelnumber + "\'";
			JDBC_GUI.stmt = JDBC_GUI.conn.prepareStatement(sqlStr);
			ResultSet rs = JDBC_GUI.stmt.executeQuery(sqlStr);
			
			while(rs.next()) {
				model = rs.getString("model");
				color = rs.getString("color");
				type = rs.getString("type");
				price = rs.getString("price");
				
				System.out.println(model);
			}
			if(model == "") {
				JOptionPane.showMessageDialog(null, "강예원님,입력한 모델명" + modelnumber + "은 존재하지 않기 때문에 구매할 수 없습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JDBC_GUI.TabPrinterTextArea.setText('\n' + "model : " + model + '\n' + '\n'
													+ " color : " + color + '\n'
													+ " type : " + type + '\n'
													+ " price : " + price + '\n');
			}
			rs.close();
			JDBC_GUI.stmt.close();
		} catch(Exception e) {
			
		} finally {
			try {
				if(JDBC_GUI.stmt != null)
					JDBC_GUI.stmt.close();
				if(JDBC_GUI.conn != null)
					JDBC_GUI.conn.close();
			} catch(SQLException e) {
				
			}
		}
	}
}
