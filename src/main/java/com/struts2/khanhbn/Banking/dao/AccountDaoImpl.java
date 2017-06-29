package com.struts2.khanhbn.Banking.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.struts2.khanhbn.Banking.model.Account;
import com.struts2.khanhbn.Banking.util.HibernateUtil;

public class AccountDaoImpl implements AccountDao {

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getsession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(" FROM Account");
			@SuppressWarnings("unchecked")
			List<Account> lstAccounts = query.list();
			transaction.commit();
			return lstAccounts;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getsession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		String SQL_QUERY = " from Account ih where ih.username='" + username + "' and ih.password='" + password + "'";
		Query query = session.createQuery(SQL_QUERY);
		transaction.commit();
		@SuppressWarnings("unchecked")
		Iterator<Account> it = query.iterate();
		while (it.hasNext()) {
			session.close();
			return true;
		}
		session.close();
		return false;
	}

	@Override
	public boolean addNew(Account account) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getsession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(account);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public Account getBookByID(int idAccount) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getsession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Account account = (Account) session.get(Account.class, idAccount);
			transaction.commit();
			return account;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getsession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(account);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean delete(Account account) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getsession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(account);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<Account> searchAccountByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
