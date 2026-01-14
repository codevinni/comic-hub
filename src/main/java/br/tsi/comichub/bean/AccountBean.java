package br.tsi.comichub.bean;

import java.io.Serializable;

import br.tsi.comichub.dao.AccountDAO;
import br.tsi.comichub.model.Account;
import br.tsi.comichub.utils.Mail;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("accountBean")
@SessionScoped
public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Account user = new Account();
	private String generatedCode;
	private String userCode;
	
	public String doLogin() {

		Account authenticatedUser = new AccountDAO().authenticate(user);
		
		if (authenticatedUser != null) {
			return authenticateSession(authenticatedUser);
		}
		
		addErrorMessage("Usuário e/ou senha inválidos");
		user = new Account();
		
		return null;
	}
	
	public String doLogout() {
		
		user = new Account();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("authUser", null); 
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();; 
		
		return "login?faces-redirect=true";
	}
	
	public String signUp() {
		
		if (new AccountDAO().findByEmail(user.getMail()) != null) {
	        addErrorMessage("Este e-mail já está cadastrado!");
	        return null;
	    }
		
		this.generatedCode = String.valueOf((int)(Math.random() * 900000) + 100000);
		
		if(new Mail().sendCode(user.getMail(), generatedCode))
			return "verify?faces-redirect=true";
		else {
			addErrorMessage("Ocorreu um erro ao enviar o e-mail! Tente novamente mais tarde.");
			return null;
		}
	}
	
	public String verifyAccount() {
		
		if(userCode.equals(generatedCode)) {
			
			user.setVerified(true);
			new AccountDAO().add(user);
			
			generatedCode = null;
			userCode = null;
			
			return authenticateSession(user);
		}
		
		addErrorMessage("Código incorreto!");
		return null;
	}
	
	public String authenticateSession(Account user) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("authUser", user);
		return "boxes?faces-redirect=true";
	}
	
    private void addErrorMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
    
	public Account getUser() {
		return user;
	}
	
	/**
	 * @return the generatedCode
	 */
	public String getGeneratedCode() {
		return generatedCode;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	public void setUser(Account user) {
		this.user = user;
	}
	
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @param generatedCode the generatedCode to set
	 */
	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}
}
