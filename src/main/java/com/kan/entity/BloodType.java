package com.kan.entity;

public enum BloodType {
	SIFIR_NEG(0), SIFIR_POZ(1), 
	A_NEG(2), A_POZ(3),
	B_NEG(4), B_POZ(5),
	AB_NEG(6),AB_POZ(7);

	private final int bloodType;

	private BloodType(int bloodType) {
		this.bloodType = bloodType;
	}

	public int getBloodType() {
		return bloodType;
	}

}
