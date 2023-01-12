package com.graphqlmeeting.meeting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meeting {
  @Id @GeneratedValue private Long id;
  private String title;
  private String description;

  @ManyToOne private Person organizer;

  @ManyToMany private List<Person> attendees;
}
