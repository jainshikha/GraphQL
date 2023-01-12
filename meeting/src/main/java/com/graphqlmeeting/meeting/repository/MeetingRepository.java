package com.graphqlmeeting.meeting.repository;

import com.graphqlmeeting.meeting.domain.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {}
