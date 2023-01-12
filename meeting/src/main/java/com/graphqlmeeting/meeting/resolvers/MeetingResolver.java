package com.graphqlmeeting.meeting.resolvers;

import com.graphqlmeeting.meeting.domain.Meeting;
import com.graphqlmeeting.meeting.domain.Person;
import com.graphqlmeeting.meeting.repository.MeetingRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MeetingResolver implements GraphQLResolver<Meeting> {
  @Autowired private MeetingRepository meetingRepository;

  public List<Person> attendees(Meeting meeting) {
    Optional<Meeting> fetchMeeting = meetingRepository.findById(meeting.getId());
    if (fetchMeeting.isPresent()) {
      return fetchMeeting.get().getAttendees();
    }
    throw new GraphQLException("Meeting did not have attendees");
  }
}
