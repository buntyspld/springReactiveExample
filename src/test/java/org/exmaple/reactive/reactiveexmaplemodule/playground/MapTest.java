package org.exmaple.reactive.reactiveexmaplemodule.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class MapTest {
    @Test
    public void mapTest1(){
        Flux.range(1,5)
                .map(data->data*data)//dping squre of data
                 .subscribe(System.out::println);
    }

    @Test
    public void mapTest2(){
        Flux.range(1,5)
                .map(data->data.toString()+"ARE  KAY HE???")//Manipulating data
                .subscribe(System.out::println);    }

    @Test
    public void mapTest3(){
        Flux.range(1,25)
                .map(data-> data * data)//Manipulating data
                .filter(data->data%2==0)
                .subscribe(System.out::println);    }
}
