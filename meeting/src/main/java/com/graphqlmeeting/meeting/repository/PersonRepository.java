package com.graphqlmeeting.meeting.repository;

import com.graphqlmeeting.meeting.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {}
