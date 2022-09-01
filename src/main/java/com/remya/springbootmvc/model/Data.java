package com.remya.springbootmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class Data {
	private String rxcui;
	private String name;
	
	public Data(String rxcui, String name) {
		super();
		this.rxcui = rxcui;
		this.name = name;
	}

	public String getRxcui() {
		return rxcui;
	}

	public void setRxcui(String rxcui) {
		this.rxcui = rxcui;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Data() {
		// TODO Auto-generated constructor stub
		
	}

	

}
