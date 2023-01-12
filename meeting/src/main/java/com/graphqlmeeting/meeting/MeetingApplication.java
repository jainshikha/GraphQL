package com.graphqlmeeting.meeting;

import com.graphqlmeeting.meeting.domain.Meeting;
import com.graphqlmeeting.meeting.domain.Person;
import com.graphqlmeeting.meeting.repository.MeetingRepository;
import com.graphqlmeeting.meeting.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class MeetingApplication {
  @Autowired private PersonRepository personRepository;
  @Autowired private MeetingRepository meetingRepository;

  public static void main(String[] args) {
    SpringApplication.run(MeetingApplication.class, args);
  }

  @Bean
  public Filter OpenFilter() {
    return (Filter) new OpenEntityManagerInViewFilter();
  }

  @PostConstruct
  public void init() {
    Person steve =
        Person.builder()
            .firstName("Steve")
            .lastName("Katra")
            .emailAddress("steve.katra@gmail.com")
            .mobilePhoneNumber("555-555-5555")
            .officePhoneNumber("444-444-4444")
            .build();
    Person john =
        Person.builder()
            .firstName("John")
            .lastName("Smith")
            .emailAddress("john.smith@gmail.com")
            .mobilePhoneNumber("555-555-5556")
            .officePhoneNumber("444-444-4447")
            .build();
    personRepository.save(steve);
    personRepository.save(john);

    List<Person> attendees = new ArrayList<>();
    attendees.add(john);

    Meeting oneOnOne =
        Meeting.builder()
            .title("1:1 with John")
            .description("Discuss goals, accomplishments, etc.")
            .organizer(steve)
            .attendees(attendees)
            .build();

    meetingRepository.save(oneOnOne);
  }
}
