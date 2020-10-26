package com.personal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"PHONES\"", schema = "Public")
public class Phones implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_PHONES\"", allocationSize=1)
	@Column(name="\"PH_ID\"", unique=true, nullable=false)         
	private Long id;                                   	    
	
    @Column(name="\"PHONE\"")
	private String phone;
	
    @Column(name="\"PH_TYPE\"")
	private String phoneType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"PER_ID\"", nullable = false)
    private Person person;

    /*================================================
     * There is a difficulty implement from Angular UI
     * Ignore it for now. We deal with later
     *================================================*/
    //@Column(name = "\"PTY_ID\"")
    //private Long ptyId;
   
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "\"PTY_ID\"")
    //private PhoneType phoneType;
    //=================================================
    
}
