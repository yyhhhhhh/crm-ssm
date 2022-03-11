package com.yyh.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yyh
 * @date 2022-03-08 15:27
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
