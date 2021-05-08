package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Helper.*;

public class BashekimGUI extends JFrame {
	static Bashekim bashekim = new Bashekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField fld_dPass;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad ";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "Þifre ";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for( int i=0;i<bashekim.getDoctorList().size();i++ )
		{
			doctorData[0]= bashekim.getDoctorList().get(i).getId();
			doctorData[1]= bashekim.getDoctorList().get(i).getName();
			doctorData[2]= bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]= bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
		
		
		setTitle("Hastane YY\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 341);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþ geldiniz Sayýn "+ bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 217, 24);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btnNewButton.setBounds(378, 12, 125, 23);
		w_pane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 46, 493, 255);
		w_pane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		label.setBounds(391, 0, 87, 24);
		panel.add(label);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(392, 21, 86, 20);
		panel.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel label_1 = new JLabel("T.C. Kimlik No");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		label_1.setBounds(391, 43, 101, 24);
		panel.add(label_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(391, 67, 86, 20);
		panel.add(fld_dTcno);
		
		JLabel label_2 = new JLabel("\u015Eifre");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		label_2.setBounds(391, 90, 72, 20);
		panel.add(label_2);
		
		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(392, 110, 86, 20);
		panel.add(fld_dPass);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_dName.getText().length()==0 || fld_dPass.getText().length()==0 || fld_dTcno.getText().length()==0) {
					
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(), fld_dName.getText());
						if(control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null); 
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		
		
		
		btn_addDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_addDoctor.setBounds(391, 137, 89, 23);
		panel.add(btn_addDoctor);
		
		JLabel label_3 = new JLabel("Kullan\u0131c\u0131 ID");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		label_3.setBounds(391, 158, 87, 24);
		panel.add(label_3);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(391, 183, 86, 20);
		panel.add(fld_doctorID);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_delDoctor.setBounds(391, 204, 89, 23);
		panel.add(btn_delDoctor);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 371, 216);
		panel.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for( int i=0;i<bashekim.getDoctorList().size();i++ )
		{
			doctorData[0]= bashekim.getDoctorList().get(i).getId();
			doctorData[1]= bashekim.getDoctorList().get(i).getName();
			doctorData[2]= bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]= bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
	}
}








