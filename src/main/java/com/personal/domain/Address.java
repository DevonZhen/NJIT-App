package com.personal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"ADDRESS\"", schema = "Public")
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_ADDRESS\"", allocationSize=1)
	@Column(name="\"ADD_ID\"", unique=true, nullable=false)         
	private Long id;     

	@Column(name="\"STREET\"")
	private String street;

	@Column(name="\"CITY\"")
	private String city;
	
	@Column(name="\"STATE\"")
	private String state;
	
	@Column(name="\"ZIP\"")
	private Long zip;
	
	@OneToOne(mappedBy = "address")
    private Person person;
	
}
