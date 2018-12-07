package com.dahua.tech.easywork.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(String name){
        log.info("参数："+ name);
        return "Hello "+ name;
    }
}
