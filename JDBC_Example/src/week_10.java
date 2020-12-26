import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class week_10 implements ActionListener {
	private JFrame frame = new JFrame();
	private JLabel idLabel = new JLabel("아이디");
	private JLabel pwdLabel = new JLabel("비밀번호");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("로그인");
	private JPanel panel = new JPanel();
	
	private String username = "ID";
	private String password = "PW";
	private static Connection dbTest;
	
	public week_10() {
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
		
		System.out.println(frame.getContentPane().getSize());
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			username = idInput.getText();
			password = new String(pwdInput.getPassword());
			
			connectDB();
		}
	}
	
	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
			System.out.println("데이터베이스에 연결되었습니다.");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스에 연결에 살패하였습니다.");
			System.out.println("SQLException : " + e);
		} catch(Exception e) {
			System.out.println("Exception : " + e);
		}
	}
	
	public static void main(String[] args) {
		new week_10();
	}
}
