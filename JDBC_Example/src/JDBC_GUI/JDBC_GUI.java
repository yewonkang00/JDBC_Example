package JDBC_GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class JDBC_GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	static Connection conn = null;
	static PreparedStatement stmt = null;
	
	JPanel MainPanel;
	JTabbedPane TabbedInfoPanel;
	
	JLabel TabProductLabel;
	JLabel TabPCLabel;
	JLabel TabLaptopLabel;
	JLabel TabPrinterLabel;
	JLabel modelLabel1;
	JLabel modelLabel2;
	JLabel modelLabel3;
	JLabel modelLabel4;
	JTextField modelInput1 = new JTextField();
	JTextField modelInput2 = new JTextField();
	JTextField modelInput3 = new JTextField();
	JTextField modelInput4 = new JTextField();
	JButton searchButton1 = new JButton("확인");
	JButton searchButton2 = new JButton("확인");
	JButton searchButton3 = new JButton("확인");
	JButton searchButton4 = new JButton("확인");
	
	JComboBox<String> TabProductmodelNumberCombobox;
	JComboBox<String> TabPCmodelNumberCombobox;
	JComboBox<String> TabLaptopmodelNumberCombobox;
	JComboBox<String> TabPrintermodelNumberCombobox;
	
	static JTextArea TabProductTextArea;
	static JTextArea TabPCTextArea;
	static JTextArea TabLaptopTextArea;
	static JTextArea TabPrinterTextArea;

	public JDBC_GUI() {
		UIManager.put("swing.blodMetal", Boolean.FALSE);
		setTitle("JDBC와 자바 GUI 실습");
		setBounds(100, 20, 540, 380);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		MainPanel = new JPanel();
		MainPanel.setLayout(null);
		
		makeComponent();
		
		getContentPane().add(MainPanel, BorderLayout.CENTER);
	}
	
	public void makeComponent() {
		TabbedInfoPanel = new JTabbedPane();
		JPanel TabbedInfoPanel_Product = new JPanel();
		JPanel TabbedInfoPanel_PC = new JPanel();
		JPanel TabbedInfoPanel_Laptop = new JPanel();
		JPanel TabbedInfoPanel_Printer = new JPanel();
		
		TabbedInfoPanel_Product.setLayout(null);
		TabbedInfoPanel_PC.setLayout(null);
		TabbedInfoPanel_Laptop.setLayout(null);
		TabbedInfoPanel_Printer.setLayout(null);
		
		TabbedInfoPanel.addTab("Product", TabbedInfoPanel_Product);
		TabbedInfoPanel.addTab("PC", TabbedInfoPanel_PC);
		TabbedInfoPanel.addTab("Laptop", TabbedInfoPanel_Laptop);
		TabbedInfoPanel.addTab("Printer", TabbedInfoPanel_Printer);
		
		TabbedInfoPanel.setBounds(10, 20, 500, 300);
		MainPanel.add(TabbedInfoPanel);
		
		TabProductLabel = new JLabel();
		TabProductLabel.setText("model");
		TabProductLabel.setIcon(new ImageIcon(""));
		TabProductLabel.setBounds(20, 0, 80, 80);
		modelLabel1 = new JLabel();
		modelLabel1.setText("model 입력");
		modelLabel1.setIcon(new ImageIcon(""));
		modelLabel1.setBounds(220, 0, 80, 80);
		
		TabbedInfoPanel_Product.add(TabProductLabel);
		TabbedInfoPanel_Product.add(modelLabel1);
		
		TabPCLabel = new JLabel();
		TabPCLabel.setText("model");
		TabPCLabel.setIcon(new ImageIcon(""));
		TabPCLabel.setBounds(20, 0, 80, 80);
		modelLabel2 = new JLabel();
		modelLabel2.setText("model 입력");
		modelLabel2.setIcon(new ImageIcon(""));
		modelLabel2.setBounds(220, 0, 80, 80);

		TabbedInfoPanel_PC.add(TabPCLabel);
		TabbedInfoPanel_PC.add(modelLabel2);
		
		TabLaptopLabel = new JLabel();
		TabLaptopLabel.setText("model");
		TabLaptopLabel.setIcon(new ImageIcon(""));
		TabLaptopLabel.setBounds(20, 0, 80, 80);
		modelLabel3 = new JLabel();
		modelLabel3.setText("model 입력");
		modelLabel3.setIcon(new ImageIcon(""));
		modelLabel3.setBounds(220, 0, 80, 80);
		
		TabbedInfoPanel_Laptop.add(TabLaptopLabel);
		TabbedInfoPanel_Laptop.add(modelLabel3);
		
		TabPrinterLabel = new JLabel();
		TabPrinterLabel.setText("model");
		TabPrinterLabel.setIcon(new ImageIcon(""));
		TabPrinterLabel.setBounds(20, 0, 80, 80);
		modelLabel4 = new JLabel();
		modelLabel4.setText("model 입력");
		modelLabel4.setIcon(new ImageIcon(""));
		modelLabel4.setBounds(220, 0, 80, 80);
		
		TabbedInfoPanel_Printer.add(TabPrinterLabel);
		TabbedInfoPanel_Printer.add(modelLabel4);
		
		modelInput1.setBounds(300, 20, 80, 40);
		modelInput2.setBounds(300, 20, 80, 40);
		modelInput3.setBounds(300, 20, 80, 40);
		modelInput4.setBounds(300, 20, 80, 40);
		
		TabProductmodelNumberCombobox = new JComboBox<String>();
		TabPCmodelNumberCombobox = new JComboBox<String>();
		TabLaptopmodelNumberCombobox = new JComboBox<String>();
		TabPrintermodelNumberCombobox = new JComboBox<String>();
		
		for (int i=1; i<10; i++) {
			TabProductmodelNumberCombobox.addItem("100" + i);
			TabPCmodelNumberCombobox.addItem("100" + i);
		}
		for (int i=1; i<9; i++) {
			TabProductmodelNumberCombobox.addItem("200" + i);
			TabLaptopmodelNumberCombobox.addItem("200" + i);
		}
		for (int i=1; i<7; i++) {
			TabProductmodelNumberCombobox.addItem("300" + i);
			TabPrintermodelNumberCombobox.addItem("300" + i);
		}
		
		searchButton1.setBounds(400, 20, 60, 40);
		searchButton2.setBounds(400, 20, 60, 40);
		searchButton3.setBounds(400, 20, 60, 40);
		searchButton4.setBounds(400, 20, 60, 40);
		
		searchButton1.addActionListener(this);
		searchButton2.addActionListener(this);
		searchButton3.addActionListener(this);
		searchButton4.addActionListener(this);
		
		TabProductmodelNumberCombobox.setBounds(80, 20, 130, 40);
		TabbedInfoPanel_Product.add(TabProductmodelNumberCombobox);
		TabProductmodelNumberCombobox.addActionListener(this);
		
		TabProductTextArea = new JTextArea();
		
		TabProductTextArea.setFont(new Font("굴림", 0, 12));
		TabProductTextArea.setForeground(Color.black);
		TabProductTextArea.setOpaque(true);
		TabProductTextArea.setBackground(Color.white);
		TabProductTextArea.setBounds(20, 80, 450, 180);
		TabProductTextArea.setBorder(null);
		TabProductTextArea.setLineWrap(true);
		TabProductTextArea.setEditable(false);
		TabbedInfoPanel_Product.add(TabProductTextArea);
		TabbedInfoPanel_Product.add(searchButton1);
		TabbedInfoPanel_Product.add(modelInput1);
		
		TabPCmodelNumberCombobox.setBounds(80, 20, 130, 40);
		TabbedInfoPanel_PC.add(TabPCmodelNumberCombobox);
		TabPCmodelNumberCombobox.addActionListener(this);
		
		TabPCTextArea = new JTextArea();
		
		TabPCTextArea.setFont(new Font("굴림", 0, 12));
		TabPCTextArea.setForeground(Color.black);
		TabPCTextArea.setOpaque(true);
		TabPCTextArea.setBackground(Color.white);
		TabPCTextArea.setBounds(20, 80, 450, 180);
		TabPCTextArea.setBorder(null);
		TabPCTextArea.setLineWrap(true);
		TabPCTextArea.setEditable(false);
		TabbedInfoPanel_PC.add(TabPCTextArea);
		TabbedInfoPanel_PC.add(searchButton2);
		TabbedInfoPanel_PC.add(modelInput2);
		
		TabLaptopmodelNumberCombobox.setBounds(80, 20, 130, 40);
		TabbedInfoPanel_Laptop.add(TabLaptopmodelNumberCombobox);
		TabLaptopmodelNumberCombobox.addActionListener(this);
		
		TabLaptopTextArea = new JTextArea();
		
		TabLaptopTextArea.setFont(new Font("굴림", 0, 12));
		TabLaptopTextArea.setForeground(Color.black);
		TabLaptopTextArea.setOpaque(true);
		TabLaptopTextArea.setBackground(Color.white);
		TabLaptopTextArea.setBounds(20, 80, 450, 180);
		TabLaptopTextArea.setBorder(null);
		TabLaptopTextArea.setLineWrap(true);
		TabLaptopTextArea.setEditable(false);
		TabbedInfoPanel_Laptop.add(TabLaptopTextArea);
		TabbedInfoPanel_Laptop.add(searchButton3);
		TabbedInfoPanel_Laptop.add(modelInput3);
		
		TabPrintermodelNumberCombobox.setBounds(80, 20, 130, 40);
		TabbedInfoPanel_Printer.add(TabPrintermodelNumberCombobox);
		TabPrintermodelNumberCombobox.addActionListener(this);
		
		TabPrinterTextArea = new JTextArea();
		
		TabPrinterTextArea.setFont(new Font("굴림", 0, 12));
		TabPrinterTextArea.setForeground(Color.black);
		TabPrinterTextArea.setOpaque(true);
		TabPrinterTextArea.setBackground(Color.white);
		TabPrinterTextArea.setBounds(20, 80, 450, 180);
		TabPrinterTextArea.setBorder(null);
		TabPrinterTextArea.setLineWrap(true);
		TabPrinterTextArea.setEditable(false);
		TabbedInfoPanel_Printer.add(TabPrinterTextArea);
		TabbedInfoPanel_Printer.add(searchButton4);
		TabbedInfoPanel_Printer.add(modelInput4);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object buttonAction = e.getSource();
		
		if(buttonAction == TabProductmodelNumberCombobox) {
			String modelnumber = (String) TabProductmodelNumberCombobox.getSelectedItem();
			JDBC_Connect_Product JCP = new JDBC_Connect_Product();
			try {
				JCP.productSearch(modelnumber);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			System.out.println(modelnumber);
		} else if(buttonAction == TabPCmodelNumberCombobox) {
			String modelnumber = (String) TabPCmodelNumberCombobox.getSelectedItem();
			JDBC_Connect_PC JCP = new JDBC_Connect_PC();
			JCP.pcSearch(modelnumber);
			System.out.println(modelnumber);
		} else if(buttonAction == TabLaptopmodelNumberCombobox) {
			String modelnumber = (String) TabLaptopmodelNumberCombobox.getSelectedItem();
			JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop();
			JCP.laptopSearch(modelnumber);
			System.out.println(modelnumber);
		} else if(buttonAction == TabPrintermodelNumberCombobox) {
			String modelnumber = (String) TabPrintermodelNumberCombobox.getSelectedItem();
			JDBC_Connect_Printer JCP = new JDBC_Connect_Printer();
			JCP.printerSearch(modelnumber);
			System.out.println(modelnumber);
		} else if(buttonAction == searchButton1) {
			String modelnumber = modelInput1.getText();
			JDBC_Connect_Product JCP = new JDBC_Connect_Product();
			JCP.productSearch(modelnumber);
			
				
			System.out.println(modelnumber);
		} else if(buttonAction == searchButton2) {
			String modelnumber = modelInput2.getText();
			JDBC_Connect_PC JCP = new JDBC_Connect_PC();
			JCP.pcSearch(modelnumber);
			System.out.println(modelnumber);
		} else if(buttonAction == searchButton3) {
			String modelnumber = modelInput3.getText();
			JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop();
			JCP.laptopSearch(modelnumber);
			System.out.println(modelnumber);
		} else if(buttonAction == searchButton4) {
			String modelnumber = modelInput4.getText();
			JDBC_Connect_Printer JCP = new JDBC_Connect_Printer();
			JCP.printerSearch(modelnumber);
			System.out.println(modelnumber);
		}
	}
}
