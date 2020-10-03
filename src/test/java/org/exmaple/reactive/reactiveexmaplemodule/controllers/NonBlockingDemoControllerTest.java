package org.exmaple.reactive.reactiveexmaplemodule.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebFluxTest(NonBlockingDemoController.class)
class NonBlockingDemoControllerTest {

    @Autowired
    WebTestClient webTestClient;
    String fluxNumbersURL="/fluxNumbers";
    String fluxNumberStreamURL="/fluxNumberStream";
    String fluxNumbersDelayedURL="/fluxNumbersDelayed";//IMPORTANT
    @DisplayName("flux api ")
    @Test
    void getNumbers() {
    webTestClient
            .get()
            .uri(fluxNumbersURL)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is2xxSuccessful();
    }

    @Test
    void getNumbersStream() {
        webTestClient
                .get()
                .uri(fluxNumberStreamURL)
                .accept(MediaType.valueOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @DisplayName("Delayed Not handledThis shall fail")
    @Test
    void getNumbersDelayed() {
       webTestClient
               .get()
               .uri(fluxNumbersDelayedURL)
               //.accept(MediaType.APPLICATION_JSON)
               .exchange()
               .expectStatus()
               .is2xxSuccessful();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx IMP NOTE   XXXXXXXXXXXXXXXXXXX \n");
        System.out.println("java.lang.IllegalStateException: Timeout on blocking read for 5000 MILLISECONDS");
        System.out.println("Being Non blocking api webclient has timeout of 5 seconds");
        System.out.println("CHECK NEXT METHOD TO LEARN HOW TO ALTER TIMEOUT OF WEBCLIENT ");
    }
    @DisplayName("Delayed ")
    @Test
    void getNumbersDelayedFixedWebClient() {
         webTestClient=webTestClient.mutate().responseTimeout(Duration.ofSeconds(12)).build();

        webTestClient
                .get()
                .uri(fluxNumbersDelayedURL)
                //.accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx IMP NOTE   XXXXXXXXXXXXXXXXXXX \n");
        System.out.println("java.lang.IllegalStateException: Timeout on blocking read for 5000 MILLISECONDS");
        System.out.println("Being Non blocking api webclient had timeout of 5 seconds, we changed it to 12 seconds");

    }


}