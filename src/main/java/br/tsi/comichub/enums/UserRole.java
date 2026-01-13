package br.tsi.comichub.enums;

public enum UserRole {

	ADMIN("admin"),
	USER("user");

	private String label;
	
	private UserRole(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
