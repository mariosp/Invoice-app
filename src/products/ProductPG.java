package products;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.Color;

public class ProductPG extends JFrame {

	private JPanel contentPane;
	public JLabel label8;
	public JLabel label9;
	private JLabel label10;
	private JLabel label5;
	private JLabel label7;
	private JToggleButton toggle;
	private JLabel label13;
	private JLabel label14;
	
	public static int id;
	public ArrayList<ProductsD> product;
	private AbstractButton toggleButton;
	private JLabel lblNewLabel_1;
	private JLabel stockav;
	private JLabel label;
	private JLabel lbled;
	private JSpinner spinner;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	public ProductPG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ΠΡΟΙΟΝ");
		lblNewLabel.setBackground(new Color(0, 128, 128));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 665, 40);
		contentPane.add(lblNewLabel);
		
		JLabel label1 = new JLabel("ΟΝΟΜΑ ΠΡΟΪΟΝΤΟΣ :");
		label1.setBounds(10, 61, 130, 14);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("ΚΩΔΙΚΟΣ ΠΡΟΪΟΝΤΟΣ :");
		label2.setBounds(10, 85, 130, 14);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("ΠΕΡΙΓΡΑΦΗ ΠΡΟΪΟΝΤΟΣ :");
		label3.setBounds(10, 110, 140, 14);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("TIMΗ :");
		label4.setBounds(10, 145, 58, 14);
		contentPane.add(label4);
		
		label5 = new JLabel("");
		label5.setBounds(78, 145, 97, 14);
		contentPane.add(label5);
		
		JLabel label6 = new JLabel("Φ.Π.Α :");
		label6.setBounds(10, 172, 58, 14);
		contentPane.add(label6);
		
		label7 = new JLabel("");
		label7.setBounds(78, 172, 97, 14);
		contentPane.add(label7);
		
		toggle = new JToggleButton("ΕΝΕΡΓΟ");
		toggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(toggle.isSelected()) {
					toggle.setSelected(true);
					toggle.setText("ΕΝΕΡΓΟ");
					
					ProductsD.updateSS('T',"Sales",id);
					
				}else {
					toggle.setSelected(false);
					toggle.setText("ΑΝΕΝΕΡΓΟ");
					ProductsD.updateSS('F',"Sales",id);
					
				}
				
				
			}
		});
		toggle.setSelected(true);
		toggle.setBounds(0, 197, 156, 23);
		contentPane.add(toggle);
		
		label8 = new JLabel("");
		label8.setBounds(150, 61, 185, 14);
		contentPane.add(label8);
		
		label9 = new JLabel("");
		label9.setBounds(150, 85, 185, 14);
		contentPane.add(label9);
		
		label10 = new JLabel("");
		label10.setBounds(150, 110, 185, 14);
		contentPane.add(label10);
		
		JButton button = new JButton("ΔΙΟΡΘΩΣΗ ΣΤΟΙΧΕΙΩΝ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewProductG newproduct = new NewProductG();
				newproduct.setVisible(true);
				newproduct.setForModify(product);
			}
		});
		button.setBounds(0, 343, 156, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ΕΠΙΣΤΡΟΦΗ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(543, 343, 122, 35);
		contentPane.add(button_1);
		
		JLabel label11 = new JLabel("ΗΜ. ΔΗΜΙΟΥΡΓΙΑΣ : ");
		label11.setBounds(10, 274, 130, 14);
		contentPane.add(label11);
		
		JLabel label12 = new JLabel("ΗΜ. ΤΕΛΕΥΤ ΤΡΟΠΟΠ :");
		label12.setBounds(10, 299, 130, 14);
		contentPane.add(label12);
		
		label13 = new JLabel("");
		label13.setBounds(150, 274, 185, 14);
		contentPane.add(label13);
		
		label14 = new JLabel("");
		label14.setBounds(150, 299, 185, 14);
		contentPane.add(label14);
		
		toggleButton = new JToggleButton("ΑΠΟΘΗΚΗ");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mbbbbbbb");
				if(toggleButton.isSelected()) {
					toggleButton.setSelected(true);
					toggleButton.setText("ΑΠΟΘΗΚΗ ΕΝΕΡΓΗ");
					
					ProductsD.updateSS('T',"Stock",id);
					
					ProductsD.showInventory(id);
					stockav.setText((String.valueOf(ProductsD.total)));
					lbled.setText(ProductsD.dateeditedinventory);
					 stockChangeG(0);
					
				}else {
					toggleButton.setSelected(false);
					toggleButton.setText("ΑΠΟΘΗΚΗ ΑΝΕΝΕΡΓΗ");
					
					ProductsD.updateSS('F',"Stock",id);
					
					toggleButton.setSelected(false);
					toggleButton.setText("ΑΠΟΘΗΚΗ ΑΝΕΝΕΡΓΗ");
					stockChangeG(1);
				}
				
				
			}
		});
		toggleButton.setSelected(true);
		toggleButton.setBounds(0, 231, 156, 23);
		contentPane.add(toggleButton);
		
		lblNewLabel_1 = new JLabel("ΔΙΑΘΕΣΙΜΗ ΑΠΟΘΗΚΗ :");
		lblNewLabel_1.setBounds(388, 61, 153, 14);
		contentPane.add(lblNewLabel_1);
		
		stockav = new JLabel("");
		stockav.setBounds(551, 61, 104, 14);
		contentPane.add(stockav);
		
		spinner = new JSpinner();
		((JSpinner) spinner).setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		((Component) spinner).setBounds(482, 107, 76, 20);
		contentPane.add((Component) spinner);
		
		btnNewButton_1 = new JButton("ΠΡΟΣΘΗΚΗ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double value = (Double) spinner.getValue();
				ProductsD.updateInventory(value+ProductsD.total,id);
				ProductsD.showInventory(id);
				stockav.setText((String.valueOf(ProductsD.total)));
				lbled.setText(ProductsD.dateeditedinventory);
				
			}
		});
		btnNewButton_1.setBounds(411, 141, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("ΑΦΑΙΡΕΣΗ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				double value = (Double) spinner.getValue();
				
				if(value<=ProductsD.total) {
				ProductsD.updateInventory(ProductsD.total-value,id);
				ProductsD.showInventory(id);
				stockav.setText((String.valueOf(ProductsD.total)));
				lbled.setText(ProductsD.dateeditedinventory);
				}else {
					JOptionPane.showMessageDialog(null, "ΛΑΘΟΣ ΣΤΗΝ ΑΦΑΙΡΕΣΗ ΠΡΟΙΟΝΤΩΝ ΑΠΟ ΑΠΟΘΗΚΗ", "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton_2.setBounds(510, 141, 89, 23);
		contentPane.add(btnNewButton_2);
		
		label = new JLabel("ΗΜ. ΤΕΛΕΥΤ ΑΛΛΑΓΗΣ  :");
		label.setBounds(388, 172, 130, 14);
		contentPane.add(label);
		
		lbled = new JLabel("");
		lbled.setBounds(442, 206, 198, 14);
		contentPane.add(lbled);
	}
	
	
	public void PreviewProduct(ArrayList<ProductsD> product) {
		this.product=product;
		
		
		for(int i = 0; i < product.size(); i++)
        {
			id=product.get(i).getId();
			//System.out.println(product.get(i).getName());
			
			label8.setText(product.get(i).getName());
			label9.setText(product.get(i).getProdCode());
			label10.setText(product.get(i).getDescription());
			label5.setText(String.valueOf(product.get(i).getPrice()));
			label7.setText(String.valueOf(product.get(i).getTax()));
			
			if(product.get(i).getSales()) {
				toggle.setSelected(true);
				toggle.setText("ΕΝΕΡΓΟ");
			}else{
				toggle.setSelected(false);
				toggle.setText("ΑΝΕΝΕΡΓΟ");
				
			}
			
			if(product.get(i).getStock()) {
				toggleButton.setSelected(true);
				toggleButton.setText("ΑΠΟΘΗΚΗ ΕΝΕΡΓΗ");
				ProductsD.showInventory(id);
				stockav.setText((String.valueOf(ProductsD.total)));
				lbled.setText(ProductsD.dateeditedinventory);
				stockChangeG(0);
			}else{
				toggleButton.setSelected(false);
				toggleButton.setText("ΑΠΟΘΗΚΗ ΑΝΕΝΕΡΓΗ");
				stockChangeG(1);
				
			}
			
			label13.setText(product.get(i).getDatecreated());
			label14.setText(product.get(i).getDateEdited());
			
			
			
			
        }
		
	}	
	
	
	private void stockChangeG(int a) {
		
		if (a==0) { //true
			
			lblNewLabel_1.setEnabled(true);
			stockav.setEnabled(true);
			spinner.setEnabled(true);
			btnNewButton_1.setEnabled(true);
			btnNewButton_2.setEnabled(true);
			label.setEnabled(true);
			lbled.setEnabled(true);
		
		}else {
			lblNewLabel_1.setEnabled(false);
			stockav.setEnabled(false);
			spinner.setEnabled(false);
			btnNewButton_1.setEnabled(false);
			btnNewButton_2.setEnabled(false);
			label.setEnabled(false);
			lbled.setEnabled(false);
		}
		
		
	}
	
	
}
