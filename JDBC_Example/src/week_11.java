import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class week_11 implements ActionListener {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel idLabel = new JLabel("아이디");
	private JLabel pwdLabel = new JLabel("비밀번호");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("로그인");
	private JTextArea check_area = new JTextArea();
	private JComboBox<String> check_box = new JComboBox<String>();
	
	private String username = "ID";
	private String password = "PW";
	private static Connection dbTest;

	private void PCstore() {
		frame = new JFrame();
		panel = new JPanel();
		
		panel.setFont(new Font("필기체", 1, 12));
		panel.setBorder(new TitledBorder("조회"));
		panel.setBounds(380, 80, 490, 280);
		panel.setLayout(null);
		
		check_box.addItem("PC");
		check_box.addItem("Laptop");
		check_box.addItem("Printer");
		check_box.addItem("Product");
		check_box.setBounds(20, 40, 70, 30);
		check_box.addActionListener(this);
		
		check_area.setBorder(new LineBorder(Color.GRAY, 2));
		check_area.setEditable(false);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(check_area);
		scroll.setBounds(10, 80, 360, 170);
		
		panel.add(check_box);
		panel.add(scroll);
		
		frame.add(panel);
		frame.setTitle("PC Store");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public week_11() {
		panel.setLayout(null);
		idLabel.setBounds(20, 10, 60, 30);
		pwdLabel.setBounds(20, 50, 60, 30);
		idInput.setBounds(100, 10, 80, 30);
		pwdInput.setBounds(100, 50, 80, 30);
		loginButton.setBounds(200, 25, 80, 35);
		
		loginButton.addActionListener(this);
		
		panel.add(idLabel);
		panel.add(pwdLabel);
		panel.add(idInput);
		panel.add(pwdInput);
		panel.add(loginButton);
		
		frame.add(panel);
		
		frame.setTitle("JDBC Practice 1");
		frame.setSize(320, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			username = idInput.getText();
			password = new String(pwdInput.getPassword());
			
			connectDB();
		} else if(e.getSource() == check_box) {
			try {
				showTable();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	private void showTable() throws SQLException {
		String specification = "";
		
		String sqlStr = "SELECT count(column_name) num from cols where table_name = '" +
						((String)check_box.getSelectedItem()).toUpperCase() + "'";
		
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		int number = rs.getInt("num");
		String[] tables = new String[number];
		
		sqlStr = "SELECT column_name from cols where table_name = '" +
				((String)check_box.getSelectedItem()).toUpperCase() + "'";

		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();
		
		for(number = 0; rs.next(); number++) {
			tables[number] = rs.getString("column_name");
			specification += tables[number] + "\t";
		}
		
		for(specification += '\n'; number > 0; number--) {
			specification += "------------------------";
		}
		
		specification += "\n";
		
		sqlStr = "SELECT * FROM " +	(String)check_box.getSelectedItem();
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();
		
		for(number = 0; rs.next(); number++) {
			for(int i = 0; i < tables.length; i++) {
				specification += rs.getString(tables[i]) + "\t"; 
			}
			specification += '\n';
		}
		
		check_area.setText(specification);
		
		rs.close();
		stmt.close();
	}
	
	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
			System.out.println("강예원님이 데이터베이스에 연결되었습니다.");
			PCstore();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스에 연결에 살패하였습니다.");
			System.out.println("SQLException : " + e);
		} catch(Exception e) {
			System.out.println("Exception : " + e);
		}
	}
	
	public static void main(String[] args) {
		new week_11();
	}
}
