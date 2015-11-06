package com.xelit3.gymstatus.control.events;

import java.util.ArrayList;
import java.util.List;

public class EventAction {
	
	/**
	 * The Enum Action.
	 * Used for specify an action for an event
	 */
	public enum Action{
		SAVE, MODIFY, DELETE
	}
	
	/** The action. */
	private Action action;
	
	/** The target. */
	private Object target;
		
	/** The errrors. */
	private List<String> errrors = new ArrayList<String>();;
	
	/**
	 * Instantiates a new event action.
	 */
	public EventAction(){
		
	}	
	
	/**
	 * Instantiates a new event action.
	 *
	 * @param anAction the action
	 * @param aTarget the target
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