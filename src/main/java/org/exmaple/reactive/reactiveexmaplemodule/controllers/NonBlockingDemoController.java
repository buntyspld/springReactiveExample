package org.exmaple.reactive.reactiveexmaplemodule.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class NonBlockingDemoController {

    @GetMapping("/fluxNumbers")
    public Flux<Integer> getNumbers(){
        return Flux.range(1,10).log();
    }

    @GetMapping(value = "/fluxNumberStream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getNumbersStream(){
        return Flux.range(1,10).log().delayElements(Duration.ofSeconds(1));
    }


}