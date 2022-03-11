package com.yyh.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yyh
 * @date 2022-03-08 21:13
 */
@Controller
public class WorkbenchIndexController {

    @RequestMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }


}
