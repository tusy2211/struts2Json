package com.struts2.khanhbn.Banking.service;

import java.util.List;

import com.struts2.khanhbn.Banking.dao.AccountDao;
import com.struts2.khanhbn.Banking.model.Account;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	
	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return accountDao.getAccounts();
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return accountDao.login(username, password);
	}

	@Override
	public boolean addNew(Account account) {
		// TODO Auto-generated method stub
		return accountDao.addNew(account);
	}

	@Override
	public Account getBookByID(int idAccount) {
		// TODO Auto-generated method stub
		return accountDao.getBookByID(idAccount);
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		return accountDao.update(account);
	}

	@Override
	public boolean delete(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> searchAccountByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
