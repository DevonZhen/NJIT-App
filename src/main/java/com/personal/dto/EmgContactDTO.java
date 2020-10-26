package com.personal.dto;

import lombok.Data;
import com.personal.dto.EmgContactDTO;

@Data
public class EmgContactDTO {

	private Long id;     
	private String contactName;
	private String contactRelation;
	private String contactPhone;
	private String contactEmail;
	//private String perId;  <-- Ignore mapping column
	
}
