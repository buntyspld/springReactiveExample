package org.exmaple.reactive.reactiveexmaplemodule.playground;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;

public class FluxTest {
    String[] values = new String[]{"SAURABH", "JANHAVI", "MADHURA", "GAYATRI", "PRADNYA", "ASHISH", "MAHESH", "VARUN", "PANKAJ"};

    @Test
    public void fluxTest() {
       /*Flux<String> str =Flux.just("A","B","C");
        str.subscribe(data->{
            System.out.println(data);
        });*/
        //Same way of writing above code
        Flux.just(values).subscribe(data -> {
            System.out.println(data);
        });
        System.out.println("---------ALL EVENTS-----------");
        Flux.just(values).log().subscribe(data -> System.out.println(data));

        StepVerifier.create(Flux.just("A","B"))
                .expectNext("A")
                .expectNext("B")
                .verifyComplete();
    }

    @Test
    public void fluxWithErrorTest() {
        System.out.println("---------IN CASE OF ERRORS---------- onComplete will not execute---");
        Flux.just(values).concatWith(Flux.error(new RuntimeException("LIGHT GELI"))).log().subscribe(data -> System.out.println(data));
        System.out.println("Subscriber method 2");
        System.out.println("---------IN CASE OF ERRORS---------- onComplete will not execute---");
        Flux.just(values).concatWith(Flux.error(new RuntimeException("LIGHT GELI"))).log()
                .subscribe(data -> System.out.println(data)
                        , error -> System.out.println(" ERROR IS " + error));
    }

    @Test
    public void fluxWithIterableTest() {
        Flux.fromIterable(Arrays.asList(values))
                .log()
                .subscribe(data-> System.out.println(data));
    }

    @Test
    public void fluxWithRangeTest() {
        Flux.fromIterable(Arrays.asList(values))
                .log()
                .range(4,3)//start from 4th item for 3 elements
                .subscribe(data-> System.out.println(data));
    }

    @Test
    public void fluxWithIntervalTest() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Flux.interval(Duration.ofSeconds(1))
               // .fromIterable(Arrays.asList(values))
                .log()
                .subscribe(data-> {System.out.println(data+" by "+Thread.currentThread().getName());
                });
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println();System.out.println(Thread.currentThread().getName());
        System.out.println("Interval will never execute onComplete " +
                "method as it is sending data every second ");
        System.out.println("Observations -:");
        System.out.println("onSubscribe & request methods are executed on main thread");
        System.out.println("data is given by threads running in parallel");
        System.out.println("data was returned by parallel threads till main thread was alive");
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void fluxWithTakeOperatorTest() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Flux.interval(Duration.ofSeconds(1))
                .fromIterable(Arrays.asList(values))
                .log()
                .take(3)//take 3 values & then cancel subscription
                .subscribe(data-> {System.out.println(data+" by "+Thread.currentThread().getName());
                });
        Thread.sleep(10);
        System.out.println("It will accept no of arguments & then will cancel the subscription");
        System.out.println("Useful when you are testing");
    }


    @Test //this didn't work ,revisit this
    public void fluxWithRequestTest() throws InterruptedException {

        Thread.sleep(10);
        System.out.println("It will accept no of arguments & then will cancel the subscription");


    }
    @Test //this didn't work ,revisit this
    public void fluxWithErrorHandlingTest() throws InterruptedException {
        //Thread.sleep(10);
        Flux.just("SAURABH","GOVIND","NAIK")
                .concatWith(Flux.error(new RuntimeException("SOMETHING FAILED")))
                .log()
                .onErrorReturn("SOMETHING WENT WRONG")

                .subscribe(data-> System.out.println(data));
       // System.out.println("here we handled errors so we will see onComplete method here");
Thread.sleep(10000);

    }


}
