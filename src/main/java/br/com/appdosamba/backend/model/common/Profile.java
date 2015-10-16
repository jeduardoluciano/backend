package br.com.appdosamba.backend.model.common;

public enum Profile {

	MEMBER("Atendente"),
	ADMINISTRATOR("Administrator"),
	SUPERADMINISTRATOR("SuperAdministrador");
	
	private String label;

	private Profile(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
