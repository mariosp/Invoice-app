package invoice;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import contacts.ContactPG;
import contacts.ContactsD;
import products.NewProductG;
import products.ProductsD;
import java.awt.Font;
import java.awt.Color;

public class InvoiceG extends JFrame {

	
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton_2;

	public InvoiceG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ΤΙΜΟΛΟΓΙΑ");
		lblNewLabel.setBackground(new Color(152, 251, 152));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 665, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnnewinvoice = new JButton("ΝΕΟ ΤΙΜΟΛΟΓΙΟ");
		btnnewinvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewInvoice newinvoice = new NewInvoice();
					newinvoice.setVisible(true);
			}
		});
		btnnewinvoice.setBounds(10, 54, 153, 33);
		contentPane.add(btnnewinvoice);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textField.getText().length() >= 0)
				{
				try {
					
					
					findInvoice();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else {
					table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "ΕΠΩΝΥΜΙΑ", "ΑΦΜ", "ΗΜ.ΕΚΔΟΣΗΣ", "ΠΟΣΟ", "ΠΛΗΡΩΜΕΝΟ"
			}
		));
					
				}
			}
		});
		
	
		
		textField.setBounds(207, 54, 256, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 113, 665, 229);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ΕΠΩΝΥΜΙΑ", "ΑΦΜ", "ΗΜ.ΕΚΔΟΣΗΣ", "ΠΟΣΟ", "ΚΑΤΑΣΤΑΣΗ"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("ΕΜΦΑΝΙΣΗ ΤΙΜΟΛΟΓΙΟΥ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				openInvoice();
				
				
			}
		});
		
		btnNewButton.setBounds(0, 346, 190, 34);
		contentPane.add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("ΕΠΙΣΤΡΟΦΗ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			dispose();
			}
		});
		btnNewButton_1.setBounds(548, 347, 117, 33);
		contentPane.add(btnNewButton_1);
		
		 btnNewButton_2 = new JButton("ΠΛΗΡΩΜΗ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				payment();
				
			}
		});
		btnNewButton_2.setBounds(200, 346, 117, 34);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent e) {
			  //enable button - put it in an EDT to be safe though
				 ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				 if (lsm.isSelectionEmpty()) 
				 {
					 btnNewButton.setEnabled(false);
					 btnNewButton_2.setEnabled(false);
			        }
				 else 
				 {
			        	btnNewButton.setEnabled(true);
			    		int row = table.getSelectedRow();
			    		String value = table.getModel().getValueAt(row, 5).toString();
			        	
			    		if(value=="true") {
			        	 
			        	 btnNewButton_2.setEnabled(true);
			        	 btnNewButton_2.setText("ΠΛΗΡΩΜΕΝΟ");
			        }else {
			        	 btnNewButton_2.setEnabled(true);
			        	 btnNewButton_2.setText("ΠΛΗΡΩΜΗ");
			       }
			 }
			 }
			});
		
		
		fillTable();
	}
	
	
	
	public void fillTable() {
	 
		ArrayList<InvoiceD> invoices = InvoiceD.ListInvoices();
		
		 DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{"ID", "ΕΠΩΝΥΜΙΑ", "ΑΦΜ", "ΗΜ.ΕΚΔΟΣΗΣ", "ΠΟΣΟ", "ΠΛΗΡΩΜΕΝΟ"});
	    // Object[] row = new Object[6];
	     
	     for(int i = 0; i < invoices.size(); i++)
	        { 
	    	 Object[] row = new Object[6];
	    	 ArrayList<ContactsD> contact = ContactsD.FindContact(Integer.toString(invoices.get(i).getClientId()));
	    	 
	            row[0] = invoices.get(i).getId();
	            row[1] = contact.get(0).getCname();
	            row[2] = contact.get(0).getAfm();
	            row[3] = invoices.get(i).getInvoiceDate();
	            row[4] = invoices.get(i).getTotal();
	            
	            if(invoices.get(i).getPaidStatus()) {
	            	row[5]="ΝΑΙ";
	            }else {
	            	row[5]="OXI";
	            	
	            }
	            	
	            	
	            	
	            	
	            	
	            //row[5] = invoices.get(i).getPaidStatus();
	            model.addRow(row);
	        }
	        table.setModel(model);
 }
	
	
	public void findInvoice() throws SQLException{
		
		ArrayList<InvoiceD> invoices = InvoiceD.FindInvoice(textField.getText());
		
		 DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{"ID", "ΕΠΩΝΥΜΙΑ", "ΑΦΜ", "ΗΜ.ΕΚΔΟΣΗΣ", "ΠΟΣΟ", "ΠΛΗΡΩΜΕΝΟ"});
	     Object[] row = new Object[6];
	     
	     for(int i = 0; i < invoices.size(); i++)
	        {
	    	 ArrayList<ContactsD> contact = ContactsD.FindContact(Integer.toString(invoices.get(i).getClientId()));
	    	 
	            row[0] = invoices.get(i).getId();
	            row[1] = contact.get(0).getCname();
	            row[2] = contact.get(0).getAfm();
	            row[3] = invoices.get(i).getInvoiceDate();
	            row[4] = invoices.get(i).getTotal();
	            row[5] = invoices.get(i).getPaidStatus();
	            model.addRow(row);
	        }
	        table.setModel(model);
		
	        
		
	}

	
	
	public void openInvoice() {
		
		int column = 0;
		int row = table.getSelectedRow();
		String value = table.getModel().getValueAt(row, column).toString();
		
		//System.out.print(value);
		ArrayList<InvoiceD> invoice = InvoiceD.SelectInvoice(value);
        
		InvoicePG invoicepg = new InvoicePG();
		invoicepg.setVisible(true);
		invoicepg.PreviewInvoice(invoice);
		
		
		
	}
	
	
	
	public void payment() {
		
		int column = 0;
		int row = table.getSelectedRow();
		String value = table.getModel().getValueAt(row, column).toString();
		
		String value1 = table.getModel().getValueAt(row, 5).toString();
		
		if(value1=="OXI") {
			InvoiceD.changePayment(value,"Y");
			
			fillTable();
			
		}else {
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Θελετε να αλλαξει σε μη πληρωμενο?","Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				InvoiceD.changePayment(value,"N");
				
				fillTable();
				
				
			}
			
		}
			
		
		
	}
	
}//end InvoiceG
