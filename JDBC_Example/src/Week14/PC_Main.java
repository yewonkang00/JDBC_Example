package Week14;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class PC_Main {
	public static void main(String[] args) {
		
		// 아이디와 비밀번호 입력창
		String userid = JOptionPane.showInputDialog("Oracle 아이디를 입력해주세요.");
		String userpw = JOptionPane.showInputDialog("Oracle 비밀번호를 입력해주세요.");
		
		try {
			
			//디비 연결
			Class.forName("oracle.jdbc.OracleDriver");
			PC_GUI3.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
					userid, userpw);
			
			//로그인이 되면 메세지창 보여주기
			JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
			System.out.println("데이터베이스 연결");
			//입력창에서 입력한 아이디와 비밀번호를 PC_GUI로 가져감
			PC_GUI3 gui = new PC_GUI3(userid, userpw);
			gui.setVisible(true); // PC GUI 완성 후 작성
		} catch(Exception x) {
			x.printStackTrace();
			JOptionPane.showMessageDialog(null, "로그인 할 수가 없습니다. 아이디와 비밀번호를 다시 입력해주세요.");
		}
	}
}
