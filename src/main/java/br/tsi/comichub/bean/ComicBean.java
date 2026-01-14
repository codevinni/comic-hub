package br.tsi.comichub.bean;

import java.io.Serializable;
import java.util.List;

import br.tsi.comichub.dao.ComicDAO;
import br.tsi.comichub.dao.DAO;
import br.tsi.comichub.model.Box;
import br.tsi.comichub.model.Comic;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("comicBean")
@ViewScoped
public class ComicBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Comic newComic = new Comic();
	private List<Comic> allComics;

	private Long boxId;
	private Box currentBox;

	public void loadBox() {
		if (boxId != null) {
			this.currentBox = new DAO<Box>(Box.class).searchById(boxId);
			
			if (currentBox != null) {
				this.allComics = new ComicDAO().findByBox(currentBox);
			}
		}
	}

	public void save() {
		if (currentBox == null) {
			// Tratamento de erro ou recarregar
			return;
		}
		newComic.setBox(currentBox);
		new DAO<Comic>(Comic.class).add(newComic);
		newComic = new Comic();
		// Recarregar lista
		this.allComics = new ComicDAO().findByBox(currentBox);
	}

	/**
	 * @return the newComic
	 */
	public Comic getNewComic() {
		return newComic;
	}

	/**
	 * @return the allComics
	 */
	public List<Comic> getAllComics() {
		return allComics;
	}

	/**
	 * @param newComic the newComic to set
	 */
	public void setNewComic(Comic newComic) {
		this.newComic = newComic;
	}

	/**
	 * @param allComics the allComics to set
	 */
	public void setAllComics(List<Comic> allComics) {
		this.allComics = allComics;
	}

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public Box getCurrentBox() {
		return currentBox;
	}

	public void setCurrentBox(Box currentBox) {
		this.currentBox = currentBox;
	}

}
