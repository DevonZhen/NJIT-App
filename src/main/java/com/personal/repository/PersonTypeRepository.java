package com.personal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.personal.domain.PersonType;


@Repository
public interface PersonTypeRepository extends BaseRepository<PersonType, Long>{

	public List<PersonType> findAll();
	
	public PersonType findByType(String type);
	
}
