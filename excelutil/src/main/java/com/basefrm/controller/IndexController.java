package com.basefrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
//@RequestMapping("import")
public class IndexController {
    @RequestMapping("/importExcel")
    public String welcome(){
        return "excel/excel_list";
    }
}
