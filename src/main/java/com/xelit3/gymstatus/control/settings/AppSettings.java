package com.xelit3.gymstatus.control.settings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * The Class AppSettings.
 * It uses a singleton pattern to load all settings needed for the application
 */
public class AppSettings {

	/** The Constant SETTINGS_RESOURCE. */
	//NOTE: Use in DEBUG MOED USE THIS
	private static final String SETTINGS_RESOURCE = "src/main/resources/app_config.txt";
	//NOTE: Use in PRODUCTION MOED USE THIS
//	private static final String SETTINGS_RESOURCE = "app_config.txt";
	
	/** The Constant USERNAME_SETTING. */
	private static final String USERNAME_SETTING = "USERNAME";
	
	/** The Constant LANGUAGE_SETTING. */
	private static final String LANGUAGE_SETTING = "LANGUAGE";

	/** The singleton. */
	private static AppSettings singleton = null;

	/** The username. */
	public String username;
	
	/** The language. */
	public String language;
	
	/** The main window pos y. */
	public int mainWindowPosX = 0, mainWindowPosY = 0;

	/**
	 * Instantiates a new app settings.
	 * Private constructor for the singleton
	 */
	private AppSettings() {

		try (BufferedReader br = new BufferedReader(new FileReader(AppSettings.SETTINGS_RESOURCE))) {
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
	
	/**
	 * Gets the single instance of AppSettings.
	 *
	 * @return single instance of AppSettings
	 */
	public static AppSettings getInstance() {
		if (singleton == null)
			singleton = new AppSettings();

		return singleton;
	}
	
	/**
	 * Save state.
	 *
	 * @return true, if successful
	 * @throws FileNotFoundException the file not found exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static boolean saveState() throws FileNotFoundException, UnsupportedEncodingException{
		if(singleton != null){
			PrintWriter writer = new PrintWriter(SETTINGS_RESOURCE, "UTF-8");
			writer.println(USERNAME_SETTING + ":" + singleton.getUsername());
			writer.println(LANGUAGE_SETTING + ":" + singleton.getLanguage());
			writer.close();
			
			return true;
		}
		else return false;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the main window pos x.
	 *
	 * @return the mainWindowPosX
	 */
	public int getMainWindowPosX() {
		return mainWindowPosX;
	}

	/**
	 * Sets the main window pos x.
	 *
	 * @param mainWindowPosX the mainWindowPosX to set
	 */
	public void setMainWindowPosX(int mainWindowPosX) {
		this.mainWindowPosX = mainWindowPosX;
	}

	/**
	 * Gets the main window pos y.
	 *
	 * @return the mainWindowPosY
	 */
	public int getMainWindowPosY() {
		return mainWindowPosY;
	}

	/**
	 * Sets the main window pos y.
	 *
	 * @param mainWindowPosY the mainWindowPosY to set
	 */
	public void setMainWindowPosY(int mainWindowPosY) {
		this.mainWindowPosY = mainWindowPosY;
	}

}
