package com.personal.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.personal.domain.EmgContact;



@Repository
public interface EmgRepository extends BaseRepository<EmgContact, Long>{

	public Optional<EmgContact> findById(Long id);
	
}
