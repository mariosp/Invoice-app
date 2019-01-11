package products;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class NewProductG extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel = new JLabel("ΝΕΟ ΠΡΟΙΟΝ");
	private JTextField tname;
	private JTextField tpcode;
	private JTextField tprice;
	private  JTextField tdes;
	private JComboBox comboBox;
	private JCheckBox tstock;
	public int id=0;
	
	public NewProductG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(0, 128, 128));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 799, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ΟΝΟΜΑ ΠΡΟΙΟΝΤΟΣ");
		lblNewLabel_1.setBounds(0, 45, 175, 14);
		contentPane.add(lblNewLabel_1);
		
		tname = new JTextField();
		tname.setBounds(0, 65, 308, 27);
		contentPane.add(tname);
		tname.setColumns(10);
		
		JLabel label = new JLabel("ΚΩΔΙΚΟΣ ΠΡΟΙΟΝΤΟΣ");
		label.setBounds(0, 109, 175, 14);
		contentPane.add(label);
		
		tpcode = new JTextField();
		tpcode.setColumns(10);
		tpcode.setBounds(0, 129, 308, 27);
		contentPane.add(tpcode);
		
		JLabel label_1 = new JLabel("ΠΕΡΙΓΡΑΦΗ ΠΡΟΙΟΝΤΟΣ");
		label_1.setBounds(0, 174, 175, 14);
		contentPane.add(label_1);
		
		tdes = new JTextField();
		tdes.setColumns(10);
		tdes.setBounds(0, 194, 308, 27);
		contentPane.add(tdes);
		
		tprice = new JTextField();
		tprice.setColumns(10);
		tprice.setBounds(0, 262, 120, 27);
		contentPane.add(tprice);
		
		JLabel label_2 = new JLabel("ΤΙΜΗ");
		label_2.setBounds(0, 242, 175, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Φ.Π.Α");
		label_3.setBounds(130, 242, 175, 14);
		contentPane.add(label_3);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"24", "13", "6"}));
		comboBox.setBounds(130, 262, 142, 27);
		contentPane.add(comboBox);
		
		 tstock = new JCheckBox("ΑΠΟΘΗΚΗ");
		tstock.setBounds(6, 312, 114, 23);
		contentPane.add(tstock);
		
		JButton btnNewButton = new JButton("ΕΦΑΡΜΟΓΗ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkInput()) {
					if(id==0) {
					
					ProductsD.addEntry(tname,tpcode,tdes,tprice,comboBox,tstock);
					dispose();
					}else	
				{
					ProductsD.updateEntry(id, tname,tpcode,tdes,tprice,comboBox,tstock);
					dispose();
				}
				}else
				{
					JOptionPane.showMessageDialog(null, "ΕΙΣΑΓΕΤΕ ΟΛΑ ΤΑ ΑΠΑΙΤΟΥΜΕΝΑ ΚΕΛΙΑ", "ΠΡΟΣΟΧΗ", JOptionPane.INFORMATION_MESSAGE);
				}	
			}
		});
		btnNewButton.setBounds(669, 396, 130, 37);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("ΑΚΥΡΩΣΗ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(526, 396, 130, 37);
		contentPane.add(button);
	}
	
	
public boolean checkInput() {

		
		if (tname.getText().isEmpty()) return false;
		//if (toc.getText().isEmpty()) return false;
		
		return true;
		
			
	}


public void setForModify(ArrayList<ProductsD> product) {
	
	
	for(int i = 0; i < product.size(); i++)
    {
		
		tname.setText(product.get(i).getName());
		tpcode.setText(product.get(i).getProdCode());
		tdes.setText(product.get(i).getDescription());
		tprice.setText(String.valueOf(product.get(i).getPrice()));
		
		if(product.get(i).getTax()==24) {
			comboBox.setSelectedIndex(0);
			
		}
		if(product.get(i).getTax()==13){
			comboBox.setSelectedIndex(1);
			
		}
		if(product.get(i).getTax()==6){
			comboBox.setSelectedIndex(2);
			
		}
		
		if(product.get(i).getStock()) {
			tstock.setSelected(true);
		}else {
			tstock.setSelected(false);
		}
		
			id=product.get(i).getId();
    }
	
	
	
}
	
	
	
	
	
	
}
