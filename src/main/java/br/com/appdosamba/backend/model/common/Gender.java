package br.com.appdosamba.backend.model.common;

public enum Gender {
    MALE("Masc"), 
    FEMALE("Fem");
	
	private String label;
	
	
	private Gender(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}