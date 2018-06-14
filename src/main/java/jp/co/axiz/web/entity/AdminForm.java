package jp.co.axiz.web.entity;

import javax.validation.constraints.NotBlank;

public class AdminForm {

	@NotBlank
	private String id;

	@NotBlank
	private String pass;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}