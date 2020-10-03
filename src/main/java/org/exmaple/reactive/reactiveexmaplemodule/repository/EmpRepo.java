package org.exmaple.reactive.reactiveexmaplemodule.repository;

import org.exmaple.reactive.reactiveexmaplemodule.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends ReactiveMongoRepository<Employee,String> {

}
