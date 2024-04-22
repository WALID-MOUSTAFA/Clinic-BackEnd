package com.clinic.data.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.ColumnDefault;


@Entity
public class Patient{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column @NotNull
	protected String name;

	@Column @NotNull
	protected String phoneNumber;

	@Column @NotNull
	protected String age;

	@Column @NotNull
	protected String governorate;

	@Column @NotNull
	protected String city;

	@Column @NotNull
	protected String adress;

	@Column @NotNull
	protected Boolean revisitation;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date attendingDate;

	@Column
	protected Boolean attended = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGovernorate() {
		return governorate;
	}

	public void setGovernorate(String governorate) {
		this.governorate = governorate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Boolean getRevisitation() {
		return revisitation;
	}

	public void setRevisitation(Boolean revisitation) {
		this.revisitation = revisitation;
	}

	public Date getAttendingDate() {
		return attendingDate;
	}

	public void setAttendingDate(Date attendingDate) {
		this.attendingDate = attendingDate;
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
	}	
	
}
