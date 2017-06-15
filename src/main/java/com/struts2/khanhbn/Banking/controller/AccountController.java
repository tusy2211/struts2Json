package com.struts2.khanhbn.Banking.controller;

import java.util.ArrayList;
import java.util.Date;
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
	private List<Account> data = new ArrayList<Account>();
	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private Account account = new Account();

	private String _username;

	private String _pass;

	private String _des;

	private String _image;
	
	private Date _birthday;
	
	public Date get_birthday() {
		return _birthday;
	}

	public void set_birthday(Date _birthday) {
		this._birthday = _birthday;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_pass() {
		return _pass;
	}

	public void set_pass(String _pass) {
		this._pass = _pass;
	}

	public String get_des() {
		return _des;
	}

	public void set_des(String _des) {
		this._des = _des;
	}

	public String get_image() {
		return _image;
	}

	public void set_image(String _image) {
		this._image = _image;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

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

	public List<Account> getData() {
		return data;
	}

	public void setData(List<Account> data) {
		this.data = data;
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

	public String getList() throws Exception {
		this.data = accountDao.getAccounts();
		System.out.println("aa==== " + data);
		return SUCCESS;
	}

	// 3. Process logout.
	public String logout() throws Exception {
		if (this.session.containsKey("username")) {
			this.session.remove("username");
		}
		return SUCCESS;
	}

	// process add
	public String processAddNew() throws Exception {
		account.setUsername(_username);
		account.setPassword(_pass);
		account.setDescription(_des);
		account.setImage(_image);
		account.setBirthday(_birthday);
		System.out.println("bbb====" + account.getUsername());
		boolean isCheck = accountDao.addNew(account);
		System.out.println("bbb====" + account);
		if (isCheck) {
			this.data = accountDao.getAccounts();
		}
		return SUCCESS;
	}
}
