package com.xelit3.gymstatus.view.settings;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xelit3.gymstatus.control.settings.AppSettings;

public class ConfigurationPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GridLayout panelLayout = null;
	
	private JLabel lblUser, lblLanguage; 
	private JTextField tfUser;
	private JTextField tfLanguage;
	
	public ConfigurationPanel() {
		
		panelLayout = new GridLayout(2, 2, 0, 0);
		panelLayout.setHgap(25);
		panelLayout.setVgap(25);
		setLayout(panelLayout);
				
		lblUser = new JLabel("User name:");
//		lblNewLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(lblUser);
		
		tfUser = new JTextField(AppSettings.getInstance().getUsername());
		add(tfUser);
		tfUser.setColumns(10);
		
		lblLanguage = new JLabel("User language:");
		add(lblLanguage);
		
		tfLanguage = new JTextField(AppSettings.getInstance().getLanguage());
		add(tfLanguage);
		tfLanguage.setColumns(10);
	}	

}
