package reports;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import contacts.ContactsD;
import invoice.InvoiceD;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class ReportsG extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panelcontacts;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JTable table;
	private Date from;
	private Date to;
	private JLabel lblNewLabel_1;
	private JLabel label_3;
	private JPanel panelsales;
	private JLabel lblNewLabel_2;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel lblNewLabel_10;
	private JPanel panelprod;
	private JTable table1;
	private JTable table2;
	private DecimalFormat df = new DecimalFormat("####0.00");
	
	
	public ReportsG() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("ΕΠΑΦΗΣ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel.setText("ΑΝΑΦΟΡΕΣ ΕΠΑΦΩΝ");
				
				panelsales.setVisible(false);
				
				panelprod.setVisible(false);
				
				
				
				panelcontacts.setVisible(true);
			}
		});
		btnNewButton.setBounds(0, 59, 146, 53);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("ΠΩΛΗΣΕΩΝ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel.setText("ΑΝΑΦΟΡΕΣ ΠΩΛΗΣΕΩΝ");
				
				
				panelcontacts.setVisible(false);
				
				panelprod.setVisible(false);
				
				
				panelsales.setVisible(true);
			}
		});
		button.setBounds(0, 113, 146, 53);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ΠΡΟΙΟΝΤΩΝ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel.setText("ΑΝΑΦΟΡΕΣ ΠΡΟΙΟΝΤΩΝ");
				
				
				panelsales.setVisible(false);
				
				
				panelcontacts.setVisible(false);
				
				
				panelprod.setVisible(true);
				
			}
		});
		button_1.setBounds(0, 166, 146, 53);
		contentPane.add(button_1);
		
		 lblNewLabel = new JLabel("ΑΝΑΦΟΡΕΣ");
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 870, 59);
		contentPane.add(lblNewLabel);
		
		
		panel3();
		panel2();
		panel1();
		panelcontacts.setVisible(false);
		panelprod.setVisible(false);
		panelsales.setVisible(false);
		
		
		lblNewLabel.setText("ΑΝΑΦΟΡΕΣ");
	}
	
	
	
	public void panel1() {
		
		 
		
		 panelcontacts = new JPanel();
			panelcontacts.setBounds(145, 59, 725, 397);
			contentPane.add(panelcontacts);
			panelcontacts.setLayout(null);
			
			JLabel label = new JLabel("Τυπος Αναφορας");
			label.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label.setBounds(10, 11, 137, 14);
			panelcontacts.add(label);
			
			JLabel label_1 = new JLabel("Απο");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_1.setBounds(243, 11, 137, 14);
			panelcontacts.add(label_1);
			
			JLabel label_2 = new JLabel("Εως");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_2.setBounds(456, 11, 137, 14);
			panelcontacts.add(label_2);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"ΥΠΟΛΟΙΠΟ ΕΠΑΦΩΝ", "ΜΕΓΑΛΥΤΕΡΟ ΠΟΣΟ"}));
			comboBox.setBounds(10, 36, 205, 20);
			panelcontacts.add(comboBox);
			
			 Date date = new Date();
			 dateChooser = new JDateChooser();
			 dateChooser.setBounds(243, 36, 159, 20);
			 panelcontacts.add(dateChooser);
			 dateChooser.setDateFormatString("dd/MM/yyyy");
			 dateChooser.setDate(date);
			 
			 dateChooser_1 = new JDateChooser();
			 dateChooser_1.setDateFormatString("dd/MM/yyyy");
			 dateChooser_1.setBounds(452, 36, 159, 20);
			 dateChooser_1.setDate(date);
			 panelcontacts.add(dateChooser_1);
			 
			 JScrollPane scrollPane = new JScrollPane();
			 scrollPane.setEnabled(true);
			 scrollPane.setBounds(10, 84, 705, 260);
			 panelcontacts.add(scrollPane);
			 
			 table1 = new JTable();
				table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table1.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ID ΠΕΛΑΤΗ", "ΕΠΩΝΥΜΙΑ", "ΑΝΕΞΟΦΛΗΤΑ ΤΙΜΟΛΟΓΙΑ", "ΣΥΝ.ΠΟΣΟ"			}
				));
				scrollPane.setViewportView(table1);
				
				
				JButton btnNewButton_1 = new JButton("ΑΝΑΖΗΤΗΣΗ");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 from=  dateChooser.getDate();
						   
						 to=  dateChooser_1.getDate();
						if(comboBox.getSelectedIndex()==0) { //YPOLOIPO EPAFWN
							
						      contactsPaymentDue();
						}
						
						if(comboBox.getSelectedIndex()==1) { //MEGALYTERO POSO
							
						      contactsBiggerAmount();
						
						}
						
						
						
					}
				});
				btnNewButton_1.setBounds(621, 35, 94, 23);
				panelcontacts.add(btnNewButton_1);
				
				lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setBounds(400, 350, 94, 14);
				panelcontacts.add(lblNewLabel_1);
				
				label_3 = new JLabel("");
				label_3.setBounds(553, 350, 94, 14);
				panelcontacts.add(label_3);
				
				
	
				
		
		
		
	}

	
	
	public void panel2() {
		
		 
	    	
		    panelsales = new JPanel();
			panelsales.setBounds(145, 59, 725, 397);
			contentPane.add(panelsales);
			panelsales.setLayout(null);
			
			JLabel label = new JLabel("Τυπος Αναφορας");
			label.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label.setBounds(10, 11, 137, 14);
			panelsales.add(label);
			
			JLabel label_1 = new JLabel("Απο");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_1.setBounds(243, 11, 137, 14);
			panelsales.add(label_1);
			
			JLabel label_2 = new JLabel("Εως");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_2.setBounds(456, 11, 137, 14);
			panelsales.add(label_2);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"ΠΑΡΑΣΤΑΤΙΚA ΠΩΛΗΣΕΩΝ"}));
			comboBox.setBounds(10, 36, 205, 20);
			panelsales.add(comboBox);
			
			 Date date = new Date();
			 dateChooser = new JDateChooser();
			 dateChooser.setBounds(243, 36, 159, 20);
			 panelsales.add(dateChooser);
			 dateChooser.setDateFormatString("dd/MM/yyyy");
			 dateChooser.setDate(date);
			 
			 dateChooser_1 = new JDateChooser();
			 dateChooser_1.setDateFormatString("dd/MM/yyyy");
			 dateChooser_1.setBounds(452, 36, 159, 20);
			 dateChooser_1.setDate(date);
			 panelsales.add(dateChooser_1);
			 
			 JScrollPane scrollPane = new JScrollPane();
			 scrollPane.setEnabled(false);
			 scrollPane.setBounds(10, 84, 705, 260);
			 panelsales.add(scrollPane);
			 
			 table2 = new JTable();
				table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table2.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ID TIM","ΦΠΑ 6","ΦΠΑ 13","ΦΠΑ 24", "ΚΑΘΑΡΗ ΑΞΙΑ", "ΤΕΛΙΚΗ ΑΞΙΑ"			}
				));
				scrollPane.setViewportView(table2);
				
				JButton btnNewButton_2 = new JButton("ΑΝΑΖΗΤΗΣΗ");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 from=  dateChooser.getDate();
						   
						 to=  dateChooser_1.getDate();
						if(comboBox.getSelectedIndex()==0) {
						
							salePP();
						     
						}
						
						
						
					}
				});
				btnNewButton_2.setBounds(621, 35, 94, 23);
				panelsales.add(btnNewButton_2);
				
				
				
				
			
				
				 lblNewLabel_2 = new JLabel("");
				  lblNewLabel_2.setBounds(10, 350, 113, 14);
				  panelsales.add(lblNewLabel_2);
				  
				   label_4 = new JLabel("");
				  label_4.setBounds(133, 350, 113, 14);
				  panelsales.add(label_4);
				  
				   label_5 = new JLabel("");
				  label_5.setBounds(243, 350, 113, 14);
				  panelsales.add(label_5);
				  
				   label_6 = new JLabel("");
				  label_6.setBounds(366, 350, 113, 14);
				  panelsales.add(label_6);
				  
				  lblNewLabel_10 = new JLabel("");
					lblNewLabel_10.setBounds(499, 350, 94, 14);
					panelsales.add(lblNewLabel_10);
					
				   label_7 = new JLabel("");
				  label_7.setBounds(603, 350, 113, 14);
				  panelsales.add(label_7);
	
				
		
		
		
	}
	
	
	public void panel3() {
		
		 
    	
	    panelprod = new JPanel();
	    panelprod.setBounds(145, 59, 725, 397);
		contentPane.add(panelprod);
		panelprod.setLayout(null);
		
		JLabel label = new JLabel("Τυπος Αναφορας");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(10, 11, 137, 14);
		panelprod.add(label);
		
		JLabel label_1 = new JLabel("Απο");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(243, 11, 137, 14);
		panelprod.add(label_1);
		
		JLabel label_2 = new JLabel("Εως");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(456, 11, 137, 14);
		panelprod.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ΠΩΛΗΣΕΙΣ ΠΡΟΙΟΝΤΩΝ"}));
		comboBox.setBounds(10, 36, 205, 20);
		panelprod.add(comboBox);
		
		 Date date = new Date();
		 dateChooser = new JDateChooser();
		 dateChooser.setBounds(243, 36, 159, 20);
		 panelprod.add(dateChooser);
		 dateChooser.setDateFormatString("dd/MM/yyyy");
		 dateChooser.setDate(date);		 
		 dateChooser_1 = new JDateChooser();
		 dateChooser_1.setDateFormatString("dd/MM/yyyy");
		 dateChooser_1.setBounds(452, 36, 159, 20);
		 dateChooser_1.setDate(date);
		 panelprod.add(dateChooser_1);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setEnabled(false);
		 scrollPane.setBounds(10, 84, 705, 260);
		 panelprod.add(scrollPane);
		 
		 table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID ΠΡΟΙΟΝΤΟΣ","ΠΡΟΙΟΝ","ΠΟΣΟΤΗΤΑ ΠΩΛΗΣΕΩΝ","ΜΕΣΗ ΤΙΜΗ","ΣΥΝΟΛΟ ΠΩΛΗΣΕΩΝ"			}
			));
			scrollPane.setViewportView(table);
			
			JButton btnNewButton_2 = new JButton("ΑΝΑΖΗΤΗΣΗ");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 from=  dateChooser.getDate();
					   
					 to=  dateChooser_1.getDate();
					if(comboBox.getSelectedIndex()==0) { 
						
					     prodSS();
					     
					}
				}
			});
			btnNewButton_2.setBounds(621, 35, 94, 23);
			panelprod.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("ΕΠΙΣΤΡΟΦΗ");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				dispose();
				}
			});
			btnNewButton_3.setBounds(0, 401, 146, 55);
			contentPane.add(btnNewButton_3);
			
			
			
	
	
	
}
	
	
	
	
	 public void contactsPaymentDue() {
		 
		 DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{"ID ΠΕΛΑΤΗ", "ΕΠΩΝΥΜΙΑ", "ΑΝΕΞΟΦΛΗΤΑ ΤΙΜΟΛΟΓΙΑ", "ΣΥΝ.ΠΟΣΟ"	});
		 
		 
		 //InvoiceD in =new InvoiceD();
		 ArrayList<InvoiceD> invoices = InvoiceD.contactsBA(from, to);
		 
         
	    int t=0;
        int o=0;
         
         for(int y = 0; y < invoices.size(); y++) {
         	 
        	 System.out.println(invoices.size());
         	 Object[] row = new Object[4];
        	 ArrayList<ContactsD> contact = ContactsD.FindContact(Integer.toString(invoices.get(y).getClientId()));
        	 int g = 0;
        	 double h=0;
        	
        	 
		for(int i = 0; i < invoices.size(); i++)
	        {
	    	    // ArrayList<ContactsD> contact = ContactsD.FindContact(Integer.toString(invoices.get(i).getClientId()));
	    	 	if(contact.get(0).getId()==invoices.get(i).getClientId())
	    	 	{
	            
	              g++;
	            
	              h+= invoices.get(i).getTotal();
	           
	              invoices.remove(i);
	              
	              i=-1;
	             
	    	    }
	    	
	    	 	
	        }
	     
	     row[0] = contact.get(0).getId();
         row[1] = contact.get(0).getCname();
         row[2] = g;
         row[3] =h;
	     t+=g;
	     o+=h;
        
	     model.addRow(row);
	        y=-1;
		 
         }
         table1.setModel(model);
         lblNewLabel_1.setText(Integer.toString(t));
         label_3.setText(Integer.toString(o));
         
	 }



	public void contactsBiggerAmount() {
		
		System.out.println("te");
		
		JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setEnabled(true);
		 scrollPane.setBounds(10, 84, 705, 260);
		 panelcontacts.add(scrollPane);
		
		table1=new JTable();
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID ΠΕΛΑΤΗ", "ΕΠΩΝΥΜΙΑ", "ΑΡ.ΤΙΜΟΛΟΓΙΩΝ", "ΣΥΝ.ΠΟΣΟ"			}
		));
		scrollPane.setViewportView(table1);
		DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{"ID ΠΕΛΑΤΗ", "ΕΠΩΝΥΜΙΑ", "ΑΡ.ΤΙΜΟΛΟΓΙΩΝ", "ΣΥΝ.ΠΟΣΟ"	});
		 
	    
		 
		 ArrayList<InvoiceD> invoices = InvoiceD.contactsPaymDue(from, to);
		 
         
	     int t=0;
         int o=0;
         
         for(int y = 0; y < invoices.size(); y++) {
         	 
        	
         	 Object[] row = new Object[4];
        	 ArrayList<ContactsD> contact = ContactsD.FindContact(Integer.toString(invoices.get(y).getClientId()));
        	 int g = 0;
        	 double h=0;
        	
        	 
		for(int i = 0; i < invoices.size(); i++)
	        {
	    	    
	    	 	if(contact.get(0).getId()==invoices.get(i).getClientId())
	    	 	{
	            
	              g++;
	            
	              h+= invoices.get(i).getTotal();
	           
	              invoices.remove(i);
	              
	              i=-1;
	             
	    	    }
	    	
	        }
	     
	     row[0] = contact.get(0).getId();
         row[1] = contact.get(0).getCname();
         row[2] = g;
         row[3] =h;
	     t+=g;
	     o+=h;
        
	     model.addRow(row);
	        y=-1;
		 
         }
         table1.setModel(model);
         TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table1.getModel());
         table1.setRowSorter(sorter);
         ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
          
         int columnIndexToSort = 3;
         sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
          
         sorter.setSortKeys(sortKeys);
         sorter.sort();
         
         table1.setModel(model);
         
		
		
	}
	
	
	
	
	public void salePP() {
		
		DecimalFormat df = new DecimalFormat("#.##");
		 DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{	"ID TIM","ΦΠΑ 6","ΦΠΑ 13","ΦΠΑ 24", "ΚΑΘΑΡΗ ΑΞΙΑ", "ΤΕΛΙΚΗ ΑΞΙΑ"});
	     
	     
	     
	      ArrayList<InvoiceD> invoices = InvoiceD.contactsPaymDue(from, to);
		 
         
	     double fp6=0,fp13=0,fp24=0,fff=0,f1=0;
       
         
         for(int y = 0; y < invoices.size(); y++) {
         	 
        	
         	 Object[] row = new Object[6];
        	 ArrayList<InvoiceD> items = InvoiceD.InvoiceItems(Integer.toString(invoices.get(y).getId()));
        	
        	 
        	 double f6 = 0,f13=0,f24=0;
        	 double ff=0,f=0;
        	 
        	 
        	 
        	 for(int i = 0; i < items.size(); i++)
 	        {
        		 
        		 double q,w;
        		 
        		 q=items.get(i).getpQuantity();
        		 w=items.get(i).getpPrice();
        		 
        		 if(items.get(i).getpTax()== 24 ) {
        				
        				f24+= Double.valueOf(df.format( (q*w) * 24.0f / 100));
        				
        				
        				
        			}else if(items.get(i).getpTax()== 13) {
        				
        				f13+=  Double.valueOf(df.format((q*w) * 13.0f / 100));
        			
        				
        			}else {
        				f6+= Double.valueOf(df.format((q*w) * 6.0f / 100));
        				
        			}
        			
        			ff+= Double.valueOf(df.format(q*w)); //meriko

        			
 	             
 	    	    
 	    	 	
 	    	 	
 	    	 	
 	        }
        	 
        	 f= Double.valueOf(invoices.get(y).getTotal());
 	     
 	      row[0] = invoices.get(y).getId();
          row[1] = f6;
          row[2] = f13;
          row[3] =f24;
          row[4] =ff;
          row[5] =f;
          
 	     fp6+=f6;
 	     fp13+=f13;
 	     fp24+=f24;
 	     fff+=ff;
 	     f1+=f;
         
 	     model.addRow(row);
 	        
          }
          table2.setModel(model);
        	
          lblNewLabel_2.setText("ΑΡ.ΤΙΜ: "+ invoices.size());
          label_4.setText("ΣΥΝ. :"+fp6);
          label_5.setText("ΣΥΝ. :"+fp13);
          label_6.setText("ΣΥΝ. :"+fp24);
          lblNewLabel_10.setText("ΣΥΝ. :"+fff);
          label_7.setText("ΣΥΝ. :"+f1);
		
		
	}
	
	
	public void prodSS() {
		
		 DefaultTableModel model = new DefaultTableModel();
	     model.setColumnIdentifiers(new Object[]{"ID ΠΡΟΙΟΝΤΟΣ","ΠΡΟΙΟΝ","ΠΟΣΟΤΗΤΑ ΠΩΛΗΣΕΩΝ","ΜΕΣΗ ΤΙΜΗ","ΚΑΘΑΡΗ ΑΞΙΑ","ΣΥΝΟΛΟ ΤΕΛΙΚΟ"	});
		
	     int g = 0;
	     double q=0,p=0;
	     
	     ArrayList<InvoiceD> items = InvoiceD.prodSS(from, to);
		
	     	for(int y = 0; y < items.size(); y++) {
         	 
        	 
         	 Object[] row = new Object[6];
         	 
         	 double mtimh=0;
        	 double it=0;
        	 double h=0;
        	 double tax=0;
        	 
        	 for(int i = 1; i < items.size(); i++)
 	         {
 	    	 	if(items.get(0).getpId()==items.get(i).getpId())
 	    	 	{
 	    	 		
 	                 g++;
 	              
 	              it+= items.get(i).getpQuantity();
 	              mtimh+=items.get(i).getpPrice();
 	              h++;
 	              
 	              items.remove(i);
 	              i=0;
 	             
 	    	    }
 	    	
 	    	 	
 	        }
        	 tax=items.get(0).getpTax();
 	     it+=items.get(0).getpQuantity();
        double mmtimh=(mtimh+items.get(0).getpPrice())/(h+1); 
        	 
        	 row[0] = items.get(0).getpId();
        	 row[1]= items.get(0).getpName();
        	 row[2] = it;
        	 row[3] = mmtimh;
        	 row[4] =it*mmtimh;
        	 row[5] =df.format(( it*mmtimh* tax)/( 100 ) + (it*mmtimh));
        	 items.remove(0);
 	         model.addRow(row);
 	         y=-1;
 		 
          }
          table.setModel(model);
		
		
		
		
		
		
		
	}
	}//telos
