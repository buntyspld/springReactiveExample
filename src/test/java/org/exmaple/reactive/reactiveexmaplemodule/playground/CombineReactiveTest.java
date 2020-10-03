package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class CombineReactiveTest {

    @Test
    public void oombineUsingMergeTest(){
        Publisher flux1= Flux.just("A","B","C");
        Publisher flux2= Flux.just("1","2","3");

        Publisher combinedFlux=Flux.merge(flux1,flux2).log();

        StepVerifier.create(combinedFlux)
                .expectNext("A","B","C")
                .expectNext("1","2","3")
                .verifyComplete();
    }

    @Test
    public void oombineUsingMergeWithDelayTest(){
        System.out.println("    ORDER IS NOT MAINTAINED DUE TO DELAY..........");
        Publisher flux1= Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Publisher flux2= Flux.just("1","2","3").delayElements(Duration.ofSeconds(1));

        Publisher combinedFlux=Flux.merge(flux1,flux2).log();

        StepVerifier.create(combinedFlux)
              //  .expectNext("A","B","C")
              //  .expectNext("1","2","3")
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void oombineUsingConcatWithDelay(){
        System.out.println("    ORDER IS NOT MAINTAINED DUE TO DELAY..........");
        Publisher flux1= Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Publisher flux2= Flux.just("1","2","3").delayElements(Duration.ofSeconds(1));

        Publisher combinedFlux=Flux.concat(flux1,flux2).log();

        StepVerifier.create(combinedFlux)
                  .expectNext("A","B","C")
                  .expectNext("1","2","3")
               // .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void oombineUsingZipWithDelay(){
        System.out.println("    ORDER IS NOT MAINTAINED DUE TO DELAY..........");
        Publisher flux1= Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Publisher flux2= Flux.just("1","2","3").delayElements(Duration.ofSeconds(1));

        Publisher combinedFlux=Flux.zip(flux1,flux2).log();//returns tuple of [A,1],[B,2],[C,3]



        StepVerifier.create(combinedFlux)
               // .expectNext("A","B","C")
                //.expectNext("1","2","3")
                 .expectNextCount(3)
                .verifyComplete();
    }
}
