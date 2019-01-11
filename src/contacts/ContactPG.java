package contacts;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import invoice.InvoiceD;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ContactPG extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("ΕΠΑΦΗ");
	
	public  JLabel lblcn1;
	public JLabel lbloc1;
	public  JLabel lblafm1;
	public  JLabel lbldoy1;
	public  JLabel lblstreet1;
	public  JLabel lbltown1;
	public  JLabel lbltk1;
	public  JLabel lblemail1;
	public  JLabel lbltel1;
	public  JLabel lblmob1;
	public  JLabel lblstatus1;
	public  JLabel lbldcreated1;
	public  JLabel lbldmodified1;
	public JLabel lblid1;
	
	public static int id;
	public ArrayList<ContactsD> contact;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	
	
	public ContactPG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 665, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblcn = new JLabel("ΕΠΩΝΥΜΙΑ :");
		lblcn.setBounds(10, 59, 71, 14);
		contentPane.add(lblcn);
		
		JLabel lbloc = new JLabel("ΔΡΑΣΤΗΡΙΟΤΗΤΑ :");
		lbloc.setBounds(10, 81, 107, 14);
		contentPane.add(lbloc);
		
		JLabel lblafm = new JLabel("ΑΦΜ:");
		lblafm.setBounds(10, 106, 71, 14);
		contentPane.add(lblafm);
		
		JLabel lbldoy = new JLabel("ΔΟΥ :");
		lbldoy.setBounds(10, 131, 71, 14);
		contentPane.add(lbldoy);
		
		JLabel lblstreet = new JLabel("ΔΙΕΥΘΥΝΣΗ :");
		lblstreet.setBounds(10, 156, 71, 14);
		contentPane.add(lblstreet);
		
		JLabel lbltown = new JLabel("ΠΟΛΗ :");
		lbltown.setBounds(10, 181, 71, 14);
		contentPane.add(lbltown);
		
		JLabel lbltk = new JLabel("TAX.K :");
		lbltk.setBounds(10, 206, 71, 14);
		contentPane.add(lbltk);
		
		JLabel lblemail = new JLabel("email :");
		lblemail.setBounds(10, 231, 71, 14);
		contentPane.add(lblemail);
		
		JLabel lbltel = new JLabel("ΤΗΛ :");
		lbltel.setBounds(10, 256, 71, 14);
		contentPane.add(lbltel);
		
		JLabel lblmob = new JLabel("ΚΙΝ :");
		lblmob.setBounds(10, 280, 71, 14);
		contentPane.add(lblmob);
		
		 lblcn1 = new JLabel("");
		lblcn1.setBounds(91, 59, 218, 14);
		contentPane.add(lblcn1);
		
		lbloc1 = new JLabel("");
		lbloc1.setBounds(111, 81, 218, 14);
		contentPane.add(lbloc1);
		
		 lblafm1 = new JLabel("");
		lblafm1.setBounds(91, 106, 218, 14);
		contentPane.add(lblafm1);
		
		 lbldoy1 = new JLabel("");
		lbldoy1.setBounds(91, 131, 218, 14);
		contentPane.add(lbldoy1);
		
		 lblstreet1 = new JLabel("");
		lblstreet1.setBounds(91, 156, 218, 14);
		contentPane.add(lblstreet1);
		
		 lbltown1 = new JLabel("");
		lbltown1.setBounds(91, 181, 218, 14);
		contentPane.add(lbltown1);
		
		 lbltk1 = new JLabel("");
		lbltk1.setBounds(91, 206, 218, 14);
		contentPane.add(lbltk1);
		
		 lblemail1 = new JLabel("");
		lblemail1.setBounds(91, 231, 218, 14);
		contentPane.add(lblemail1);
		
		 lbltel1 = new JLabel("");
		lbltel1.setBounds(91, 256, 218, 14);
		contentPane.add(lbltel1);
		
		 lblmob1 = new JLabel("");
		lblmob1.setBounds(91, 280, 218, 14);
		contentPane.add(lblmob1);
		
		 lbldcreated1 = new JLabel("");
			lbldcreated1.setBounds(242, 318, 218, 14);
			contentPane.add(lbldcreated1);
			
			 lbldmodified1 = new JLabel("");
				lbldmodified1.setBounds(10, 318, 218, 14);
				contentPane.add(lbldmodified1);
		
		JButton btnedit = new JButton("ΔΙΟΡΘΩΣΗ ΣΤΟΙΧΕΙΩΝ");
		btnedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewContact newcontact = new NewContact();
				newcontact.setVisible(true);
				newcontact.setForModify(contact);
				newcontact.lblNewLabel.setText("ΕΠΕΞΕΡΓΑΣΙΑ ΕΠΑΦΗΣ");
				
			}
		});
		btnedit.setBounds(0, 343, 188, 35);
		contentPane.add(btnedit);
		
		btnNewButton = new JButton("ΕΠΙΣΤΡΟΦΗ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		
		
		btnNewButton.setBounds(543, 343, 122, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ΜΗ ΕΞΟΦΛΗΜΕΝΑ ΤΙΜΟΛΟΓΙΑ");
		lblNewLabel_1.setBounds(382, 59, 198, 14);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 74, 283, 260);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ΗΜ.ΕΚΔΟΣΗΣ", "ΠΟΣΟ"			}
		));
		scrollPane.setViewportView(table);
		scrollPane.setEnabled(false);
		
		JLabel label = new JLabel("Ημ.Τροποποιησης");
		label.setBounds(14, 305, 142, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Ημ. Δημιουργιας");
		label_1.setBounds(242, 305, 142, 14);
		contentPane.add(label_1);
		
		
		
	}
	
	
	public void PreviewContact(ArrayList<ContactsD> contact) {
		this.contact=contact;
		
		for(int i = 0; i < contact.size(); i++)
        {
			//lblid1.setText(contact.get(i).getId());
			lblcn1.setText(contact.get(i).getCname());
			lbloc1.setText(contact.get(i).getOc());
			lblafm1.setText(contact.get(i).getAfm());
			lbldoy1.setText(contact.get(i).getDoy());
			lblstreet1.setText(contact.get(i).getStreet());
			lbltown1.setText(contact.get(i).getTown());
			lbltk1.setText(contact.get(i).getZip());
			lblemail1.setText(contact.get(i).getEmail());
			lbltel1.setText(contact.get(i).getTel());
			lblmob1.setText(contact.get(i).getMob());
			//lblstatus1.setText(contact.get(i).getStatus());
			lbldcreated1.setText(contact.get(i).getDcreated());
			lbldmodified1.setText(contact.get(i).getDmodified());
			
			id=contact.get(i).getId();
			
			
        }
		
		filltable();		
	}
	
	
	
	public void filltable() {
		
		ArrayList<InvoiceD> invoices = InvoiceD.selectUnpaidInvoices(id);
		
		 DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{"ID","ΗΜ.ΕΚΔΟΣΗΣ", "ΠΟΣΟ"});
		
		for(int i = 0; i < invoices.size(); i++)
        { 
    	 Object[] row = new Object[3];
    	 
    	 
            row[0] = invoices.get(i).getId();
            row[1] = invoices.get(i).getInvoiceDate();
            row[2] = invoices.get(i).getTotal();
            model.addRow(row);
        }
        table.setModel(model);
		
	
	}
}
