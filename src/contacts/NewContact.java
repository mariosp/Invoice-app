package contacts;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.ws.Holder;

import gr.gspr.webservicealldatadesktopclient.ServiceClient;
import gr.gspr.webservicealldatadesktopclient.client.RgWsPublicBasicRtUser;

import javax.swing.event.AncestorEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;

public class NewContact extends JFrame {

	public JPanel contentPane;
	public JTextField tcn;
	public JTextField toc;
	static JTextField tafm;
	public JTextField tdoy;
	public JTextField tstreet;
	public JTextField ttown;
	public JTextField ttk;
	public JTextField temail;
	public JTextField ttel;
	public JTextField tmob;
	public JLabel lblNewLabel_1;
	public int id=0;
	
	public ServiceClient sc = new ServiceClient();
	public String un; //username of taxis
	public String pass; //pass of taxis
	public String o=" ";
	public JLabel lblNewLabel;
	
	
	
	public NewContact() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tcn.setText(sc.basicRtUser.value.getOnomasia());
				
				tstreet.setText(sc.basicRtUser.value.getPostalAddress().trim() + sc.basicRtUser.value.getPostalAddressNo().trim() );
				ttk.setText(sc.basicRtUser.value.getPostalZipCode().trim());
				ttown.setText(sc.basicRtUser.value.getPostalAreaDescription().trim());
				tdoy.setText(sc.basicRtUser.value.getDoyDescr());
			}
		});
		
		
		lblNewLabel_1.setIcon(new ImageIcon(NewContact.class.getResource("/icons/if_check_1930264.png")));
		lblNewLabel_1.setBounds(161, 233, 28, 32);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JLabel label_15 = new JLabel("");
		label_15.setBounds(161, 233, 28, 32);
		contentPane.add(label_15);
		label_15.setVisible(false);
		
		tafm = new JTextField();
		tafm.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				if(tafm.getText().length()==9) {
					try {
						 if(checktAfm()) {
						 lblNewLabel_1.setVisible(true);
						 }else {
							 lblNewLabel_1.setVisible(false);
						 }
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					 lblNewLabel_1.setVisible(false);
				}
	
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(tafm.getText().length()==9) {
					try {
						 if(checktAfm()) {
						 lblNewLabel_1.setVisible(true);
						 }else {
							 lblNewLabel_1.setVisible(false);
							
						 }
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					 lblNewLabel_1.setVisible(false);
						
				}
				
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		tafm.setColumns(10);
		tafm.setBounds(10, 233, 137, 32);
		contentPane.add(tafm);
		
		 lblNewLabel = new JLabel("ΔΗΜΙΟΥΡΓΙΑ ΕΠΑΦΗΣ");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 867, 54);
		contentPane.add(lblNewLabel);
		
		tcn = new JTextField();
		tcn.setBounds(10, 85, 374, 32);
		contentPane.add(tcn);
		tcn.setColumns(10);
		
		JLabel label = new JLabel("ΕΠΩΝΥΜΙΑ ΕΠΙΧΕΙΡΗΣΗΣ");
		label.setBounds(10, 65, 133, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("ΔΡΑΣΤΗΡΙΟΤΗΤΑ");
		label_1.setBounds(10, 139, 133, 14);
		contentPane.add(label_1);
		
		toc = new JTextField();
		toc.setColumns(10);
		toc.setBounds(10, 160, 374, 32);
		contentPane.add(toc);
		
		JLabel label_2 = new JLabel("ΑΦΜ");
		label_2.setBounds(10, 214, 90, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("ΔΟΥ");
		label_3.setBounds(202, 214, 133, 14);
		contentPane.add(label_3);
		
		tdoy = new JTextField();
		tdoy.setColumns(10);
		tdoy.setBounds(195, 233, 189, 32);
		contentPane.add(tdoy);
		
		JLabel label_4 = new JLabel("ΔΙΕΥΘΥΝΣΗ");
		label_4.setBounds(10, 288, 133, 14);
		contentPane.add(label_4);
		
		tstreet = new JTextField();
		tstreet.setColumns(10);
		tstreet.setBounds(10, 312, 236, 32);
		contentPane.add(tstreet);
		
		JLabel label_5 = new JLabel("ΠΟΛΗ");
		label_5.setBounds(261, 288, 133, 14);
		contentPane.add(label_5);
		
		ttown = new JTextField();
		ttown.setColumns(10);
		ttown.setBounds(261, 312, 236, 32);
		contentPane.add(ttown);
		
		JLabel label_6 = new JLabel("ΤΑΧΥΔΡΟΜΙΚΟΣ ΚΩΔΙΚΑΣ");
		label_6.setBounds(10, 360, 133, 14);
		contentPane.add(label_6);
		
		ttk = new JTextField();
		ttk.setColumns(10);
		ttk.setBounds(10, 385, 102, 32);
		contentPane.add(ttk);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(537, 112, 0, -58);
		contentPane.add(separator);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(553, 65, 46, 14);
		contentPane.add(lblEmail);
		
		temail = new JTextField();
		temail.setColumns(10);
		temail.setBounds(552, 85, 306, 32);
		contentPane.add(temail);
		
		JLabel label_7 = new JLabel("ΤΗΛΕΦΩΝΟ");
		label_7.setBounds(553, 139, 122, 14);
		contentPane.add(label_7);
		
		ttel = new JTextField();
		ttel.setColumns(10);
		ttel.setBounds(552, 160, 306, 32);
		contentPane.add(ttel);
		
		JLabel label_8 = new JLabel("ΚΙΝΗΤΟ");
		label_8.setBounds(553, 214, 46, 14);
		contentPane.add(label_8);
		
		tmob = new JTextField();
		tmob.setColumns(10);
		tmob.setBounds(552, 239, 306, 32);
		contentPane.add(tmob);
		
		JButton btnNewButton = new JButton("ΕΦΑΡΜΟΓΗ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkInput()){
					if(id==0) {
				ContactsD.addEntry(tcn,toc,tafm,tdoy,tstreet,ttown,ttk,temail,ttel,tmob);
						dispose();
					}
					else {
						ContactsD.updateEntry(id, tcn, toc, tafm, tdoy, tstreet, ttown, ttk, temail, ttel, tmob);
						dispose();
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null, "ΕΙΣΑΓΕΤΕ ΟΛΑ ΤΑ ΑΠΑΙΤΟΥΜΕΝΑ ΚΕΛΙΑ", "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(745, 385, 102, 36);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("ΑΚΥΡΩΣΗ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		button.setBounds(633, 385, 102, 36);
		contentPane.add(button);
		
		JLabel label_9 = new JLabel("*");
		label_9.setBounds(388, 85, 46, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("*");
		label_10.setBounds(388, 160, 46, 14);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("*");
		label_11.setBounds(151, 233, 46, 14);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("*");
		label_12.setBounds(388, 233, 46, 14);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("*");
		label_13.setBounds(246, 312, 46, 14);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("*");
		label_14.setBounds(505, 312, 46, 14);
		contentPane.add(label_14);
		
	}
	
	
	public boolean checkInput() {

		
		if (tcn.getText().isEmpty()) return false;
		if (toc.getText().isEmpty()) return false;
		if (tafm.getText().isEmpty()) return false;
		if (tdoy.getText().isEmpty()) return false;
		if (tstreet.getText().isEmpty()) return false;
		if (ttown.getText().isEmpty()) return false;
		
		return true;		
	}

	public boolean checktAfm() throws Exception {
		
		String rr = sc.getData(un, pass, tafm.getText(),o);
		//System.out.println(rr);
			
		if(sc.basicRtUser.value.getAfm()!=null) {
			return true;
			
		}else {
			return false;
		}
	}
		
	
	public void setForModify(ArrayList<ContactsD> contact) {
		
		
		for(int i = 0; i < contact.size(); i++)
        {
			
			tcn.setText(contact.get(i).getCname());
			toc.setText(contact.get(i).getOc());
			tafm.setText(contact.get(i).getAfm());
			tdoy.setText(contact.get(i).getDoy());
			tstreet.setText(contact.get(i).getStreet());
			ttown.setText(contact.get(i).getTown());
			ttk.setText(contact.get(i).getZip());
			temail.setText(contact.get(i).getEmail());
			ttel.setText(contact.get(i).getTel());
			tmob.setText(contact.get(i).getMob());
			//lblstatus1.setText(contact.get(i).getStatus());
			
			id=contact.get(i).getId();
        }
		
		
		
	}
	
	
	
	
}/////////////////
