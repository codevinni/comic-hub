package br.tsi.comichub.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Loan {

	@Id
	@SequenceGenerator(name = "loan_gen", sequenceName = "loan_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_gen")
	private Long id;
	
	@ManyToOne
	@NotNull(message = "Campo usuário vazio")
	private Account user;
	
	@ManyToOne
	@NotNull(message = "Campo revista vazio")
	private Comic comic;
	
	@NotNull(message = "A data do empréstimo é obrigatória")
	private LocalDateTime loanDate;
	
	@NotNull(message = "A data prevista de devolução é obrigatória")
	private LocalDateTime predictedDevolutionDate;
	
	private LocalDateTime devolutionDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public Account getUser() {
		return user;
	}

	/**
	 * @return the comic
	 */
	public Comic getComic() {
		return comic;
	}

	/**
	 * @return the loanDate
	 */
	public LocalDateTime getLoanDate() {
		return loanDate;
	}

	/**
	 * @return the predictedDevolutionDate
	 */
	public LocalDateTime getPredictedDevolutionDate() {
		return predictedDevolutionDate;
	}

	/**
	 * @return the devolutionDate
	 */
	public LocalDateTime getDevolutionDate() {
		return devolutionDate;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Account user) {
		this.user = user;
	}

	/**
	 * @param comic the comic to set
	 */
	public void setComic(Comic comic) {
		this.comic = comic;
	}

	/**
	 * @param loanDate the loanDate to set
	 */
	public void setLoanDate(LocalDateTime loanDate) {
		this.loanDate = loanDate;
	}

	/**
	 * @param predictedDevolutionDate the predictedDevolutionDate to set
	 */
	public void setPredictedDevolutionDate(LocalDateTime predictedDevolutionDate) {
		this.predictedDevolutionDate = predictedDevolutionDate;
	}

	/**
	 * @param devolutionDate the devolutionDate to set
	 */
	public void setDevolutionDate(LocalDateTime devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	@Override
	public String toString() {
		return String.format("Loan [id=%s, loanDate=%s]", id, loanDate);
	}
	
}
