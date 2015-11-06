package com.xelit3.gymstatus;

import com.xelit3.gymstatus.control.Controller;

/**
 * The Class GymStatusMain.
 * This class start the Application, it need a controller and controller start GUI
 */
public class GymStatusMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
//		controller.testApp();
		controller.startApp();	
		
	}

}
