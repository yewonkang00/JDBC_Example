package Week14;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class PC_Main {
	public static void main(String[] args) {
		
		// ���̵�� ��й�ȣ �Է�â
		String userid = JOptionPane.showInputDialog("Oracle ���̵� �Է����ּ���.");
		String userpw = JOptionPane.showInputDialog("Oracle ��й�ȣ�� �Է����ּ���.");
		
		try {
			
			//��� ����
			Class.forName("oracle.jdbc.OracleDriver");
			PC_GUI3.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
					userid, userpw);
			
			//�α����� �Ǹ� �޼���â �����ֱ�
			JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
			System.out.println("�����ͺ��̽� ����");
			//�Է�â���� �Է��� ���̵�� ��й�ȣ�� PC_GUI�� ������
			PC_GUI3 gui = new PC_GUI3(userid, userpw);
			gui.setVisible(true); // PC GUI �ϼ� �� �ۼ�
		} catch(Exception x) {
			x.printStackTrace();
			JOptionPane.showMessageDialog(null, "�α��� �� ���� �����ϴ�. ���̵�� ��й�ȣ�� �ٽ� �Է����ּ���.");
		}
	}
}
