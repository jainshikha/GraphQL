package com.graphqlmeeting.meeting.resolvers;

import com.graphqlmeeting.meeting.domain.Meeting;
import com.graphqlmeeting.meeting.domain.Person;
import com.graphqlmeeting.meeting.repository.MeetingRepository;
import com.graphqlmeeting.meeting.repository.PersonRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

  @Autowired private PersonRepository personRepository;

  @Autowired private MeetingRepository meetingRepository;

  public Meeting meeting(Long id) {
    Optional<Meeting> meeting = meetingRepository.findById(id);
    if (meeting.isPresent()) return meeting.get();
    throw new GraphQLException("Meeting does not exist");
  }

  public List<Meeting> meetings() {
    return (List<Meeting>) meetingRepository.findAll();
  }

  public Person person(Long id) {
    Optional<Person> person = personRepository.findById(id);
    if (person.isPresent()) return person.get();
    throw new GraphQLException("Person does not exist");
  }

  public List<Person> people() {
    return (List<Person>) personRepository.findAll();
  }
}
