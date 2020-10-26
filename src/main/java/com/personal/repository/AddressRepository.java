package com.personal.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.personal.domain.Address;


@Repository
public interface AddressRepository extends BaseRepository<Address, Long>{

	public Optional<Address> findById(Long id);
	
}
