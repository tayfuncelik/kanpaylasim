package com.kan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//@Embeddable
@MappedSuperclass
public class BaseEntity extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Id")
	private Long Id;

	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
