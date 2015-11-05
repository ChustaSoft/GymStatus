package com.xelit3.gymstatus.control.events;

import java.util.List;

public class EventAction {
	
	public enum Action{
		SAVE, MODIFY, DELETE
	}
	
	private Object aTarget;
	private List<String> theErrors;
	
	
	public EventAction(){
		
	}


	/**
	 * @return the aTarget
	 */
	private Object getaTarget() {
		return aTarget;
	}


	/**
	 * @param aTarget the aTarget to set
	 */
	private void setaTarget(Object aTarget) {
		this.aTarget = aTarget;
	}


	/**
	 * @return the theErrors
	 */
	private List<String> getTheErrors() {
		
		return theErrors;
	}


	/**
	 * @param theErrors the theErrors to set
	 */
	private void setTheErrors(List<String> theErrors) {
		this.theErrors = theErrors;
	}
}