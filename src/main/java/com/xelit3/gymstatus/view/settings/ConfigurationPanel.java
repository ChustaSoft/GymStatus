package com.xelit3.gymstatus.view.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.xelit3.gymstatus.control.settings.AppSettings;

public class ConfigurationPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblUser, lblLanguage; 
	private JTextField tfUser;
	private JComboBox<String> cbLanguage;
	private JButton btnSaveSettings;
	
	private static String[] APP_LANGUAGES= {"SPANISH", "ENGLISH"};
		
	public ConfigurationPanel() {
		createComponents();
	}

	private void createComponents() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblUser = new JLabel("User name:");
		add(lblUser, "8, 8, right, default");
		
		tfUser = new JTextField(AppSettings.getInstance().getUsername());
		add(tfUser, "10, 8, center, default");
		tfUser.setColumns(10);
		
		lblLanguage = new JLabel("User language:");
		add(lblLanguage, "8, 10, right, default");
		
		cbLanguage = new JComboBox<>(APP_LANGUAGES);
		add(cbLanguage, "10, 10, center, default");
		
		btnSaveSettings = new JButton("Save");
		btnSaveSettings.addActionListener(this);
		add(btnSaveSettings, "10, 16, center, default");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean tmpFlag = true;
		String tmpName = this.tfUser.getText();
		String tmpLanguage = this.cbLanguage.getSelectedItem().toString();
		if(!"".equals(tmpName) && !"".equals(tmpLanguage)){
			AppSettings.getInstance().setUsername(tmpName);
			AppSettings.getInstance().setLanguage(tmpLanguage);
			
			try {
				tmpFlag = AppSettings.saveState();
			} 
			catch (FileNotFoundException | UnsupportedEncodingException e1) {
				tmpFlag = false;
			}
						
		} 
		else
			settingsNotSaved();
		
		if(!tmpFlag)
			settingsNotSaved();
			
	}

	private void settingsNotSaved() {
		JOptionPane.showMessageDialog(this, "Undefined username or language");
	}	

}