package com.ctgpaas.webfront.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class DefaultController {

    @ApiOperation(value = "默认示例")
    @GetMapping(value = "/")
    public String sayHello(@ApiParam(value = "用户名") @RequestParam(required = false) String userName) {
        if (userName == null)
            userName = "world";
        return "Hello, " + userName + "!";
    }
}