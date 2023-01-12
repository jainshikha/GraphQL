package com.graphqlmeeting.meeting.resolvers;

import com.graphqlmeeting.meeting.domain.Meeting;
import com.graphqlmeeting.meeting.domain.MeetingInput;
import com.graphqlmeeting.meeting.domain.Person;
import com.graphqlmeeting.meeting.domain.PersonInput;
import com.graphqlmeeting.meeting.repository.MeetingRepository;
import com.graphqlmeeting.meeting.repository.PersonRepository;
import com.sun.istack.NotNull;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

  @Autowired private PersonRepository personRepository;

  @Autowired private MeetingRepository meetingRepository;

  public Meeting createMeeting(MeetingInput input) {
    return meetingRepository.save(getMeeting(input));
  }

  private Meeting getMeeting(MeetingInput meetingInput) {
    return Meeting.builder()
        .id(meetingInput.getId())
        .title(meetingInput.getTitle())
        .description(meetingInput.getDescription())
        .organizer(getPerson(meetingInput.getOrganizer()))
        .attendees(getPeople(meetingInput.getAttendees()))
        .build();
  }

  private List<Person> getPeople(@NotNull List<PersonInput> attendees) {
    List<Person> people = new ArrayList<>();
    for (PersonInput input : attendees) {
      people.add(getPerson(input));
    }
    return people;
  }

  public Meeting updateMeeting(MeetingInput meetingInput) {
    Meeting meeting = getMeeting(meetingInput);
    Optional<Meeting> targetMeeting = meetingRepository.findById(meeting.getId());
    if (targetMeeting.isPresent()) {
      return meetingRepository.save(meeting);
    }
    throw new GraphQLException("Meeting id " + meeting.getId() + " does not exist.");
  }

  public Person createPerson(PersonInput input) {
    return personRepository.save(getPerson(input));
  }

  private Person getPerson(PersonInput personInput) {
    return Person.builder()
        .firstName(personInput.getFirstName())
        .lastName(personInput.getLastName())
        .emailAddress(personInput.getEmailAddress())
        .mobilePhoneNumber(personInput.getMobilePhoneNumber())
        .officePhoneNumber(personInput.getOfficePhoneNumber())
        .build();
  }

  public Person updatePerson(PersonInput personInput) {
    Person person = getPerson(personInput);
    Optional<Person> targetPerson = personRepository.findById(person.getId());
    if (targetPerson.isPresent()) {
      return personRepository.save(person);
    }
    throw new GraphQLException("Person id " + person.getId() + " does not exist.");
  }
}
