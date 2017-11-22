package com.internousdev.template.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	/**
	 * Session情報
	 */
	public Map<String, Object> sessionMap = new HashMap<>();

	public String execute() {

		sessionMap.clear();
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
