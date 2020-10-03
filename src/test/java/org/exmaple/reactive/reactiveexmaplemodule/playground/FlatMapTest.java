package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static reactor.core.scheduler.Schedulers.parallel;

public class FlatMapTest {
    Map<String, String> empMap = new HashMap<>();

    List<String> empIds = Arrays.asList("A1",/* "A2", "A3", "A4", "A5", "A6", "A7", "A8"*/"A9","A10","A11");

    public FlatMapTest() {
        empMap.put("A1", "SAURABH");
     /*   empMap.put("A2", "JANHAVI");
        empMap.put("A3", "MADHURA");
        empMap.put("A4", "GAYATRI");
        empMap.put("A5", "PRADNYA");
        empMap.put("A6", "ASHISH");
        empMap.put("A7", "MAHESH");
        empMap.put("A8", "VARUN");*/
        empMap.put("A9", "AISHWARYA");
        empMap.put("A10", "BHAVANA");
        empMap.put("A11", "RUPALI");
    }

    @Test
    public void flatMapTest() {
        Flux.fromIterable(empIds)
                .flatMap(
                        value->Mono
                                .just(getEmpNameMono(value)
                                        .subscribe()))
                .log()
                .subscribe(System.out::println);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        Flux.fromIterable(empIds)
                .flatMap(
                        value->Mono
                                .just(getEmpById(value)
                                ))
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void flatMapSchedulerTest() {
        Flux.fromIterable(empIds)
                .flatMap(                        value->Mono
                        .just(getEmpNameMono(value)
                                .subscribe()))

                .subscribeOn(parallel())
                .log()
                .subscribe(System.out::println);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        Flux.fromIterable(empIds)
                .flatMap(
                        value->Mono
                                .just(getEmpById(value)
                                ))
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void concatMapSchedulerTest() {
        System.out.println("ORDER IS MAINTAINED .BUT TAKES MORE TIME.......");

        Flux.fromIterable(empIds)
                .concatMap(                        value->Mono
                        .just(getEmpNameMono(value)
                                .subscribe()))

                .subscribeOn(parallel())
                .log()
                .subscribe(System.out::println);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        Flux.fromIterable(empIds)
                .concatMap(
                        value->Mono
                                .just(getEmpById(value)
                                ))
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void flatMapSequentialSchedulerTest() {
        System.out.println("ORDER IS MAINTAINED .BUT faster......");

        Flux.fromIterable(empIds)
                .flatMapSequential(                        value->Mono
                        .just(getEmpNameMono(value)
                                .subscribe()))

                .subscribeOn(parallel())
                .log()
                .subscribe(System.out::println);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        Flux.fromIterable(empIds)
                .flatMapSequential(
                        value->Mono
                                .just(getEmpById(value)
                                ))
                .log()
                .subscribe(System.out::println);
    }
    public Mono<String> getEmpNameMono(String id) {
        return Mono.just(getEmpById(id));
    }

    private String getEmpById(String id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return empMap.getOrDefault(id, "NOT FOUND").toString();
    }
}
