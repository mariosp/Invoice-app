package settings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import contacts.ContactsD;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;



import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.Blob;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;




public class SettingsG extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_5;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JLabel lblimg;
	public String s;
	public File selectedFile;
	public FileInputStream is;
	
	public File newpic;
	public byte[] bytes =null;
	
	
	
	private static final int IMG_WIDTH = 120;
    private static final int IMG_HEIGHT = 120;
    JLabel label;
    ImageIcon photo;
    WritableRaster raster;
    DataBufferByte data;
    File image;
	private JPanel panel1;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	
	
	
	public SettingsG() throws SQLException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 panel1 = new JPanel();
		 panel1.setBounds(171, 0, 660, 458);
		 contentPane.add(panel1);
		 panel1.setLayout(null);
		 JLabel lblNewLabel_1 = new JLabel("ΕΠΩΝΥΜΙΑ ΕΠΙΧΕΙΡΗΣΗΣ");
		 lblNewLabel_1.setBounds(10, 11, 165, 27);
		 panel1.add(lblNewLabel_1);
		 
		 JLabel label_10 = new JLabel("ΔΙΑΚΡΙΤΟΣ ΤΙΤΛΟΣ");
		 label_10.setBounds(323, 11, 165, 27);
		 panel1.add(label_10);
		 
		 textField = new JTextField();
		 textField.setBounds(10, 35, 281, 27);
		 panel1.add(textField);
		 textField.setColumns(10);
		 
		 textField_1 = new JTextField();
		 textField_1.setColumns(10);
		 textField_1.setBounds(323, 35, 294, 27);
		 panel1.add(textField_1);
		 
		 JLabel label_1 = new JLabel("ΔΡΑΣΤΗΡΙΟΤΗΤΑ");
		 label_1.setBounds(10, 73, 165, 27);
		 panel1.add(label_1);
		 
		 textField_2 = new JTextField();
		 textField_2.setColumns(10);
		 textField_2.setBounds(10, 97, 281, 27);
		 panel1.add(textField_2);
		 
		 JLabel label_2 = new JLabel("ΑΦΜ");
		 label_2.setBounds(10, 135, 294, 27);
		 panel1.add(label_2);
		 
		 textField_3 = new JTextField();
		 textField_3.setColumns(10);
		 textField_3.setBounds(10, 159, 294, 27);
		 panel1.add(textField_3);
		 
		 JLabel label_3 = new JLabel("ΔΟΥ");
		 label_3.setBounds(323, 135, 165, 27);
		 panel1.add(label_3);
		 
		 textField_4 = new JTextField();
		 textField_4.setColumns(10);
		 textField_4.setBounds(323, 159, 281, 27);
		 panel1.add(textField_4);
		 
		 textField_6 = new JTextField();
		 textField_6.setColumns(10);
		 textField_6.setBounds(10, 225, 281, 27);
		 panel1.add(textField_6);
		 
		 JLabel label_5 = new JLabel("ΔΙΕΥΘΥΝΣΗ");
		 label_5.setBounds(10, 201, 165, 27);
		 panel1.add(label_5);
		 
		 textField_7 = new JTextField();
		 textField_7.setColumns(10);
		 textField_7.setBounds(323, 225, 202, 27);
		 panel1.add(textField_7);
		 
		 JLabel label_6 = new JLabel("ΠΕΡΙΟΧΗ");
		 label_6.setBounds(323, 201, 165, 27);
		 panel1.add(label_6);
		 
		 textField_8 = new JTextField();
		 textField_8.setColumns(10);
		 textField_8.setBounds(535, 225, 105, 27);
		 panel1.add(textField_8);
		 
		 JLabel label_7 = new JLabel("ΤΑΧΥΔΡΟΜΙΚΟΣ ΚΩΔΙΚΑΣ");
		 label_7.setBounds(524, 197, 165, 27);
		 panel1.add(label_7);
		 
		 JLabel label_4 = new JLabel("ΤΗΛΕΦΩΝΟ 1");
		 label_4.setBounds(10, 263, 165, 27);
		 panel1.add(label_4);
		 
		 textField_5 = new JTextField();
		 textField_5.setColumns(10);
		 textField_5.setBounds(10, 287, 165, 27);
		 panel1.add(textField_5);
		 
		 textField_9 = new JTextField();
		 textField_9.setColumns(10);
		 textField_9.setBounds(10, 354, 165, 27);
		 panel1.add(textField_9);
		 
		 JLabel label_8 = new JLabel("ΤΗΛΕΦΩΝΟ 2");
		 label_8.setBounds(10, 325, 165, 27);
		 panel1.add(label_8);
		 
		 textField_10 = new JTextField();
		 textField_10.setColumns(10);
		 textField_10.setBounds(185, 287, 165, 27);
		 panel1.add(textField_10);
		 
		 JLabel label_9 = new JLabel("ΚΙΝΗΤΟ");
		 label_9.setBounds(185, 263, 165, 27);
		 panel1.add(label_9);
		 
		 JLabel lblFax = new JLabel("Fax");
		 lblFax.setBounds(185, 325, 165, 27);
		 panel1.add(lblFax);
		 
		 textField_11 = new JTextField();
		 textField_11.setColumns(10);
		 textField_11.setBounds(185, 354, 165, 27);
		 panel1.add(textField_11);
		 
		 textField_12 = new JTextField();
		 textField_12.setColumns(10);
		 textField_12.setBounds(374, 287, 266, 27);
		 panel1.add(textField_12);
		 
		 JLabel lblEmail = new JLabel("email");
		 lblEmail.setBounds(374, 263, 266, 27);
		 panel1.add(lblEmail);
		 
		 JButton btnNewButton_1 = new JButton("ΑΝΑΖΗΤΗΣΗ");
		 btnNewButton_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		 JFileChooser file = new JFileChooser();
		           file.setCurrentDirectory(new File(System.getProperty("user.home")));
		           //filter the files
		           FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		           file.addChoosableFileFilter(filter);
		           int result = file.showOpenDialog(null);
		            //if the user click on save in Jfilechooser
		           if(result == JFileChooser.APPROVE_OPTION){
		         	  
		         	  
		               selectedFile = file.getSelectedFile();
		               String path = selectedFile.getAbsolutePath();
		    
		               
		               Image img1 = Toolkit.getDefaultToolkit().createImage(path);
		 			  
		 			  lblimg.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_DEFAULT)));
		           
		           }
	
		           else if(result == JFileChooser.CANCEL_OPTION){
		               System.out.println("No File Select");
		           }
		 		
		 		
		 		
		 	}
		 });
		 btnNewButton_1.setBounds(360, 356, 107, 23);
		 panel1.add(btnNewButton_1);
		 
		 JButton button = new JButton("ΔΙΑΓΡΑΦΗ");
		 button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		lblimg.setIcon(null);
		 		//selectedFile=null;
		 		
		 		 try {		    	
				    	FileOutputStream fos = null;
						
							byte[] t= {0};
				    	
				    	    File file =File.createTempFile("tempfile", ".jpg");
							
							fos = new FileOutputStream(file);
							
							fos.write(t);
							fos.flush();
							fos.close();	
					
				       
				       
				       file.deleteOnExit();
				       
				       selectedFile=file;
				        
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				
		 		
		 		
		 		
		 		
		 	}
		 });
		 button.setBounds(360, 390, 107, 23);
		 panel1.add(button);
		 
		 lblimg = new JLabel("");
		 lblimg.setBounds(477, 325, 178, 122);
		 panel1.add(lblimg);
		 
		 JButton btnNewButton_2 = new JButton("ΕΠΙΣΤΡΟΦΗ");
		 btnNewButton_2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		dispose();
		 	}
		 });
		 btnNewButton_2.setBounds(0, 420, 150, 38);
		 panel1.add(btnNewButton_2);
		 
		 JButton button_2 = new JButton("ΕΦΑΡΜΟΓΗ");
		 button_2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 			
		 		
		 		try {
		 			changeMyCompany();
		 			
		 			
		 		} catch (FileNotFoundException e1) {
		 			// TODO Auto-generated catch block
		 			e1.printStackTrace();
		 		} finally {
		 			
		 			dispose();
		 		}
		 		
		 		
		 		
		 	}
		 });
		 button_2.setBounds(154, 420, 150, 38);
		 panel1.add(button_2);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(171, 0, 660, 458);
		contentPane.add(panel2);
		panel2.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("email :");
		lblNewLabel.setBounds(10, 60, 55, 23);
		panel2.add(lblNewLabel);
		
		textField_13 = new JTextField();
		textField_13.setBounds(75, 61, 208, 20);
		panel2.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel label_11 = new JLabel("κωδικος :");
		label_11.setBounds(10, 94, 55, 23);
		panel2.add(label_11);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(75, 95, 208, 20);
		panel2.add(textField_14);
		
		JLabel lblEmailsmtpserver = new JLabel("emailSMTPserver :");
		lblEmailsmtpserver.setBounds(10, 127, 99, 23);
		panel2.add(lblEmailsmtpserver);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(119, 126, 208, 20);
		panel2.add(textField_15);
		
		JLabel label_12 = new JLabel("emailSMTPserver :");
		label_12.setBounds(10, 162, 99, 23);
		panel2.add(label_12);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(119, 161, 208, 20);
		panel2.add(textField_16);
		
		JLabel lblEmail_1 = new JLabel("ΑΛΛΓΗ ΡΥΘΜΙΣΕΩΝ EMAIL");
		lblEmail_1.setBounds(10, 11, 173, 14);
		panel2.add(lblEmail_1);
		
		JButton btnNewButton_3 = new JButton("ΕΦΑΡΜΟΓΗ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setEmail();
				dispose();
			}
		});
		btnNewButton_3.setBounds(401, 403, 121, 44);
		panel2.add(btnNewButton_3);
		
		JButton button_1 = new JButton("ΑΚΥΡΩΣΗ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(529, 403, 121, 44);
		panel2.add(button_1);
		
		JButton btnNewButton = new JButton("ΣΤΟΙΧΕΙΑ ΕΠΙΧΕΙΡΗΣΗΣ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				panel1.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(0, 54, 169, 65);
		contentPane.add(btnNewButton);
		
		JButton btnEmail = new JButton("EMAIL");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel1.setVisible(false);
				panel2.setVisible(true);
				
				loadEmailDb();
				
				
				
			}
		});
		btnEmail.setBounds(0, 117, 169, 65);
		contentPane.add(btnEmail);
		
		fillmycompany();
		
	}

	
	public void fillmycompany() throws IOException{
		
		ContactsD mycompany=new ContactsD();
		mycompany.getMyCompany();
		
		
		textField.setText(mycompany.getCname());
		textField_1.setText(mycompany.getCtitle());
		textField_2.setText(mycompany.getOc());
		textField_3.setText(mycompany.getAfm());
		textField_4.setText(mycompany.getDoy());
		textField_6.setText(mycompany.getStreet());
		textField_7.setText(mycompany.getTown());
		textField_8.setText(mycompany.getZip());
		textField_5.setText(mycompany.getTel());
		textField_9.setText(mycompany.getTel2());
		textField_10.setText(mycompany.getMob());
		textField_11.setText(mycompany.getFax());
		textField_12.setText(mycompany.getEmail());
		if(mycompany.getLogo()!= null) {
		
			  Image img = Toolkit.getDefaultToolkit().createImage(mycompany.getLogo());
			  
			
			  
			
				    try {
				    	
				    	FileOutputStream fos = null;
						
							
				    	
				    	    File file =File.createTempFile("tempfile", ".jpg");
							
							fos = new FileOutputStream(file);
							
							fos.write(mycompany.getLogo());
							fos.flush();
							fos.close();	
					
				       
				       
				       file.deleteOnExit();
				       
				       selectedFile=file;
				        
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				
			  
			// BufferedImage bi = toBufferedImage(img);  // retrieve image
			  
			 //BufferedImage mapmodifiable = ImageIO.read(new File("images/main.gif"));
			 
			 //FileInputStream fis=new FileInputStream();
			 
			// ImageIO.read(mycompany.getLogo());
			 // InputStream in = new ByteArrayInputStream(mycompany.getLogo());
			//	BufferedImage bi = ImageIO.read(in);
			    
			  
			 // selectedFile =File.createTempFile("tempfile", ".jpg");
			 // ImageIO.write(bi, "jpg",selectedFile); 
			  
			  
			  
			  
			  lblimg.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_DEFAULT)));
			
			
			
		}
		
	
		
	}

	
	public void changeMyCompany() throws FileNotFoundException  {
		
		
		  
		ContactsD.updateMyCompany(textField,textField_1, textField_2, textField_3, textField_4,  textField_6,  textField_7,  textField_8,  textField_12,  textField_5,  textField_9, textField_10, textField_11, selectedFile);
		
	}
	
	
	
	
	public void loadEmailDb() {
		
		ContactsD n=new ContactsD();
		n.getSEmail();
		
		textField_13.setText(n.getEm());
		textField_14.setText(n.getPas());
		textField_15.setText(n.getSser());
		textField_16.setText(n.getSpor());
		
		
		//ContactsD.updateEmail(textField_13,textField_14,textField_15,textField_16);
	}
	
	
	public void setEmail() {
		ContactsD n=new ContactsD();
		
		
		
		
		n.updateEmail(textField_13,textField_14,textField_15,textField_16);
		
		
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
}
