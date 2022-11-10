import java.awt.EventQueue;

import javax.swing.JFrame;



/**
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
*/
//import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
/**import java.awt.BorderLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.Label; */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Frame1 {
	
	
	private JFrame frame;
	private JTextField StokKod_textField;
	private JTextField StokAd_textField;
	private JTextField Barkod_textField;
	private JButton btnUpdate;
	private JTable table;
	private JComboBox StokTip_comboBox;
	private JComboBox Birim_comboBox;
	private JComboBox KDVTip_comboBox;
	private JComboBox comboBoxSelection_AramaKriteri;
	private JScrollPane scrollPane_DBTable;
	private JTextField textFieldSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	public void refreshTable()
	{
		try {
			String query="select * from stokkart";
			PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query);
			//pst.execute();
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	Connection connection=null;
	
	public Frame1() {
		connection=DBConnectionGuru.dbConnector();
		initialize();
	}

	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 846, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		StokKod_textField = new JTextField();
		StokKod_textField.setBounds(101, 29, 96, 16);
		frame.getContentPane().add(StokKod_textField);
		StokKod_textField.setColumns(10);
		
		StokAd_textField = new JTextField();
		StokAd_textField.setBounds(101, 45, 96, 16);
		frame.getContentPane().add(StokAd_textField);
		StokAd_textField.setColumns(10);
		
		StokTip_comboBox = new JComboBox();
		StokTip_comboBox.setModel(new DefaultComboBoxModel(new String[] {"           50", "          100", "          200", "          500", "          1000"}));
		StokTip_comboBox.setBounds(101, 61, 96, 16);
		frame.getContentPane().add(StokTip_comboBox);
		
		Birim_comboBox = new JComboBox();
		Birim_comboBox.setModel(new DefaultComboBoxModel(new String[] {"adet", "çift", "kg", "kutu", "paket", "tek"}));
		Birim_comboBox.setBounds(101, 77, 96, 16);
		frame.getContentPane().add(Birim_comboBox);
		
		Barkod_textField = new JTextField();
		Barkod_textField.setBounds(101, 93, 96, 16);
		frame.getContentPane().add(Barkod_textField);
		Barkod_textField.setColumns(10);
		
		KDVTip_comboBox = new JComboBox();
		KDVTip_comboBox.setModel(new DefaultComboBoxModel(new String[] {"         0.01", "         0.08", "         0.15"}));
		KDVTip_comboBox.setBounds(101, 109, 96, 16);
		frame.getContentPane().add(KDVTip_comboBox);
		
		final JTextArea Aciklama_textArea = new JTextArea();
		Aciklama_textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Aciklama_textArea.setBackground(new Color(255, 255, 255));
		Aciklama_textArea.setBounds(101, 125, 96, 16);
		frame.getContentPane().add(Aciklama_textArea);
		
		final JFormattedTextField OlusturmaTarih_formattedTextField = new JFormattedTextField();
		OlusturmaTarih_formattedTextField.setBounds(101, 141, 96, 16);
		frame.getContentPane().add(OlusturmaTarih_formattedTextField);
		
		JButton btnSave = new JButton("KAYDET");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query1="insert into stokkart (StokKod,StokAd,StokTip,Birim,Barkod,KDVTip,Aciklama,OlusturmaTarih) values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query1);
					pst.setString(1, StokKod_textField.getText());
					pst.setString(2, StokAd_textField.getText());
					pst.setString(3, StokTip_comboBox.getSelectedItem().toString());
					pst.setString(4, Birim_comboBox.getSelectedItem().toString());
					pst.setString(5, Barkod_textField.getText());
					pst.setString(6, KDVTip_comboBox.getSelectedItem().toString());
					pst.setString(7, Aciklama_textArea.getText());
					pst.setString(8, OlusturmaTarih_formattedTextField.getText());
					
					pst.execute();
					
			
					JOptionPane.showMessageDialog(null, "Yeni Stok Kart başarıyla kaydedilmiştir.");
					
					pst.close(); 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnSave.setBounds(3, 168, 96, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnDelete = new JButton("SİL");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query2="delete from stokkart where StokKod='"+StokKod_textField.getText()+"'  ";
					PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query2);
					
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Stok Kart başarıyla silinmiştir.");
					
					pst.close();
				
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				refreshTable();
			}
		});
		btnDelete.setBounds(3, 198, 96, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnCopy = new JButton("KOPYALA");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String query3="INSERT INTO stokkart (SELECT (select max(StokKod) from stokkart)+'1',StokAd, StokTip, Birim, Barkod, KDVTip, Aciklama, OlusturmaTarih\r\n"
							+ "FROM stokkart\r\n"
							+ "WHERE stokkart.StokKod='"+StokKod_textField.getText()+"');";
					PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query3);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Stok Kart başarıyla kopyalanmıştır.");
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				
			}
		});
		btnCopy.setBounds(3, 258, 96, 23);
		frame.getContentPane().add(btnCopy);
		
		btnUpdate = new JButton("GÜNCELLE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query4="Update stokkart set StokKod='"+StokKod_textField.getText()+"',StokAd='"+StokAd_textField.getText()+"',StokTip='"+StokTip_comboBox.getSelectedItem().toString()+"',Birim='"+Birim_comboBox.getSelectedItem().toString()+"',Barkod='"+Barkod_textField.getText()+"',KDVTip='"+KDVTip_comboBox.getSelectedItem().toString()+"',Aciklama='"+Aciklama_textArea.getText()+"' ,OlusturmaTarih='"+OlusturmaTarih_formattedTextField.getText()+"'  where StokKod='"+StokKod_textField.getText()+"' ";
					PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query4);
					
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Stok Kart başarıyla güncellenmiştir.");
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnUpdate.setBounds(3, 228, 96, 23);
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblNewLabel = new JLabel("      STOK KART ");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(145, 11, 121, 14);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane_DBTable = new JScrollPane();
		scrollPane_DBTable.setBounds(207, 61, 613, 327);
		frame.getContentPane().add(scrollPane_DBTable);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row =table.getSelectedRow();
					String StokKod_=(table.getModel().getValueAt(row, 0)).toString();
					String query6="select * from stokkart where StokKod='"+StokKod_+"'";
					PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query6);
					
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						StokKod_textField.setText(rs.getString("StokKod"));
						StokAd_textField.setText(rs.getString("StokAd"));
						StokTip_comboBox.setSelectedItem(rs.getInt("StokTip"));
						Birim_comboBox.setSelectedItem(rs.getString("Birim"));
						Barkod_textField.setText(rs.getString("Barkod"));
						KDVTip_comboBox.setSelectedItem(rs.getDouble("KDVTip"));
						Aciklama_textArea.setText(rs.getString("Aciklama"));
						OlusturmaTarih_formattedTextField.setText(rs.getString("OlusturmaTarih"));
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		scrollPane_DBTable.setViewportView(table);
		
		
		
		JButton btnList = new JButton("TÜM LİSTE");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query5 = "SELECT * FROM gurustokkartdb.stokkart";
					PreparedStatement pst = DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query5);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnList.setBounds(100, 168, 96, 23);
		frame.getContentPane().add(btnList);
		
		JLabel lblNewLabel_StokKod = new JLabel("StokKod");
		lblNewLabel_StokKod.setBounds(10, 29, 85, 16);
		frame.getContentPane().add(lblNewLabel_StokKod);
		
		JLabel lblNewLabel_StokAd = new JLabel("StokAd");
		lblNewLabel_StokAd.setBounds(10, 45, 85, 16);
		frame.getContentPane().add(lblNewLabel_StokAd);
		
		JLabel lblNewLabel_StokTip = new JLabel("StokTip");
		lblNewLabel_StokTip.setBounds(10, 61, 85, 16);
		frame.getContentPane().add(lblNewLabel_StokTip);
		
		JLabel lblNewLabel_Birim = new JLabel("Birim");
		lblNewLabel_Birim.setBounds(10, 77, 85, 16);
		frame.getContentPane().add(lblNewLabel_Birim);
		
		JLabel lblNewLabel_OlusturmaTarih = new JLabel("OlusturmaTarih");
		lblNewLabel_OlusturmaTarih.setBounds(10, 141, 105, 16);
		frame.getContentPane().add(lblNewLabel_OlusturmaTarih);
		
		JLabel lblNewLabel_KDVTip = new JLabel("KDVTip");
		lblNewLabel_KDVTip.setBounds(10, 109, 85, 16);
		frame.getContentPane().add(lblNewLabel_KDVTip);
		
		JLabel lblNewLabel_Aciklama = new JLabel("Aciklama");
		lblNewLabel_Aciklama.setBounds(10, 125, 85, 16);
		frame.getContentPane().add(lblNewLabel_Aciklama);
		
		JLabel lblNewLabel_Barkod = new JLabel("Barkod");
		lblNewLabel_Barkod.setBounds(10, 93, 85, 16);
		frame.getContentPane().add(lblNewLabel_Barkod);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String selection=(String)comboBoxSelection_AramaKriteri.getSelectedItem();
					String query6="select * from stokkart where "+selection+"=? ";
					PreparedStatement pst=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*").prepareStatement(query6);
					pst.setString(1, textFieldSearch.getText() );
				    ResultSet rs=pst.executeQuery();
					
				    table.setModel(DbUtils.resultSetToTableModel(rs));
		
					pst.close();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		textFieldSearch.setBounds(370, 29, 181, 23);
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		comboBoxSelection_AramaKriteri = new JComboBox();
		comboBoxSelection_AramaKriteri.setModel(new DefaultComboBoxModel(new String[] {"Arama Kriteri ", "      StokKod", "      StokAd", "      StokTip", "      Birim", "      Barkod", "      KDVTip", "      OlusturmaTarih"}));
		comboBoxSelection_AramaKriteri.setBounds(207, 29, 115, 23);
		frame.getContentPane().add(comboBoxSelection_AramaKriteri);
		
		
		

	}
}

