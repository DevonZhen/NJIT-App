package com.personal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

import lombok.Data;
import com.personal.domain.Address;
import com.personal.domain.EmgContact;
import com.personal.domain.Person;
import com.personal.domain.Phones;

@Data
@Entity
@Table(name = "\"PERSON\"", schema = "Public")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_PERSON\"", allocationSize=1)
	@Column(name="\"PER_ID\"", unique=true, nullable=false)         
	private Long id;                                   	    

	@Column(name="\"UID\"")
	private String userId;

	@Column(name="\"PWD\"")
	private String password;

	@Column(name="\"FIRST_NAME\"")
	private String firstName;

	@Column(name="\"LAST_NAME\"")
	private String lastName;

	@Column(name="\"SSN\"")
	private Long ssn;

	@Column(name="\"BIRTH_DAY\"")
	private Date birthDay;
	
    @Column(name = "\"PERT_ID\"")
    private Long pertId;
    
    /*--------------------------------------
     * Ignore when column is used by mapping
     *--------------------------------------*/
    //Person(Foreign key:ADD_ID) --> Address(Primary key:ADD_ID)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"ADD_ID\"")
    private Address address;
    
    //Person(Primary key:PER_ID) --> EmgContact(Foreign key:PER_ID)
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)     
	private EmgContact emgContact;                                          

	//Person(Primary key:PER_ID) --> Phones(Foreign key:PER_ID)
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Phones> phoneList = Lists.newArrayList();                  


}
