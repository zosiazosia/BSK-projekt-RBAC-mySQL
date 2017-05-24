package com.jwt.hibernate.bean;
// Generated 18-May-2017 12:52:35 by Hibernate Tools 5.2.3.Final

public class Treatment implements java.io.Serializable {

	private String name;
	private Integer price;
	private Integer duration;

	public Treatment() {
	}

	public Treatment(String name) {
		this.name = name;
	}

	public Treatment(String name, Integer price, Integer duration) {
		this.name = name;
		this.price = price;
		this.duration = duration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
