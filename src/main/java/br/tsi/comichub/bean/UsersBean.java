package br.tsi.comichub.bean;

import java.io.Serializable;
import java.util.List;

import br.tsi.comichub.dao.AccountDAO;
import br.tsi.comichub.dao.DAO;
import br.tsi.comichub.model.Account;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("usersBean")
@ViewScoped
public class UsersBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Account newUser = new Account();
	private List<Account> allUsers;
	
	public void save() {
		newUser.setVerified(true); // Se o admin esta adicionando, ja considera verificado
		new DAO<Account>(Account.class).add(newUser);
		newUser = new Account();
	}

	/**
	 * @return the newUser
	 */
	public Account getNewUser() {
		return newUser;
	}

	/**
	 * @return the allUsers
	 */
	public List<Account> getAllUsers() {
		allUsers = new AccountDAO().listAll();
		return allUsers;
	}

	/**
	 * @param newUser the newUser to set
	 */
	public void setNewUser(Account newUser) {
		this.newUser = newUser;
	}

	/**
	 * @param allUsers the allUsers to set
	 */
	public void setAllUsers(List<Account> allUsers) {
		this.allUsers = allUsers;
	}
	
	
	

}
