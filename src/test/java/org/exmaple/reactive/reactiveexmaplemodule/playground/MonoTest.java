package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    public void monoTest(){
        Mono.just("SAURABH")
                .log()
                .subscribe(data-> System.out.println("data is "+data));

    }
    @Test
    public void monoErrorTest(){
        Mono.just("SAURABH")
                .concatWith(Mono.error(new RuntimeException("SOMETHING WENT WRONG IN MONO")))
                .log()
                .doOnError(error-> System.out.println("ERROR IS "+error))
                .subscribe(data-> System.out.println("data is "+data));
        System.out.println("Observation : No onComplete method" );
    }
    @Test
    public void monoRunTest(){
        StepVerifier.create(Mono.just("SAURABH")
                .log()).expectNext("SAURABH")
                .verifyComplete();
    }

    @Test
    public void monoExceptionTest(){
        Mono
                .error(new RuntimeException("RUNTIME EXCEPTION HERE"))
                .log()
                .doOnError(error-> System.out.println(" ERROR IS " +error))
                .subscribe();
    }
}
