package com.personal.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.extern.log4j.Log4j2;
import com.personal.controller.PersonController;

import com.personal.dto.PersonDTO;
import com.personal.dto.PersonTypeDTO;
import com.personal.dto.PhoneTypeDTO;
import com.personal.resturl.RESTUrls;
import com.personal.service.PersonService;
import com.personal.service.PersonTypeService;
import com.personal.service.PhoneTypeService;


@Log4j2
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@Autowired
	PersonTypeService personTypeService;
	
	@Autowired
	PhoneTypeService phoneTypeService;
	
	
	@RequestMapping(value = RESTUrls.URL_PERSON_ALL, method = RequestMethod.GET)
	
	public ResponseEntity<List<PersonDTO>> findAllPerson() 
	{
		try {
			List<PersonDTO> personDTOList = personService.findAll();
			
			if (personDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<PersonDTO>>(personDTOList, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Error calling contractService.get()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = RESTUrls.URL_PERSON_BY_UID_AND_PWD, method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> findByUserIdAndPassword(@PathVariable String uid, @PathVariable String pwd) 
	{
		try {
			PersonDTO personDTO = personService.findByUserIdAndPassword(uid.toUpperCase(), pwd);
			
			if (personDTO == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
			return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Error calling contractService.get()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = RESTUrls.URL_PERSON_BY_SSN, method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> getPersonBySsn(@PathVariable Long ssn) 
	{
		try {
			PersonDTO personDTO = personService.getPersonBySsn(ssn);
			
			if (personDTO == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
			return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Error calling contractService.get()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = RESTUrls.URL_PERSON_TYPES, method = RequestMethod.GET)
	public ResponseEntity<List<PersonTypeDTO>> getPersonTypes() 
	{
		try {
			List<PersonTypeDTO> personTypeDTOList = personTypeService.listAll();
			
			if (personTypeDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<PersonTypeDTO>>(personTypeDTOList, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Error calling contractService.get()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	
	@GetMapping(value = RESTUrls.URL_PHONE_TYPES, produces = "application/json")
	public ResponseEntity<List<PhoneTypeDTO>> getPhoneTypes() 
	{
		try {
			List<PhoneTypeDTO> phoneTypeDTOList = phoneTypeService.listAll();
			
			if (phoneTypeDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<PhoneTypeDTO>>(phoneTypeDTOList, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Error calling contractService.get()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	@RequestMapping(value = RESTUrls.URL_PERSON_TYPE_BY_TYPE, method = RequestMethod.GET)
	public ResponseEntity<PersonTypeDTO> getPersonTypeByType(@PathVariable String type) 
	{
		try {
			PersonTypeDTO personTypeDTO = personTypeService.getPersonTypeByType(type);
			
			if (personTypeDTO == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
			return new ResponseEntity<PersonTypeDTO>(personTypeDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Error calling contractService.get()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = RESTUrls.URL_POST_PERSON, produces = "application/json")
    public PersonDTO postPerson(@RequestBody PersonDTO personDTO) {
		
		System.out.println("DTD=="+personDTO);
		PersonDTO responseDTO = null;
	
		try {
			 if (personDTO.getId() == null)
			     responseDTO = personService.personCreate(personDTO);
			 else
			     responseDTO = personService.personUpdate(personDTO);
		} catch (Exception e) {
		     log.error("Error calling postPerson()", e);
		}
	
		return responseDTO;
    }
	

	@DeleteMapping(value = RESTUrls.URL_DELETE_PERSON, produces = "application/json")
    public void deletePerson(@PathVariable String id) {
		
		try {
			 Long perId = Long.parseLong(id);
		     personService.personDelete(perId);
		     
		} catch (Exception e) {
		     log.error("Error calling postPerson()", e);
		}
	
    }
	

}
