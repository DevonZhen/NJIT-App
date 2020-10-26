package com.personal.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"PERSON_TYPE\"", schema = "Public")
public class PersonType implements Serializable {
	 
		private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
		@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_PERTYPE\"", allocationSize=1)
		@Column(name="\"PERT_ID\"", unique=true, nullable=false)         
		private Long id;                                   	    
		                                                             
		@Column(name="\"PERSON_TYPE\"")
		private String type;


}
