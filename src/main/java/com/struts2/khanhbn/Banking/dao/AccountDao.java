package com.struts2.khanhbn.Banking.dao;

import java.util.List;

import com.struts2.khanhbn.Banking.model.Account;

public interface AccountDao {

	public List<Account> getAccounts();
	
	public boolean login(String username, String password);
	
	public boolean addNew(Account account);
	
	public Account getBookByID(int idAccount);
	
	public boolean update(Account account);
	
	public boolean delete(Account account);
	
	public List<Account> searchAccountByName(String name);
}
