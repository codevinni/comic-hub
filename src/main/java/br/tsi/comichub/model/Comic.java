package br.tsi.comichub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Comic {


	@Id
	@SequenceGenerator(name = "comic_gen", sequenceName = "comic_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comic_gen")
	private Long id;
	
	@NotBlank(message = "Campo título vazio")
	@Size(max = 50, message = "O tamanho máximo é de 50 caracteres")
	private String title;
	
	@NotBlank(message = "Campo coleção vazio")
	@Size(max = 50, message = "O tamanho máximo é de 50 caracteres")
	private String collection;
	
	@NotNull(message = "Campo edição vazio")
	private Integer edition;
	
	@NotNull(message = "Campo ano vazio")
	@Max(value = 9999, message = "Ano inválido")
	private Integer year;
	
	@ManyToOne
	@NotNull(message = "Revista não associada a uma caixa!")
	private Box box;
		
	private boolean available = true;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the collection
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * @return the edition
	 */
	public Integer getEdition() {
		return edition;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @return the box
	 */
	public Box getBox() {
		return box;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * @param edition the edition to set
	 */
	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @param box the box to set
	 */
	public void setBox(Box box) {
		this.box = box;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return String.format("Comic [id=%s, title=%s, available=%s]", id, title, available);
	}
	
}
