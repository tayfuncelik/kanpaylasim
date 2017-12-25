package com.kan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "donor")
public class Donor extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long Id;

	private String dataName;
	private String location;

	private String xLocation;
	private String yLocation;

	private BloodType bloodType;
	private String sicknesses;
	private boolean surgery;
	private String donationDate;
	private int age;
	private int bloodPressure;
	private int weight;

	private boolean feelGood;
	private boolean viralHepatit;
	private boolean bloodTransf;
	private boolean aids;
	private boolean sitma;
	private boolean brainSurgery;
	private boolean takenAlcohol;
	private boolean anemi;
	private boolean harmfullMedicine;

	// private Long userId;

	// @OneToOne
	// @JoinColumn(name = "USER_ID")
	// private User user;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public String getSicknesses() {
		return sicknesses;
	}

	public void setSicknesses(String sicknesses) {
		this.sicknesses = sicknesses;
	}

	public boolean isSurgery() {
		return surgery;
	}

	public void setSurgery(boolean surgery) {
		this.surgery = surgery;
	}

	public String getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(String donationDate) {
		this.donationDate = donationDate;
	}

	public String getxLocation() {
		return xLocation;
	}

	public void setxLocation(String xLocation) {
		this.xLocation = xLocation;
	}

	public String getyLocation() {
		return yLocation;
	}

	public void setyLocation(String yLocation) {
		this.yLocation = yLocation;
	}

	public boolean isFeelGood() {
		return feelGood;
	}

	public void setFeelGood(boolean feelGood) {
		this.feelGood = feelGood;
	}

	public boolean isViralHepatit() {
		return viralHepatit;
	}

	public void setViralHepatit(boolean viralHepatit) {
		this.viralHepatit = viralHepatit;
	}

	public boolean isBloodTransf() {
		return bloodTransf;
	}

	public void setBloodTransf(boolean bloodTransf) {
		this.bloodTransf = bloodTransf;
	}

	public boolean isAids() {
		return aids;
	}

	public void setAids(boolean aids) {
		this.aids = aids;
	}

	public boolean isSitma() {
		return sitma;
	}

	public void setSitma(boolean sitma) {
		this.sitma = sitma;
	}

	public boolean isBrainSurgery() {
		return brainSurgery;
	}

	public void setBrainSurgery(boolean brainSurgery) {
		this.brainSurgery = brainSurgery;
	}

	public boolean isTakenAlcohol() {
		return takenAlcohol;
	}

	public void setTakenAlcohol(boolean takenAlcohol) {
		this.takenAlcohol = takenAlcohol;
	}

	public boolean isAnemi() {
		return anemi;
	}

	public void setAnemi(boolean anemi) {
		this.anemi = anemi;
	}

	public boolean isHarmfullMedicine() {
		return harmfullMedicine;
	}

	public void setHarmfullMedicine(boolean harmfullMedicine) {
		this.harmfullMedicine = harmfullMedicine;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(int bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
