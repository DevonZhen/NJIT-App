package com.personal.dto;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.personal.util.CustomDateDeserializer;
//import njit.cs.demo.util.CustomDateDeserializer;
//import njit.cs.demo.util.CustomDateSerializer;
import com.personal.util.CustomDateSerializer;

import lombok.Data;

@Data
public class PersonDTO {

	private Long id;     
    private Long pertId; 
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private Long ssn;
	@JsonDeserialize(using = CustomDateDeserializer.class)     // java.util.Date object with JSON format
	@JsonSerialize(using   = CustomDateSerializer.class)
	private Date birthDay;
	private AddressDTO address;
	private EmgContactDTO emgContact; 
    private List<PhonesDTO> phones = Lists.newArrayList();  
    
}
