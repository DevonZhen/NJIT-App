package com.personal.dto;

import lombok.Data;
import com.personal.dto.PhonesDTO;

@Data
public class PhonesDTO {

	private Long id;   
    //private Long ptyId;  <-- Ignore mapping column
	private String phone;
	private String phoneType;
	
	/*-----------------------------------
	 * Ignore it for now. Deal with later
	 *-----------------------------------*/
	//private PhoneTypeDTO phoneType;
	
}
