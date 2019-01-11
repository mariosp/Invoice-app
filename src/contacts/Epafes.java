package contacts;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Font;

public class Epafes extends JFrame {

	public JPanel contentPane;
	private JTextField textField;
	public JTable table;
	public JButton btnselect;
	
	public Epafes() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ΕΠΑΦΕΣ");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(0, 0, 665, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnnewcontact = new JButton("ΝΕΑ ΕΠΑΦΗ");
		btnnewcontact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewContact newcontact = new NewContact();
					newcontact.setVisible(true);
			}
		});
		btnnewcontact.setBounds(10, 54, 109, 33);
		contentPane.add(btnnewcontact);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textField.getText().length() >= 0)
				{
				findUsers();
				}else {
					table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ΕΠΩΝΥΜΙΑ", "ΑΦΜ"
			}
		));
					
				}
				
			}
		});
		
	
		textField.setBounds(151, 54, 256, 33);
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
				"ID", "ΕΠΩΝΥΜΙΑ", "ΑΦΜ"
			}
		));
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(150);
		tcm.getColumn(1).setPreferredWidth(265);
		tcm.getColumn(2).setPreferredWidth(250);
		
		scrollPane.setViewportView(table);
		
		
		
		JButton btnNewButton = new JButton("ΕΜΦΑΝΙΣΗ ΕΠΑΦΗΣ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				opencontact();
				
				
			}
		});
		
		btnNewButton.setBounds(0, 346, 163, 34);
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
		
		btnselect = new JButton("ΕΠΙΛΟΓΗ");
		btnselect.setVisible(false);
		btnselect.setBounds(429, 347, 109, 33);
		contentPane.add(btnselect);
		
		
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
	
	
	public void findUsers()
    {
        ArrayList<ContactsD> users = ContactsD.ListUsers(textField.getText());
     DefaultTableModel model = new DefaultTableModel();
     model.setColumnIdentifiers(new Object[]{"ID","ΕΠΩΝΥΜΙΑ","ΑΦΜ"});
    
		
		
        Object[] row = new Object[3];
        
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getCname();
            row[2] = users.get(i).getAfm();
            model.addRow(row);
        }
      
        table.setModel(model);
       
    }
	
	public void opencontact()
    {
		int column = 0;
		int row = table.getSelectedRow();
		String value = table.getModel().getValueAt(row, column).toString();
		
		System.out.print(value);
		ArrayList<ContactsD> contact = ContactsD.FindContact(value);
        
		ContactPG contactpg = new ContactPG();
		contactpg.setVisible(true);
		contactpg.PreviewContact(contact);
		
		
    }
  }
