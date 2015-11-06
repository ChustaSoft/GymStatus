package com.xelit3.gymstatus.control.events;

import java.util.ArrayList;
import java.util.List;

public class EventAction {
	
	public enum Action{
		SAVE, MODIFY, DELETE
	}
	
	private Action action;
	
	private Object target;
		
	private List<String> errrors = new ArrayList<String>();;
	
	/**
	 * Instantiates a new event action.
	 */
	public EventAction(){
		
	}	
	
	/**
	 * Instantiates a new event action.
	 *
	 * @param anAction the an action
	 * @param aTarget the a target
	 */
	public EventAction(Action anAction, Object aTarget){
		setAction(anAction);
		setTarget(aTarget);
	}


	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}


	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}


	/**
	 * @return the target
	 */
	public Object getTarget() {
		return target;
	}


	/**
	 * @param target the target to set
	 */
	public void setTarget(Object target) {
		this.target = target;
	}


	/**
	 * @return the errrors
	 */
	public List<String> getErrrors() {
		return errrors;
	}


	/**
	 * @param errrors the errrors to set
	 */
	public void setErrrors(List<String> errrors) {
		this.errrors = errrors;
	}


}