package br.tsi.comichub.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import br.tsi.comichub.dao.DAO;
import br.tsi.comichub.dao.LoanDAO;
import br.tsi.comichub.enums.UserRole;
import br.tsi.comichub.model.Account;
import br.tsi.comichub.model.Comic;
import br.tsi.comichub.model.Loan;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@ViewScoped
@Named("loanBean")
public class LoanBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Loan newLoan = new Loan();
	private List<Loan> loans;
	private Comic selectedComic;

	private List<Loan> lateLoans;
	
	public String save() {
		
		Account authUser = (Account) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authUser");

		newLoan.setUser(authUser);
		newLoan.setComic(selectedComic);
		newLoan.setLoanDate(LocalDateTime.now());
		newLoan.setPredictedDevolutionDate(LocalDateTime.now().plusDays(7));

		new LoanDAO().add(newLoan);

		selectedComic.setAvailable(false);
		new DAO<Comic>(Comic.class).update(selectedComic);

		newLoan = new Loan();
		return "loans?faces-redirect=true";
	}

	public List<Loan> getLoans() {
		
		Account authUser = (Account) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authUser");

		if (authUser != null) 
			loans = authUser.getRole().equals(UserRole.ADMIN) ? new LoanDAO().listAll() : new LoanDAO().findByUser(authUser);

		return loans;
	}

	public void registerReturn(Loan loan) {
		
		loan.setDevolutionDate(LocalDateTime.now());
		new LoanDAO().update(loan);

		Comic comic = loan.getComic();
		comic.setAvailable(true);
		new DAO<Comic>(Comic.class).update(comic);

		getLoans();
	}

	public List<Loan> getLateLoans() {
		
		lateLoans = new LoanDAO().findLateLoans();
		return lateLoans;
	}
	
	/**
	 * @return the newLoan
	 */
	public Loan getNewLoan() {
		return newLoan;
	}

	/**
	 * @return the selectedComic
	 */
	public Comic getSelectedComic() {
		return selectedComic;
	}

	/**
	 * @param selectedComic the selectedComic to set
	 */
	public void setSelectedComic(Comic selectedComic) {
		this.selectedComic = selectedComic;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	public void setLateLoans(List<Loan> lateLoans) {
		this.lateLoans = lateLoans;
	}
	
	/**
	 * @param newLoan the newLoan to set
	 */
	public void setNewLoan(Loan newLoan) {
		this.newLoan = newLoan;
	}

	
}
