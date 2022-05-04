package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/count")
    public String count(){
        return "Count";
    }

    @GetMapping("/sale")
    public String sale(){
        return "SalePrice";
    }
}
