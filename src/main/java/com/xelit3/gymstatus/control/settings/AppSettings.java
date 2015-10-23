package com.xelit3.gymstatus.control.settings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class AppSettings {

	private static final String SETTINGS_FILE = "src/main/resources/app_config.txt";
	private static final String USERNAME_SETTING = "USERNAME";
	private static final String LANGUAGE_SETTING = "LANGUAGE";

	private static AppSettings singleton = null;

	public String username;
	public String language;

	private AppSettings() {

		try (BufferedReader br = new BufferedReader(new FileReader(AppSettings.SETTINGS_FILE))) {
			String line = br.readLine();

			while (line != null) {
				String[] tmpConfig = line.split(":");

				switch (tmpConfig[0]) {
					case AppSettings.USERNAME_SETTING:
						setUsername(tmpConfig[1]);
						break;
	
					case AppSettings.LANGUAGE_SETTING:
						setLanguage(tmpConfig[1]);
						break;
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public static AppSettings getInstance() {
		if (singleton == null)
			singleton = new AppSettings();

		return singleton;
	}
	
	public static boolean saveState() throws FileNotFoundException, UnsupportedEncodingException{
		if(singleton != null){
			PrintWriter writer = new PrintWriter(SETTINGS_FILE, "UTF-8");
			writer.println(USERNAME_SETTING + ":" + singleton.getUsername());
			writer.println(LANGUAGE_SETTING + ":" + singleton.getLanguage());
			writer.close();
			
			return true;
		}
		else return false;
	}

}
