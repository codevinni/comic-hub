package br.tsi.comichub.model;

import java.util.Date;

import br.tsi.comichub.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
public class Account {

	@Id
	@SequenceGenerator(name = "account_gen", sequenceName = "account_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "account_gen")
	private Long id;
	
	@NotBlank(message = "Campo nome de usuário vazio")
	private String name;
	
	@NotBlank(message = "Campo senha vazio")
	private String password;
	
	@NotBlank(message = "Campo e-mail vazio")
	@Email(message = "Formato de e-mail inválido")
	private String mail;
	
	@NotBlank(message = "Campo telefone vazio")
	private String phoneNumber;
	
	//@CPF
	@NotBlank(message = "Campo CPF vazio")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Formato de CPF Inválido")
	private String cpf;
	
	@Past(message = "Data de nascimento inválida")
	@Temporal(TemporalType.DATE)
	private Date birth;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	private boolean verified = false;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	public boolean isVerified() {
		return this.verified;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	@Override
	public String toString() {
		return String.format("Account [id=%s, name=%s, mail=%s]", id, name, mail);
	}
	
}