package invoice;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Container;

import com.toedter.calendar.JDateChooser;

import contacts.ContactsD;
import contacts.Epafes;
import products.ProductsD;
import products.ProductsG;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class NewInvoice extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public JButton btnclient;
	public ArrayList<ContactsD> contact = null;
	public JComboBox comboBox;
	private JTextField textField_1;
	public ArrayList<ProductsD> product =null;
	public JSpinner txtp;
	public JComboBox comboBox_1;
	private JLabel lbla;
	private JLabel lblat;
	private JSpinner spinner;
	private DefaultTableModel model;
	private JLabel lblfinal;
	private JLabel lblffinal;
	private JLabel label_6;
	private JLabel label_5;
	private JLabel lblNewLabel_4;
	
	public double f24 , f13 , f6;
	public double ff, f;
	
	private Object[] row;
	private Object[][] tablecell=new Object[10][6];
	private int i;
	private Object forlistener="False";
	private JButton btnNewButton_1;
	private JDateChooser dateChooser;
	private JLabel lblar;
	private JFormattedTextField ftxt;
	private JButton btnNewButton_3;
	private InvoiceD invoice;
	private JLabel label_7;
	private JLabel label_9;
	private JLabel label_10;
	
	public NewInvoice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ΝΕΟ ΤΙΜΟΛΟΓΙΟ");
		lblNewLabel.setBackground(new Color(152, 251, 152));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 858, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ΠΕΛΑΤΗΣ*");
		lblNewLabel_1.setBounds(10, 40, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		 btnclient = new JButton("ΕΠΙΛΟΓΗ ΠΕΛΑΤΗ");
		btnclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contactselect();
				
			}
		});
		btnclient.setBounds(95, 36, 272, 23);
		contentPane.add(btnclient);
		
		 dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		Date date = new Date();
		dateChooser.setDate(date);
	
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(454, 40, 159, 20);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2 = new JLabel("ΗΜΕΡ*");
		lblNewLabel_2.setBounds(404, 40, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Α.ΠΑΡΑΣΤ*");
		lblNewLabel_3.setBounds(633, 44, 65, 14);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel label = new JLabel("ΠΡΟΙΟΝΤΑ");
		label.setBounds(0, 65, 75, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Τιτλος");
		label_1.setBounds(10, 101, 46, 14);
		contentPane.add(label_1);
		
		
		NumberFormat format = DecimalFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		
		txtp =new JSpinner();
		txtp.setBounds(394, 117, 80, 20);
		txtp.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(1)));
		contentPane.add(txtp);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"24", "13", "6"}));
		comboBox_1.setBounds(495, 117, 46, 20);
		contentPane.add(comboBox_1);
		
		btnNewButton_1 = new JButton("ΠΡΟΣΘΗΚΗ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(product != null) {
				
					
				addToTable();
				
				}else {
					JOptionPane.showMessageDialog(null, "ΠΑΡΑΚΑΛΩ ΕΠΙΛΕΞΤΕ ΠΡΟΙΟΝ", "InfoBox: " + "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton_1.setBounds(573, 116, 104, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 167, 705, 194);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ΠΡΟΙΟΝ", "ΠΟΣΟΤΗΤΑ", "ΤΙΜΗ", "ΦΠΑ", "ΣΥΝΟΛΟ"
			}
		));
		 model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ΠΡΟΙΟΝ", "ΠΟΣΟΤΗΤΑ", "ΤΙΜΗ", "ΦΠΑ", "ΣΥΝΟΛΟ"});
		scrollPane.setViewportView(table);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(10, 117, 239, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("ΕΠΙΛΟΓΗ ΠΡΟΙΟΝΤΟΣ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				productselect();
			}
		});
		btnNewButton.setBounds(10, 138, 141, 23);
		contentPane.add(btnNewButton);
		
		lblat = new JLabel("Δαθεσ.Αποθ :");
		lblat.setBounds(161, 142, 88, 14);
		lblat.setVisible(false);
		contentPane.add(lblat);
		
		
		 lbla = new JLabel("");
		lbla.setBounds(255, 142, 46, 14);
		lbla.setVisible(false);
		contentPane.add(lbla);
		
		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				
				if (forlistener=="False")
				{
					available_quantity_change();
				}else
				{
					lbla.setText(null);
					forlistener="False";
				}
			}
		});
		spinner.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(1)));
		spinner.setBounds(285, 117, 86, 20);
		contentPane.add(spinner);
		
		JButton btnNewButton_2 = new JButton("ΑΠΟΘΗΚΕΥΣΗ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
				
			}
		});
		btnNewButton_2.setBounds(706, 412, 131, 43);
		contentPane.add(btnNewButton_2);
		
		JLabel label_2 = new JLabel("ΦΠΑ 24 :");
		label_2.setBounds(372, 363, 59, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("ΦΠΑ 13 :");
		label_3.setBounds(372, 388, 59, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel(" ΦΠΑ 6 :");
		label_4.setBounds(372, 412, 59, 14);
		contentPane.add(label_4);
		
		 lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(441, 363, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		 label_5 = new JLabel("");
		label_5.setBounds(441, 388, 46, 14);
		contentPane.add(label_5);
		
		 label_6 = new JLabel("");
		label_6.setBounds(441, 412, 46, 14);
		contentPane.add(label_6);
		
		JLabel lblNewLabel_5 = new JLabel("ΣΥΝ. ΠΛΗΝ ΦΠΑ : ");
		lblNewLabel_5.setBounds(527, 363, 104, 14);
		contentPane.add(lblNewLabel_5);
		
		 lblffinal = new JLabel("");
		lblffinal.setBounds(634, 363, 83, 14);
		contentPane.add(lblffinal);
		
		JLabel label_8 = new JLabel("ΤΕΛΙΚΟ ΣΥΝΟΛΟ : ");
		label_8.setBounds(527, 398, 104, 14);
		contentPane.add(label_8);
		
		 lblfinal = new JLabel("");
		lblfinal.setBounds(644, 398, 73, 14);
		contentPane.add(lblfinal);
		
		JButton button = new JButton("ΔΙΑΓΡΑΦΗ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deleteTableRow();
				
			}

			
		});
		button.setBounds(49, 384, 113, 23);
		contentPane.add(button);
		
		lblar = new JLabel("");
		lblar.setBounds(671, 77, 46, 14);
		contentPane.add(lblar);
		
		ftxt = new JFormattedTextField();
		ftxt.setBounds(708, 40, 75, 20);
		contentPane.add(ftxt);
		
		
		
		
		btnNewButton_3 = new JButton("ΑΚΥΡΩΣΗ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(706, 374, 131, 33);
		contentPane.add(btnNewButton_3);
		
		
		
		invoice = new InvoiceD();
		invoice.last_inserted_id();
		ftxt.setValue(invoice.idToInsert());
		
		label_7 = new JLabel("Ποσοτητα");
		label_7.setBounds(284, 101, 83, 14);
		contentPane.add(label_7);
		
		label_9 = new JLabel("Τιμη");
		label_9.setBounds(394, 101, 56, 14);
		contentPane.add(label_9);
		
		label_10 = new JLabel("ΦΠΑ");
		label_10.setBounds(495, 101, 46, 14);
		contentPane.add(label_10);
	
		
	}
	
	
	public void contactselect() {
		Epafes epafes=new Epafes();
		
		epafes.btnselect.setVisible(true);
		epafes.setVisible(true);
		
		epafes.btnselect.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				int column = 0;
				int row = epafes.table.getSelectedRow();
						
				String value = epafes.table.getModel().getValueAt(row, column).toString();		
				 contact = ContactsD.FindContact(value);
				 btnclient.setText(contact.get(0).getCname());
				 
				epafes.dispose();
				
			}
		});
		
		

	}
	
	
	
	public void productselect() {
		ProductsG productsg = new ProductsG();
		
		productsg.btnok.setVisible(true);
		//productsg.btnok.setEnabled(false);
		productsg.setVisible(true);
		
     productsg.btnok.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				int column = 0;
				int row = productsg.table.getSelectedRow();
						
				String value = productsg.table.getModel().getValueAt(row, column).toString();		
				 product = ProductsD.FindProductsD(value);
				 
				 if(product.get(0).getSales()) {
				 textField_1.setText(product.get(0).getName());
				 txtp.setValue(product.get(0).getPrice());
				 
				 if(product.get(0).getTax()==24) {
					 comboBox_1.setSelectedIndex(0);
				 }else if(product.get(0).getTax()==13) {
					 comboBox_1.setSelectedIndex(1);
				 }else {
					 comboBox_1.setSelectedIndex(2);
				 }
				 
				 if(product.get(0).getStock()) {
					 lblat.setVisible(true);
					 lbla.setVisible(true);
					 
				ProductsD.showInventory(product.get(0).getId());
				
					double t = product.get(0).getTotal();
					double y = (double) spinner.getValue();
					
					lbla.setText(String.valueOf(t-y));
					 
				 }
				 
				productsg.dispose();
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "ΤΟ ΠΡΟΙΟΝ ΕΙΝΑΙ ΑΝΕΝΕΡΓΟ ΔΕΝ ΜΠΟΡΕΙ ΝΑ ΓΙΝΕΙ ΧΡΗΣΗ ΤΟΥ", "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
				 }
			}
		});
		
	
	}//telos product select
	
	public void available_quantity_change() {
		
		//allazei tin diathesimh posothta sto label
		double t = product.get(0).getTotal();
		double y = (double)spinner.getValue();
		
		
		lbla.setText(String.valueOf(t-y));
				
			
		
	}
	
public void quantity_change() {
		
		//allazei tin diathesimh posothta sthn vash afou patithei to add
	
		double t = product.get(0).getTotal();
		double y = (double) tablecell[i][1];
		
		double u = t-y;
			
			ProductsD.updateInventory(u, product.get(0).getId());
			
			
		
	}
	
	
	
	public void addToTable() {
		
		double y = (double) spinner.getValue();
		double p = (Double) txtp.getValue();
		
		
		//APOTHIKEYSH SE ENAN PINAKA
		row = new Object[6];
		  i = table.getRowCount();
	        
		  System.out.println(i);
	           // row[0] = product.get(i).getId();
	            row[0] = textField_1.getText(); //proion
	            row[1] = spinner.getValue();
	            row[2] = txtp.getValue();
	            row[3] = comboBox_1.getSelectedItem();
	            row[4] = y*p;
	           
	           
	            model.addRow(row);
	            table.setModel(model);
	        
	            row[5] =  product.get(0).getId();
	            
	          
	            tablecell[i]=row;

	            updateFinalPrice();
	            
	            if(product.get(0).getStock()) {
	           quantity_change();
	            }
	            
	            setnull();
	            
	         
		
	}
	
	public void setnull() {
		
	    textField_1.setText(null);
	    forlistener = "True";
        spinner.setValue(1.0);
        txtp.setValue(1.0);
        comboBox_1.setSelectedItem(0);
        lbla.setText(null);
        product =null;
	}
	
	public void updateFinalPrice(){
		
		double q= (double)row[1];
		double w =(double) row[2];
		double e =(double)row[4];   //meriko sunolo
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		if(row[3].toString()== "24" ) {
			
			f24+= Double.valueOf(df.format( (q*w) * 24.0f / 100));
			
			lblNewLabel_4.setText(Double.toString(f24));
			
		}else if(row[3].toString()== "13") {
			
			f13+=  Double.valueOf(df.format((q*w) * 13.0f / 100));
			label_5.setText(Double.toString(f13));
			
		}else {
			f6+= Double.valueOf(df.format((q*w) * 6.0f / 100));
			label_6.setText(Double.toString(f6));
		}
		
		ff+= Double.valueOf(df.format(e)); //meriko

		f= Double.valueOf(df.format(ff+f24+f13+f6));
		
		lblffinal.setText(Double.toString(ff));
		lblfinal.setText(Double.toString(f));
		
		
		
	} 
	
	
	public void deleteTableRow(){
		
		int selectedrow = table.getSelectedRow();
		int gid = (int) tablecell[selectedrow][5];
		int ggid =(int) tablecell[selectedrow][5];
		model.removeRow(selectedrow);
		
		//diagrafh apo telika apotelesmata
		
		updateFinalPriceChange(selectedrow);
		
			
		//allagh apothikis ean uparxei
		 product = ProductsD.FindProductsD( Integer.toString(gid)); 
		 if(product.get(0).getStock()) {
			 
			 double tl =product.get(0).getTotal();
			 double throwback = (double) tablecell[selectedrow][1];
			 double total = tl+throwback;
			 
			 addStock(ggid,total);
			 
		 }
		 
		 
		 //diafrafh apo pinaka tablecell
		 
		 removeArrayRow(selectedrow);
		
		System.out.println(tablecell[selectedrow][0]);
	
		
		
	}
	
	
	
	
	
	
	
    public void updateFinalPriceChange(int selectedrow){
		
		double q= (double)tablecell[selectedrow][1];
		double w =(double) tablecell[selectedrow][2];
		double e =(double)tablecell[selectedrow][4];   //meriko sunolo
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		if(tablecell[selectedrow][3].toString()== "24" ) {
			
			f24-= Double.valueOf(df.format( (q*w) * 24.0f / 100));
			
			lblNewLabel_4.setText(Double.toString(f24));
			
		}else if(tablecell[selectedrow][3].toString()== "13") {
			
			f13-=  Double.valueOf(df.format((q*w) * 13.0f / 100));
			label_5.setText(Double.toString(f13));
			
		}else {
			f6-= Double.valueOf(df.format((q*w) * 6.0f / 100));
			label_6.setText(Double.toString(f6));
		}
		
		ff-= Double.valueOf(df.format(e)); //meriko
	//	DecimalFormat df = new DecimalFormat("#.##");
		
		f= Double.valueOf(df.format(ff+f24+f13+f6));
		
		lblffinal.setText(Double.toString(ff));
		lblfinal.setText(Double.toString(f));
		
		
		
	} 
    
    
    
    
    
    
    
    public void addStock(int ggid,double total) {
    	
    	ProductsD.updateInventory(total, ggid);
    	
    	
    }
	
    
   public void removeArrayRow(int selectedrow) {
	   Object[][] tablecell2=new Object[tablecell.length-1][6];
	   
	   System.arraycopy(tablecell, 0, tablecell2, 0, selectedrow);
	   
	   
	   System.arraycopy(tablecell, selectedrow+1, tablecell2, selectedrow, tablecell.length-selectedrow-1); 
	   
  
	   tablecell=tablecell2;
	    
   }
   
   public void check() {
	   
	   if(contact != null) {
		   
		   invoice.addInvoiceToDB(contact.get(0).getId(), dateChooser.getDate(),f);
		   
		   invoice.addProductsToDB(tablecell);
		   
		   ArrayList<InvoiceD> inv = InvoiceD.SelectInvoice(ftxt.getText());
	        
			InvoicePG invoicepg = new InvoicePG();
			invoicepg.PreviewInvoice(inv);
		    invoicepg.generateInvoice();
		    dispose();
		   
		   
	   }else {
		   JOptionPane.showMessageDialog(null, "ΠΑΡΑΚΑΛΩ ΕΠΙΛΕΞΤΕ ΠΕΛΑΤΗ", "InfoBox: " + "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
		   
	   }
	   
   }
   
   
	

		} //end class

