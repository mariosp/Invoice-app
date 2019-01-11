package invoice;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import contacts.ContactsD;
import settings.Email;
import settings.GenerateInvoice;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.Sides;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.print.*;
import java.awt.Color;
import java.awt.Font;

public class InvoicePG extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<InvoiceD> invoice;
	private ArrayList<ContactsD> contact;
	private JLabel lblNewLabel_2;
	private JLabel label_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private ArrayList<InvoiceD> invoiceitems;

	double f6,f13,f24,ff,f;
	private JLabel label_9;
	private JLabel label_8;
	private JLabel label_7;
	private JLabel label_11;
	private JLabel label_12;
	
	
	public InvoicePG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ΤΙΜΟΛΟΓΙΟ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(152, 251, 152));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 673, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ΕΠΩΝΥΜΙΑ :");
		lblNewLabel_1.setBounds(10, 40, 85, 14);
		contentPane.add(lblNewLabel_1);
		
		 lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(90, 40, 188, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("ΑΦΜ :");
		label.setBounds(300, 40, 68, 14);
		contentPane.add(label);
		
		 label_1 = new JLabel("");
		label_1.setBounds(378, 40, 108, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("ΗΜ.ΕΚΔΟΣΗΣ :");
		label_2.setBounds(12, 65, 83, 14);
		contentPane.add(label_2);
		
		 lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(100, 65, 122, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel label_3 = new JLabel("ΑΡ.ΠΑΡΑΣΤΑΤΙΚΟΥ :");
		label_3.setBounds(302, 65, 108, 14);
		contentPane.add(label_3);
		
		 lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(420, 65, 108, 14);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 108, 673, 194);
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
		
		JLabel label_4 = new JLabel("ΦΠΑ 24 :");
		label_4.setBounds(317, 307, 68, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("ΦΠΑ 13 :");
		label_5.setBounds(317, 332, 68, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel(" ΦΠΑ 6 :");
		label_6.setBounds(317, 356, 68, 14);
		contentPane.add(label_6);
		
		label_7 = new JLabel("");
		label_7.setBounds(408, 356, 58, 14);
		contentPane.add(label_7);
		
		label_8 = new JLabel("");
		label_8.setBounds(408, 332, 65, 14);
		contentPane.add(label_8);
		
		label_9 = new JLabel("");
		label_9.setBounds(405, 307, 68, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("ΣΥΝ. ΠΛΗΝ ΦΠΑ : ");
		label_10.setBounds(474, 313, 108, 14);
		contentPane.add(label_10);
		
		label_11 = new JLabel("");
		label_11.setBounds(579, 315, 92, 14);
		contentPane.add(label_11);
		

		label_12 = new JLabel("");
		label_12.setBounds(576, 347, 88, 14);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("ΤΕΛΙΚΟ ΣΥΝΟΛΟ : ");
		label_13.setBounds(474, 348, 199, 14);
		contentPane.add(label_13);
		
		JButton btnNewButton = new JButton("ΑΠΟΘΗΚΕΥΣΗ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				savePdftoFile();
			}	
		});
		btnNewButton.setBounds(6, 313, 116, 57);
		contentPane.add(btnNewButton);
		
		
		JButton button = new JButton("ΑΠΟΣΤΟΛΗ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InvoiceD invo = new InvoiceD();
				invo.getPdfFromDb(invoice.get(0).getId());
				
				if(contact.get(0).getEmail()!="") {
				Email mail =new Email(contact.get(0).getEmail(),"ΤΙΜΟΛΟΓΙΟ" ,"ΤΙΜΟΛΟΓΙΟ ΜΕ ΑΡΙΘΜΟ ΠΑΡΑΣΤΑΤΙΚΟΥ "+invo.getId(),invo.getPdf());
				
				button.setText("ΣΤΑΛΘΗΚΕ");
				button.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(null, "ΔΕΝ ΥΠΑΡΧΕΙ ΔΙΕΥΘΥΝΣΗ EMAIL ΣΤΗΝ ΕΠΑΦΗ", "InfoBox: " + "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		button.setBounds(132, 313, 116, 57);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ΑΝΟΙΓΜΑ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				opedPdf();
			}
		});
		button_1.setBounds(9, 375, 116, 48);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("ΕΠΙΣΤΡΟΦΗ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setBounds(556, 390, 117, 33);
		contentPane.add(button_2);
		
	}
	
	
	public void PreviewInvoice(ArrayList<InvoiceD> invoice) {
		
		double q,w,e,t;
		this.invoice=invoice;
		
		int value = this.invoice.get(0).getClientId();
		
		contact = ContactsD.FindContact(Integer.toString(value));
		
		lblNewLabel_2.setText(contact.get(0).getCname());
		
		label_1.setText(contact.get(0).getAfm());
		
		lblNewLabel_3.setText(invoice.get(0).getInvoiceDate());
		
		lblNewLabel_4.setText(Integer.toString(invoice.get(0).getId()));
		
		invoiceitems =InvoiceD.InvoiceItems(Integer.toString(invoice.get(0).getId()));
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		Object[] row = new Object[5];
		for(int i = 0; i < invoiceitems.size(); i++)
        {
			    row[0] = invoiceitems.get(i).getpName();
	            row[1] = invoiceitems.get(i).getpQuantity();
	            row[2] = invoiceitems.get(i).getpPrice();
	            row[3] = invoiceitems.get(i).getpTax();
	            row[4] = invoiceitems.get(i).getpQuantity() * invoiceitems.get(i).getpPrice();
	            model.addRow(row);
	            
	            q= invoiceitems.get(i).getpQuantity();
	            w= invoiceitems.get(i).getpPrice();
	            e= invoiceitems.get(i).getpQuantity() * invoiceitems.get(i).getpPrice();
	            t=invoiceitems.get(i).getpTax();
	            
	            if(t == 24 ) {
	    			
	    			f24+= Double.valueOf(df.format( (q*w) * 24.0f / 100));
	    			
	    			label_9.setText(Double.toString(f24));
	    			
	    		}else if(t == 13) {
	    			
	    			f13+=  Double.valueOf(df.format((q*w) * 13.0f / 100));
	    			label_8.setText(Double.toString(f13));
	    			
	    		}else {
	    			
	    			f6+= Double.valueOf(df.format((q*w) * 6.0f / 100));
	    			label_7.setText(Double.toString(f6));
	    		}
	    		
	            ff+= Double.valueOf(df.format(e)); //meriko
	            
	            f= Double.valueOf(df.format(ff+f24+f13+f6)); //teliko sunolo
	            
	            label_11.setText(Double.toString(ff));
	            label_12.setText(Double.toString(f));
	            
	            
        }
	        table.setModel(model);
		
		
	}
	
	
	public void generateInvoice() {
		
		GenerateInvoice pdf=new GenerateInvoice(f,ff,f24,f13,f6,invoiceitems,contact,invoice);
		pdf.genpdf();
	}
	
	
	
	public void savePdftoFile() {
		
		
		FileOutputStream fos = null;
		try {
			
		
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("ΑΠΟΘΗΚΕΥΣΗ PDF");
			
			int selectedFile = fileChooser.showSaveDialog(null);
			
			if (selectedFile == JFileChooser.APPROVE_OPTION) {
			
			File pdftosave = fileChooser.getSelectedFile();
			fos = new FileOutputStream(pdftosave.getAbsolutePath()+".pdf");
			
			InvoiceD invo = new InvoiceD();
			invo.getPdfFromDb(invoice.get(0).getId());
			fos.write(invo.getPdf());
			fos.flush();
			fos.close();	
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	

	public void opedPdf() {


		InvoiceD invo = new InvoiceD();
		invo.getPdfFromDb(invoice.get(0).getId());
		
		
		
		
		
		
		if (Desktop.isDesktopSupported()) {
		    try {
		    	
		    	FileOutputStream fos = null;
				
					
		    	
		    	    File file =File.createTempFile("tempfile", ".pdf");
					
					fos = new FileOutputStream(file);
					
					fos.write(invo.getPdf());
					fos.flush();
					fos.close();	
			
		        Desktop.getDesktop().open(file);
		       
		       file.deleteOnExit();
		        
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
		
		
	}


	


}//telos
