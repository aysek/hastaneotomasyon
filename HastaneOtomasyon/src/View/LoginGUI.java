package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_hastaPass;
	private JPasswordField fld_doktorPass;
	private JTextField fld_doktorTc;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medcn.png")));
		lbl_logo.setBounds(215, 0, 70, 70);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setBounds(99, 70, 320, 30);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 123, 474, 187);
		w_tabpane.setBackground(Color.WHITE);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaran\u0131z: ");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNumaranz.setBounds(31, 27, 143, 30);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblifreniz = new JLabel("\u015Eifreniz: ");
		lblifreniz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifreniz.setBounds(31, 68, 143, 30);
		w_hastaLogin.add(lblifreniz);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaTc.setBounds(184, 35, 137, 20);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		fld_hastaPass = new JTextField();
		fld_hastaPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaPass.setColumns(10);
		fld_hastaPass.setBounds(184, 68, 137, 20);
		w_hastaLogin.add(fld_hastaPass);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		btn_register.setBounds(29, 125, 145, 23);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLgin = new JButton("Giri\u015F Yap");
		btn_hastaLgin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		btn_hastaLgin.setBounds(243, 125, 145, 23);
		w_hastaLogin.add(btn_hastaLgin);
		
		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_doktorLogin.setForeground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriþi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);
		
		JLabel label = new JLabel("T.C. Numaran\u0131z: ");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label.setBounds(42, 11, 143, 30);
		w_doktorLogin.add(label);
		
		JLabel label_1 = new JLabel("\u015Eifreniz: ");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_1.setBounds(42, 52, 143, 30);
		w_doktorLogin.add(label_1);
		
		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorTc.getText().length()==0 || fld_doktorPass.getText().length()==0) {//kullanýcý sifre veya tc alanýný dodurmadýysa
										
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con = conn.connDb();  
						Statement st= con.createStatement();
						ResultSet rs = st.executeQuery("SELECT *FROM user ");
						while (rs.next()) {
							if( fld_doktorTc.getText().equals(rs.getString("tcno") )  && fld_doktorPass.getText().equals(rs.getString("password"))) {
								 Bashekim bhekim = new Bashekim();
								 bhekim.setId(rs.getInt("id"));
								 bhekim.setPassword("password");
								 bhekim.setTcno(rs.getString("tcno"));
								 bhekim.setName(rs.getString("name"));
								 bhekim.setType(rs.getString("type"));
								 BashekimGUI bGUI = new BashekimGUI (bhekim);
								 bGUI.setVisible(true);
								 dispose();
								 
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				Connection con = conn.connDb();  
			}
		});
		btn_doktorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		btn_doktorLogin.setBounds(112, 109, 254, 23);
		w_doktorLogin.add(btn_doktorLogin);
		
		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setBounds(195, 50, 171, 30);
		w_doktorLogin.add(fld_doktorPass);
		
		fld_doktorTc = new JTextField();
		fld_doktorTc.setBounds(195, 11, 171, 31);
		w_doktorLogin.add(fld_doktorTc);
		fld_doktorTc.setColumns(10);
	}
}








