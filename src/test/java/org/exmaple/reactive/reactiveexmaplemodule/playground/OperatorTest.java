package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class OperatorTest {
    List<String> cities= Arrays.asList("Kolkata","Mumbai","Pune","Delhi","Panji");
    @Test
    public void testFlux1(){
      Flux<String> citiesFlux=Flux.fromIterable(cities);
        Flux<String> filteredCitiesFlux=citiesFlux.filter(thisCity->thisCity.length()>6);

        StepVerifier.create(filteredCitiesFlux.log())
                .expectNext("Kolkata")
                .verifyComplete();
    }

    @Test
    public void testFlux2(){
        Flux<String> citiesFlux=Flux.fromIterable(cities);
        Flux<String> filteredCitiesFlux=citiesFlux.filter(thisCity->thisCity.startsWith("P"));

        StepVerifier.create(filteredCitiesFlux.log())
                .expectNext("Pune")
                .verifyComplete();

    }
}
