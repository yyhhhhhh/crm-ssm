package com.yyh.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yyh
 * @date 2022-03-10 11:54
 */
@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String index(){
        return "workbench/main/index";
    }
}
