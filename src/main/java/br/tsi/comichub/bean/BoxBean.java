package br.tsi.comichub.bean;

import java.io.Serializable;
import java.util.List;

import br.tsi.comichub.dao.DAO;
import br.tsi.comichub.model.Box;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("boxBean")
@ViewScoped
public class BoxBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Box newBox = new Box();
	private List<Box> allBoxes;

	public void save() {
		new DAO<Box>(Box.class).add(newBox);
		newBox = new Box();
	}

	public String goToBoxDetails(Box box) {
		return "comics?faces-redirect=true&boxId=" + box.getId();
	}

	/**
	 * @return the newBox
	 */
	public Box getNewBox() {
		return newBox;
	}

	/**
	 * @return the allBoxes
	 */
	public List<Box> getAllBoxes() {
		allBoxes = new DAO<Box>(Box.class).listAll();
		return allBoxes;
	}

	/**
	 * @param newBox the newBox to set
	 */
	public void setNewBox(Box newBox) {
		this.newBox = newBox;
	}

	/**
	 * @param allBoxes the allBoxes to set
	 */
	public void setAllBoxes(List<Box> allBoxes) {
		this.allBoxes = allBoxes;
	}

}
