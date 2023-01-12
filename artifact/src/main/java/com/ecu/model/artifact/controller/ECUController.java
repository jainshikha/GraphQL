package com.ecu.model.artifact.controller;


import org.springframework.data.annotation.Id;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
public class ECUController {

    @QueryMapping
    public Flux<Company> ECUModels() {
        return Flux.just(new Company(1L, "Company A"), new Company(2L, "Company B"));
    }

}

record Company(@Id Long id, String name) {

}
