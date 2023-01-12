package com.graphqlmeeting.meeting.resolvers;

import com.graphqlmeeting.meeting.domain.Person;
import com.graphqlmeeting.meeting.repository.PersonRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

  @Autowired private PersonRepository personRepository;

  public Publisher<List<Person>> people() {
    return subscriber ->
        Executors.newScheduledThreadPool(1)
            .scheduleAtFixedRate(
                () -> {
                  List<Person> people = (List<Person>) personRepository.findAll();
                  subscriber.onNext(people);
                },
                0,
                1,
                TimeUnit.SECONDS);
  }
}
