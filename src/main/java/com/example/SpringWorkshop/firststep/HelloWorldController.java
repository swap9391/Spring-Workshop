package com.example.SpringWorkshop.firststep;

import com.example.SpringWorkshop.firststep.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello-world")
    public String helloWorld(){
      return "Hello World!";
    }

    @RequestMapping("/hello-worldbean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World!");
    }

    @RequestMapping("/hello-world/{message}")
    public HelloWorldBean helloWorldPath(@PathVariable String message){
        return new HelloWorldBean("Hello "+message);
    }
}
