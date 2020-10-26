package com.personal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.personal.domain.PhoneType;


@Repository
public interface PhoneTypeRepository extends BaseRepository<PhoneType, Long>{

	public List<PhoneType> findAll();
	
}
