package org.exmaple.reactive.reactiveexmaplemodule.controllers;

import org.exmaple.reactive.reactiveexmaplemodule.model.Employee;
import org.exmaple.reactive.reactiveexmaplemodule.repository.EmpRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/emp")
public class EmpController {

    private EmpRepo empRepo;

    @GetMapping(value = "/allEmp")
    public Flux<Employee> getAllEmployee() {
        return empRepo.findAll();
    }
}
