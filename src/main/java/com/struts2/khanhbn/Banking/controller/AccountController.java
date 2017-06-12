package com.struts2.khanhbn.Banking.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.struts2.khanhbn.Banking.dao.AccountDao;
import com.struts2.khanhbn.Banking.dao.AccountDaoImpl;
import com.struts2.khanhbn.Banking.model.Account;

public class AccountController extends ActionSupport implements SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;
	private HttpServletRequest request;
	private String username;
	private String password;
	private List<Account> lstAccounts;
	private AccountDaoImpl accountDao = new AccountDaoImpl();

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Account> getLstAccounts() {
		return lstAccounts;
	}

	public void setLstAccounts(List<Account> lstAccounts) {
		this.lstAccounts = lstAccounts;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDaoImpl accountDao) {
		this.accountDao = accountDao;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String processLogin() throws Exception {

		if (accountDao.login(username, password)) {
			// this.lstAccounts = accountService.getAccounts();
			this.session.put("username", username);
			return SUCCESS;
		}
		request.setAttribute("message", "Username or password is invalid!");
		return ERROR;
	}

	// 3. Process logout.
	public String logout() throws Exception {
		if (this.session.containsKey("username")) {
			this.session.remove("username");
		}
		return SUCCESS;
	}

}
