package com.jwt.hibernate.bean;
// Generated 18-May-2017 12:52:35 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

public class Client implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer birthYear;
	private String name;
	private String surname;
	private String pesel;
	private String phone;
	private Set<Appointment> appointments = new HashSet<Appointment>(0);

	public Client() {
	}

	public Client(String name, String surname, String pesel) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
	}

	public Client(Integer birthYear, String name, String surname,
			String pesel, String phone, Set<Appointment> appointments) {
		this.birthYear = birthYear;
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.phone = phone;
		this.appointments = appointments;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getBirthYear() {
		return this.birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return this.pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

}
