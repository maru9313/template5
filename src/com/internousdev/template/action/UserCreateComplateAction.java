package com.internousdev.template.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.UserCreateComplateDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateComplateAction extends ActionSupport implements SessionAware{

	public String loginUserId;

	public String loginPassword;

	public String userName;

	public Map<String,Object> createUserSession;

	public String result;

	public SessionAware session = new UserCreateConfirmAction();

	public UserCreateComplateDAO userCreateComplateDAO = new UserCreateComplateDAO();

	public String execute() throws SQLException {

		userCreateComplateDAO.cerateUser(createUserSession.get("loginUserId").toString(),
				createUserSession.get("loginPassword").toString(),
				createUserSession.get("userName").toString());

		result = SUCCESS;

		return result ;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void setSession(Map<String, Object> createUserSession) {
		this.createUserSession = createUserSession;
	}
}

			/*⑦*/