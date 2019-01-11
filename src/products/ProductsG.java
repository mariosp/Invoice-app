package products;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

public class ProductsG extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public JTable table;
	public JButton btnok;
	public int l;

	public ProductsG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ΠΡΟΙΟΝΤΑ");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 665, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnnewproduct = new JButton("ΝΕΟ ΠΡΟΙΟΝ");
		btnnewproduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewProductG newproductg = new NewProductG();
					newproductg.setVisible(true);
			}
		});
		btnnewproduct.setBounds(10, 54, 109, 33);
		contentPane.add(btnnewproduct);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textField.getText().length() >= 0)
				{
				findProducts();
				}else {
					table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ΟΝΟΜΑ ΠΡΟΙΟΝΤΟΣ", "ΠΕΡΙΓΡΑΦΗ"
			}
		));
					
				}
			}
		});
		
	/*	textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				findUsers();
				
			}
		}); */
		
		textField.setBounds(144, 54, 256, 33);
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
					"ID","ΠΡΟΙΟΝ","ΤΙΜΗ"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("ΕΜΦΑΝΙΣΗ ΠΡΟΙΟΝΤΟΣ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				openproduct();
				
				
			}
		});
		
		btnNewButton.setBounds(0, 346, 184, 34);
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
		
		btnok = new JButton("ΕΠΙΛΟΓΗ");
		btnok.setBounds(421, 347, 117, 33);
		btnok.setVisible(false);
		contentPane.add(btnok);
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent e) {
			  //enable button - put it in an EDT to be safe though
				 ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				 if (lsm.isSelectionEmpty()) {
					 btnNewButton.setEnabled(false);
			        }else {
			        	 btnNewButton.setEnabled(true);
			        }
			 }
			});
		
		
		
	}
	
	
	public void findProducts()
    {
        ArrayList<ProductsD> products = ProductsD.ListProducts(textField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID","ΠΡΟΙΟΝ","ΤΙΜΗ"});
        Object[] row = new Object[3];
        
        for(int i = 0; i < products.size(); i++)
        {
            row[0] = products.get(i).getId();
            row[1] = products.get(i).getName();
            row[2] = products.get(i).getPrice();
            
            if(products.get(i).getSales()==false)
            	
            model.addRow(row);
            
        }
        table.setModel(model);
       
    }
	
	public void openproduct()
    {
		int column = 0;
		int row = table.getSelectedRow();
		String value = table.getModel().getValueAt(row, column).toString();
		
		System.out.print(value);
		ArrayList<ProductsD> product = ProductsD.FindProductsD(value);
        
		ProductPG productpg = new ProductPG();
		productpg.setVisible(true);
		productpg.PreviewProduct(product);
		
		
    }
  }
	


