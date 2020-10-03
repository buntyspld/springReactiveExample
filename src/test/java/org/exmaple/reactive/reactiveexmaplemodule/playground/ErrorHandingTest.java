package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ErrorHandingTest {

    @Test
    public void errorNotHandlingTest(){
        System.out.println("NO COMPLETE METHOD");
        Publisher flux= Flux.just("SAURABH","GOVIND","NAIK")
                .concatWith(Flux.error(new RuntimeException("SOME ERROR")))
                .concatWith(Flux.just("BUNTYAAAAA"))
                .log();
        StepVerifier.create(flux)
                .expectNext("SAURABH","GOVIND","NAIK")
                .expectError(RuntimeException.class)
                .verify();
        System.out.println("ON ERROR it terminates receving data ,hence we didn't got BUNTYAAA");
        System.out.println("\n\n\n\n\n");
        System.out.println("HOW TO HANDLE IN REACTIVE PROGRAMMING?????????,GO TO errorHandlingTest series of methods");
    }

    @Test
    public void errorHandlingTest1(){
        System.out.println("ERRORS ARE HANDLED HERE...........");
        System.out.println("JUST LOGGING ERROR");
        Publisher flux= Flux.just("SAURABH","GOVIND","NAIK")
                .concatWith(Flux.error(new RuntimeException("SOME ERROR"))).doOnError(error-> System.out.println(error))
                .concatWith(Flux.just("BUNTYAAAAA"))
                .log();
        StepVerifier.create(flux)
                .expectNext("SAURABH","GOVIND","NAIK")
                .expectError(RuntimeException.class)
                .verify();
        System.out.println("ON ERROR it terminates receving data ,hence we didn't got BUNTYAAA");
        System.out.println("\n\n\n\n\n");
        System.out.println("STILL NOT SURE HOW TO HANDLE IN REACTIVE PROGRAMMING?????????,GO TO errorHandlingTest2");
    }

    @DisplayName("onErrorReturn")
    @Test
    public void errorHandlingTest2(){
        System.out.println("ERRORS ARE HANDLED HERE...........");
        System.out.println("JUST LOGGING ERROR");
        Publisher flux= Flux.just("SAURABH","GOVIND","NAIK")
                .concatWith(Flux.error(new RuntimeException("SOME ERROR")))
                .onErrorReturn("ARE YOU HUMAN?????????")//fallback value
                .concatWith(Flux.just("BUNTYAAAAA"))
                .log();

        StepVerifier.create(flux)
                .expectNext("SAURABH","GOVIND","NAIK")
                //.expectError(RuntimeException.class)
                .expectNext("ARE YOU HUMAN?????????")
                .expectNext("BUNTYAAAAA")
                .verifyComplete();
        System.out.println("ON ERROR it terminates receving data ,hence we didn't got BUNTYAAA");
        System.out.println("\n\n\n\n\n");
        System.out.println("STILL NOT SURE HOW TO HANDLE IN REACTIVE PROGRAMMING?????????,GO TO errorHandlingTest2");
    }

    @DisplayName("onErrorResume")
    //@Test   //REVISIT THIS
    public void errorHandlingTest3(){
        System.out.println("ERRORS ARE HANDLED HERE...........");
        System.out.println("JUST LOGGING ERROR");
        Publisher flux= Flux.just("SAURABH","GOVIND","NAIK")
                .concatWith(Flux.error(new RuntimeException("SOME ERROR")))
                .onErrorResume(
                        e -> Mono.just("Error " + e.getMessage())
                ).log();
        //THIS IS ALTERNATIVE SOURCE IN CASE OF ERROR

       StepVerifier.create(flux)
                .expectNext("SAURABH","GOVIND","NAIK")
             //   .expectNext("BUNTYAAAAA")
                .verifyComplete();
        System.out.println("ON ERROR it terminates receving data ,hence we didn't got BUNTYAAA");
        System.out.println("\n\n\n\n\n");
        System.out.println("STILL NOT SURE HOW TO HANDLE IN REACTIVE PROGRAMMING?????????,GO TO errorHandlingTest2");
    }

    @DisplayName("Method of Bob the builder")
    @Test
    public void doFinally(){

    }

}
