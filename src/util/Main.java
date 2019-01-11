package util;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import products.ProductsG;
import reports.ReportsG;
import settings.SettingsG;
import contacts.Epafes;
import invoice.InvoiceG;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class Main {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Main m=new Main();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public Main() {
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 537, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setVisible(true);
		JLabel lblNewLabel = new JLabel("ΑΡΧΙΚΗ");
		lblNewLabel.setBackground(new Color(222, 184, 135));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 521, 45);
		contentPane.add(lblNewLabel);
		
		JButton btnepafes = new JButton("ΕΠΑΦΕΣ");
		btnepafes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Epafes epafes= new Epafes();
				epafes.setVisible(true);
			}
		});
		btnepafes.setBounds(0, 56, 132, 71);
		contentPane.add(btnepafes);
		
		JButton button = new JButton("ΠΡΟΙΟΝΤΑ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductsG productsg=new ProductsG();
				productsg.setVisible(true);
			}
		});
		button.setBounds(0, 137, 132, 71);
		contentPane.add(button);
		
		JButton btnsettings = new JButton("ΡΥΘΜΙΣΕΙΣ");
		btnsettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						SettingsG settingsg = null;
						try {
							settingsg = new SettingsG();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						settingsg.setVisible(true);
			}	
		});
		btnsettings.setBounds(0, 219, 132, 71);
		contentPane.add(btnsettings);
		
		JButton btninvoice = new JButton("ΤΙΜΟΛΟΓΙΑ");
		btninvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvoiceG invoiceg = new InvoiceG();
				invoiceg.setVisible(true);
			}
		});
		btninvoice.setBounds(142, 56, 132, 71);
		contentPane.add(btninvoice);
		
		JButton button_1 = new JButton("ΑΝΑΦΟΡΕΣ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReportsG reportsg=new ReportsG();
				reportsg.setVisible(true);
				
			}
		});
		button_1.setBounds(142, 137, 132, 71);
		contentPane.add(button_1);
	}
}

