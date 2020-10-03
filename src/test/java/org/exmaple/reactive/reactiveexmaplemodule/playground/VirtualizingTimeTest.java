package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class VirtualizingTimeTest {

    //TODO daya pata lagao flux 2 onComplete why was't called????
    @DisplayName("Without Virtual Time scheduler")
    @Test
    public void testWithoutTimeSchedular() {
        Publisher<Long> flux = Flux
                .interval(Duration
                        .ofSeconds(1))
                .take(3)
                .log();
        Publisher<Long> flux2 = Flux
                .interval(Duration
                        .ofSeconds(1))
                .log()
                .take(3);

        StepVerifier.create(flux)
                .expectNext(Long.valueOf(0), Long.valueOf(1), Long.valueOf(2))
                .verifyComplete();
        StepVerifier.create(flux2)
                .expectNext(Long.valueOf(0), Long.valueOf(1), Long.valueOf(2))
                .verifyComplete();
        System.out.println("OBESERVATION:");
        System.out.println(" Flux 2 cancelled ,no onComplete method was called");
    }

    @DisplayName("With Virtual Time scheduler")
    @Test
    public void testWithTimeSchedular() {

        VirtualTimeScheduler.getOrSet();
        Publisher<Long> flux = Flux
                .interval(Duration
                        .ofSeconds(1))
                .take(3)
                .log();

        StepVerifier.create(flux)
                .expectNext(Long.valueOf(0), Long.valueOf(1), Long.valueOf(2))
                .thenAwait(Duration.ofSeconds(3))
                .verifyComplete();

    }

}
