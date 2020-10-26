package com.personal.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.personal.domain.Person;


@Repository
public interface PersonRepository extends BaseRepository<Person, Long>{

	//#1. Without using the query annotation
	public List<Person> findAll();
	public Optional<Person> findById(Long id);
	Person findByUserIdAndPassword(String uid, String pwd);

	//#2. Using the query annotation
	@Query("SELECT d FROM Person d Where d.ssn = ?1")
	public Person findBySsn(Long ssn);
	


}
