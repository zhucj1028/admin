package com.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
//@CrossOrigin
public class IndexController {

    @GetMapping("demo")
    public R test(){
        System.out.println("------------------");
        return R.ok().data("code","test");
    }


}
