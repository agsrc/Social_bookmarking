package edu.gwu.akshay.sb.constants;

public enum UserType {		//no instance needed as we will define only constants
	

	 USER ("user"),
	  EDITOR("editor"),
	 CHIEF_EDITOR("chiefeditor");
	private UserType(String name) {
		this.name=name;
	}
	private String name;
	public String getUser(){
		return name;
	}
}
